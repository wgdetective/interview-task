package com.example.interview.service;

import java.time.LocalDateTime;
import java.util.logging.Level;

import com.example.interview.exception.NoAvailableCopiesException;
import com.example.interview.exception.NoSuchBookException;
import com.example.interview.exception.NoSuchReservationException;
import com.example.interview.exception.ReservationOperationIsNotSupported;
import com.example.interview.model.Book;
import com.example.interview.model.Reservation;
import com.example.interview.model.ReservationRequest;
import com.example.interview.model.ReservationStatus;
import com.example.interview.model.ReservationUpdateRequest;
import com.example.interview.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Log
public class ReservationService {

    private final BookService bookService;

    private final ReservationRepository reservationRepository;

    public Mono<Reservation> reserveBook(final ReservationRequest request) {
        final var bookId = request.getBookId();
        return validateAndGetBook(bookId)
                .flatMap(book -> reserveBook(request, book))
                .doOnError(NoSuchBookException.class, e -> log.log(Level.SEVERE, "No book with id=" + bookId))
                .doOnError(NoAvailableCopiesException.class,
                        e -> log.log(Level.SEVERE, "No available copies of book with id=" + bookId));
    }

    public Flux<Book> getAvailableBooks() {
        final var books = bookService.getAll();
        return books.flatMap(book -> reservationRepository.getCountOfOpenReservationsByBookId(book.getId())
                        .map(reservedBooksCount -> new BookAndCount(book, reservedBooksCount)))
                .filter(bookAndCount -> bookAndCount.reservedBooksCount < bookAndCount.book.getCopies())
                .map(BookAndCount::book);
    }

    private Mono<Reservation> reserveBook(final ReservationRequest request, final Book book) {
        // thread pool by id
        // test synchronization
        return reservationRepository.getCountOfOpenReservationsByBookId(request.getBookId())
                .flatMap(reservedBooksCount -> {
                    if (reservedBooksCount < book.getCopies()) {
                        final Reservation reservation = Reservation.builder()
                                .userFullName(request.getUserFullName())
                                .bookId(request.getBookId())
                                .createDateTime(LocalDateTime.now())
                                .reservationStatus(ReservationStatus.SUCCESS)
                                .build();
                        return reservationRepository.save(reservation);
                    } else {
                        return Mono.error(new NoAvailableCopiesException());
                    }
                })
                .subscribeOn(Schedulers.single());
    }

    private Mono<Book> validateAndGetBook(final String bookId) {
        return bookService.getById(bookId)
                .switchIfEmpty(Mono.error(new NoSuchBookException()));
    }

    public Flux<Reservation> getReservations(final String userFullName) {
        return reservationRepository.findAllByUserFullName(userFullName);
    }

    public Mono<Reservation> updateReservation(final ReservationUpdateRequest request) {
        return validateAndGetReservation(request)
                .map(r -> {
                    r.setReservationStatus(request.getReservationStatus());
                    r.setUpdateDateTime(LocalDateTime.now());
                    return r;
                })
                .flatMap(reservationRepository::save);
    }

    private Mono<Reservation> validateAndGetReservation(final ReservationUpdateRequest request) {
        if (!request.getReservationStatus().equals(ReservationStatus.CLOSED)) {
            log.log(Level.SEVERE, "Only CLOSE status update is supported");
            return Mono.error(new ReservationOperationIsNotSupported());
        }
        return reservationRepository.findById(request.getReservationId())
                .switchIfEmpty(Mono.error(new NoSuchReservationException()))
                .doOnError(NoSuchReservationException.class,
                        e -> log.log(Level.SEVERE, "No reservation with id=" + request.getReservationId()))
                .flatMap(r -> {
                    if (!r.getReservationStatus().equals(ReservationStatus.SUCCESS)) {
                        log.log(Level.SEVERE, "Only SUCCESS status update is supported");
                        return Mono.error(new ReservationOperationIsNotSupported());
                    } else {
                        return Mono.just(r);
                    }
                });
    }

    private record BookAndCount(Book book, Long reservedBooksCount) {

    }
}



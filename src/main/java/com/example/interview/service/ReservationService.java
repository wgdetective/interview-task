package com.example.interview.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.example.interview.exception.NoAvailableCopiesException;
import com.example.interview.exception.NoSuchBookException;
import com.example.interview.exception.ReservationException;
import com.example.interview.model.Book;
import com.example.interview.model.Reservation;
import com.example.interview.model.ReservationRequest;
import com.example.interview.model.ReservationStatus;
import com.example.interview.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class ReservationService {

    private final BookService bookService;

    private final ReservationRepository reservationRepository;

    private final Object lock = new Object();

    public Reservation reserveBook(final ReservationRequest request) throws ReservationException {
        final var bookId = request.getBookId();
        try {
            final Book book = validateAndGetBook(bookId);
            return reserveBook(request, book);
        } catch (final NoSuchBookException e) {
            log.log(Level.SEVERE, "No book with id=" + bookId);
            throw e;
        } catch (final NoAvailableCopiesException e) {
            log.log(Level.SEVERE, "No available copies of book with id=" + bookId);
            throw e;
        }
    }

    public List<Book> getAvailableBooks() {
        final var books = bookService.getAll();
        return books.stream().filter(book -> {
            final long reservedBooksCount = reservationRepository.getCountOfOpenReservationsByBookId(book.getId());
            return reservedBooksCount < book.getCopies();
        }).toList();
    }

    private Reservation reserveBook(final ReservationRequest request, final Book book)
            throws NoAvailableCopiesException {
        synchronized (lock) {
            final long reservedBooksCount = reservationRepository.getCountOfOpenReservationsByBookId(
                    request.getBookId());
            if (reservedBooksCount < book.getCopies()) {
                final Reservation reservation = Reservation.builder()
                        .userFullName(request.getUserFullName())
                        .bookId(request.getBookId())
                        .reservationDateTime(LocalDateTime.now())
                        .reservationStatus(ReservationStatus.SUCCESS)
                        .build();
                return reservationRepository.save(reservation);
            } else {
                throw new NoAvailableCopiesException();
            }
        }
    }

    private Book validateAndGetBook(final String bookId) throws NoSuchBookException {
        final Optional<Book> book = bookService.getById(bookId);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new NoSuchBookException();
        }
    }
}



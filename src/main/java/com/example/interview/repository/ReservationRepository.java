package com.example.interview.repository;

import com.example.interview.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationRepository {

    Mono<Long> getCountOfOpenReservationsByBookId(final String bookId);

    Mono<Reservation> save(Reservation reservation);

    Flux<Reservation> findAllByUserFullName(String userFullName);

    Mono<Reservation> findById(String id);
}

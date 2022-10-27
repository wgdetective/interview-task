package com.example.interview.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.interview.model.Reservation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class InMemoryReservationRepository implements ReservationRepository {

    private final Map<String, List<Reservation>> reservationsByBookId = new HashMap<>();

    private final Map<String, List<Reservation>> reservationsByUserFullName = new HashMap<>();

    private final Map<String, Reservation> reservationsById = new HashMap<>();

    @Override
    public Mono<Long> getCountOfOpenReservationsByBookId(final String bookId) {
        final var reservations = reservationsByBookId.get(bookId);
        return Mono.just((long) (reservations == null ? 0 : reservations.size()));
    }

    @Override
    public Mono<Reservation> save(final Reservation reservation) {
        reservation.setId(UUID.randomUUID().toString());
        putToReservationsByBookId(reservation);
        putToReservationsByUserFullName(reservation);
        putToReservationsById(reservation);
        return Mono.just(reservation);
    }

    private void putToReservationsByBookId(final Reservation reservation) {
        if (!reservationsByBookId.containsKey(reservation.getBookId())) {
            reservationsByBookId.put(reservation.getBookId(), new ArrayList<>());
        }
        final var reservations = reservationsByBookId.get(reservation.getBookId());
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    private void putToReservationsByUserFullName(final Reservation reservation) {
        if (!reservationsByUserFullName.containsKey(reservation.getUserFullName())) {
            reservationsByUserFullName.put(reservation.getUserFullName(), new ArrayList<>());
        }
        final var reservations = reservationsByUserFullName.get(reservation.getUserFullName());
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    private void putToReservationsById(final Reservation reservation) {
        reservationsById.put(reservation.getId(), reservation);
    }

    @Override
    public Flux<Reservation> findAllByUserFullName(final String userFullName) {
        return Flux.fromIterable(reservationsByUserFullName.getOrDefault(userFullName, Collections.emptyList()));
    }

    @Override
    public Mono<Reservation> findById(final String id) {
        return reservationsById.containsKey(id) ? Mono.just(reservationsById.get(id)) : Mono.empty();
    }
}

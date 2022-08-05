package com.example.interview.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.interview.model.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryReservationRepository implements ReservationRepository {

    private final Map<String, List<Reservation>> reservationsByBookId = new HashMap<>();

    @Override
    public long getCountOfOpenReservationsByBookId(final String bookId) {
        final var reservations = reservationsByBookId.get(bookId);
        return reservations == null ? 0 : reservations.size();
    }

    @Override
    public Reservation save(final Reservation reservation) {
        if (!reservationsByBookId.containsKey(reservation.getBookId())) {
            reservationsByBookId.put(reservation.getBookId(), new ArrayList<>());
        }
        reservation.setId(UUID.randomUUID().toString());
        reservationsByBookId.get(reservation.getBookId()).add(reservation);
        return reservation;
    }
}

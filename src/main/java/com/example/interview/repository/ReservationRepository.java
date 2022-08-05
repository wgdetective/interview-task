package com.example.interview.repository;

import com.example.interview.model.Reservation;

public interface ReservationRepository {

    long getCountOfOpenReservationsByBookId(final String bookId);

    Reservation save(Reservation reservation);
}

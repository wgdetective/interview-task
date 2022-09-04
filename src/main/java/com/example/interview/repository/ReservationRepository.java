package com.example.interview.repository;

import java.util.List;

import com.example.interview.model.Reservation;

public interface ReservationRepository {

    long getCountOfOpenReservationsByBookId(final String bookId);

    Reservation save(Reservation reservation);

    List<Reservation> findAllByUserFullName(String userFullName);
}

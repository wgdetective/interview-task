package com.example.interview.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reservation {

    private String id;

    private String userFullName;

    private String bookId;

    private LocalDateTime reservationDateTime;

    private ReservationStatus reservationStatus;

    private String message;
}

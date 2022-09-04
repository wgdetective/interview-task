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

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private ReservationStatus reservationStatus;

    private String message;
}

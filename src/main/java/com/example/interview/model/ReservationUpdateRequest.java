package com.example.interview.model;

import lombok.Data;

@Data
public class ReservationUpdateRequest {

    private String reservationId;

    private ReservationStatus reservationStatus;
}

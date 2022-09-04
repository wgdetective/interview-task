package com.example.interview.dto;

import java.time.LocalDateTime;

import com.example.interview.model.ReservationStatus;
import lombok.Data;

@Data
public class ReservationDto {

    private String id;

    private String userFullName;

    private String bookId;

    private LocalDateTime reservationDateTime;

    private ReservationStatus reservationStatus;

    private String message;
}

package com.example.interview.dto;

import java.time.LocalDateTime;

import com.example.interview.model.ReservationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationDto {

    private String id;

    private String userFullName;

    private String bookId;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private ReservationStatus reservationStatus;

    private String message;
}

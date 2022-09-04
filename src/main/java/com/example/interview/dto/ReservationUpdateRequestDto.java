package com.example.interview.dto;

import com.example.interview.model.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateRequestDto {

    @NonNull
    private ReservationStatus reservationStatus;
}

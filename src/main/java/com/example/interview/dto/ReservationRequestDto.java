package com.example.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
public class ReservationRequestDto {
    @NonNull
    private String userFullName;
    @NonNull
    private String bookId;
}

package com.example.interview.controller;

import java.time.LocalDateTime;

import com.example.interview.controller.mapper.ReservationDtoMapper;
import com.example.interview.controller.mapper.ReservationRequestDtoMapper;
import com.example.interview.dto.ReservationDto;
import com.example.interview.dto.ReservationRequestDto;
import com.example.interview.exception.NoAvailableCopiesException;
import com.example.interview.exception.NoSuchBookException;
import com.example.interview.exception.ReservationException;
import com.example.interview.model.Reservation;
import com.example.interview.model.ReservationStatus;
import com.example.interview.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationDtoMapper mapper;
    private final ReservationRequestDtoMapper requestMapper;

    @PostMapping
    public ResponseEntity<ReservationDto> reserveBook(@RequestBody final ReservationRequestDto reservationRequestDto) {
        Reservation reservation;
        try {
            reservation = reservationService.reserveBook(requestMapper.map(reservationRequestDto));
            reservation.setMessage("Book is successfully booked.");
            return ResponseEntity.ok(mapper.map(reservation));
        } catch (ReservationException e) {
            reservation = Reservation.builder()
                    .bookId(reservationRequestDto.getBookId())
                    .userFullName(reservationRequestDto.getUserFullName())
                    .reservationStatus(ReservationStatus.FAIL)
                    .reservationDateTime(LocalDateTime.now())
                    .build();
            reservation.setMessage(getMessageByException(e));
            return ResponseEntity.badRequest().body(mapper.map(reservation));
        }
    }

    private String getMessageByException(final ReservationException e) {
        if (e instanceof NoSuchBookException) {
            return "There is no such book in library.";
        } else if (e instanceof NoAvailableCopiesException) {
            return "There are no more copies of such book.";
        }
        return null;
    }
}

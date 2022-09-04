package com.example.interview.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.interview.controller.mapper.ReservationDtoMapper;
import com.example.interview.controller.mapper.ReservationRequestDtoMapper;
import com.example.interview.controller.mapper.ReservationUpdateRequestDtoMapper;
import com.example.interview.dto.ReservationDto;
import com.example.interview.dto.ReservationRequestDto;
import com.example.interview.dto.ReservationUpdateRequestDto;
import com.example.interview.exception.NoAvailableCopiesException;
import com.example.interview.exception.NoSuchBookException;
import com.example.interview.exception.ReservationException;
import com.example.interview.model.Reservation;
import com.example.interview.model.ReservationStatus;
import com.example.interview.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private final ReservationUpdateRequestDtoMapper updateRequestMapper;

    @PostMapping
    public ResponseEntity<ReservationDto> reserveBook(@RequestBody final ReservationRequestDto reservationRequestDto) {
        Reservation reservation;
        try {
            reservation = reservationService.reserveBook(requestMapper.map(reservationRequestDto));
            reservation.setMessage("Book is successfully booked.");
            return ResponseEntity.ok(mapper.map(reservation));
        } catch (final ReservationException e) {
            reservation = Reservation.builder()
                    .bookId(reservationRequestDto.getBookId())
                    .userFullName(reservationRequestDto.getUserFullName())
                    .reservationStatus(ReservationStatus.FAIL)
                    .createDateTime(LocalDateTime.now())
                    .build();
            reservation.setMessage(getMessageByException(e));
            return ResponseEntity.badRequest().body(mapper.map(reservation));
        }
    }

    @GetMapping
    public List<ReservationDto> getReservations(final String userFullName) {
        return reservationService.getReservations(userFullName).stream().map(mapper::map).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable final String id,
            @RequestBody final ReservationUpdateRequestDto reservationRequestDto) {
        try {
            return ResponseEntity.ok(mapper.map(
                    reservationService.updateReservation(updateRequestMapper.map(reservationRequestDto, id))));
        } catch (final ReservationException e) {
            final var reservation = Reservation.builder()
                    .reservationStatus(ReservationStatus.FAIL)
                    .message(e.getClass().getName() + " " + e.getMessage())
                    .build();
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

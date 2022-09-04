package com.example.interview.controller.mapper;

import com.example.interview.dto.ReservationRequestDto;
import com.example.interview.model.ReservationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationRequestDtoMapper {

    ReservationRequest map(final ReservationRequestDto reservationRequest);
}

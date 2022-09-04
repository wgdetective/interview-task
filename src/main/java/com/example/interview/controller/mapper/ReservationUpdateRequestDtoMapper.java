package com.example.interview.controller.mapper;

import com.example.interview.dto.ReservationUpdateRequestDto;
import com.example.interview.model.ReservationUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationUpdateRequestDtoMapper {

    ReservationUpdateRequest map(final ReservationUpdateRequestDto reservationRequest, final String reservationId);
}

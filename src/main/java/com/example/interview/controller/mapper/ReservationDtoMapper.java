package com.example.interview.controller.mapper;

import com.example.interview.dto.ReservationDto;
import com.example.interview.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationDtoMapper {

    ReservationDto map(final Reservation reservation);
}

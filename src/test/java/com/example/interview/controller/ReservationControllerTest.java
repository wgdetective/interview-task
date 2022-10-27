package com.example.interview.controller;

import com.example.interview.controller.mapper.ReservationDtoMapper;
import com.example.interview.controller.mapper.ReservationRequestDtoMapper;
import com.example.interview.controller.mapper.ReservationUpdateRequestDtoMapper;
import com.example.interview.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest({ ReservationController.class, ReservationDtoMapper.class, ReservationRequestDtoMapper.class,
        ReservationUpdateRequestDtoMapper.class })
class ReservationControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private ReservationService service;

    @Test
    void testEmptyFieldsInReservationRequest() {
        // given
        // when
        final var responseBody = client.post().uri("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"bookId\" : null}")
                .exchange()
                // then
                .expectStatus().isBadRequest()
                .expectBody()
                .consumeWith(System.out::println);
    }
}

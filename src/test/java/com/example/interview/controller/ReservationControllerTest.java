package com.example.interview.controller;

import com.example.interview.controller.mapper.ReservationDtoMapper;
import com.example.interview.controller.mapper.ReservationRequestDtoMapper;
import com.example.interview.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ ReservationController.class, ReservationDtoMapper.class, ReservationRequestDtoMapper.class })
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService service;

    @Test
    public void testEmptyFieldsInReservationRequest() throws Exception {
        // given
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations")
                        .content("{\"bookId\" : null}")
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

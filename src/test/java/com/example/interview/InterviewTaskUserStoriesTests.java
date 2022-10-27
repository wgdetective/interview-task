package com.example.interview;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.example.interview.dto.ReservationDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InterviewTaskUserStoriesTests {

    @Autowired
    private WebTestClient client;

    @Test
    void testGetAllBooks() throws IOException {
        // given
        final var expectedJson = readResource("bdd/getAllBooksOutput.json");
        // when
        client.get().uri("/v1/books").exchange()
                // then
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .json(expectedJson);
    }

    @Test
    @Order(1)
    void testReserveBook() throws Exception {
        // given
        final var requestBodyJson = readResource("bdd/reserveBookInput.json");
        final var expectedJson = readResource("bdd/reserveBookOutput.json");
        // when
        client.post().uri("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBodyJson)
                .exchange()
                // then
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .json(expectedJson)
                .jsonPath("id").exists();
    }

    @Test
    @Order(2)
    void testReserveBookThatDoesNotHaveEnoughCopies() throws Exception {
        // given
        final var requestBodyJson = readResource("bdd/reserveBookInput.json");
        final var expectedJson = readResource("bdd/reserveThatDoesNotHaveEnoughCopiesOutput.json");
        // when
        client.post().uri("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBodyJson)
                .exchange()
                // then
                .expectStatus().isBadRequest()
                .expectBody()
                .consumeWith(System.out::println)
                .json(expectedJson);
    }

    @Test
    @Order(3)
    void testGetOfAvailableBooks() throws Exception {
        // given
        final var expectedJson = readResource("bdd/getAvailableBooksOutput.json");
        // when
        client.get().uri("/v1/books?available=true")
                .exchange()
                // then
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .json(expectedJson);
    }

    @Test
    @Order(4)
    void testGetReservations() throws Exception {
        // given
        final var expectedJson = readResource("bdd/getReservationsOutput.json");
        // when
        client.get().uri("/v1/reservations?userFullName=Test User")
                .exchange()
                // then
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .json(expectedJson);
    }

    @Test
    @Order(5)
    void testCloseReservation() throws Exception {
        // given
        final var requestBodyJson = readResource("bdd/closeReservationInput.json");
        final var expectedJson = readResource("bdd/closeReservationOutput.json");
        final var expectedGetJson = readResource("bdd/getClosedReservationsOutput.json");
        final String reservationId = getReservationId();
        // when
        client.put().uri("/v1/reservations/" + reservationId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBodyJson)
                .exchange()
                // then
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .json(expectedJson);
        client.get().uri("/v1/reservations?userFullName=Test User")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .json(expectedGetJson);
    }

    private String getReservationId() throws Exception {
        final var reservationsStr = client.get().uri("/v1/reservations?userFullName=Test User")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();
        final var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        final var reservations = objectMapper.readValue(reservationsStr,
                new TypeReference<List<ReservationDto>>() {

                });
        assertThat(reservations).isNotNull();
        assertThat(reservations).isNotEmpty();
        return reservations.get(0).getId();
    }

    private String readResource(final String resourceName) throws IOException {
        try (final var resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            return new String(Objects.requireNonNull(resourceAsStream).readAllBytes());
        }
    }
}

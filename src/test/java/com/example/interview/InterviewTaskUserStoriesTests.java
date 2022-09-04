package com.example.interview;

import java.io.IOException;
import java.util.Objects;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InterviewTaskUserStoriesTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllBooks() throws Exception {
        // given
        final var expectedJson = readResource("bdd/getAllBooksOutput.json");
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/books"))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @Order(1)
    void testReserveBook() throws Exception {
        // given
        final var requestBodyJson = readResource("bdd/reserveBookInput.json");
        final var expectedJson = readResource("bdd/reserveBookOutput.json");
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations")
                        .content(requestBodyJson)
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andExpect(jsonPath("id").exists());
    }

    @Test
    @Order(2)
    void testReserveBookThatDoesNotHaveEnoughCopies() throws Exception {
        // given
        final var requestBodyJson = readResource("bdd/reserveBookInput.json");
        final var expectedJson = readResource("bdd/reserveThatDoesNotHaveEnoughCopiesOutput.json");
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations")
                        .content(requestBodyJson)
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @Order(3)
    void testGetOfAvailableBooks() throws Exception {
        // given
        final var expectedJson = readResource("bdd/getAvailableBooksOutput.json");
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/books?available=true"))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    private String readResource(final String resourceName) throws IOException {
        try (final var resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            return new String(Objects.requireNonNull(resourceAsStream).readAllBytes());
        }
    }
}

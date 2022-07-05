package com.example.interview;

import java.io.IOException;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InterviewTaskUserStoriesTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllBooks() throws Exception {
        // given
        final var expectedJsonContent = readResource("bdd/getAllBooksOutput.json");
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/books"))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonContent));
    }

    private String readResource(final String resourceName) throws IOException {
        try (final var resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            return new String(Objects.requireNonNull(resourceAsStream).readAllBytes());
        }
    }
}

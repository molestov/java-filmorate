package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnSuccessResponseWhenCreateFilm() throws Exception {
        String testJson = "{\n  \"name\": \"nisi eiusmod\",\n  \"description\": \"adipisicing\",\n  \"releaseDate\": " +
                "\"1967-03-25\",\n  \"duration\": 100\n}";
        mockMvc.perform(post("/films")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnSuccessResponseWhenUpdateFilm() throws Exception {
        String testJson1 = "{\n  \"name\": \"nisi eiusmod\",\n  \"description\": \"adipisicing\",\n  \"releaseDate\": " +
                "\"1967-03-25\",\n  \"duration\": 100\n}";
        String testJson2 = "{\n  \"id\": 1,\n \"name\": \"nisi eiusmod Updated\",\n  \"description\": \"adipisicing\"," +
                "\n  \"releaseDate\": \"1967-03-25\",\n  \"duration\": 100\n}";
        mockMvc.perform(post("/films")
                        .content(testJson1)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        mockMvc.perform(put("/films")
                .content(testJson2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnErrorResponseWhenFailName() throws Exception {
        String testJson = "{\n  \"name\": \"\",\n  \"description\": \"Description\",\n  \"releaseDate\": \"1900-03-25\""
                + ",\n  \"duration\": 200\n}";
        mockMvc.perform(post("/films")
                .content(testJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnErrorResponseWhenFailDescription() throws Exception {
        String testJson = "{\n  \"name\": \"Film name\",\n  \"description\": \"Пятеро друзей ( комик-группа «Шарло»)" +
                ", приезжают в город Бризуль. Здесь они хотят разыскать господина Огюста Куглова, который " +
                "задолжал им деньги, а именно 20 миллионов. о Куглов, который за время «своего отсутствия», стал " +
                "кандидатом Коломбани.\",\n    \"releaseDate\": \"1900-03-25\",\n  \"duration\": 200\n}";
        mockMvc.perform(post("/films")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnErrorResponseWhenFailReleaseDate() throws Exception {
        String testJson = "{\n  \"name\": \"Name\",\n  \"description\": \"Description\",\n  \"releaseDate\": " +
                "\"1890-03-25\",\n  \"duration\": 200\n}";
        mockMvc.perform(post("/films")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnErrorResponseWhenFailDuration() throws Exception {
        String testJson = "{\n  \"name\": \"Name\",\n  \"description\": \"Descrition\",\n  \"releaseDate\": " +
                "\"1980-03-25\",\n  \"duration\": -200\n}";
        mockMvc.perform(post("/films")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnErrorResponseWhenUpdateFilmWithUnknownId() throws Exception {
        String testJson = "{\n  \"id\": 9999,\n  \"name\": \"Film Updated\",\n  \"releaseDate\": \"1989-04-17\",\n  " +
                "\"description\": \"New film update decription\",\n  \"duration\": 190,\n  \"rate\": 4\n}";
        mockMvc.perform(put("/films")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

}
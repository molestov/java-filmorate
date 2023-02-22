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
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnSuccessResponseWhenCreateUser() throws Exception {
        String testJson = "{\n  \"login\": \"dolore\",\n  \"name\": \"Nick Name\",\n  \"email\": \"mail@mail.ru\",\n  " +
                "\"birthday\": \"1946-08-20\"\n}";
        mockMvc.perform(post("/users")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnSuccessResponseWhenUpdateUser() throws Exception {
        String testJson1 = "{\n  \"login\": \"dolore\",\n  \"name\": \"Nick Name\",\n  \"email\": \"mail@mail.ru\",\n  " +
                "\"birthday\": \"1946-08-20\"\n}";
        String testJson2 = "{\n  \"id\": 1,\n  \"login\": \"dolore\",\n  \"name\": \"Nick Name\",\n  \"email\": " +
                "\"mail@mail.ru\",\n  \"birthday\": \"1946-08-20\"\n}";
        mockMvc.perform(post("/users")
                        .content(testJson1)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        mockMvc.perform(put("/users")
                        .content(testJson2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnErrorResponseWhenFailLogin() throws Exception {
        String testJson = "{\n  \"login\": \"dolore ullamco\",\n  \"email\": \"yandex@mail.ru\",\n  \"birthday\": " +
                "\"2446-08-20\"\n}";
        mockMvc.perform(post("/users")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorResponseWhenFailEmail() throws Exception {
        String testJson = "{\n  \"login\": \"dolore ullamco\",\n  \"name\": \"\",\n  \"email\": \"mail.ru\",\n  " +
                "\"birthday\": \"1980-08-20\"\n}";
        mockMvc.perform(post("/users")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorResponseWhenFailBirthday() throws Exception {
        String testJson = "{\n  \"login\": \"dolore\",\n  \"name\": \"\",\n  \"email\": \"test@mail.ru\",\n  " +
                "\"birthday\": \"2446-08-20\"\n}";
        mockMvc.perform(post("/users")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorResponseWhenUpdateUserWithWrongId() throws Exception {
        String testJson = "{\n  \"login\": \"doloreUpdate\",\n  \"name\": \"est adipisicing\",\n  \"id\": 9999,\n  " +
                "\"email\": \"mail@yandex.ru\",\n  \"birthday\": \"1976-09-20\"\n}";
        mockMvc.perform(put("/users")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

}
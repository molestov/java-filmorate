package ru.yandex.practicum.filmorate.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private int id;

    @Email(message = "Provided not valid email")
    private String email;

    @NotBlank(message = "Field login cannot be empty or null")
    @Pattern(message = "Login is not valid", regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$")
    private String login;
    private String name;
    @Birthday
    private LocalDate birthday;
}

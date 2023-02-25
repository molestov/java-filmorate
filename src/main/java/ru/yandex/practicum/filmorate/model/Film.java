package ru.yandex.practicum.filmorate.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.ReleaseDate;

import java.time.LocalDate;

@Data
public class Film {
    private int id;

    @NotBlank(message = "Field Name cannot be empty or null")
    private String name;

    @Size(max = 200, message = "Field Description cannot be more than 200 chars")
    private String description;

    @ReleaseDate
    private LocalDate releaseDate;

    @Positive(message = "Field Duration must be positive")
    private int duration;
}

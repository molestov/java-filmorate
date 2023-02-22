package ru.yandex.practicum.filmorate.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ReleaseDateValidator implements ConstraintValidator<ReleaseDate, LocalDate> {
    @Override
    public void initialize(ReleaseDate releaseDate) {}

    @Override
    public boolean isValid(LocalDate releaseDate, ConstraintValidatorContext cxt) {
        return releaseDate.isAfter(LocalDate.of(1895, 12, 28));
    }
}

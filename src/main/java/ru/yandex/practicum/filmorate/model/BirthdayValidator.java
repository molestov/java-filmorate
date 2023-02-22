package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthdayValidator implements ConstraintValidator<Birthday, LocalDate> {
    @Override
    public void initialize(Birthday birthday) {}

    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext cxt) {
        return birthday.isBefore(LocalDate.now());
    }
}

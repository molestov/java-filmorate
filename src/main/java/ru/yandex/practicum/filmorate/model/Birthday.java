package ru.yandex.practicum.filmorate.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Constraint(validatedBy = BirthdayValidator.class)
public @interface Birthday {
    String message() default "Not valid birthday";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
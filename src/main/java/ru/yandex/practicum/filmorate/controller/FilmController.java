package ru.yandex.practicum.filmorate.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {

    private Map<Integer, Film> films = new HashMap<>();
    private int id = 1;

    private int setFilmId() {
        while (films.containsKey(id)) {
            id++;
        }
        return id;
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        if (film.getId() == 0) {
            film.setId(setFilmId());
        }
        films.put(film.getId(), film);
        log.info("New film added: '{}'", film.getName());
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (!films.containsKey(film.getId()) || film.getId() == 0) {
            throw new UnknownIdExcerption();
        }
        films.replace(film.getId(), film);
        log.info("Film updated: '{}'", film.getName());
        return film;
    }

    @GetMapping
    public List<Film> getFilms() {
        return new ArrayList<Film>(films.values());
    }
}

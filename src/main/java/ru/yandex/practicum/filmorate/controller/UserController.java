package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private Map<Integer, User> users = new HashMap<>();
    private int id = 1;

    private int setUserId() {
        while (users.containsKey(id)) {
            ++id;
        }
        return id;
    }

    @GetMapping
    public Map<Integer, User> getUsers() {
        return users;
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        if (user.getId() == 0) {
            user.setId(setUserId());
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        log.info("New user added: '{}'", user.getLogin());
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        if (!users.containsKey(user.getId()) || user.getId() == 0) {
            throw new UnknownIdExcerption();
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        users.replace(user.getId(), user);
        log.info("User updated: '{}'", user.getLogin());
        return user;
    }

}

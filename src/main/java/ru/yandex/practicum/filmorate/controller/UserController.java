package ru.yandex.practicum.filmorate.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private Map<Integer, User> users = new HashMap<>();
    private int id = 1;

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<User>(users.values());
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        user.setId(id);
        id++;
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        log.info("New user added: '{}'", user.getLogin());
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        if (!users.containsKey(user.getId())) {
            throw new UnknownIdException();
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        users.replace(user.getId(), user);
        log.info("User updated: '{}'", user.getLogin());
        return user;
    }

}

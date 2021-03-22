package mymori.mymoriapi.controllers;

import mymori.mymoriapi.exceptions.UserAlreadyExistsException;
import mymori.mymoriapi.exceptions.UserCouldntBeRemovedException;
import mymori.mymoriapi.exceptions.UserCouldntBeSavedException;
import mymori.mymoriapi.exceptions.UserNotFoundException;
import mymori.mymoriapi.models.User;
import mymori.mymoriapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PutMapping("/user")
    public String create(@RequestBody User user) {
        if (userRepository.findByFirstName(user.getFirstName()).size() == 0)
            try {
                userRepository.save(new User(user.getFirstName(), user.getLastName()));
                return user.toString();
            } catch (Exception e) {
                throw new UserCouldntBeSavedException();
            }
        else
            throw new UserAlreadyExistsException();
    }

    @PostMapping("/user")
    public String update(@RequestBody User user) {
        try {
            User oldUser = userRepository.findByFirstName(user.getFirstName()).get(0);
            userRepository.save(new User(oldUser.getId(), user.getFirstName(), user.getLastName()));
            return user.toString();
        } catch (Exception e) {
            throw new UserCouldntBeSavedException();
        }
    }

    @GetMapping("/userWithName/{name}")
    public User get(@PathVariable String name) {
        try {
            return userRepository.findByFirstName(name).get(0);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/user/{id}")
    public Optional<User> get(@PathVariable long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping("/userWithName/{name}")
    public String delete(@PathVariable String name) {
        try {
            userRepository.deleteByFirstName(name);
            return "User deleted";
        } catch (Exception e) {
            throw new UserCouldntBeRemovedException();
        }
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable long id) {
        try {
            userRepository.deleteById(id);
            return "User deleted";
        } catch (Exception e) {
            throw new UserCouldntBeRemovedException();
        }
    }
}

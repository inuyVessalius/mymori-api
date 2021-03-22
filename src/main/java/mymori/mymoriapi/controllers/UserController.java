package mymori.mymoriapi.controllers;

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

    @PostMapping("/user")
    public String create(@RequestBody User user) {
        userRepository.save(new User(user.getFirstName(), user.getLastName()));
        System.out.println(user.toString());
        return "User created";
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
        return userRepository.findById(id);
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}

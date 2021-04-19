package mymori.mymoriapi.controllers;

import mymori.mymoriapi.exceptions.UserAlreadyExistsException;
import mymori.mymoriapi.exceptions.UserCouldntBeRemovedException;
import mymori.mymoriapi.exceptions.UserCouldntBeSavedException;
import mymori.mymoriapi.exceptions.UserNotFoundException;
import mymori.mymoriapi.models.User;
import mymori.mymoriapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Optional;

@RestController
@Api(value = "User")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "Creaste a user")
    @PutMapping("/user")
    public User create(@RequestBody User user) {
        try {
            if (user.getFirstName().isEmpty() || user.getFirstName().equals("null") || user.getFirstName() == null || user.getLastName().isEmpty() || user.getLastName().equals("null") || user.getLastName() == null)
                throw new UserCouldntBeSavedException();
            if (userRepository.findByFirstName(user.getFirstName()).size() == 0) {
                User newUser = userRepository.save(user);
                return newUser;
            } else
                throw new UserAlreadyExistsException();
        } catch (Exception e) {
            throw new UserCouldntBeSavedException();
        }
    }

    @PostMapping("/user")
    public String update(@RequestBody User user) {
        try {
            if (user.getFirstName().isEmpty() || user.getFirstName().equals("null") || user.getFirstName() == null || user.getLastName().isEmpty() || user.getLastName().equals("null") || user.getLastName() == null)
                throw new UserCouldntBeSavedException();
            User oldUser = userRepository.findByFirstName(user.getFirstName()).get(0);
            user.setId(oldUser.getId());
            userRepository.save(user);
            return user.toString();
        } catch (Exception e) {
            throw new UserCouldntBeSavedException();
        }
    }

    @GetMapping("/userWithName/{name}")
    public User get(@PathVariable String name) {
        try {
            if (name.isEmpty() || name.equals("null") || name == null)
                throw new UserNotFoundException();
            return userRepository.findByFirstName(name).get(0);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/user/{id}")
    public Optional<User> get(@PathVariable long id) {
        try {
            if (id == 0)
                throw new UserNotFoundException();
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping("/userWithName/{name}")
    public String delete(@PathVariable String name) {
        try {
            if (name.isEmpty() || name.equals("null") || name == null)
                throw new UserCouldntBeRemovedException();
            userRepository.deleteByFirstName(name);
            return "User deleted";
        } catch (Exception e) {
            throw new UserCouldntBeRemovedException();
        }
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable long id) {
        try {
            if (id == 0)
                throw new UserCouldntBeRemovedException();
            userRepository.deleteById(id);
            return "User deleted";
        } catch (Exception e) {
            throw new UserCouldntBeRemovedException();
        }
    }
}

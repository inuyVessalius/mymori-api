package mymori.mymoriapi.controllers;

import mymori.mymoriapi.exceptions.GameCouldntBeSavedException;
import mymori.mymoriapi.exceptions.GameNotFoundException;
import mymori.mymoriapi.models.Game;
import mymori.mymoriapi.repository.GameRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @PutMapping("/game")
    public Game create(@RequestBody Game game) {
        try {
            Game newGame = gameRepository.save(game);
            return newGame;
        } catch (Exception e) {
            throw new GameCouldntBeSavedException();
        }
    }

    @GetMapping("/game/{id}")
    public Optional<Game> get(@PathVariable long id) {
        try {
            if (id == 0)
                throw new GameNotFoundException();
            return gameRepository.findById(id);
        } catch (Exception e) {
            throw new GameNotFoundException();
        }
    }

    @GetMapping("/gameByUserId/{gameId}&{userId}")
    public Game get(@PathVariable long gameId, @PathVariable long userId) {
        try {
            return gameRepository.findByUserId(gameId, userId);
        } catch (Exception e) {
            throw new GameNotFoundException();
        }
    }

    @DeleteMapping("/game/{id}")
    public String delete(@PathVariable long id) {
        gameRepository.deleteById(id);
        return "Game deleted";
    }
}

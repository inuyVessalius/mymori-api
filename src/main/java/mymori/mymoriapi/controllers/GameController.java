package mymori.mymoriapi.controllers;

import mymori.mymoriapi.models.Game;
import mymori.mymoriapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @PostMapping("/game")
    public String create(@RequestBody Game game){
        gameRepository.save(new Game(game.getUserId()));

        return "Game created";
    }

//    @GetMapping("/getGameByUserId/{userId}&{gameId}")
//    public Game get(@PathVariable long userId, @PathVariable long gameId){
//        return repository.findByUserId(userId, gameId);
//    }

    @DeleteMapping("/game/{id}")
    public String delete(@PathVariable long id){
        gameRepository.deleteById(id);
        return "Game deleted";
    }
}

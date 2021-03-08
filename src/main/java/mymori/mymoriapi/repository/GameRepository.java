package mymori.mymoriapi.repository;

import mymori.mymoriapi.models.Game;
import mymori.mymoriapi.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long>{
    // Game findByUserId(long userId, long gameId);
}
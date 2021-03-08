package mymori.mymoriapi.repository;

import mymori.mymoriapi.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long>{
    // Game findByUserId(long userId, long gameId);
}
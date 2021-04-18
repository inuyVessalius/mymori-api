package mymori.mymoriapi.repository;

import mymori.mymoriapi.models.Game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GameRepository extends CrudRepository<Game, Long> {
    @Query(value = "SELECT * FROM games g WHERE g.id = ?1 AND g.user_id=?2 LIMIT 1", nativeQuery = true)
    Game findByUserId(long id, long user_id);
}
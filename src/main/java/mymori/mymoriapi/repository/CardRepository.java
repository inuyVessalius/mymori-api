package mymori.mymoriapi.repository;

import mymori.mymoriapi.models.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CardRepository extends CrudRepository<Card, Long>{
}
package mymori.mymoriapi.repository;

import mymori.mymoriapi.models.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long>{
}
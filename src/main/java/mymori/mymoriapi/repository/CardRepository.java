package mymori.mymoriapi.repository;

import mymori.mymoriapi.controllers.CardController;
import mymori.mymoriapi.models.Card;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CardRepository extends CrudRepository<Card, Long>{
    List<Card> findByQuestion(String question);
    void deleteByQuestion(String question);
}
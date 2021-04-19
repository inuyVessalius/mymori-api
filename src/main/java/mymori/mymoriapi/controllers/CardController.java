package mymori.mymoriapi.controllers;

import mymori.mymoriapi.exceptions.CardAlreadyExistsException;
import mymori.mymoriapi.exceptions.CardCouldntBeRemovedException;
import mymori.mymoriapi.exceptions.CardCouldntBeSavedException;
import mymori.mymoriapi.exceptions.CardNotFoundException;
import mymori.mymoriapi.models.Card;
import mymori.mymoriapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @PutMapping("/card")
    public Card create(@RequestBody Card card) {
        try {
            if (card.getQuestion().isEmpty() || card.getQuestion().equals("null") || card.getQuestion() == null || card.getAnswer().isEmpty() || card.getAnswer().equals("null") || card.getAnswer() == null)
                throw new CardCouldntBeSavedException();
            if (!cardRepository.findById(card.getId()).isPresent()) {
                Card newCard = cardRepository.save(card);
                return newCard;
            } else
                throw new CardAlreadyExistsException();
        } catch (Exception e) {
            throw new CardCouldntBeSavedException();
        }
    }

    @PutMapping("/cards")
    public Iterable<Card> create(@RequestBody Iterable<Card> cards) {
        try {
            Iterable<Card> newCards = cardRepository.saveAll(cards);
            return newCards;
        } catch (Exception e) {
            throw new CardCouldntBeSavedException();
        }
    }

    @PostMapping("/card")
    public String update(@RequestBody Card card) {
        try {
            if (card.getQuestion().isEmpty() || card.getQuestion().equals("null") || card.getQuestion() == null || card.getAnswer().isEmpty() || card.getAnswer().equals("null") || card.getAnswer() == null)
                throw new CardCouldntBeSavedException();
            Card oldCard = cardRepository.findByQuestion(card.getQuestion()).get(0);
            card.setId(oldCard.getId());
            cardRepository.save(card);
            return card.toString();
        } catch (Exception e) {
            throw new CardCouldntBeSavedException();
        }
    }

    @GetMapping("/cardWithQuestion/{question}")
    public Card get(@PathVariable String question) {
        try {
            if (question.isEmpty() || question.equals("null") || question == null)
                throw new CardCouldntBeRemovedException();
            return cardRepository.findByQuestion(question).get(0);
        } catch (Exception e) {
            throw new CardNotFoundException();
        }
    }

    @GetMapping("/card/{id}")
    public Optional<Card> get(@PathVariable long id) {
        try {
            if (id == 0)
                throw new CardNotFoundException();
            return cardRepository.findById(id);
        } catch (Exception e) {
            throw new CardNotFoundException();
        }
    }

    @GetMapping("/cards")
    public Iterable<Card> get() {
        try {
            return cardRepository.findAll();
        } catch (Exception e) {
            throw new CardNotFoundException();
        }
    }

    @DeleteMapping("/cardWithQuestion/{question}")
    public String delete(@PathVariable String question) {
        try {
            if (question.isEmpty() || question.equals("null") || question == null)
                throw new CardCouldntBeRemovedException();
            cardRepository.deleteByQuestion(question);
            return "Card deleted";
        } catch (Exception e) {
            throw new CardCouldntBeRemovedException();
        }
    }

    @DeleteMapping("/card/{id}")
    public String delete(@PathVariable long id) {
        try {
            if (id == 0)
                throw new CardCouldntBeRemovedException();
            cardRepository.deleteById(id);
            return "Card deleted";
        } catch (Exception e) {
            throw new CardCouldntBeRemovedException();
        }
    }

    @DeleteMapping("/cards")
    public String deleteCards(@RequestBody Iterable<Card> cards) {
        try {
            for (Card card : cards) {
                if (card.getId() == 0)
                    throw new Exception();
            }

            cardRepository.deleteAll(cards);
            return "Cards deleted";
        } catch (Exception e) {
            throw new CardCouldntBeRemovedException();
        }
    }
}

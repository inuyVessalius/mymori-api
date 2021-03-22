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
    public String create(@RequestBody Card card) {
        if (cardRepository.findById(card.getId()).equals(0))
            try {
                cardRepository.save(new Card(card.getQuestion(), card.getQuestion()));
                return card.toString();
            } catch (Exception e) {
                throw new CardCouldntBeSavedException();
            }
        else
            throw new CardAlreadyExistsException();
    }

    @PostMapping("/card")
    public String update(@RequestBody Card card) {
        try {
            Card oldCard = cardRepository.findByQuestion(card.getQuestion()).get(0);
            cardRepository.save(new Card(oldCard.getId(), card.getQuestion(), card.getAnswer()));
            return card.toString();
        } catch (Exception e) {
            throw new CardCouldntBeSavedException();
        }
    }

    @GetMapping("/cardWithQuestion/{question}")
    public Card get(@PathVariable String question) {
        try {
            return cardRepository.findByQuestion(question).get(0);
        } catch (Exception e) {
            throw new CardNotFoundException();
        }
    }

    @GetMapping("/card/{id}")
    public Optional<Card> get(@PathVariable long id) {
        try {
            return cardRepository.findById(id);
        } catch (Exception e) {
            throw new CardNotFoundException();
        }
    }

    @DeleteMapping("/cardWithQuestion/{question}")
    public String delete(@PathVariable String question) {
        try {
            cardRepository.deleteByQuestion(question);
            return "Card deleted";
        } catch (Exception e) {
            throw new CardCouldntBeRemovedException();
        }
    }

    @DeleteMapping("/card/{id}")
    public String delete(@PathVariable long id) {
        try {
            cardRepository.deleteById(id);
            return "Card deleted";
        } catch (Exception e) {
            throw new CardCouldntBeRemovedException();
        }
    }
}

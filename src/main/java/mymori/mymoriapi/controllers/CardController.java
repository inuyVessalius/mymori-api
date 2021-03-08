package mymori.mymoriapi.controllers;

import mymori.mymoriapi.models.Card;
import mymori.mymoriapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @PostMapping("/createCard")
    public String create(@RequestBody Card card){
        this.cardRepository.save(new Card(card.getQuestion(), card.getAnswer()));

        return "Card created";
    }

    @GetMapping("/getCardById/{id}")
    public Optional<Card> get(@PathVariable long id){
        return cardRepository.findById(id);
    }

    @DeleteMapping("/deleteCard/{id}")
    public String delete(@PathVariable long id){
        cardRepository.deleteById(id);
        return "Card deleted";
    }
}

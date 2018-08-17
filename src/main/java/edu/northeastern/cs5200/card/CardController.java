package edu.northeastern.cs5200.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "/api/card")
    public ResponseEntity<String> fetchAllCards(@RequestBody CardCriteria cardCriteria) {
        return cardService.fetchAllCards(cardCriteria);
    }

    @GetMapping(value = "/api/card/{cardId}")
    public ResponseEntity<String> fetchCardById(@PathVariable("cardId") String cardId) {
        return cardService.fetchCardById(cardId);
    }


}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.cards.Card;

/**
 * Each player owns two cards. These cards are dealt during the preflop.
 * @author esiProfs
 */
class PlayerCards {

    private final List<Card> cards;

    /**
     * At first the player has no card.
     */
    PlayerCards() {
        cards = new ArrayList<>();
    }

    /**
     * Add a card to the player's hand.
     *
     * @param card the card to add
     */
    void add(Card card) {
        cards.add(card);
    }
    
    /**
     * Show all the card to the player.
     */
    void show() {
        for (Card card : cards) {
            card.show();
        }
    }

    /**
     * Return an unmodifiableList of cards.
     *
     * @return an unmodifiableList of cards
     */

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

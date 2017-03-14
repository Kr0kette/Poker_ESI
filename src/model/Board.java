package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.cards.Card;

/**
 * The shared cards of the players are showned on the board. This board contains
 * 5 cards. Each state revelas more cards of this board.
 *
 * @author esiProfs
 */
class Board {

    private final List<Card> cards;

    /**
     * At first the board has no card.
     */
    Board() {
        cards = new ArrayList<>();
    }

    /**
     * Add a card to the board.
     *
     * @param card the card to add
     */
    public void add(Card card) {
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

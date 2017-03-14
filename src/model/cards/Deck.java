package model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A complete set of cards.
 */
public class Deck {


    public final static int SIZE = 52;

    private final List<Card> cards;


    public Deck() {
        cards = new ArrayList<>();
        for (Color color : Color.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(color, value));
            }
        }
    }


    public void shuffle() {
        Collections.shuffle(cards);
    }


    public boolean isEmpty() {
        return cards.isEmpty();
    }


    public Card pick() {
        return cards.remove(cards.size() - 1);
    }

}

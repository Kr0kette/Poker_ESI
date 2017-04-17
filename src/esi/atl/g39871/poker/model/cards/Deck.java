package esi.atl.g39871.poker.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A complete set of cards.
 */
public class Deck {

  /**
   * The deck's size. It represents the amount of cards within the deck.
   */
  public final static int SIZE = 52;

  private final List<Card> cards;

  /**
   * Create a new instance of Deck
   */
  public Deck() {
    cards = new ArrayList<>();
    for (Color color : Color.values()) {
      for (Value value : Value.values()) {
        cards.add(new Card(color, value));
      }
    }
  }

  /**
   * Shuffle the deck's cards.
   */
  public void shuffle() {
    Collections.shuffle(cards);
  }

  /**
   * Checks if the deck is empty.
   * <p>
   * A deck is empty if it doesn't contains any cards.
   *
   * @return true if the deck is empty, false otherwise
   */
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  /**
   * Pick a card from the top of the deck
   * <p>
   * The card is removed from the deck
   *
   * @return the card at the top of the deck
   */
  public Card pick() {
    return cards.remove(cards.size() - 1);
  }

}

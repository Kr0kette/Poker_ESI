package esi.atl.g39871.poker.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * A Poker table's view. The user of this Interface has precise control over what to do with each
 * gui's elements
 * 
 * @author g39871
 * @param <T> the type that this poker table's view should extends or implements
 */
public interface PokerTableViewInterface<T extends BorderPane> {

  /**
   * Add a card to the board.
   *
   * @param <T> the type this method should extends or implements
   * @param card the card to add
   */
  <T extends VBox> void addCard(T card);

  /**
   * Clears all board's elements.
   */
  void clearBoard();

  /**
   * Sets the pot's value field to the value given in argument
   *
   * @param value the new value for the pot
   */
  void setPot(String value);

  /**
   * Sets the status field to the value given in argument
   *
   * @param value the new value
   */
  void setStatus(String value);

}

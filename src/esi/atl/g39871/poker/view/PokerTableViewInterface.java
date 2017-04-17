package esi.atl.g39871.poker.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Krokro
 */
public interface PokerTableViewInterface<T extends BorderPane> {

  /**
   * Add a card to the board.
   *
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

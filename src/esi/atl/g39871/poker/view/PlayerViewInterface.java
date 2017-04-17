package esi.atl.g39871.poker.view;

import javafx.scene.layout.VBox;

/**
 *
 * @author Krokro
 */
public interface PlayerViewInterface<T extends VBox> {

  /**
   * Add a card to the player.
   *
   * @param card the card to add
   */
  <T extends VBox> void addCard(T card);

  void clearCards();

  /**
   * Display a visual effect player to show the current. The visual effect depends on the argument
   * player.
   *
   * @param css the css rules, if more than one rule, they must be separated by the traditional
   *        semicolon
   */
  void setCurrentPlayerEffect(String css);

  /**
   * Sets the visual element to represent if a player is folded or not. It's advised to set true
   * when the player is folded.
   *
   * @param b true to show the element, false otherwise
   */
  void setFolded(boolean b);

  /**
   * Sets the visual element to represent if a player has the button or not. It's advised to set
   * true when the player has the button.
   *
   * @param b true to show the element, false otherwise.
   */
  void setHasButton(boolean b);

  /**
   * Update the player's money
   *
   * @param money the player's money
   */
  void setMoney(String money);

  /**
   * Sets the player's name
   *
   * @param name the player's name
   */
  void setName(String name);

  /**
   * Update the player's profit.
   *
   * @param profit the player's profit
   */
  void setProfit(String profit);

}

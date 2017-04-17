package esi.atl.g39871.poker.mvc;

import esi.atl.g39871.poker.view.PokerView;

/**
 *
 * @author Krokro
 */
public interface ControllerInterface {

  /**
   * Add a player to the game, and create a visual for this player
   *
   * @param name the player's name
   * @param money the player's amount of money
   */
  void addPlayer(String name, String money);

  /**
   * Bet all player's chips.
   *
   */
  void allIn();

  /**
   * Match a bigBlind.
   */
  void bigBlind();

  /**
   * Match a call.
   */
  void call();

  /**
   * Match a check.
   */
  void check();

  /**
   * Discard one's hand and forfeit interest in the current pot.
   *
   */
  void fold();

  /**
   * Returns the view's facade
   *
   * @return the view's facade
   */
  PokerView getView();

  /**
   * Match a raise
   */
  void raise();

  /**
   * Match a smallBlind.
   */
  void smallBlind();

  /**
   * Start de game.
   * <p>
   * Disable the start button.
   * <p>
   * Disable the button to add a player.
   * <p>
   * Enable the stop button.
   *
   */
  void start();

  /**
   * Stop de game and set the stop button to invisible
   *
   */
  void stop();

}

package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Bet;
import esi.atl.g39871.poker.model.Player;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author g39871
 */
public interface PokerViewInterface {
  /**
   * Add a visual component for a new player.
   *
   * @param player the new player
   */
  void addPlayerInLayout(Player player);

  void alert(String message);

  /**
   * Disable all bets buttons
   */
  void disableAllBetsButtons();


  /**
   * Enable or disable the button to add a player according to the argument. Give true enable the
   * button and false to disable it.
   *
   * @param b the boolean value.
   */
  void enableAddPlayerButton(boolean b);

  /**
   * Enable or disable the allIn button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  void enableAllInButton(boolean b);

  /**
   * Enable or disable the bet's amount input field according to the argument. Give true enable the
   * button and false to disable it.
   *
   * @param b the boolean value.
   */
  void enableAmountField(boolean b);

  /**
   * Enable or disable the bigBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b the boolean value.
   */
  void enableBigBlindButton(boolean b);

  /**
   * Enable or disable the call button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  void enableCallButton(boolean b);

  /**
   * Enable or disable the check button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  void enableCheckButton(boolean b);

  /**
   * Enable or disable the fold button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  void enableFoldButton(boolean b);

  /**
   * Enable or disable the raise button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  void enableRaiseButton(boolean b);

  /**
   * Enable or disable the smallBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b the boolean value.
   */
  void enableSmallBlindButton(boolean b);

  /**
   * Enable or disable the start button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  void enableStartButton(boolean b);

  /**
   * Enable the right available bets buttons that the player is authorized to use.
   *
   * @param availableBets the available bets
   */
  void enableStatusButtons(List<Bet> availableBets);

  /**
   * Enable or disable the stop button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  void enableStopButton(boolean b);

  int getBetAmount();

  /**
   * Sets the minimum bet's value field to the value given in argument
   *
   * @param value the value of the minimum bet
   */
  void setMinBet(String value);

  /**
   * Returns the poker table.
   *
   * @return the poker table
   */
  PokerTableView getPokerTable();

  /**
   * Sets the smallBlind value field to the value given in argument
   *
   * @param value the smallBlind value
   */
  void setSmallBlindValue(String value);

  void update(Observable o, Object o1);

}

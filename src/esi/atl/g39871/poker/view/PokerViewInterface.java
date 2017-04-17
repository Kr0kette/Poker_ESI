package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Bet;
import esi.atl.g39871.poker.model.Player;
import java.util.List;

/**
 * A Poker's view. The user of this Interface has precise control over what to do with each gui's
 * elements
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

  /**
   * Generates an alert for the given message.
   * 
   * @param message the message
   */
  void alert(String message);

  /**
   * Disable all bets buttons
   */
  void disableAllBetsButtons();


  /**
   * Enable or disable the button to add a player according to the argument.
   * 
   * @param b true enable the button and false disable it.
   */
  void enableAddPlayerButton(boolean b);

  /**
   * Enable or disable the allIn button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b true enable the button and false disable it
   */
  void enableAllInButton(boolean b);


  /**
   * Enable or disable the bet's amount input field according to the argument. Give true enable the
   * button and false to disable it.
   *
   * @param b true enable the field and false disable it.
   */
  void enableAmountField(boolean b);

  /**
   * Enable or disable the bigBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b true enable the button and false disable it
   */
  void enableBigBlindButton(boolean b);

  /**
   * Enable or disable the call button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b true enable the button and false disable it
   */
  void enableCallButton(boolean b);

  /**
   * Enable or disable the check button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b true enable the button and false disable it
   */
  void enableCheckButton(boolean b);

  /**
   * Enable or disable the fold button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b true enable the button and false disable it
   */
  void enableFoldButton(boolean b);

  /**
   * Enable or disable the new player's money input field according to the argument. Give true
   * enable the button and false to disable it.
   *
   * @param b true enable the field and false disable it.
   */
  void enableNewPlayerMoneyField(boolean b);

  /**
   * Enable or disable the new player's name input field according to the argument. Give true enable
   * the button and false to disable it.
   *
   * @param b true enable the field and false disable it.
   */
  void enableNewPlayerNameField(boolean b);

  /**
   * Enable or disable the raise button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b true enable the button and false disable it
   */
  void enableRaiseButton(boolean b);

  /**
   * Enable or disable the smallBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b true enable the button and false disable it
   */
  void enableSmallBlindButton(boolean b);

  /**
   * Enable or disable the start button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b true enable the button and false disable it
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
   * @param b true enable the button and false disable it
   */
  void enableStopButton(boolean b);


  /**
   * Returns the bet's amount.
   * 
   * @return the bet's amount
   */
  int getBetAmount();

  /**
   * Sets the minimum bet's value field to the value given in argument
   *
   * @param value the value of the minimum bet
   */
  void setMinBet(String value);


  /**
   * Returns the new player's name's field's content.
   * 
   * @return the new player's name's field's content
   */
  String getNewPlayerMoney();

  /**
   * Returns the new player's money's field's content.
   * 
   * @return the new player's money's field's content
   */
  String getNewPlayerName();


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


}

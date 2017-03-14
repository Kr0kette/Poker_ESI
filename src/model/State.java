package model;

import java.util.List;

/**
 *
 * @author esiProfs
 */
interface State {

    /**
     * The current player matches a bet/raise.
     *
     * @param currentPlayer current player
     * @param minimum current minimum of the round
     * @param pot betting pot
     * @throws model.GameException if the match is in Blind State
     */
    void call(Player currentPlayer, int minimum, Pots pot) throws GameException;

    /**
     * The current player discards his hand and forfeits interest in the current
     * pot.
     *
     * @param currentPlayer current player
     * @throws model.GameException if the match is in Blind State
     */
    void fold(Player currentPlayer) throws GameException;

    /**
     * The current player increases the size of the existing bet.
     *
     * @param currentPlayer current player
     * @param minimum current minimum of the round
     * @param amount bet's amount
     * @param pot betting pot
     * @throws model.GameException if the match is in Blind State, or if the
     * player does not own enough money, or if the amount is not > 0
     */
    void raise(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException;

    /**
     * The current player bets the given amount.
     *
     * @param currentPlayer current player
     * @param amount bet's amount
     * @param minimum current minimum of the round
     * @param pot betting pot
     * @throws model.GameException if the match is in Blind State or if the
     * amount is not the Match.SMALLBLIND value
     */
    void smallBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException;

    /**
     * The current player bets the given amount.
     *
     * @param currentPlayer current player
     * @param amount bet's amount
     * @param minimum current minimum of the round
     * @param pot betting pot
     * @throws model.GameException if the amount is not twice the smallblind or
     * if the match is not in the Blind State
     */
    void bigBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException;

    /**
     * The current player bets all his chips.
     *
     * @param currentPlayer current player
     * @param minimum current minimum of the round
     * @param pot betting pot
     * @throws model.GameException if the match is in Blind State, or if the
     * player owns enough chips to call
     */
    void allIn(Player currentPlayer, int minimum, Pots pot) throws GameException;

    /**
     * Return the list of available bets in this round.
     *
     * @return the list of available bets in this round
     */
    List<Bet> getAvailable();

    /**
     * Move on to the next player or to the next State (Blind,Preflop,Flop,Turn,
     * River)
     *
     * @throws GameException
     */
    void nextState() throws GameException;

    /**
     * Add all the chips to the pot. If a player is all in, add thje chips to a
     * another pot.
     *
     * @throws GameException
     */
    void addPot();
}

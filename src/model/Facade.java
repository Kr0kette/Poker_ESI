package model;

import java.util.List;
import model.cards.Card;

/**
 * Facade of Poker model.
 * @link https://en.wikipedia.org/wiki/Facade_pattern
 * @author esiProfs
 */
public interface Facade {

    /**
     * Add a player to the game.
     *
     * @param name name of the player
     * @param money amount of player's chips
     */
    void addPlayer(String name, int money);

    /**
     * Bet the given amount.
     *
     * @param amount amount of the bet
     * @throws model.GameException if the amount is not twice the smallblind or
     * if the match is not in the Blind State
     */
    void bigBlind(int amount) throws GameException;

    /**
     * Match a bet/raise.
     *
     * @throws model.GameException if the match is in Blind State
     */
    void call() throws GameException;

    /**
     * Discard one's hand and forfeit interest in the current pot.
     *
     * @throws GameException if the match is in Blind State
     */
    void fold() throws GameException;

    /**
     * Bet all player's chips.
     *
     * @throws GameException if the match is in Blind State or if the player
     * owns enough chips to call
     */
    void allIn() throws GameException;

    /**
     * Return the list of available bets in this round.
     *
     * @return the list of available bets in this round
     */
    List<Bet> getAvailable();

    /**
     * Return the card's list deal on the board.
     *
     * @return the card's list deal on the board
     */
    List<Card> getBoard();

    /**
     * Return the player's cards.
     *
     * @return the player's cards
     */
    List<Card> getCards();

    /**
     * Return the current player.
     *
     * @return the current player
     */
    Player getCurrentPlayer();

    /**
     * Return the minimum bet's amount.
     *
     * @return the minimum bet's amount
     */
    int getMinimium();

    /**
     * Return the list of players.
     *
     * @return the list of players
     */
    List<Player> getPlayers();

    /**
     * Return the pot's content.
     *
     * @return the pot's content
     */
    int getPot();

    /**
     * Return the game's status.
     * <ul>
     * <li>INIT </li>
     * <li>BLIND</li>
     * <li>PREFLOP</li>
     * <li>FLOP</li>
     * <li>TURN</li>
     * <li>RIVER</li>
     * <li>SHOWDOWN</li>
     * <li>SPLITPOT</li>
     * <li>END_MATCH</li>
     * <li>END_GAME</li>
     * </ul>
     *
     * @return the game's status
     */
    Status getStatus();

    /**
     * Increase the size of an existing bet in the same betting round.
     *
     * @param amount increasing amount
     * @throws model.GameException if the match is in Blind State, or if the
     * player does not own enough money, or if the amount is not greater than 0
     */
    void raise(int amount) throws GameException;

    /**
     * Bet the given amount.
     *
     * @param amount amount of the bet
     * @throws model.GameException if the match is not in the Blind State
     */
    void smallBlind(int amount) throws GameException;

    /**
     * Start a gameof poker.
     *
     * @throws model.GameException if there is not enough players or if a match
     * is in progress
     */
    void start() throws GameException;

    /**
     * End the game.
     */
    void stop();

    /**
     * Start a match of poker.
     *
     * @throws GameException if a match is in progress
     */
    void startMatch() throws GameException;

    /**
     * Return the small blind value.
     * @return the small blind value
     */
    int getSmallBlindValue();
}

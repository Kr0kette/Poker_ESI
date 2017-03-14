package model;

import java.util.Collections;
import java.util.List;
import model.cards.Card;
import model.cards.Deck;

/**
 * A poker match. A button is rotated clockwise among the players to indicate a
 * nominal dealer to determine the order of betting. The cards are dealt
 * clockwise around the poker table, one at a time.
 *
 * Two players are required to make forced bets : a blind bet. The dealer
 * shuffles the cards, and deals the appropriate number of cards to the players
 * one at a time, beginning with the player to his left. After the initial deal,
 * the first of what may be several betting rounds begins. Between rounds, some
 * cards has been dealt to the board. At the end of each round, all bets are
 * gathered into the central pot.
 *
 * At any time during a betting round, if one player bets, no opponents choose
 * to call (match) the bet, and all opponents instead fold, the hand ends
 * immediately, the bettor is awarded the pot, no cards are required to be
 * shown, and the next hand begins.
 *
 * At the end of the last betting round, if more than one player remains, there
 * is a showdown, in which the players reveal cards and evaluate their hands.
 * The player with the best hand wins the pot. A poker hand comprises five
 * cards.
 *
 * @author esiProfs
 */
class Match {

    final static int SMALLBLIND = 1;

    private final State blind;
    private final State preFlop;
    private final State flop;
    private final State turn;
    private final State river;

    private State state;

    private final Pots potList;
    private final Deck deck;

    private final Board board;

    private PlayerIterator iterator;
    private Player currentPlayer;
    private int minimum;

    private boolean isOver;

    /**
     * To start a match all the players needs a minimumof chips.
     *
     * @param players list of players
     * @throws model.GameException if a player does not own enough chips
     */
    Match(List<Player> players) throws GameException {
        for (Player player : players) {
            if (player.getMoney() < 10 * SMALLBLIND) {
                throw new GameException("Les joueurs doivent posséder au moins 10 fois la SMALLBLIND en caisse");
            }
        }
        iterator = new PlayerIterator(players);
        currentPlayer = iterator.next();
        blind = new Blind(this);
        preFlop = new Preflop(this);
        flop = new Flop(this);
        river = new River(this);
        turn = new Turn(this);
        potList = new Pots();
        board = new Board();
        deck = new Deck();
        deck.shuffle();
        isOver = false;
        state = blind;
    }

    /**
     * Dealt some cards to all players.
     *
     * @param nbCards amount of cards to deal
     */
    void dealPlayer(int nbCards) {
        while (iterator.hasNext()) {
            Player player = iterator.next();
            for (int i = 0; i < nbCards; i++) {
                Card card = deck.pick();
                card.show();
                player.add(card);
            }
        }
    }

    /**
     * Dealt some cards to the board.
     *
     * @param nbCards amount of cards to deal
     */
    void dealBoard(int nbCards) {
        for (int i = 0; i < nbCards; i++) {
            Card card = deck.pick();
            board.add(card);
        }
    }

    /**
     * Show the board'scards.
     */
    void showBoard() {
        board.show();
    }

    /**
     * Return the current player's cards.
     *
     * @returnthe current player's cards
     */
    List<Card> getCards() {
        return currentPlayer.getCards();
    }

    /**
     * Return the board's cards.
     *
     * @return the board's cards
     */
    List<Card> getBoard() {
        return board.getCards();
    }

    /**
     * Increase the size of an existing bet in the same betting round.
     *
     * @param amount increasing amount
     * @throws model.GameException if the match is in Blind State, or if the
     * player does not own enough money, or if the amount is not > 0
     */
    void raise(int amount) throws GameException {
        state.raise(currentPlayer, minimum, amount, potList);
    }

    /**
     * Discard one's hand and forfeit interest in the current pot.
     *
     * @throws GameException if the match is in Blind State
     */
    void fold() throws GameException {
        state.fold(currentPlayer);
    }

    /**
     * Match a bet/raise.
     *
     * @throws model.GameException if the match is in Blind State
     */
    void call() throws GameException {
        state.call(currentPlayer, minimum, potList);
    }

    /**
     * Bet all player's chips.
     *
     * @throws GameException if the match is in Blind State or if the player
     * owns enough chips to call
     */
    void allIn() throws GameException {
        state.allIn(currentPlayer, minimum, potList);
    }

    /**
     * Bet the given amount.
     *
     * @param amount amount of the bet
     * @throws model.GameException if the match is not in the Blind State
     */
    void smallBlind(int amount) throws GameException {
        state.smallBlind(currentPlayer, minimum, amount, potList);
    }

    /**
     * Bet the given amount.
     *
     * @param amount amount of the bet
     * @throws model.GameException if the amount is not twice the smallblind or
     * if the match is not in the Blind State
     */
    void bigBlind(int amount) throws GameException {
        state.bigBlind(currentPlayer, minimum, amount, potList);
    }

    /**
     * Return the current state of the ongoing match.
     * <ul>
     * <li>Blind</li>< <li>Preflop</li>< <li>Flop</li>< <li>Turn</li><      <li>River</li><
     * /ul>
     *
     * @return the current state of the ongoing match
     */
    State getState() {
        return state;
    }

    /**
     * Return the current player.
     *
     * @return the current player
     */
    Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Return the list of available bets in this round.
     *
     * @return the list of available bets in this round
     */
    List<Bet> getAvailable() {
        return state.getAvailable();
    }

    /**
     * Return the pot's content.
     *
     * @return the pot's content
     */
    int getPot() {
        int total = 0;
        List<Player> players = iterator.getPlayers();
        for (Player player : players) {
            total += player.getCurrentBet();
        }
        return total + potList.getTotal();
    }

    /**
     * Return the minimum bet's amount.
     *
     * @return the minimum bet's amount
     */
    int getMinimum() {
        return minimum;
    }

    /**
     * Return the blind state.
     *
     * @return the blind state
     */
    State getBlind() {
        return blind;
    }

    /**
     * Return the preflop state.
     *
     * @return the preflop state
     */
    State getPreFlop() {
        return preFlop;
    }

    /**
     * Return the flop state.
     *
     * @return the flop state
     */
    State getFlop() {
        return flop;
    }

    /**
     * Return the turn state.
     *
     * @return the turn state
     */
    State getTurn() {
        return turn;
    }

    /**
     * Return the river state.
     *
     * @return the river state
     */
    State getRiver() {
        return river;
    }

    /**
     * Set the current state amongst BLind, Prflop, Flop, TUrn or River.
     *
     * @param state next state ofthe ongoing match
     */
    void setState(State state) {
        this.state = state;
    }

    /**
     * Set the minimum bet's amount.
     *
     * @param minimum the minimum bet's amount
     */
    void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    /**
     * Find the next player.
     */
    void nextPlayer() {
        currentPlayer = iterator.next();
    }

    /**
     * Check if another player has to play in the current round.
     *
     * @return if another player has to play in the current round
     */
    boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * Set the iterator to deal mode. After the blind,2 cards are dealt to each
     * player.
     *
     * @throws GameException if no button is given
     */
    void setDealIterator() throws GameException {
        iterator = new DealIterator(iterator);
    }

    /**
     * Set the iterator to raise mode. When the current player raises, all other
     * players still in the pot must either call the full amount of the bet or
     * raise if they wish remain in
     *
     * @throws GameException if no button is given
     */
    void setRaiseIterator() throws GameException {
        iterator = new RaiseIterator(iterator);
    }

    /**
     * Set the iterator to bet mode. The first player to start a ne betting
     * round is set with the betIterator
     *
     * @throws GameException if no button is given
     */
    void setBetIterator() throws GameException {
        iterator = new BetIterator(iterator);
    }

    /**
     * Check if only one player remains,
     *
     * @return if only one player remains
     */
    boolean onlyOne() {
        return iterator.onlyOne();
    }

    /**
     * At the end of the last betting round, if more than one player remains,
     * there is a showdown, in which the players reveal their previously hidden
     * cards and evaluate their hands.
     */
    void showDown() throws GameException {
        setBetIterator();
        while (hasNext()) {
            nextPlayer();
            HandFactory factory = new HandFactory(getCards(), getBoard());
            Hand best = factory.build();
            currentPlayer.setHand(best);
        }
    }

    /**
     * The players with the best hand according being played win the pot.
     */
    void splitPot() throws GameException {
        potList.split(iterator);
    }

    /**
     * End the ongoing match.
     */
    void end() {
        isOver = true;
    }

    /**
     * Check if the match is over,
     *
     * @return if the match is over
     */
    boolean isOver() {
        return isOver;
    }

    /**
     * At the end of a betting round, the total amount ofthe round is add to the
     * pot. If a player is all in a sub-pot is create.If the list of members
     * changes a sub-pot is create
     */
    void createPot(int totalPot, List<Player> members) {
        potList.create(totalPot, members);
    }

    /**
     * Return the list of players sort by bet amount,
     *
     * @return the list of players sort by bet amount,
     */
    List<Player> getSortPlayers() {
        List<Player> players = iterator.getPlayers();
        Collections.sort(players, new BetComparator());
        return players;
    }

}

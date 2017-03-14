package model;

import java.util.List;
import java.util.Objects;
import model.cards.Card;

/**
 * Poker's player.
 *
 * @author esiProfs
 */
public class Player {

    private final String name;
    private boolean fold;
    private PlayerCards cards;
    private boolean button;
    private Hand hand;

    private int money;

    private int currentBet;
    private int currentProfit;

    /**
     * Copy constructor.
     *
     * @param other player to copy
     */
    Player(String name, int money) {
        this.name = name;
        this.money = money;
        cards = new PlayerCards();
    }

    /**
     * A player is call by its name and owns an amount of money to play. He
     * received two cards during the preflop.
     *
     * @param name
     * @param money
     */
    Player(Player other) {
        name = other.getName();
        button = other.hasButton();
        cards = other.getPlayerCards();
        money = other.getMoney();
        fold = other.isFold();
        hand = other.getHand();
        currentBet = other.getCurrentBet();
        currentProfit = other.getCurrentProfit();
    }

    /**
     * Each match starts with a player whith no bet and no card.
     */
    void initializeMatch() {
        cards = new PlayerCards();
        fold = (money == 0);
        hand = null;
        currentBet = 0;
        currentProfit = 0;
    }

    /**
     * Each bets starts with a player whith no bet and no profit.
     */
    void initializeBet() {
        currentBet = 0;
        currentProfit = 0;
    }

    /**
     * Check if the player owns the button. The first player to bet is next to
     * the button's owner
     *
     * @return if the player owns the button
     */
    public boolean hasButton() {
        return button;
    }

    /**
     * Give the button to the player.
     */
    public void giveButtton() {
        this.button = true;
    }

    /**
     * Remove the button from the player.
     */
    void removeButton() {
        this.button = false;
    }

    /**
     * Check if the player has fold.
     *
     * @return if the player has fold
     */
    public boolean canPlay() {
        return !fold;
    }

    /**
     * Unfold the player.
     */
    public void unFold() {
        fold = false;
    }

    /**
     * Add a card to the player's hand.
     *
     * @param card card to add
     */
    public void add(Card card) {
        cards.add(card);
    }

    /**
     * Return the player's amount of chips.
     *
     * @return the player's amount of chips
     */
    public int getMoney() {
        return money;
    }

    /**
     * Make the bet. Add the amount to the currentBet and withdraw the amount
     * from the money
     *
     * @param amount
     */
    public void makeBet(int amount) {
        money = this.money - amount;
        currentBet = currentBet + amount;
    }

    /**
     * Fold.
     */
    public void fold() {
        fold = true;
    }

    /**
     * Return the two cards own by the player.
     *
     * @return the two cards own by the player
     */
    public List<Card> getCards() {
        return cards.getCards();
    }

    /**
     * Return the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the player's cards.
     *
     * @return the player's cards
     */
    PlayerCards getPlayerCards() {
        return cards;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return Objects.equals(this.name, other.getName());
    }

    /**
     * Set the player's hand. The 2 player's cards and the 3 cards from the
     * board
     *
     * @param hand the player's hand
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Compare a hand to the player's hand.
     *
     * @param other a hand to compare
     * @return -1 if the other's hand is better than this one, 0 if the two hand
     * is equivalent and 1 in the other cases
     */
    public int compareHand(Player other) {
        if (hand == null) {
            return -1;
        }
        if (other.getHand() == null) {
            return 1;
        }
        return this.hand.compareTo(other.getHand());
    }

    /**
     * Add money to the player's stock.
     *
     * @param amount the amount to add
     */
    void addMoney(int amount) {
        this.money += amount;
    }

    /**
     * Return the player's hand. The 2 player's cards and the 3 cards from the
     * board
     *
     * @return the player's hand
     */
    Hand getHand() {
        return hand;
    }

    /**
     * The current player bets all his chips.
     *
     */
    void allIn() {
        currentBet = currentBet + money;
        money = 0;
    }

    /**
     * Add money to the expected profit.
     *
     * @param amount the amount to add
     */
    void addprofit(int amount) {
        currentProfit += amount;
    }

    /**
     * Return the amount in the current bet.
     *
     * @return the amount in the current bet
     */
    int getCurrentBet() {
        return currentBet;
    }

    /**
     * Return the expected profit.
     *
     * @return the expected profit
     */
    public int getCurrentProfit() {
        return currentProfit;
    }

    /**
     * Return the combination associates with the hand.
     *
     * @return the combination associates with the hand
     */
    public Category getCategory() {
        return (hand == null) ? null : hand.getCategory();
    }

    /**
     * Withdraw money from the current bet.
     *
     * @param partBet the amount to withdraw
     */
    void withdrawBet(int partBet) {
        this.currentBet -= partBet;
    }

    /**
     * Return the 5 cards of the hand.
     *
     * @return the 5 cards of the hand
     */
    public List<Card> getHandCards() {
        return  (hand == null) ? null : hand.getCards();
    }

    /**
     * Check if the player has fold.
     *
     * @return if the player has fold
     */
    public boolean isFold() {
        return fold;
    }

}

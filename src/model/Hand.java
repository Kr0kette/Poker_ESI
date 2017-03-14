package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.cards.Card;

/**
 * The 5 cards chosen by a player at the end of the game. A hand is composed by
 * 2 cards from the PlayerCards and 3 cards from the board. The HandFactory can
 * build a Hand : the factory send a Category and a Proposal.
 *
 * @author esiProfs
 */
public class Hand implements Comparable<Hand> {

    private final List<Card> selected;

    private final List<Card> rest;

    private final Category category;

    private final int totalValue;

    /**
     * The HandFactory send a Category and a Proposal to build this Hand. The
     * Hand is split in two list of cards :
     * <ul>
     * <li>List<Card> selected : the cards which form the Category</li>
     * <li>List<Card> rest : the cards not mandatory to build the Category</li>
     * </ul>
     *
     * @param category the category of this combianisons of selected cards
     * @param proposal : the proposal generate by the HandFactory
     */
    Hand(Category category, Proposal proposal) {
        this.category = category;
        this.totalValue = proposal.getTotalValue();
        this.selected = proposal.getSelectedColor();
        this.rest = new ArrayList<>(proposal.getCards());
        this.rest.removeAll(selected);
        Collections.sort(this.selected);
        Collections.sort(this.rest);
    }

    /**
     * Return the order of a combinaison. Usefull for hands comparison
     *
     * @return the order of a combinaison
     */
    private int getOrder() {
        return this.category.getOrdrer();
    }

    @Override
    public int compareTo(Hand other) {
        int result = this.getOrder() - other.getOrder();

        if (result >= 1) {
            return 1;
        }

        if (result <= -1) {
            return -1;
        }

        result = this.totalValue - other.getTotalValue();

        if (result >= 1) {
            return 1;
        }

        if (result <= -1) {
            return -1;
        }

        return 0;
    }

    /**
     * Return the sum of the 5 cards value.
     *
     * @return the sum of the 5 cards value
     */
    public int getTotalValue() {
        return totalValue;
    }

    /**
     * Return the combination associates with the 5 cards.
     *
     * @return the combination associates with the 5 cards
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Return a list of the 5 cards. The cards are not copied.
     *
     * @return a list of the 5 cards
     */
    public List<Card> getCards() {
        List<Card> all = new ArrayList<>();
        for (Card card : selected) {
            all.add(card);
        }
        for (Card card : rest) {
            all.add(card);
        }
        return all;
    }

}

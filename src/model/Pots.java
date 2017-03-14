package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sum of money that players wager during a single hand. The pot can be split
 * into several pot if a player is all in
 *
 * @author esiProfs
 */
class Pots {

    private Pot current;
    private final List<Pot> pots;

    /**
     * Sum of money that players wager during a single hand. The pot may contain
     * some sub-pots if a player is all in.
     */
    Pots() {
        pots = new ArrayList<>();
        current = new Pot();
    }

    /**
     * Add a player that can win the pot.
     */
    void addMember(Player member) {
        if (!member.isFold()) {
            current.addMember(member);
        }
    }

    /**
     * Add money to the pot
     *
     * @param amount the amount to add
     */
    void addMoney(int amount) {
        current.add(amount);
    }

    /**
     * Return the sum of the pot, current bets include.
     *
     * @return the sum of the pot, current bets include.
     */
    int getTotal() {
        int total = (current == null) ? 0 : current.getTotal();
        for (Pot pot : pots) {
            total += pot.getTotal();
        }
        return total;
    }

    /**
     * Split the pot between the winners.
     */
    void split(PlayerIterator iterator) throws GameException {
        List<Player> winners;
        for (Pot pot : pots) {
            PlayerIterator it = new PlayerIterator(iterator);
            winners = pot.findWinners(it);
            pot.split(winners);
        }
    }

    /**
     * At the end of a betting round, the total amount ofthe round is add to the
     * pot. If a player is all in a sub-pot is create.If the list of members
     * changes a sub-pot is create
     */
    void create(int total, List<Player> members) {
        for (Player member : members) {
            addMember(member);
        }
        addMoney(total);
        //si meme membre que precedent add total au pots.get(pots.size() -1)
        if (pots.isEmpty()) {
            pots.add(current);
            current = new Pot();
        } else if (pots.get(pots.size() - 1).sameMembers(current)) {
            pots.get(pots.size() - 1).add(total);
            current = new Pot();
        } else {
            pots.add(current);
            current = new Pot();
        }
    }

}

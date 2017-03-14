package model;

import java.util.ArrayList;
import java.util.List;

/**
 * If a player is all in during the match, the pot is split in some sub-Pot.
 *
 * @author esiProfs
 */
class Pot {

    private int total;
    private final List<Player> members;

    /**
     * A sub-pot has no money and no members for start.
     */
    Pot() {
        total = 0;
        members = new ArrayList<>();
    }

    /**
     * Add money to the pot
     *
     * @param amount the amount to add
     */
    void add(int amount) {
        total += amount;
    }

    /**
     * Return the sum of the sub-pot.
     *
     * @return the sum of the sub-pot.
     */
    int getTotal() {
        return total;
    }

    /**
     * Check if the player is a member of the sub-pot.
     *
     * @return if the player is a member of the sub-pot
     */
    boolean contains(Player player) {
        return members.contains(player);
    }

    /**
     * Add a player that can win the sub-pot.
     */
    void addMember(Player member) {
        members.add(member);
    }

    /**
     * Return all the winners of this sub-pot.
     *
     * @param iterator iterator of players
     * @return all the winners of this sub-pot
     */
    List<Player> findWinners(PlayerIterator iterator) {
        List<Player> winners = new ArrayList<>();
        Player currentPlayer;
        while (iterator.hasNext()) {
            currentPlayer = iterator.next();
            if (winners.isEmpty()) {
                winners.add(currentPlayer);
            } else {
                switch (currentPlayer.compareHand(winners.get(0))) {
                    case 1:
                        winners.clear();
                        winners.add(currentPlayer);
                        break;
                    case 0:
                        winners.add(currentPlayer);
                        break;
                }
            }
        }
        return winners;
    }

    /**
     * Split the sub-pot into all the winners.
     *
     * @param winners list of winners
     */
    void split(List<Player> winners) {
        for (Player winner : winners) {
            int amount = total / winners.size();
            winner.addprofit(amount);
        }
        if (!winners.isEmpty()) {
            winners.get(0).addprofit(total % winners.size());
        }
    }

    /**
     * Check if two sub-pot have the same members.
     *
     * @param other a second sub-pot
     * @return if two sub-pot have the same members
     */
    boolean sameMembers(Pot other) {
        List<Player> otherMembers = other.members;
        //null checking
        if (members == null && otherMembers == null) {
            return true;
        }
        if ((members == null && otherMembers != null) || (members != null && otherMembers == null)) {
            return false;
        }

        if (members.size() != otherMembers.size()) {
            return false;
        }
        for (Player myItem : members) {
            if (!otherMembers.contains(myItem)) {
                return false;
            }
        }

        return true;
    }

}

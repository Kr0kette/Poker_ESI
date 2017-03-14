package model;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author esiProfs
 */
public class BetComparator implements Comparator<Player>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(Player player1, Player player2) {
        int result = player1.getCurrentBet() - player2.getCurrentBet();

        if (result >= 1) {
            return 1;
        }

        if (result <= -1) {
            return -1;
        }
        return 0;
    }

}

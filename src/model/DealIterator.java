package model;

/**
 *
 * @author esiProfs
 */
public class DealIterator extends PlayerIterator {

    /**
     * After the blind,2 cards are dealt to each player.
     *
     * @param other precedent iterator
     * @throws GameException if no button is given
     */
    public DealIterator(PlayerIterator other) throws GameException {
        super(other);
        this.startIndex = (this.buttonIndex + 2) % players.size();
    }

}

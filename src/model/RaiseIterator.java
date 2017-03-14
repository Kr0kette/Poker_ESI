package model;

/**
 *
 * @author esiProfs
 */
public class RaiseIterator extends PlayerIterator {

    /**
     * Needed iterator when a plyer make a raise. When the current player
     * raises, all other players still in the pot must either call the full
     * amount of the bet or raise if they wish remain in.
     *
     * @param other precedent iterator
     * @throws GameException if no button is given
     */
    public RaiseIterator(PlayerIterator other) throws GameException {
        super(other);
        this.startIndex = (other.current + other.startIndex) % players.size();
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = current + 1 < players.size();
        return hasNext;
    }
}

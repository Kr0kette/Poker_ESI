package model;

/**
 *
 * @author esiProfs
 */
public class BetIterator extends PlayerIterator {

    /**
     * Iterator usefull for all the bet's state (Blind, Preflop,Flop, Turn and
     * River).
     * At each bet's round, a new Iterator is instanciante on the precedent iterator basis.
     * The  stating index is ...
     * @param other precedent iterator
     * @throws GameException if no button is given
     */
    public BetIterator(PlayerIterator other) throws GameException {
        super(other);
        this.startIndex = (other.current + other.startIndex) % players.size();
    }

}

package esi.atl.g39871.poker.exception;

/**
 * Exception throwed by dto's manipulations.
 *
 * @author g39871
 */
public class PokerModelException extends PokerException{

    /**
     * Creates a new instance of <code>PokerModelException</code> without
     * detail message.
     */
    public PokerModelException() {
    }

    /**
     * Constructs an instance of <code>PokerModelException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PokerModelException(String msg) {
        super(msg);
    }
}

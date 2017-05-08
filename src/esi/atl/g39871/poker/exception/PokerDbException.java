package esi.atl.g39871.poker.exception;

/**
 * Throwed by database's access
 *
 * @author g39871
 */
public class PokerDbException extends PokerException {

    /**
     * Creates a new instance of <code>PokerDbException</code> without detail message.
     */
    public PokerDbException() {
    }

    /**
     * Constructs an instance of <code>PokerDbException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public PokerDbException(String msg) {
        super(msg);
    }
}

package esi.atl.g39871.poker.exception;

/**
 * Exception  throwed by a Facade's methods invocation.
 * @author g39871
 */
class PokerException extends Exception{
    
     /**
     * Creates a new instance of <code>PokerException</code> without detail message.
     */
    public PokerException() {
    }


    /**
     * Constructs an instance of <code>PokerException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public PokerException(String msg) {
        super(msg);
    }
}

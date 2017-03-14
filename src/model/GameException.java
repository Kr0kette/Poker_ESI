package model;

/**
 * Indicates conditions that a reasonable game might want to catch.
 *
 * @author esiProfs
 */
public class GameException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>GameException</code> without detail
     * message.
     */
    public GameException() {
    }

    /**
     * Constructs an instance of <code>GameException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GameException(String msg) {
        super(msg);
    }
}

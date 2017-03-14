package model;

/**
 * List of all relevant combinaisons.
 *
 * @author esiProfs
 */
public enum Category {

    /**
     *
     */
    ONE_CARD(1),
    /**
     *
     */
    PAIR(2),
    /**
     *
     */
    DOUBLE_PAIR(3),
    /**
     *
     */
    THREE_OF_A_KIND(4),
    /**
     *
     */
    STRAIGHT(5),
    /**
     *
     */
    FLUSH(6),
    /**
     *
     */
    FULL(7),
    /**
     *
     */
    FOUR_OF_A_KIND(8),
    /**
     *
     */
    STRAIGHT_FLUSH(9);

    private final int ordrer;

    private Category(int ordrer) {
        this.ordrer = ordrer;
    }

    /**
     * Return the order of a combinaison. Usefull for hands comparison
     *
     * @return the order of a combinaison
     */
    int getOrdrer() {
        return ordrer;
    }
}

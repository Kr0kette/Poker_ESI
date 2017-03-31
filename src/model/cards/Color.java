package model.cards;

public enum Color {

    CLUB("Clubs", 3),
    DIAMOND("Diamonds", 2),
    HEART("Hearts", 1),
    SPADE("Spades", 4);


    private final String name;


    private final int orderValue;

    Color(String name, int orderValue) {
        this.name = name;
        this.orderValue = orderValue;
    }

    /**
     * Returns the color's name.
     *
     * @return the color's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns' the color's order value.
     *
     * @return the color's order value
     */
    public int getOrderValue() {
        return orderValue;
    }

}

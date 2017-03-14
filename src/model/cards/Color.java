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


    public String getName() {
        return name;
    }


    public int getOrderValue() {
        return orderValue;
    }

}

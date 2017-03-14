package model.cards;


public enum Value {

    ACE("Ace", 14),

    TWO("Two", 2),

    THREE("Three", 3),
    
    FOUR("Four", 4),
    
    FIVE("Five", 5),
   
    SIX("Six", 6),
    
    SEVEN("Seven", 7),
    
    EIGHT("Eight", 8),
   
    NINE("Nine", 9),
    
    TEN("Ten", 10),

    JACK("Jack", 11),
    
    QUEEN("Queen", 12),
    
    KING("King", 13);
    private final String name;
    private final int orderValue;

    
    Value(String name, int orderValue) {
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

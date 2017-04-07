package esi.atl.g39871.poker.model.cards;

public enum Value {

  ACE("Ace", 14), TWO("Two", 2), THREE("Three", 3), FOUR("Four", 4), FIVE("Five", 5), SIX("Six",
      6), SEVEN("Seven", 7), EIGHT("Eight", 8), NINE("Nine",
          9), TEN("Ten", 10), JACK("Jack", 11), QUEEN("Queen", 12), KING("King", 13);

  private final String name;

  private final int orderValue;

  Value(String name, int orderValue) {
    this.name = name;
    this.orderValue = orderValue;
  }

  /**
   * Returns the value's name.
   *
   * @return the value's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the value's order value.
   *
   * @return the value's order value
   */
  public int getOrderValue() {
    return orderValue;
  }
}

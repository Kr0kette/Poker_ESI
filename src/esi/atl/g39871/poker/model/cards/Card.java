package esi.atl.g39871.poker.model.cards;

/**
 * A classic playing card.
 * 
 */
public class Card implements Comparable<Card> {

  private final Color color;

  private final Value value;

  private boolean hide;

  /**
   * Create a new instance of a Card whith a color and a value.
   *
   * @param color the card's color.
   * @param value the card's value.
   */
  public Card(Color color, Value value) {
    this.color = color;
    this.value = value;
    this.hide = true;
  }

  Card(Card copy) {
    this.color = copy.getColor();
    this.value = copy.getValue();
  }

  /**
   * Returns the card's color.
   *
   * @return the card's color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Returns the card's value.
   *
   * @return the card's value
   */
  public Value getValue() {
    return value;
  }

  /**
   * Flip the card (from hidden to visible)
   */
  public void show() {
    this.hide = false;
  }

  /**
   * Checks if the card is the next one of an other card. It will check both the card's value and
   * the card's color
   *
   * @param other the other card
   * @return true if the card is the card is the next one of another card, false otherwise
   */
  public boolean isNext(Card other) {
    return this.getColor().equals(other.getColor()) && isNextValue(other);
  }

  /**
   * Checks if the card's value is the next value from an other given card
   * <p>
   * For example: the card has the value 'TWO' the other card has the value 'ACE'
   * <p>
   * the following call will return true: card.isNextValue(theOtherCard)
   *
   * @param other the other card
   * @return true if the card's value is just the next value from the other card, false otherwise
   */
  public boolean isNextValue(Card other) {
    return ((this.getValue().equals(Value.ACE) && other.getValue().equals(Value.KING))
        || (this.getValue().equals(Value.TWO) && other.getValue().equals(Value.ACE))
        || (this.getValue().getOrderValue() - other.getValue().getOrderValue() == 1));
  }

  /**
   * Returns the card's color's order value.
   *
   * @return the card's color's order value
   */
  public int getColorOrder() {
    return this.getColor().getOrderValue();
  }

  /**
   * Returns the card's value's order value.
   *
   * @return the card's value's order value
   */
  public int getValueOrder() {
    return this.getValue().getOrderValue();
  }

  @Override
  public int compareTo(Card other) {
    int resultColor = this.getColorOrder() - other.getColorOrder();
    int resultValue = this.getValueOrder() - other.getValueOrder();

    if (resultColor >= 1) {
      return 1;
    }

    if (resultColor <= -1) {
      return -1;
    }

    if (resultColor == 0 && resultValue >= 1) {
      return 1;
    }

    if (resultColor == 0 && resultValue <= -1) {
      return -1;
    }

    return 0;
  }

  /**
   * Checks if the color in argument is the same as the card's one.
   *
   * @param color the color to compare with
   * @return true if the color in argument is the same as the card's color, false otherwise
   */
  public boolean is(Color color) {
    return this.color.equals(color);
  }

  /**
   * Checks if the value in argument is the same as the card's one.
   *
   * @param value the value to compare with
   * @return true if the value in argument is the same as the card's value, false otherwise
   */
  public boolean is(Value value) {
    return this.value.equals(value);
  }

  /**
   * Checks if the card's value is the same as the other card's one
   *
   * @param other the other card
   * @return true if the card's value is the same as the other card's value , false otherwise
   */
  public boolean sameValue(Card other) {
    return this.value.equals(other.getValue());
  }

  @Override
  public int hashCode() {
    int hash = 5;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Card other = (Card) obj;
    if (this.color != other.getColor()) {
      return false;
    }
    return this.value == other.getValue();
  }

  /**
   *
   * @return true if a card is hidden, false otherwise
   */
  public boolean isHidden() {
    return hide;
  }

}

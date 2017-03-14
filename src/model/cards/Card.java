package model.cards;

/**
 * A classic playing card.
 *
 */
public class Card implements Comparable<Card> {

    private final Color color;
    private final Value value;
    private boolean hide;


    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
        this.hide = true;
    }

    Card(Card copy) {
        this.color = copy.getColor();
        this.value = copy.getValue();
    }



    public Color getColor() {
        return color;
    }


    public Value getValue() {
        return value;
    }


    public void show() {
        this.hide = false;
    }


    public boolean isNext(Card other) {
        return this.getColor().equals(other.getColor())
                && isNextValue(other);
    }


    public boolean isNextValue(Card other) {
        return ((this.getValue().equals(Value.ACE) && other.getValue().equals(Value.KING))
                || (this.getValue().equals(Value.TWO) && other.getValue().equals(Value.ACE))
                || (this.getValue().getOrderValue() - other.getValue().getOrderValue() == 1));
    }


    public int getColorOrder() {
        return this.getColor().getOrderValue();
    }


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


    public boolean is(Color color) {
        return this.color.equals(color);
    }

    public boolean is(Value value) {
        return this.value.equals(value);
    }


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
     * @return
     */
    public boolean isHidden() {
        return hide;
    }

}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.cards.Card;
import model.cards.Color;
import model.cards.Value;
import model.cards.ValueComparator;

/**
 * To build the best player's hand, the factory needs to build all the ways to
 * chose a 5 cards in 7. Each way forms a proposal
 *
 */
class Proposal {

    private final List<Card> cards;
    private final Map<Value, Long> mapValue;
    private final Map<Color, Long> mapColor;
    private final boolean areAllConsecutives;

    private int totalValue;

    /**
     * A proposal is build based on 2 cards from the player's cards and 3 cards
     * from the board. During this build the sum of the card's value, the amount
     * of each color and the amount of each Value are computed.
     *
     */
    Proposal(List<Card> cards1, List<Card> cards2) {
        this.cards = new ArrayList<>();
        totalValue = 0;
        for (Card card : cards1) {
            this.cards.add(card);
            totalValue += card.getValueOrder();
        }
        for (Card card : cards2) {
            this.cards.add(card);
            totalValue += card.getValueOrder();
        }
        Collections.sort(cards, new ValueComparator());
        this.mapValue = getMapValue();
        this.mapColor = getMapColor();
        this.areAllConsecutives = areAllConsecutives();
    }

    /**
     * Return an unmodifiableList of cards.
     *
     * @return an unmodifiableList of cards
     */
    List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private Map<Value, Long> getMapValue() {
        Map<Value, Long> map = cards.stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        map.values().removeAll(Collections.singleton((long) 1));
        return map;
    }

    private Map<Color, Long> getMapColor() {
        Map<Color, Long> map = cards.stream().collect(Collectors.groupingBy(Card::getColor, Collectors.counting()));
        map.values().removeAll(Collections.singleton((long) 1));
        return map;
    }

    private boolean areAllConsecutives() {
        Iterator<Card> iterator = cards.iterator();
        boolean consecutive = iterator.hasNext();
        Card precedent = consecutive ? iterator.next() : null;
        while (consecutive && iterator.hasNext()) {
            Card current = iterator.next();
            consecutive = precedent.isNextValue(current);
            precedent = current;
        }
        return consecutive;
    }
    
    List<Card> getSelectedValue() {
        List<Card> selected = new ArrayList<>();
        for (Value value : mapValue.keySet()) {
            for (Card card : cards) {
                if (card.is(value)) {
                    selected.add(card);
                }
            }
        }
        return selected;
    }

    List<Card> getSelectedColor() {
        List<Card> selected = new ArrayList<>();
        boolean find = false;
        for (Color color : mapColor.keySet()) {
            Iterator<Card> iterator = cards.iterator();
            while (!find && iterator.hasNext()) {
                Card card = iterator.next();
                find = card.is(color);
                if (find) {
                    selected.add(card);
                }
            }
        }
        return selected;
    }

    boolean isFourOfAKind() {
        return mapValue.values().contains((long) 4) && mapValue.size() == 1;
    }

    boolean isThreeOfAKind() {
        return mapValue.values().contains((long) 3) && mapValue.size() == 1;
    }

    boolean isPair() {
        return mapValue.values().contains((long) 2) && mapValue.size() == 1;
    }

    boolean isFull() {
        return mapValue.values().contains((long) 3) && mapValue.values().contains((long) 2) && mapValue.size() == 2;
    }

    boolean isDoublePair() {
        Predicate<Long> predicate = nb -> nb == (long) 2;
        return mapValue.size() == 2 && mapValue.values().stream().allMatch(predicate);
    }

    boolean isStraigthFlush() {
        return areAllConsecutives && mapColor.values().contains((long) 5);
    }

    boolean isFlush() {
        return !areAllConsecutives && mapColor.values().contains((long) 5);
    }

    boolean isStraigth() {
        return areAllConsecutives && !mapColor.values().contains((long) 5);
    }

    boolean isOneCard() {
        return mapValue.isEmpty() && mapValue.isEmpty();
    }

    int getTotalValue() {
        return totalValue;
    }

}

package esi.atl.g39871.poker.model.cards;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A card's value's comparator 
 * 
 * @author g39871
 */
public class ValueComparator implements Comparator<Card>, Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public int compare(Card o1, Card o2) {
    int resultValue = o1.getValueOrder() - o2.getValueOrder();

    if (resultValue >= 1) {
      return 1;
    }

    if (resultValue <= -1) {
      return -1;
    }
    return 0;
  }

}

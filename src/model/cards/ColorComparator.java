package model.cards;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author esiProfs
 */
public class ColorComparator implements Comparator<Card>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(Card o1, Card o2) {
        int resultColor = o1.getColorOrder() - o2.getColorOrder();

        if (resultColor == 0) {
            ValueComparator comparator = new ValueComparator();
            return comparator.compare(o1, o2);
        }

        if (resultColor >= 1) {
            return 1;
        }

        if (resultColor <= -1) {
            return -1;
        }
        return 0;
    }

}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.cards.Card;

/**
 * The HandFactory can build a Hand : The 5 cards chosen by a player at the end
 * of the game. A hand is composed by 2 cards from the PlayerCards and 3 cards
 * from the board. To build the best hand, this factory build some proposal
 * first.
 *
 * @author esiProfs
 */
public class HandFactory {

    final static int BOARD_NB = 3;
    private final List<Proposal> proposals;

    /**
     * To build the best player's hand, the factory needs the 2 cards from the
     * players cards and the 5 cards from the board. The factory need to select
     * 3 cards from the board to build the best hand. There is 10 ways to chose
     * a hand. Each way forms a proposal
     *
     *
     * @param handCards the 2 cards from the players cards
     * @param board the 5 cards from the board
     */
    public HandFactory(List<Card> handCards, List<Card> board) {
        proposals = new ArrayList<>();
        List<Card> boardPick = null;
        int nb_cards = board.size();
        for (int indexFirstCard = 0; indexFirstCard < nb_cards - 2; indexFirstCard++) {
            for (int indexSecondCard = indexFirstCard + 1; indexSecondCard < nb_cards; indexSecondCard++) {
                for (int indexThirdCard = indexSecondCard + 1; indexThirdCard < nb_cards; indexThirdCard++) {
                    boardPick = new ArrayList<>();
                    boardPick.add(board.get(indexFirstCard));
                    boardPick.add(board.get(indexSecondCard));
                    boardPick.add(board.get(indexThirdCard));
                    proposals.add(new Proposal(handCards, boardPick));
                }
            }
        }
    }

    /**
     * Build the best hand from the proposals list.
     *
     * @return the best hand from the proposals list
     */
    public Hand build() {
        List<Hand> hands = new ArrayList<>();
        for (Proposal current : proposals) {
            if (current.isFourOfAKind()) {
                hands.add(new Hand(Category.FOUR_OF_A_KIND, current));
            }
            if (current.isThreeOfAKind()) {
                hands.add(new Hand(Category.THREE_OF_A_KIND, current));
            }
            if (current.isPair()) {
                hands.add(new Hand(Category.PAIR, current));
            }

            if (current.isFull()) {
                hands.add(new Hand(Category.FULL, current));
            }
            if (current.isDoublePair()) {
                hands.add(new Hand(Category.DOUBLE_PAIR, current));
            }

            if (current.isStraigthFlush()) {
                hands.add(new Hand(Category.STRAIGHT_FLUSH, current));
            }

            if (current.isFlush()) {
                hands.add(new Hand(Category.FLUSH, current));
            }

            if (current.isStraigth()) {
                hands.add(new Hand(Category.STRAIGHT, current));
            }

            if (current.isOneCard()) {
                hands.add(new Hand(Category.ONE_CARD, current));
            }
        }
        return Collections.max(hands);
    }
}

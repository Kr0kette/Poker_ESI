package model;

import java.util.ArrayList;

/**
 * The third bet's round, the flop allow to all players to call, fold, raise or allIn.
 * All players owns two cards and the board shows three cards. 
 * @author esiProfs
 */
public class Flop extends AbstrState {

    /**
     * The third state of a poker match is the Flop.
     * @param match ongoing match
     */
    Flop(Match match) {
        this.match = match;
        availableBet = new ArrayList<>();
        availableBet.add(Bet.CALL);
        availableBet.add(Bet.FOLD);
        availableBet.add(Bet.RAISE);
    }

    @Override
    public void nextState() throws GameException {
        if (match.onlyOne()) {
            match.splitPot();
            match.end();
        } else if (match.hasNext()) {
            match.nextPlayer();
        } else {
            this.addPot();
            match.setState(match.getTurn());
            match.setBetIterator();
            match.showBoard();
            match.dealBoard(1);
            match.nextPlayer();
        }
    }

    @Override
    public void smallBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        throw new GameException("smallBlind Impossible");
    }

    @Override
    public void bigBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        throw new GameException("bigBlind Impossible");
    }

}

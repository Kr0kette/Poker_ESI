package model;

import java.util.ArrayList;

/**
 * The fifth and last bet's round, the river allow to all players to call, fold,
 * raise or allIn. All players owns two cards and the board shows five cards.
 *
 * @author esiProfs
 */
class River extends AbstrState {

    /**
     * The fifth and last state of a poker match is the river.
     *
     * @param match ongoing match
     */
    River(Match match) {
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
            match.showDown();
            match.splitPot();
            match.end();
        }
    }

    @Override
    public void smallBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        throw new GameException("smallBlind Impossible");
    }

    /**
     *
     * @param currentPlayer
     * @param amount
     * @param minimum
     * @param pot
     * @throws model.GameException
     */
    @Override
    public void bigBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        throw new GameException("bigBlind Impossible");
    }
}

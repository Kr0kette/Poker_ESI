package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The first bet's round, the blind contains two part : the small and the big blind.
 * No card has been dealt yet.
 * @author esiProfs
 */
class Blind extends AbstrState {

    /**
     * The first state of a poker match is the blind.
     * @param match ongoing match
     */
    Blind(Match match) {
        this.match = match;
        availableBet = new ArrayList<>();
        availableBet.add(Bet.SMALLBLIND);
        currentBet = Bet.SMALLBLIND;
    }

    @Override
    public void nextState() throws GameException {
        this.addPot();
        match.setState(match.getPreFlop());
        match.setDealIterator();
        match.dealPlayer(2);
        match.dealBoard(3);
        match.setBetIterator();
        match.nextPlayer();
    }

    @Override
    public void call(Player currentPlayer, int minimum, Pots pot) throws GameException {
        throw new GameException("Call Impossible");
    }

    @Override
    public void fold(Player currentPlayer) throws GameException {
        throw new GameException("Fold Impossible");
    }

    @Override
    public void raise(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        throw new GameException("Raise Impossible");
    }

    @Override
    public void allIn(Player currentPlayer, int minimum, Pots pot) throws GameException {
        throw new GameException("All In Impossible");
    }
    
    
    @Override
    public List<Bet> getAvailable() {
        return availableBet;
    }

    @Override
    public void addPot() {
        List<Player> players = match.getSortPlayers();
        List<Player> members = new ArrayList<>();
        int totalPot = 0;
        for (Player current : players) {
            members.add(current);
            int currentMoney = current.getCurrentBet();
            current.withdrawBet(currentMoney);
            totalPot += currentMoney;
        }
        match.createPot(totalPot, members);
    }

}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author esiProfs
 */
abstract class AbstrState implements State {

    /**
     *
     */
    protected Bet currentBet;

    /**
     *
     */
    protected List<Bet> availableBet;

    /**
     *
     */
    protected Match match;

    @Override
    public void call(Player currentPlayer, int minimum, Pots pot) throws GameException {
        if (currentPlayer.getMoney() < minimum) {
            throw new GameException("Call impossible " + currentPlayer.getMoney() + " " + minimum);
        }
        currentPlayer.makeBet(minimum);
        nextState();
    }

    @Override
    public void fold(Player currentPlayer) throws GameException {
        currentPlayer.fold();
        nextState();
    }

    @Override
    public void raise(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        if (amount == 0) {
            throw new GameException("Raise doit être strictement suérieure à 0 " + amount + " " + minimum);
        }
        if (currentPlayer.getMoney() < amount + minimum) {
            throw new GameException("Raise impossible " + currentPlayer.getMoney() + " " + amount + " " + minimum);
        }
        currentPlayer.makeBet(amount + minimum);
        match.setRaiseIterator();
        nextState();
    }

    @Override
    public void smallBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        if (!(currentBet.equals(Bet.SMALLBLIND))) {
            throw new GameException("smallBlind Impossible - statut incoherent " + currentBet);
        }
        if (amount != Match.SMALLBLIND) {
            throw new GameException("Small Blind doit etre de " + Match.SMALLBLIND + " different de " + amount);
        }
        currentPlayer.makeBet(amount);

        match.setMinimum(2 * amount);
        match.nextPlayer();
        currentBet = Bet.BIGBLIND;
        availableBet.clear();
        availableBet.add(Bet.BIGBLIND);
    }

    @Override
    public void bigBlind(Player currentPlayer, int minimum, int amount, Pots pot) throws GameException {
        if (!(currentBet.equals(Bet.BIGBLIND))) {
            throw new GameException("bigBlind Impossible - statut incoherent " + currentBet);
        }
        if (amount != minimum) {
            throw new GameException("Big Blind impossible " + amount + " " + minimum);
        }
        currentPlayer.makeBet(amount);
        nextState();
    }

    @Override
    public void allIn(Player currentPlayer, int minimum, Pots pot) throws GameException {
        if (currentPlayer.getMoney() >= minimum) {
            throw new GameException("Il vous reste suffisament d'argent pour parier " + currentPlayer.getMoney() + " " + minimum);
        }
        currentPlayer.allIn();
        nextState();
    }

    @Override
    public List<Bet> getAvailable() {
        return Collections.unmodifiableList(availableBet);
    }

    @Override
    public void addPot() {
        List<Player> players = match.getSortPlayers();
        List<Player> members;
        int start = 1;
        int totalPot;
        for (Player current : players) {
            totalPot = 0;
            members = new ArrayList<>();
            members.add(current);
            int currentMoney = current.getCurrentBet();
            if (currentMoney != 0) {
                totalPot += currentMoney;
                for (int index = start; index < players.size(); index++) {
                    Player other = players.get(index);
                    other.withdrawBet(currentMoney);
                    totalPot += currentMoney;
                    members.add(other);
                }
                current.withdrawBet(currentMoney);
                match.createPot(totalPot, members);
            }
            start++;
        }
    }

}

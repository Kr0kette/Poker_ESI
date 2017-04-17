package esi.atl.g39871.poker.model;

import java.util.ArrayList;

/**
 * The fourth bet's round, the turn allow to all players to call, fold, raise or allIn. All players
 * owns two cards and the board shows four cards.
 *
 * @author g39871
 */
class Turn extends AbstrState {

  /**
   * The fourth state of a poker match is the preflop.
   *
   * 
   * @param match ongoing match
   */
  Turn(Match match) {
    this.match = match;
    availableBet = new ArrayList<>();
    availableBet.add(Bet.CHECK);
    availableBet.add(Bet.FOLD);
    availableBet.add(Bet.RAISE);
    availableBet.add(Bet.ALLIN);
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
      match.setBetIterator();
      match.showBoard();
      match.dealBoard(1);
      match.setState(match.getRiver());
      match.resetMinimum();
      match.nextPlayer();
    }
  }

  @Override
  public void smallBlind(Player currentPlayer, int minimum, int amount, Pots pot)
      throws GameException {
    throw new GameException("smallBlind Impossible");
  }

  @Override
  public void bigBlind(Player currentPlayer, int minimum, int amount, Pots pot)
      throws GameException {
    throw new GameException("bigBlind Impossible");
  }
}

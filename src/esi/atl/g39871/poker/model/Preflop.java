package esi.atl.g39871.poker.model;

import java.util.ArrayList;

/**
 * The second bet's round, the preflop allow to all players to call, fold, raise or allIn. All
 * players owns two cards and the board has no card.
 *
 * @author g39871
 */
class Preflop extends AbstrState {

  /**
   * The second state of a poker match is the preflop.
   *
   * @param match ongoing match
   */
  Preflop(Match match) {
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
      match.setState(match.getFlop());
      match.resetMinimum(); 
      match.setBetIterator();
      match.showBoard();
      match.dealBoard(3);
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

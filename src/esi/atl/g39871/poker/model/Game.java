package esi.atl.g39871.poker.model;

import static esi.atl.g39871.poker.model.Status.BLIND;
import static esi.atl.g39871.poker.model.Status.END_GAME;
import static esi.atl.g39871.poker.model.Status.END_MATCH;
import static esi.atl.g39871.poker.model.Status.FLOP;
import static esi.atl.g39871.poker.model.Status.INIT;
import static esi.atl.g39871.poker.model.Status.PREFLOP;
import static esi.atl.g39871.poker.model.Status.RIVER;
import static esi.atl.g39871.poker.model.Status.TURN;
import esi.atl.g39871.poker.model.cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * The main class of the poker game.
 *
 * @author g39871
 */
public class Game extends Observable implements Facade {

  private final static int NB_MIN = 4;

  private Match match;

  private final List<Player> players;

  private Status status;

  /**
   * Main class of the poker game. It instanciates the list of players and set the game's statut to
   * INIT
   */
  public Game() {
    players = new ArrayList<>();
    status = INIT;
  }

  @Override
  public void addPlayer(String name, int money) throws GameException {
    
    Player player = new Player(name, money);
    if (!players.contains(player)){
    players.add(player);
    }else{
      throw new GameException("It already exists a " + name +" in the game ");
  }
    notifyChange();
  }

  @Override
  public void allIn() throws GameException {
    match.allIn();
    updateSatus();
    notifyChange();
  }

  @Override
  public void bigBlind(int amount) throws GameException {
    match.bigBlind(amount);
    updateSatus();
    notifyChange();
  }

  @Override
  public void call() throws GameException {
    match.call();
    updateSatus();
    notifyChange();
  }

  @Override
  public void fold() throws GameException {
    match.fold();
    updateSatus();
    notifyChange();
  }

  @Override
  public List<Bet> getAvailable() {
    return match.getAvailable();
  }

  @Override
  public List<Card> getBoard() {
    return match.getBoard();
  }

  @Override
  public List<Card> getCards() {
    return match.getCards();
  }

  @Override
  public Player getCurrentPlayer() {
    return (Player) match.getCurrentPlayer();

  }

  @Override
  public int getMinimium() {
    return match.getMinimum();
  }

  @Override
  public List<Player> getPlayers() {
    return Collections.unmodifiableList(players);
  }

  @Override
  public int getPot() {
    return match.getPot();
  }

  @Override
  public int getSmallBlindValue() {
    return Match.SMALLBLIND;
  }

  @Override
  public Status getStatus() {
    return status;
  }

  @Override
  public void raise(int amount) throws GameException {
    match.raise(amount);
    updateSatus();
    notifyChange();
  }

  @Override
  public void check() throws GameException {
    match.check();
    updateSatus();
    notifyChange();
  }

  @Override
  public void smallBlind(int amount) throws GameException {
    match.smallBlind(amount);
    updateSatus();
    notifyChange();
  }

  @Override
  public void start() throws GameException {
    if (players.size() < NB_MIN) {
      throw new GameException("Il manque des joueurs. Minimum: " + NB_MIN);
    }
    startMatch();

  }

  @Override
  public void startMatch() throws GameException {
    if (match != null && !match.isOver()) {
      throw new GameException("Vous devez terminer le match en cours");
    }
    int indexNextButton = 0;
    Player player;
    for (int index = 0; index < players.size(); index++) {
      player = players.get(index);
      player.initializeMatch();
      if (player.hasButton()) {
        player.removeButton();
        indexNextButton = (index+ 1) % players.size();
      }
    }
    players.get(indexNextButton).giveButtton();

    match = new Match(players);
    updateSatus();
    notifyChange();
  }

  @Override
  public void stop() {
    status = END_GAME;
    notifyChange();
  }

  private void notifyChange() {
    setChanged();
    notifyObservers(this.status);
  }

  private void updateSatus() {
    if (status != END_GAME) {
      if (match == null) {
        status = INIT;
      } else if (match.isOver()) {
        status = END_MATCH;
      } else {
        State state = match.getState();
        if (state instanceof Blind) {
          status = BLIND;
        } else if (state instanceof Preflop) {
          status = PREFLOP;
        } else if (state instanceof Flop) {
          status = FLOP;
        } else if (state instanceof Turn) {
          status = TURN;
        } else if (state instanceof River) {
          status = RIVER;
        }
      }
    }
  }
}

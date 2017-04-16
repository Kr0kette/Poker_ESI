package esi.atl.g39871.poker.mvc;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import esi.atl.g39871.poker.view.PokerView;

public class PokerController {

  // TODO essayer de respecter https://sourcemaking.com/refactoring/smells
  Game model;

  PokerView view;

  public PokerController(Game model) { // TODO changer la déclaration par les interfaces facade par
    // ex ?
    this.model = model;

    view = new PokerView(this, model);
    model.addObserver(view);

    // TODO virer les addPlayer, c'est juste pour tester
    addPlayer("Kuroketto", Integer.toString(10000));
    addPlayer("Imo", Integer.toString(10000));
    addPlayer("ViniVidiVici", Integer.toString(10000));
    addPlayer("Tex", Integer.toString(10000));

  }

  /**
   * Add a player to the game, and create a visual for this player
   *
   * @param name the player's name
   * @param money the player's amount of money
   */
  public void addPlayer(String name, String money) {
    
    if (!money.equals("") && !name.equals("")) {
       
      model.addPlayer(name, Integer.parseInt(money));
      view.addPlayerInLayout(model.getPlayers().get(model.getPlayers().size() - 1));
    }
  }

  /**
   * Start de game.
   * <p>
   * Disable the start button.
   * <p>
   * Disable the button to add a player.
   * <p>
   * Enable the stop button.
   *
   */
  public void start() {
    try {
      model.start();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }

  }

  /**
   * Stop de game and set the stop button to invisible
   * 
   */
  public void stop() {
    model.stop();
    view.enableStartButton(true);
    view.enableAddPlayerButton(true);
  }

  /**
   * Returns the view's facade
   *
   * @return the view's facade
   */
  public PokerView getView() {
    return view;
  }

  /**
   * Discard one's hand and forfeit interest in the current pot.
   *
   */
  public void fold() {
    try {
      model.fold();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  /**
   * Match a call.
   */
  public void call() {
    try {
      model.call();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  /**
   * Match a check.
   */
  public void check() {
    try {
      model.check();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  /**
   * Match a raise
   */
  public void raise() {
    try {
      model.raise(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  /**
   * Match a smallBlind.
   */
  public void smallBlind() {
    try {
      model.smallBlind(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  /**
   * Match a bigBlind.
   */
  public void bigBlind() {
    try {
      model.bigBlind(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  /**
   * Bet all player's chips.
   * 
   */
  public void allIn() {
    try {
      model.allIn();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

}

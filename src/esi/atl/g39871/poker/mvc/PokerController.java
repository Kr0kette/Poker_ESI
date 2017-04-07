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
    model.addPlayer("Kuroketto", 10000);
    model.addPlayer("Imo", 5000);
    model.addPlayer("ViniVidiVici", 40000);
    model.addPlayer("Tex", 100);

  }

  /**
   * Add a player to the game, and create a visual for this player
   *
   * @param name the player's name
   * @param money the player's amount of money
   */
  public void addPlayer(String name, String money) { // TODO ne doit pas forcément être un int , si
    // ???
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
   * @throws GameException
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
   * <p>
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
   * @throws GameException if the match is in Blind State
   */
  public void fold() {
    try {
      // TODO GERER LES ERREURS

      model.fold();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  public void call() {
    try {
      model.call();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  public void raise() {
    try {
      model.raise(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  public void smallBlind() {
    try {
      model.smallBlind(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

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
   * @throws esi.atl.g39871.poker.model.GameException
   */
  public void allIn() {
    try {
      model.allIn();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

}

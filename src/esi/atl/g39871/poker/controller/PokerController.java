package esi.atl.g39871.poker.controller;

import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.model.FacadeDB;
import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.view.PokerView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller for a Poker Game.
 *
 * @author g39871
 */
public class PokerController implements ControllerInterface {

  Game model;

  PokerView view;

  int idGame;

  /**
   * Creates a new controller for a Poker Game A view for the game will be generated.
   *
   * @param model the model
   */
  public PokerController(Game model) {
    this.model = model;

    view = new PokerView(this, model);
    model.addObserver(view);

  }

  @Override
  public void addGains() {
    model.getPlayers().forEach(p -> {
      try {
        FacadeDB.updatePlayer(new PlayerDto(p.getName(), p.getCurrentProfit()));
       FacadeDB.addGameHistory(idGame, p.getName(), p.getCurrentProfit(),
            p.getCategory().toString());

      } catch (PokerModelException ex) {
        Logger.getLogger(PokerController.class.getName()).log(Level.SEVERE, null, ex);
      }

    });

  }

  @Override
  public void addPlayer() {

    String name = view.getNewPlayerName();
    String money = view.getNewPlayerMoney();

    if (!money.equals("") && !name.equals("")) {

      try {

        int id = FacadeDB.addPlayer(name, Integer.parseInt(money));
        model.addPlayer(name, FacadeDB.getPlayerById(id).getMoney());
        view.addPlayerInLayout(model.getPlayers().get(model.getPlayers().size() - 1));

      } catch (PokerModelException ex) {
        view.alert(ex.getMessage());
      } catch (GameException ex) {
        view.alert(ex.getMessage());
      }

    }
  }

  @Override
  public void allIn() {
    try {
      model.allIn();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public void bigBlind() {
    try {
      model.bigBlind(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public void call() {
    try {
      model.call();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public void check() {
    try {
      model.check();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public void fold() {
    try {
      model.fold();
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public PokerView getView() {
    return view;
  }

  @Override
  public void raise() {
    try {
      model.raise(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public void smallBlind() {
    try {
      model.smallBlind(view.getBetAmount());
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public void start() {

    idGame = FacadeDB.getNewIdGame();
    try {

      model.start();
      view.enableStartButton(false);
      view.enableStopButton(false);
      view.enableAddPlayerButton(false);
      view.enableNewPlayerNameField(false);
      view.enableNewPlayerMoneyField(false);
    } catch (GameException ex) {
      view.alert(ex.getMessage());
    }
  }

  @Override
  public void stop() {
    model.stop();

  }

}

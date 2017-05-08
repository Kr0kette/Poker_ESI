package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Bet;
import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.Player;
import esi.atl.g39871.poker.model.Status;
import esi.atl.g39871.poker.controller.PokerController;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *  A Poker's view
 * 
 * @author g39871
 */
public class PokerView extends BorderPane implements PokerViewInterface, Initializable, Observer {

  @FXML
  private Button addPlayerButton;

  @FXML
  private Button allInButton;

  @FXML
  private TextField betAmount;

  @FXML
  private Button bigBlindButton;

  @FXML
  private Button callButton;


  @FXML
  private VBox centerBox;
  @FXML
  private Button checkButton;

  private PokerController controller;

  @FXML
  private Button foldButton;

  @FXML
  private Label minBet;

  private Game model;

  @FXML
  private TextField newPlayerMoney;

  @FXML
  private TextField newPlayerName;

  private HashMap<Player, PlayerView> players;

  @FXML
  private HBox playersLayout;


  private PokerTableView pokerTable;

  @FXML
  private Button raiseButton;

  @FXML
  private Button smallBlindButton;

  @FXML
  private Label smallBlindValue;

  @FXML
  private Button startButton;

  @FXML
  private Button stopButton;


  /**
   * Creates a new Poker's view.
   * 
   * @param controller the mvc's controller
   * @param model the model
   */
  public PokerView(PokerController controller, Game model) {
    this.model = model;
    this.controller = controller;
    players = new HashMap<Player, PlayerView>();
    FXMLLoader pokerViewFXML = new FXMLLoader(getClass().getResource("PokerView.fxml"));

    pokerViewFXML.setRoot(this);
    pokerViewFXML.setController(this);

    try {
      pokerViewFXML.load();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void addPlayerInLayout(Player player) {
    PlayerView playerView = new PlayerView(player.getName(), Integer.toString(player.getMoney()));
    players.put(player, playerView);
    playersLayout.getChildren().add(playerView);
  }

  @Override
  public void alert(String message) {
    Alert alertMessage = new Alert(Alert.AlertType.ERROR);
    alertMessage.setTitle("Attention");
    alertMessage.setHeaderText(message);
    alertMessage.show();

  }

  @Override
  public void disableAllBetsButtons() {
    enableSmallBlindButton(false);
    enableBigBlindButton(false);
    enableAmountField(false);
    enableAllInButton(false);
    enableCallButton(false);
    enableFoldButton(false);
    enableRaiseButton(false);
    enableCheckButton(false);

  }



  @Override
  public void enableAddPlayerButton(boolean b) {
    addPlayerButton.setDisable(!b);
  }


  @Override
  public void enableAllInButton(boolean b) {
    allInButton.setDisable(!b);
  }


  @Override
  public void enableAmountField(boolean b) {
    betAmount.setDisable(!b);
  }


  @Override
  public void enableBigBlindButton(boolean b) {
    bigBlindButton.setDisable(!b);
  }


  @Override
  public void enableCallButton(boolean b) {
    callButton.setDisable(!b);
  }

  @Override
  public void enableCheckButton(boolean b) {
    checkButton.setDisable(!b);
  }


  @Override
  public void enableFoldButton(boolean b) {
    foldButton.setDisable(!b);
  }
  @Override
  public void enableNewPlayerMoneyField(boolean b) {
      newPlayerMoney.setDisable(!b);
  }

  @Override
  public void enableNewPlayerNameField(boolean b) {
    newPlayerName.setDisable(!b);
  }
  @Override
  public void enableRaiseButton(boolean b) {
      raiseButton.setDisable(!b);
  }


  @Override
  public void enableSmallBlindButton(boolean b) {
    smallBlindButton.setDisable(!b);
  }


  @Override
  public void enableStartButton(boolean b) {
    startButton.setDisable(!b);
  }

  @Override
  public void enableStatusButtons(List<Bet> availableBets) {
    enableAmountField(true);
    for (Bet availableBet : availableBets) {
      switch (availableBet) {
        case SMALLBLIND:
          enableSmallBlindButton(true);
          break;
        case BIGBLIND:
          enableBigBlindButton(true);
          break;
        case CALL:
          enableCallButton(true);
          break;
        case FOLD:
          enableFoldButton(true);
          break;
        case RAISE:
          enableRaiseButton(true);
          break;
        case CHECK:
          enableCheckButton(true);
          break;
        case ALLIN:
          enableAllInButton(true);
          break;
      }
    }

  }


  @Override
  public void enableStopButton(boolean b) {
    stopButton.setDisable(!b);
  }

  @Override
  public int getBetAmount() {
    return !betAmount.getText().isEmpty() ? Integer.parseInt(betAmount.getText()) : 0;
  }


  @Override
  public void setMinBet(String value) {
    this.minBet.setText(value);
  }
  @Override
  public String getNewPlayerMoney() {
      return newPlayerMoney.getText();
  }
  @Override
  public String getNewPlayerName() {
      return newPlayerName.getText();
  }


  @Override
  public PokerTableView getPokerTable() {
    return pokerTable;
  }

  @Override
  public void setSmallBlindValue(String value) {
    this.smallBlindValue.setText(value);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    pokerTable = new PokerTableView();
    centerBox.getChildren().add(pokerTable);

  }

  @Override
  public void update(Observable o, Object o1) {
    getPokerTable().setStatus(o1.toString()); // show the status

    switch ((Status) o1) {
      case INIT:
        enableStartButton(true);
        break;
      case BLIND:
        setSmallBlindValue(Integer.toString(model.getSmallBlindValue()));
      case PREFLOP:
      case FLOP:
      case TURN:
      case RIVER:
        disableAllBetsButtons();
        enableStatusButtons(model.getAvailable());
        updatePlayers();
        updateTable();
        break;
      case END_MATCH:
        enableStartButton(true);
        enableStopButton(true);
        enableAddPlayerButton(true);
        enableNewPlayerNameField(true);
        enableNewPlayerMoneyField(true);
      case SHOWDOWN:
        disableAllBetsButtons();
      case SPLITPOT:
        updatePlayers();
        updateTable();
        break;
      case END_GAME:
        enableStartButton(false);
        enableStopButton(false);
        break;
    }
  }



  @FXML
  private void addPlayer() {
    controller.addPlayer();
  }


  @FXML
  private void allIn() {
    controller.allIn();
  }

  @FXML
  private void bigBlind() {
    controller.bigBlind();
  }

  @FXML
  private void call() {
    controller.call();
  }

  @FXML
  private void check() {
    controller.check();
  }

  @FXML
  private void fold() {
    controller.fold();
  }

  @FXML
  private void numKeyEventFilter(KeyEvent event) {
    if (!event.getCharacter().matches("[0-9]*")) {
      event.consume();
    }
  }

  @FXML
  private void raise() {
    controller.raise();
  }


  @FXML
  private void smallBlind() {
    controller.smallBlind();
  }

  @FXML
  private void start() {
    controller.start();
  }

  @FXML
  private void stop() {
    controller.stop();
  }



  private void updatePlayerCards(Player player, PlayerView playerView) {
    playerView.clearCards();
    player.getCards().forEach(card -> {
      CardView cardView = new CardView();
      cardView.setColor(card.getColor().toString());
      cardView.setValue(card.getValue().toString());
      playerView.addCard(cardView);
    });
  }

  private void updatePlayers() {
    players.forEach((player, playerView) -> {
      playerView.setFolded(player.isFold());
      playerView.setMoney(Integer.toString(player.getMoney())); // I prefer to convert int to String
      // here than inside the component,
      // for more component's versatility

      playerView.setHasButton(player.hasButton());
      playerView.setProfit(Integer.toString(player.getCurrentProfit()));// same reason as setMoney
      if (player.equals(model.getCurrentPlayer())) {
        playerView.setCurrentPlayerEffect("-fx-border-style:solid;" + "-fx-border-radius:3;"
            + "-fx-border-color:orange;" + "-fx-border-width:4;");
      } else {
        playerView.setCurrentPlayerEffect("");
      }

      updatePlayerCards(player, playerView);
    });
  }

  private void updateTable() {
    getPokerTable().clearBoard();
    model.getBoard().forEach(card -> {
      CardView cardView = new CardView();
      cardView.setColor(card.getColor().toString());
      cardView.setValue(card.getValue().toString());
      getPokerTable().addCard(cardView);

    });
    setMinBet(Integer.toString(model.getMinimium()));
    getPokerTable().setPot(Integer.toString(model.getPot()));

  }
}

package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Bet;
import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.Player;
import esi.atl.g39871.poker.model.Status;
import esi.atl.g39871.poker.model.cards.Card;
import esi.atl.g39871.poker.mvc.PokerController;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * @SRV Cette classe sera une "facade" de la vue, c'est à dire qu'elle comprendra des composants(
 * table, joueur, cartes, etc) mais le controlleur PokerController interagira avec cette facade,
 * comme il interragit avec la facade du modèle
 *
 * Donc tous les autres éléments de l'interface, hormis ceux avec lequel l'utilisateur va interragir
 * (les boutons) seront des composants à priori. Et les éléments interactifs seront déclarés ici,
 * dans la facade de la vue.
 */
public class PokerView extends BorderPane implements PokerViewInterface,Initializable,Observer {

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
  private Button checkButton;

  @FXML
  private VBox centerBox;

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

  private HashMap<Player, PlayerView> players;

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


  /**
   * Enable or disable the button to add a player according to the argument. Give true enable the
   * button and false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableAddPlayerButton(boolean b) {
    addPlayerButton.setDisable(!b);
  }

  /**
   * Enable or disable the allIn button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableAllInButton(boolean b) {
    allInButton.setDisable(!b);
  }

  /**
   * Enable or disable the bet's amount input field according to the argument. Give true enable the
   * button and false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableAmountField(boolean b) {
    betAmount.setDisable(!b);
  }

  /**
   * Enable or disable the bigBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableBigBlindButton(boolean b) {
    bigBlindButton.setDisable(!b);
  }

  /**
   * Enable or disable the call button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableCallButton(boolean b) {
    callButton.setDisable(!b);
  }

  /**
   * Enable or disable the fold button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableFoldButton(boolean b) {
    foldButton.setDisable(!b);
  }

  /**
   * Enable or disable the raise button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableRaiseButton(boolean b) {
    raiseButton.setDisable(!b);
  }

  /**
   * Enable or disable the smallBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableSmallBlindButton(boolean b) {
    smallBlindButton.setDisable(!b);
  }

  /**
   * Enable or disable the start button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableStartButton(boolean b) {
    startButton.setDisable(!b);
  }

  /**
   * Enable or disable the stop button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableStopButton(boolean b) {
    stopButton.setDisable(!b);

  }

    @Override
  public int getBetAmount() {
    return !betAmount.getText().isEmpty() ? Integer.parseInt(betAmount.getText()) : 0;
  }

  /**
   * Sets the minimum bet's value field to the value given in argument
   *
   * @param value the value of the minimum bet
   */
    @Override
  public void setMinBet(String value) {
    this.minBet.setText(value);
  }

  /**
   * Returns the poker table.
   *
   * @return the poker table
   */
    @Override
  public PokerTableView getPokerTable() {
    return pokerTable;
  }

  /**
   * Sets the smallBlind value field to the value given in argument
   *
   * @param value the smallBlind value
   */
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

    if (o1 == Status.INIT) {
      if (model.getPlayers().size() >= 4) {
        enableStartButton(true);
      }
    }

    if (o1 == Status.BLIND) {
      disableAllBetsButtons();
      setAvailableBlindButtons();
      enableStatusButtons(model.getAvailable());

      setSmallBlindValue(Integer.toString(model.getSmallBlindValue()));

      getPokerTable().setPot(Integer.toString(model.getPot()));
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.PREFLOP) {
      disableAllBetsButtons();
      enableStatusButtons(model.getAvailable());
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.FLOP) {
      disableAllBetsButtons();
      enableStatusButtons(model.getAvailable());
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.TURN) {
      disableAllBetsButtons();
      enableStatusButtons(model.getAvailable());
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.RIVER) {
      disableAllBetsButtons();
      enableStatusButtons(model.getAvailable());
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.SHOWDOWN) {
      disableAllBetsButtons();

      updatePlayers();
      updateTable();
    }

    if (o1 == Status.SPLITPOT) {
      disableAllBetsButtons();
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.END_MATCH) {
      disableAllBetsButtons();
      updatePlayers();
      updateTable();

    }

    if (o1 == Status.END_GAME) {
      // TODO ca sert a quoi le end_GAME ???
      updatePlayers();
      updateTable();
    }
  }

  /**
   * Enable or disable the check button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
    @Override
  public void enableCheckButton(boolean b) {
    checkButton.setDisable(!b);
  }

  private void setAvailableBlindButtons() {
    enableStartButton(false);
    enableStopButton(true);
    enableAddPlayerButton(false);
    enableAmountField(true);
  }

  /**
   * Enable the right available bets buttons that the player is authorized to use.
   *
   * @param availableBets the available bets
   */
    @Override
  public void enableStatusButtons(List<Bet> availableBets) {
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
      }
    }

  }

  /**
   * Disable all bets buttons
   */
    @Override
  public void disableAllBetsButtons() {
    enableSmallBlindButton(false);
    enableBigBlindButton(false);
    enableAllInButton(false);
    enableCallButton(false);
    enableFoldButton(false);
    enableRaiseButton(false);
    enableCheckButton(false);

  }

  @FXML
  private void addPlayer() {
    controller.addPlayer(newPlayerName.getText(), newPlayerMoney.getText());
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

  // TODO verifier que le currentprofit est ajouté a la money du player
  // TODO vérifier que toutes les méthodes publics (' toutes les fonctionnalités dispo du modeles )
  // sont implémentées dans la vue , sinonh ca sert a rien qu'ils l'aient fait
  /**
   * Add a visual component for a new player.
   *
   * @param player the new player
   */
    @Override
  public void addPlayerInLayout(Player player) { // TODO refaire ccette méthode, il y a trop de
    // logique dedans, il vaudrait mieux la séparer
    // et/ou passer directement les attributs en
    // paramètre plutot que le joueur.
    // TODO, mettre les joueurs dans une liste ? et faire un update playersview a la place ? Plus
    // opti ?
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

  private void updatePlayers() {

    // clearPlayersLayout();
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
      playerView.clearCards();
      for (Card card : player.getCards()) {// TODO lambda or for ? performances ?
        CardView cardView = new CardView();
        cardView.setColor(card.getColor().toString());
        cardView.setValue(card.getValue().toString());
        playerView.addCard(cardView);
      }
    });

  }

  private void updateTable() {
    getPokerTable().clearBoard();

    for (Card card : model.getBoard()) {
      CardView cardView = new CardView();
      cardView.setColor(card.getColor().toString());
      cardView.setValue(card.getValue().toString());
      getPokerTable().addCard(cardView);
    }
    setMinBet(Integer.toString(model.getMinimium()));

    getPokerTable().setPot(Integer.toString(model.getPot()));

  }

}

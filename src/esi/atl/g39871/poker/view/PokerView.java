package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.Player;
import esi.atl.g39871.poker.model.Status;
import esi.atl.g39871.poker.model.cards.Card;
import esi.atl.g39871.poker.mvc.PokerController;
import java.io.IOException;
import java.net.URL;
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

// TODO essayer de respecter les google java style ( dans toutes les classes )
/*
 * @SRV Cette classe sera une "facade" de la vue, c'est à dire qu'elle comprendra des composants(
 * table, joueur, cartes, etc) mais le controlleur PokerController interagira avec cette facade,
 * comme il interragit avec la facade du modèle
 *
 * Donc tous les autres éléments de l'interface, hormis ceux avec lequel l'utilisateur va interragir
 * (les boutons) seront des composants à priori. Et les éléments interactifs seront déclarés ici,
 * dans la facade de la vue.
 */
public class PokerView extends BorderPane implements Initializable, Observer {

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
  // TODO virer initializable ?
  // TODO est-ce que c'est bien d'étendre VBox ? faut peut-être rien étendre vu que c'est la facade
  // de la vue

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

  public PokerView(PokerController controller, Game model) { // TODO passer des interface plutot que
    // des classes concrètes ?
    this.model = model;
    this.controller = controller;
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
   * Add a visual component for a new player.
   *
   * @param player the new player
   */
  public void addPlayerInLayout(Player player) { // TODO refaire ccette méthode, il y a trop de
    // logique dedans, il vaudrait mieux la séparer
    // et/ou passer directement les attributs en
    // paramètre plutot que le joueur.
    PlayerView playerView = new PlayerView(player.getName(), player.getMoney());
    playerView.setFolded(player.isFold());
    playerView.setHasButton(player.hasButton());

    playersLayout.getChildren().add(playerView);

    if (player.equals(model.getCurrentPlayer())) {
      playerView.setCurrentPlayerEffect("-fx-border-style:solid;" + "-fx-border-radius:3;"
          + "-fx-border-color:orange;" + "-fx-border-width:4;");
    }

    for (Card card : player.getCards()) {// TODO virer ces lignes ca doit se faire via des updates
      // spécifiques, ici c'estj uste pour tester.
      CardView cardView = new CardView();
      cardView.setColor(card.getColor().toString());
      cardView.setValue(card.getValue().toString());
      playerView.addCard(cardView);
    }

  }

  public void alert(String message) {
    Alert alertMessage = new Alert(Alert.AlertType.ERROR);
    alertMessage.setTitle("Attention");
    alertMessage.setHeaderText(message);
    // helpAlert.initModality(); //does not block interaction
    alertMessage.show();

  }

  /**
   * Clear all elements in the players layout.
   */
  public void clearPlayersLayout() {
    playersLayout.getChildren().clear();
  }

  /**
   * Enable or disable the button to add a player according to the argument. Give true enable the
   * button and false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableAddPlayerButton(boolean b) {
    addPlayerButton.setDisable(!b);
  }

  /**
   * Enable or disable the allIn button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableAllInButton(boolean b) {
    allInButton.setDisable(!b);
  }

  /**
   * Enable or disable the bet's amount input field according to the argument. Give true enable the
   * button and false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableAmountField(boolean b) {
    betAmount.setDisable(!b);
  }

  /**
   * Enable or disable the bigBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableBigBlindButton(boolean b) {
    bigBlindButton.setDisable(!b);
  }

  /**
   * Enable or disable the call button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableCallButton(boolean b) {
    callButton.setDisable(!b);
  }

  /**
   * Enable or disable the fold button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableFoldButton(boolean b) {
    foldButton.setDisable(!b);
  }

  /**
   * Enable or disable the raise button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableRaiseButton(boolean b) {
    raiseButton.setDisable(!b);
  }

  /**
   * Enable or disable the smallBlind button according to the argument. Give true enable the button
   * and false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableSmallBlindButton(boolean b) {
    smallBlindButton.setDisable(!b);
  }

  /**
   * Enable or disable the start button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableStartButton(boolean b) {
    startButton.setDisable(!b);
  }

  /**
   * Enable or disable the stop button according to the argument. Give true enable the button and
   * false to disable it.
   *
   * @param b the boolean value.
   */
  public void enableStopButton(boolean b) {
    stopButton.setDisable(!b);

  }

  public int getBetAmount() {
    return !betAmount.getText().isEmpty() ? Integer.parseInt(betAmount.getText()) : 0;
  }

  /**
   * Sets the minimum bet's value field to the value given in argument
   *
   * @param value the value of the minimum bet
   */
  public void setMinBet(String value) {
    this.minBet.setText(value);
  }

  /**
   * Returns the poker table.
   *
   * @return the poker table
   */
  public PokerTableView getPokerTable() {
    return pokerTable;
  }

  /**
   * Sets the smallBlind value field to the value given in argument
   *
   * @param value the smallBlind value
   */
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

    if (o1 == Status.INIT && model.getPlayers().size() >= 4) {
      enableStartButton(true);
    }

    if (o1 == Status.BLIND) {
      // TODO peut-être mettre une méthode qui appelle model.getAvaible pour faire les disable des
      // boutons , sinon on utilise pas la méthode du model :/
      enableStartButton(false);
      enableStopButton(true);
      enableAllInButton(true);
      enableAmountField(true);
      enableBigBlindButton(true);
      enableCallButton(true);
      enableFoldButton(true);
      enableRaiseButton(true);
      enableSmallBlindButton(true);

      setSmallBlindValue(Integer.toString(model.getSmallBlindValue()));

      getPokerTable().setPot(Integer.toString(model.getPot()));
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.PREFLOP) {
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.FLOP) {
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.TURN) {
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.RIVER) {
      updatePlayers();
      updateTable();
    }

    if (o1 == Status.SHOWDOWN) {
      updatePlayers();
      updateTable();

    }

    if (o1 == Status.SPLITPOT) {
      updatePlayers();
      updateTable();

    }

    if (o1 == Status.END_MATCH) {
      updatePlayers();
    }

    if (o1 == Status.END_GAME) {

    }
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

  private void updatePlayers() {

    clearPlayersLayout();

    for (Player player : model.getPlayers()) {
      addPlayerInLayout(player);
    }
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

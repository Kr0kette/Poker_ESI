package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import esi.atl.g39871.poker.model.Player;
import esi.atl.g39871.poker.model.cards.Card;
import esi.atl.g39871.poker.mvc.PokerController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


/*
 * @SRV Cette classe sera une "facade" de la vue, c'est à dire qu'elle
 * comprendra des composants( table, joueur, cartes, etc) mais le controlleur
 * PokerController interagira avec cette facade, comme il interragit avec la
 * facade du modèle
 *
 * Donc tous les autres éléments de l'interface, hormis ceux avec lequel
 * l'utilisateur va interragir (les boutons) seront des composants à priori. Et
 * les éléments interactifs seront déclarés ici, dans la facade de la vue.
 */
public class PokerView extends BorderPane { //TODO virer initializable ? 
    //TODO est-ce que c'est bien d'étendre VBox ? faut peut-être rien étendre vu que c'est la facade de la vue 

    @FXML
    private HBox playersLayout;


    @FXML
    private TextField newPlayerName;


    @FXML
    private TextField newPlayerMoney;


    @FXML
    private Button startButton;
    
    @FXML
    private Button stopButton;


    @FXML
    private Button addPlayerButton;


    private Game model;


    private PokerController controller;

    public PokerView(PokerController controller, Game model) { //TODO passer des interface plutot que des classes concrètes ?
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

    @FXML
    private void addPlayer() {
        controller.addPlayer(newPlayerName.getText(), Integer.parseInt(newPlayerMoney.getText()));
    }

    @FXML
    private void start() throws GameException {
        controller.start();
    }
    
    @FXML
    private void stop() throws GameException {
        controller.stop();
    }

    /**
     * Add a visual component for a new player.
     *
     * @param player the new player
     */
    public void addPlayerInLayout(Player player) {
        PlayerView playerView = new PlayerView(player.getName(), player.getMoney());
        playersLayout.getChildren().add(playerView);

        for (Card card : player.getCards()) {//TODO virer ces lignes ca doit se faire via des updates spécifiques, ici c'estj uste pour tester.
            CardView cardView = new CardView();
            cardView.setColor(card.getColor().toString());
            cardView.setValue(card.getValue().toString());
            playerView.addCard(cardView);
        }

    }

    /**
     * Enable or disable the start button according to the argument. Give true
     * enable the button and false to disable it.
     *
     * @param b the boolean value.
     */
    public void enableStartButton(boolean b) {
        startButton.setDisable(!b);
    }
    
    /**
     * Enable or disable the stop button according to the argument. Give true
     * enable the button and false to disable it.
     *
     * @param b the boolean value.
     */
    public void enableStopButton(boolean b) {
        stopButton.setDisable(!b);
    }

    /**
     * Enable or disable the button to add a player according to the argument.
     * Give true enable the button and false to disable it.
     *
     * @param b the boolean value.
     */
    public void enableAddPlayerButton(boolean b) {
        addPlayerButton.setDisable(!b);
    }

}

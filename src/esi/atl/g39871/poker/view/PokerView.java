package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import esi.atl.g39871.poker.model.Player;
import esi.atl.g39871.poker.mvc.PokerController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


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
public class PokerView extends VBox implements Initializable { //TODO virer initializable ? 
    //TODO est-ce que c'est bien d'étendre VBox ? faut peut-être rien étendre vu que c'est la facade de la vue 

    @FXML
    TextField addPlayerName;


    @FXML
    TextField addPlayerMoney;


    @FXML
    Button startButton;


    @FXML
    Button addPlayerButton;


    Game model;


    PokerController controller;

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
        controller.addPlayer(addPlayerName.getText(), Integer.parseInt(addPlayerMoney.getText()));

    }

    @FXML
    private void start() throws GameException {
        controller.start();
    }

    /**
     * Add a visual component for a new player.
     *
     * @param player the new player
     */
    public void addLayoutPlayer(Player player) {
        this.getChildren().add(new Label(player.getName() + " : " + player.getMoney()));
    }

    public void enableStartButton(boolean b) {
        startButton.setVisible(b);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

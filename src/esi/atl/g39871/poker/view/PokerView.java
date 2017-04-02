package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.mvc.PokerController;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Krokro
 */
public class PokerView extends VBox implements Initializable { //TODO virer initializable ? 
    //TODO est-ce que c'est bien d'étendre VBox ? faut peut-être rien étendre vu que c'est la facade de la vue 

    @FXML
    TextField addPlayerName;


    @FXML
    TextField addPlayerMoney;


    Game model;


    PokerController controller;

    public PokerView(PokerController controller, Game model) { //TODO passer des interface plutot que des classes concrètes ?
        this.model = model;
        this.controller = controller;
    }

    @FXML
    public void addPlayer() {
        controller.addPlayer(addPlayerName.getText(), Integer.parseInt(addPlayerMoney.getText()));

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

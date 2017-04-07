package esi.atl.g39871.poker.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PokerTableView extends BorderPane {

    @FXML
    private HBox board;

    @FXML
    private Label pot;

    @FXML
    private Label status;

    public PokerTableView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PokerTableView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Add a card to the board.
     *
     * @param card the card to add
     */
    public void addCard(CardView card) {
        board.getChildren().add(card);
    }

    /**
     * Sets the status field to the value given in argument
     *
     * @param value the new value
     */
    public void setStatus(String value) {
        this.status.setText(value);
    }

    /**
     * Sets the pot's value field to the value given in argument
     *
     * @param value the new value for the pot
     */
    public void setPot(String value) {
        this.pot.setText(value);
    }

    /**
     * Clears all board's elements.
     */
    public void clearBoard() {
        board.getChildren().clear();
    }

}

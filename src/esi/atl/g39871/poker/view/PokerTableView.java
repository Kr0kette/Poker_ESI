package esi.atl.g39871.poker.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author g39871
 */
public class PokerTableView extends BorderPane implements PokerTableViewInterface {

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


  @Override
  public void addCard(VBox card) {
    board.getChildren().add(card);
  }

  @Override
  public void clearBoard() {
    board.getChildren().clear();
  }

  @Override
  public void setPot(String value) {
    this.pot.setText(value);
  }

  @Override
  public void setStatus(String value) {
    this.status.setText(value);
  }

}

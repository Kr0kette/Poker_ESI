package esi.atl.g39871.poker.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author g39871
 */
public class CardView extends VBox implements CardViewInterface {

  @FXML
  private Label labelColor;

  @FXML
  private Label labelValue;



  public CardView() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardView.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }


  @Override
  public void setColor(String color) {
    labelColor.setText(color);
  }


  @Override
  public void setValue(String value) {
    labelValue.setText(value);
  }



  @Override
  public void hide() {
    this.setVisible(false);
  }

  @Override
  public void show() {
    this.setVisible(true);
  }

}

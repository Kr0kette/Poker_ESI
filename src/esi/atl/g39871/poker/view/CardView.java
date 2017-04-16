package esi.atl.g39871.poker.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class for a CardView
 *
 * @author Krokro
 */
public class CardView extends VBox implements CardViewInterface {

  @FXML
  private Label labelColor;

  @FXML
  private Label labelValue;

  
  @Override
  public CardView self(){
      return this;
  }
  /**
   * Create a new instance of the FicheSignaletique's component
   */
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

  /**
   * Set the card's color
   *
   * @param color the card's color
   */
    @Override
  public void setColor(String color) {
    labelColor.setText(color);
  }

  /**
   * Set the card's value
   *
   * @param value the card's value
   */
    @Override
  public void setValue(String value) {
    labelValue.setText(value);
  }

  /**
   * Show the card.
   */
    @Override
  public void show() {
    this.setVisible(true);
  }

  /**
   * Hide the card.
   */
    @Override
  public void hide() { //TODO essayer d'implémenter si j'ai le temps
    this.setVisible(false);
  }

}

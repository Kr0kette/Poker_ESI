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
public class PlayerView extends VBox implements PlayerViewInterface {


  @FXML
  private VBox cardsBox;

  @FXML
  private Label currentBet;
  @FXML
  private Label folded;

  @FXML
  private Label hasButton;


  private int index;

  @FXML
  private Label money;

  @FXML
  private Label name;
  @FXML
  private Label profit;

  public PlayerView() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerView.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);



    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  PlayerView(String name, String money) { // money is a String, for more component's versatility
    this();
    this.setMoney(money);
    this.setName(name);
  }


  @Override
  public void addCard(VBox card) {

    cardsBox.getChildren().add(card);

  }

  @Override
  public void clearCards() {
    cardsBox.getChildren().clear();
  }

  @Override
  public void setCurrentPlayerEffect(String css) {
    this.setStyle(css);

  }



  @Override
  public void setFolded(boolean b) {
    folded.setVisible(b);
  }


  @Override
  public void setHasButton(boolean b) {
    hasButton.setVisible(b);
  }


  @Override
  public void setMoney(String money) {
    this.money.setText("Money: " + money + "$");
  }

  @Override
  public void setName(String name) {
    this.name.setText(name);
  }

  @Override
  public void setProfit(String profit) {
    this.profit.setText("Profit: " + profit + "$ ");
  }

}

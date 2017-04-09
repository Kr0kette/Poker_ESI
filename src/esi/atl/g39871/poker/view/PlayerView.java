package esi.atl.g39871.poker.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Krokro
 */
public class PlayerView extends VBox {

  private List<CardView> cards;// TODO je sais pas encore à quoi ça va servir mais il me semble que
  // je vais en avoir besoin, je sais plus pourquoi.

  @FXML
  private Label folded;
  
  @FXML
  private Label currentBet;

  @FXML
  private Label hasButton;

  @FXML
  private Label profit;

  private int index;

  @FXML
  private Label money;

  @FXML
  private Label name;

  public PlayerView() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerView.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    cards = new ArrayList<CardView>();

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  PlayerView(String name, int money) {
    this();
    this.setMoney(money);
    this.setName(name);
  }

  /**
   * Add a card to the player.
   *
   * @param card the card to add
   */
  public void addCard(CardView card) {
    cards.add(card);
    this.getChildren().add(card);

  }

  /**
   * Sets the visual element to represent if a player is folded or not. It's advised to set true
   * when the player is folded.
   *
   * @param b true to show the element, false otherwise
   */
  public void setFolded(boolean b) {
    folded.setVisible(b);
  }

  /**
   * Sets the visual element to represent if a player has the button or not. It's advised to set
   * true when the player has the button.
   *
   * @param b true to show the element, false otherwise.
   */
  public void setHasButton(boolean b) {
    hasButton.setVisible(b);
  }

  /**
   * Update the player's money
   *
   * @param money the player's money
   */
  public void setMoney(int money) {
    this.money.setText(this.money.getText() + Integer.toString(money) + "$");
  }

  /**
   * Update the player's profit.
   *
   * @param profit the player's profit
   */
  public void setProfit(int profit) {
    this.profit.setText(this.profit.getText() + Integer.toString(profit) + "$ ");
  }

  /**
   * Sets the player's name
   *
   * @param name the player's name
   */
  public void setName(String name) {
    this.name.setText(name);
  }
  
  
  /**
   * Sets the player's current bet
   *
   * @param name the player's current bet
   */
  public void setCurrentBet(String bet) {
    this.currentBet.setText(this.currentBet.getText()+ bet);
  }

  /**
   * Display a visual effect player to show the current. The visual effect depends on the argument
   * player.
   *
   * @param css the css rules, if more than one rule, they must be separated by the traditional
   *        semicolon
   */
  public void setCurrentPlayerEffect(String css) {
    this.setStyle(css);

  }

}

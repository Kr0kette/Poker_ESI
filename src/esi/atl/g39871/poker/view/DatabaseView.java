package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.model.FacadeDB;
import esi.atl.g39871.poker.persistence.dto.GameHistoryDto;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.persistence.dto.ReviewDto;
import esi.atl.g39871.poker.seldto.GameHistorySel;
import esi.atl.g39871.poker.seldto.PlayerSel;
import esi.atl.g39871.poker.seldto.ReviewSel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author g39871
 */
public class DatabaseView extends VBox implements Initializable {

  @FXML
  private TextField nameFilter;

  @FXML
  private TextField amount;

  @FXML
  private TableView<PlayerData> players;

  @FXML
  private TableColumn<PlayerData, String> nameColumn;

  @FXML
  private TableColumn<PlayerData, String> moneyColumn;

  @FXML
  private TableColumn<PlayerData, Date> lastConnectionColumn;

  private ObservableList<PlayerData> dataPlayers = FXCollections.observableArrayList();
  
  
  

  @FXML
  private TextField gameFilter;
  @FXML
  private TableView<ReviewData> reviews;

  @FXML
  private TableColumn<ReviewData, String> idReviewGameColumn;

  @FXML
  private TableColumn<ReviewData, String> reviewNamePlayerColumn;

  @FXML
  private TableColumn<ReviewData, String> ratingColumn;

  @FXML
  private TableColumn<ReviewData, String> detailsColumn;

  private ObservableList<ReviewData> dataReview = FXCollections.observableArrayList();
  
  
  
  


  @FXML
  private TableView<GameHistoryData> games;

  @FXML
  private TableColumn<GameHistoryData, String> idGameColumn;

  @FXML
  private TableColumn<GameHistoryData, String> namePlayerColumn;

  @FXML
  private TableColumn<GameHistoryData, String> gainColumn;

  @FXML
  private TableColumn<GameHistoryData, String> handCategoryColumn;

  private ObservableList<GameHistoryData> dataHistory = FXCollections.observableArrayList();

  public DatabaseView() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DatabaseView.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();

    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
      
     

    // initialize columns players list
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
    lastConnectionColumn
        .setCellValueFactory(cellData -> cellData.getValue().lastConnectionProperty());

    players.setItems(dataPlayers);

    // initialize columns games history
    idGameColumn.setCellValueFactory(cellData -> cellData.getValue().idGameProperty());
    namePlayerColumn.setCellValueFactory(cellData -> cellData.getValue().namePlayerProperty());
    gainColumn.setCellValueFactory(cellData -> cellData.getValue().gainProperty());
    handCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().handCategoryProperty());

    games.setItems(dataHistory);
    
    
    //initialize columns reviews
    idReviewGameColumn.setCellValueFactory(cellData -> cellData.getValue().idGameProperty());
    reviewNamePlayerColumn.setCellValueFactory(cellData -> cellData.getValue().namePlayerProperty());
    ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
    detailsColumn.setCellValueFactory(cellData -> cellData.getValue().detailsProperty());

    reviews.setItems(dataReview);
    

  }

  @FXML
  private void searchInPlayers() { // todo move cette methode dans un controleur
    try {
      dataPlayers.clear();
      // gets corresponding records from the database
      ArrayList<PlayerDto> playersDto =
          new ArrayList<>(FacadeDB.getSelectedPlayers(new PlayerSel(nameFilter.getText())));

      // Create playerData for each corresponding record in the database
      playersDto.forEach(p -> {
        PlayerData playerData = new PlayerData(p.getName(), p.getMoney(), p.getLastConnection());
        dataPlayers.add(playerData);

      });

    } catch (PokerModelException ex) {
      Logger.getLogger(DatabaseView.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @FXML
  private void searchInHistory() { // todo move cette methode dans un controleur
    try {
 
      dataHistory.clear();
      // gets corresponding records from the database
      ArrayList<GameHistoryDto> gameHistoryDto = new ArrayList<>(
          FacadeDB.getSelectedGamesHistory(new GameHistorySel(nameFilter.getText())));


      // Create playerData for each corresponding record in the database
      gameHistoryDto.forEach(p -> {
        GameHistoryData gameHistoryData =
            new GameHistoryData(p.getIdGame(), p.getNamePlayer(), p.getGain(), p.getHandCategory());
        dataHistory.add(gameHistoryData);

      });

    } catch (PokerModelException ex) {
      Logger.getLogger(DatabaseView.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
  
  
   @FXML
  private void searchInReview() { // todo move cette methode dans un controleur
    try {
 
      dataReview.clear();
      // gets corresponding records from the database
      ArrayList<ReviewDto> reviewDto = new ArrayList<>(
          FacadeDB.getSelectedReview(new ReviewSel(nameFilter.getText())));


      // Create review Data for each corresponding record in the database
      reviewDto.forEach(p -> {
        ReviewData reviewData =
            new ReviewData(p.getIdGame(), p.getNamePlayer(), p.getRating(), p.getDetails());
        dataReview.add(reviewData);

      });

    } catch (PokerModelException ex) {
      Logger.getLogger(DatabaseView.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @FXML
  private void addMoney() { // todo passer ca dans un controleur ça doit pas être dans la vue, mais j'ai pas le temps :'( idem pour les autres méthodes ici 
    if (players.getSelectionModel().getSelectedItem() != null) {
      PlayerDto player = new PlayerDto(players.getSelectionModel().getSelectedItem().getName(),
          Integer.parseInt(amount.getText()));

      try {
        FacadeDB.updatePlayer(player);
      } catch (PokerModelException ex) {
        Logger.getLogger(DatabaseView.class.getName()).log(Level.SEVERE, null, ex);
      }

    } else {
      alert(
          "You have to selected a player in the list below. If no player shown, please adjust the filter");

    }

  }

  @FXML
  private void numKeyEventFilter(KeyEvent event) {
    if (!event.getCharacter().matches("[0-9]*")) {
      event.consume();
    }
  }

  public void alert(String message) {
    Alert alertMessage = new Alert(Alert.AlertType.WARNING);
    alertMessage.setTitle("Warning");
    alertMessage.setHeaderText(message);
    alertMessage.show();

  }

}

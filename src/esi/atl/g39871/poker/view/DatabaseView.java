package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.model.AdminFacadeDB;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.seldto.PlayerSel;
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

  private ObservableList<PlayerData> data = FXCollections.observableArrayList();

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

    // initialize columns
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
    lastConnectionColumn
        .setCellValueFactory(cellData -> cellData.getValue().lastConnectionProperty());

    players.setItems(data);

  }

  @FXML
  private void search() { // todo move cette methode dans un controleur
    try {
      data.clear();
      // gets corresponding records from the database
      ArrayList<PlayerDto> playersDto =
          new ArrayList<>(AdminFacadeDB.getSelectedPlayers(new PlayerSel(nameFilter.getText())));

      // Create playerData for each corresponding record in the database
      playersDto.forEach(p -> {
        PlayerData playerData = new PlayerData(p.getName(), p.getMoney(), p.getLastConnection());
        data.add(playerData);

      });

    } catch (PokerModelException ex) {
      Logger.getLogger(DatabaseView.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @FXML
  private void addMoney() { // todo passer ca dans un controleur
    if (players.getSelectionModel().getSelectedItem() != null) {
      PlayerDto player = new PlayerDto(players.getSelectionModel().getSelectedItem().getName(),
          Integer.parseInt(amount.getText()));

      try {
        AdminFacadeDB.updatePlayer(player);
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

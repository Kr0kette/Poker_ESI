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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author g39871
 */
public class SearchingPlayers extends VBox implements Initializable {

    @FXML
    private TextField nameFilter;

    @FXML
    private TableView<PlayerData> players;

    @FXML
    private TableColumn<PlayerData, String> nameColumn;

    @FXML
    private TableColumn<PlayerData, String> moneyColumn;

    @FXML
    private TableColumn<PlayerData, Date> lastConnectionColumn;

    private ObservableList<PlayerData> data = FXCollections.observableArrayList();

    public SearchingPlayers() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchingPlayers.fxml"));
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

        //initialize columns
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        lastConnectionColumn.setCellValueFactory(cellData -> cellData.getValue().lastConnectionProperty());

        players.setItems(data);

        search();
    }

    private void search() {
        try {
            //gets corresponding records from the database
            ArrayList<PlayerDto> playersDto = new ArrayList<>(
                    AdminFacadeDB.getSelectedPlayers(new PlayerSel()));
            //Create playerData for each corresponding record in the database
            playersDto.forEach(p -> {
                PlayerData playerData = new PlayerData(p.getName(), p.getMoney(), p.getLastConnection());
                data.add(playerData);

            });

        } catch (PokerModelException ex) {
            Logger.getLogger(SearchingPlayers.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        //   FilteredList<PlayerData> filteredData = new FilteredList<>(data, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
//        nameFilter.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(playerDto -> {
//                // If filter text is empty, display all playerDtos.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                // Compare name  of every playerDto with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (playerDto.getName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches name.
//                    //  } else if (playerDto.getMoney().contains(lowerCaseFilter)) {
//                    //    return true; // Filter matches money.
//                }
//                return false; // Does not match.
//            });
//        });
// 3. Wrap the FilteredList in a SortedList.
//     SortedList<PlayerData> sortedData = new SortedList<>(filteredData);
// 4. Bind the SortedList comparator to the TableView comparator.
// sortedData.comparatorProperty().bind(players.comparatorProperty());
// 5. Add sorted (and filtered) data to the table.
// players.setItems(sortedData);
    }

}

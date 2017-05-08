package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.controller.ControllerInterface;
import esi.atl.g39871.poker.controller.PokerController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Krokro
 */
public class Main extends Application {

    private Game game;

    @Override
    public void start(Stage primaryStage) {

        game = new Game();
        ControllerInterface controller = new PokerController(game);

        Scene scene = new Scene(controller.getView());

        primaryStage.setTitle("Poker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Connection conn = null;

        try {
           
            // db parameters
            String url = "jdbc:sqlite:Poker.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        launch(args);
    }

}

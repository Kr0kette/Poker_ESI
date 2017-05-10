package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.controller.ControllerInterface;
import esi.atl.g39871.poker.controller.PokerController;
import esi.atl.g39871.poker.model.Game;
import java.text.ParseException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author g39871
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
    public static void main(String[] args) throws ParseException  {


        launch(args);
    }

}

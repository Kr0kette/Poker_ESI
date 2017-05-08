package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.controller.ControllerInterface;
import esi.atl.g39871.poker.controller.PokerController;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.model.AdminFacadeDB;
import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import java.util.Date;
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
    public static void main(String[] args)  {

      /*
     * Etape 9 Ajout et suppression d'une catégorie **********************
     */
    System.out.println("");
    System.out.println("ajout d'un player *****************");
    System.out.println("");
    try {

      int catNum = AdminFacadeDB.addPlayer(new PlayerDto("krokro", 100, new Date(2017,12,25)));

    } catch (PokerModelException ex) {
      System.out.println(ex.getMessage());
    }
    System.out.println("");
    System.out.println("");
        launch(args);
    }

}

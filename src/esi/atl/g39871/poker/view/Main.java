package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.controller.ControllerInterface;
import esi.atl.g39871.poker.controller.PokerController;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.model.AdminFacadeDB;
import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.seldto.PlayerSel;
import java.text.ParseException;
import java.util.ArrayList;
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

        
        
    System.out.println("");
    System.out.println("ajout d'un player *****************");
    System.out.println("");
    try {

      
     // int catNum = AdminFacadeDB.addPlayer(new PlayerDto("krokro", 100,new Date() ));
      
      PlayerSel player= new PlayerSel("krokro");
      System.out.println("get player by name: " + (ArrayList)(AdminFacadeDB.getSelectedPlayers(player)));

    } catch (PokerModelException ex) {
      System.out.println(ex.getMessage());
    }
    System.out.println("");
    System.out.println("");
        launch(args);
    }

}

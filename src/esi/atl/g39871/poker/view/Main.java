package esi.atl.g39871.poker.view;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Krokro
 */
public class Main extends Application implements Observer {

    VBox root = new VBox();


    Game game;

    @Override
    public void start(Stage primaryStage) throws GameException {

        testingGame();

        MainTestController mainTest = new MainTestController();

        root.getChildren().addAll(mainTest);


        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void playGame(Game game) {

    }

    private void testingGame() throws GameException {
        //testing o add an observer to a game
        game = new Game();
        
        game.addObserver(this);

        //add players  a game
        game.addPlayer("pierre", 1500);
        game.addPlayer("kevin", 2000);
        game.addPlayer("roxane", 3000);
        game.addPlayer("vincane", 4000);

        game.start();

        playGame(game);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /*
     * THIS IS NOT JAVADOC The first argument is the observable object ( the one
     * who called notifyObservers(arg) ) and the second is the arg argument from
     * the notifyObservers)
     *
     */
    @Override
    public void update(Observable o, Object o1) {

        System.out.println("param observable " + o.toString());
        System.out.println("param object " + o1.toString() + "\n");

        //Label label=new Label();
        //root.getChildren().add(label);
    }

}

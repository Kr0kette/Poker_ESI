/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Krokro
 */
public class MainTestController extends VBox{

    /**
     * Create a new instance of the FicheSignaletique's component
     */
    public MainTestController()  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainTest.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}

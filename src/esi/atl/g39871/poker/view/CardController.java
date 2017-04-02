
package esi.atl.g39871.poker.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Krokro
 */
public class CardController extends Region {

    /**
     * Create a new instance of the FicheSignaletique's component
     */
    public CardController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}

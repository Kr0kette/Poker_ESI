
package esi.atl.g39871.poker.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class for a CardView
 *
 * @author Krokro
 */
public class CardView extends VBox {
    
    @FXML
    private Label labelColor;
    
    @FXML
    private Label labelValue;
    


    /**
     * Create a new instance of the FicheSignaletique's component
     */
    public CardView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    /**
     * Set the card's color
     * @param color the card's color
     */
    public void setColor(String color){
        labelColor.setText(color);
    }
    
    /**
     * Set the card's value
     * @param value the card's value
     */
    public void setValue(String value){
        labelValue.setText(value);
    }
    
    /**
     * Show the card.
     */
    public void show(){
        this.setVisible(true);
    }
    
    /**
     * Hide the card.
     */
    public void hide(){
        this.setVisible(false);
    }
    
}

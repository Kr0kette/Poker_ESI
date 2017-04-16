package esi.atl.g39871.poker.view;

import javafx.scene.layout.VBox;

/**
 *
 * @author Krokro
 */
public interface CardViewInterface<T extends VBox> {

    T self();
    /**
     * Set the card's color
     *
     * @param color the card's color
     */
    void setColor(String color);

    /**
     * Set the card's value
     *
     * @param value the card's value
     */
    void setValue(String value);

    /**
     * Hide the card.
     */
    void hide();

    /**
     * Show the card.
     */
    void show();
    
}

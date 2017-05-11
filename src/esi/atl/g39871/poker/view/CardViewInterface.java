package esi.atl.g39871.poker.view;

import javafx.scene.layout.VBox;

/**
 * A Card's view. The user of this Interface has precise control over what to do with each gui's
 * elements
 * 
 * @author g39871
 * @param <T> the type that this card's view should extends or implements
 */
public interface CardViewInterface<T extends VBox> {


  /**
   * Set the card's color
   *
   * @param color the card's color.
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

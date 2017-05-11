package esi.atl.g39871.poker.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author g39871
 */
public class ReviewData {
    


  private final StringProperty idGame;
  private final StringProperty namePlayer;
  private final StringProperty rating;
  private final StringProperty details;

    public ReviewData(int idGame, String namePlayer, int rating, String details) {
        this.idGame = new SimpleStringProperty(Integer.toString(idGame));
        this.namePlayer = new SimpleStringProperty(namePlayer);
        this.rating = new SimpleStringProperty(Integer.toString(rating));
        this.details = new SimpleStringProperty(details);
    }

    public StringProperty idGameProperty() {
        return idGame;
    }

    public StringProperty namePlayerProperty() {
        return namePlayer;
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public StringProperty detailsProperty() {
        return details;
    }
    
    
    
     public String getIdGameProperty() {
        return idGame.get();
    }

    public String getNamePlayer() {
        return namePlayer.get();
    }

    public String getRating() {
        return rating.get();
    }

    public String getDetails() {
        return details.get();
    }

  

  
 


}

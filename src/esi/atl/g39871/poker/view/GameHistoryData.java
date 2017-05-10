package esi.atl.g39871.poker.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a game history's observable properties
 * @author g39871
 */
public class GameHistoryData {


  private final StringProperty idGame;
  private final StringProperty namePlayer;
  private final StringProperty gain;
  private final StringProperty handCategory;

    public GameHistoryData(int idGame, String namePlayer, int gain, String handCategory) {
        this.idGame = new SimpleStringProperty(Integer.toString(idGame));
        this.namePlayer = new SimpleStringProperty(namePlayer);
        this.gain = new SimpleStringProperty(Integer.toString(gain));
        this.handCategory = new SimpleStringProperty(handCategory);
    }

    public StringProperty idGameProperty() {
        return idGame;
    }

    public StringProperty namePlayerProperty() {
        return namePlayer;
    }

    public StringProperty gainProperty() {
        return gain;
    }

    public StringProperty handCategoryProperty() {
        return handCategory;
    }
    
    
    
     public String getIdGameProperty() {
        return idGame.get();
    }

    public String getNamePlayer() {
        return namePlayer.get();
    }

    public String getGain() {
        return gain.get();
    }

    public String getHandCategory() {
        return handCategory.get();
    }

  

  
 


}

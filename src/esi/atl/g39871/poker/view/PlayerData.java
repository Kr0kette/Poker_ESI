package esi.atl.g39871.poker.view;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a player's observable properties
 *
 * @author g39871
 */
public class PlayerData {

  private ObjectProperty<Date> lastConnection;

 // private final SimpleIntegerProperty money;
  //private final SimpleStringProperty name;
  private final StringProperty money;
  private final StringProperty name;

  public PlayerData(String name, int money, Date lastConnection) {
    this.name = new SimpleStringProperty(name);
    this.money =  new SimpleStringProperty(Integer.toString(money));
    this.lastConnection = new SimpleObjectProperty<Date> (lastConnection);

  }

  
  public Date getLastConnection(){
      return lastConnection.get();
  }

  public String getMoney() {
    return money.get();
  }

  public String getName() {
    return name.get();
  }
  public ObjectProperty<Date> lastConnectionProperty() {
      return lastConnection;
  }

  public StringProperty moneyProperty() {
    return money;
  }

  public StringProperty nameProperty() {
    return name;
  }

}

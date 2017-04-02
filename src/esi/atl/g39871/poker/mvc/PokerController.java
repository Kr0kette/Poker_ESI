package esi.atl.g39871.poker.mvc;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.view.PokerView;

/**
 *
 * @author Krokro
 */
public class PokerController {
    Game model;
    PokerView view;
    
    
    public PokerController(Game model,PokerView view){ //TODO changer la déclaration par les interfaces facade par ex ? 
        this.model=model;
        this.view=view;
        view=new PokerView(); 

        
    }
    
    public void addPlayer(String name, int money){
        model.addPlayer(name, money);
    }
}

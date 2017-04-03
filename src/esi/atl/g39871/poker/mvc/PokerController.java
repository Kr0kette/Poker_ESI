package esi.atl.g39871.poker.mvc;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import esi.atl.g39871.poker.view.PokerView;
import java.util.Observable;
import java.util.Observer;

public class PokerController implements Observer {

    Game model;


    PokerView view;

    public PokerController(Game model) { //TODO changer la déclaration par les interfaces facade par ex ? 
        this.model = model;
        view = new PokerView(this, model);
        model.addObserver(this);

    }

    /**
     * Add a player to the game, and create a visual for this player
     *
     * @param name  the player's name
     * @param money the player's amount of money
     */
    public void addPlayer(String name, int money) { //TODO ne doit pas forcément être un int , si ??? 
        model.addPlayer(name, money);
        view.addLayoutPlayer(model.getPlayers().get(model.getPlayers().size() - 1));

    }

    /**
     * Start de game and set the start button to invisible
     *
     * @throws GameException
     */
    public void start() throws GameException {
        model.start();
        view.enableStartButton(false);
    }

    /**
     * Returns the view's facade
     *
     * @return the view's facade
     */
    public PokerView getView() {
        return view;
    }

    @Override
    public void update(Observable o, Object o1) {

        if (model.getPlayers().size() >= 4) {
            view.enableStartButton(true);
        }

        /*
         * @SRV update les différents éléments de la vue via la facade de la
         * vue( PokerView), je mettrai probablement des updates dans chaque vue,
         * qui seront appelés depuis la classe facade PokerView
         */
    }
}

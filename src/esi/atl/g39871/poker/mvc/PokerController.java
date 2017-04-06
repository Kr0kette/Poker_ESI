package esi.atl.g39871.poker.mvc;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import esi.atl.g39871.poker.model.Status;
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
        view.addPlayerInLayout(model.getPlayers().get(model.getPlayers().size() - 1));

    }

    /**
     * Start de game.
     * <p>
     * Disable the start button.
     * <p>
     * Disable the button to add a player.
     * <p>
     * Enable the stop button.
     *
     * @throws GameException
     */
    public void start() {
        try {
            model.start();
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }

    }

    /**
     * Stop de game and set the stop button to invisible
     * <p>
     */
    public void stop() {
        model.stop();
        view.enableStartButton(true);
        view.enableAddPlayerButton(true);
    }

    /**
     * Returns the view's facade
     *
     * @return the view's facade
     */
    public PokerView getView() {
        return view;
    }

    /**
     * Discard one's hand and forfeit interest in the current pot.
     *
     * @throws GameException if the match is in Blind State
     */
    public void fold() {
        try {
            //TODO GERER LES ERREURS

            model.fold();
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    public void call() {
        try {
            model.call();
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    public void raise() {
        try {
            model.raise(Integer.parseInt(view.getBetAmount()));
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    public void smallBlind() {
        try {
            model.smallBlind(Integer.parseInt(view.getBetAmount()));
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    public void bigBlind() {
        try {
            model.bigBlind(Integer.parseInt(view.getBetAmount()));
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    /**
     * Bet all player's chips.
     *
     * @throws esi.atl.g39871.poker.model.GameException
     */
    public void allIn() {
        try {
            model.allIn();
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        view.getPokerTable().setStatus(o1.toString()); //show the status

        if (o1 == Status.INIT && model.getPlayers().size() >= 4) {
            view.enableStartButton(true);
        }

        if (o1 == Status.BLIND) {
            view.enableStartButton(false);
            view.enableStopButton(true);
            view.enableAllInButton(true);
            view.enableAmountField(true);
            view.enableBigBlindButton(true);
            view.enableCallButton(true);
            view.enableFoldButton(true);
            view.enableRaiseButton(true);
            view.enableSmallBlindButton(true);

            view.setSmallBlindValue(Integer.toString(model.getSmallBlindValue()));

            view.getPokerTable().setPot(Integer.toString(model.getPot()));

        }
        if (o1 == Status.PREFLOP) {

        }
        if (o1 == Status.FLOP) {

        }
        if (o1 == Status.TURN) {

        }
        if (o1 == Status.RIVER) {

        }
        if (o1 == Status.SHOWDOWN) {

        }
        if (o1 == Status.SPLITPOT) {

        }
        if (o1 == Status.END_MATCH) {

        }
        if (o1 == Status.END_GAME) {

        }

        //TODO faire plein d'updates différents pcq faire des try c'est vrmt de la merde !!!
        // view.getPokerTable().setStatus(model.getStatus().toString());
        //  view.getPokerTable().setPot(Integer.toString(model.getPot()));
//        for (Card card : model.getBoard()) {//TODO c'est nul , faut pas  recréer tout le board à chaque fois, faudrait rajouter seulement les nouvelles cartes sur la table.
//            CardView cardView = new CardView();
//            cardView.setColor(card.getColor().toString());
//            cardView.setValue(card.getValue().toString());
//            view.getPokerTable().addCard(cardView);
//        }
        /*
         * @SRV update les différents éléments de la vue via la facade de la
         * vue( PokerView), je mettrai probablement des updates dans chaque vue,
         * qui seront appelés depuis la classe facade PokerView
         */
    }
}

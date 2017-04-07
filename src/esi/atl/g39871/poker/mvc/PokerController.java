package esi.atl.g39871.poker.mvc;

import esi.atl.g39871.poker.model.Game;
import esi.atl.g39871.poker.model.GameException;
import esi.atl.g39871.poker.view.PokerView;

public class PokerController{

    Game model;

    PokerView view;

    public PokerController(Game model) { //TODO changer la déclaration par les interfaces facade par ex ? 
        this.model = model;
        view = new PokerView(this, model);
        model.addObserver(view);

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
            model.raise(view.getBetAmount());
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    public void smallBlind() {
        try {
            model.smallBlind(view.getBetAmount());
        } catch (GameException ex) {
            view.alert(ex.getMessage());
        }
    }

    public void bigBlind() {
        try {
            model.bigBlind(view.getBetAmount());
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

//    @Override //TODO changer ca, c'est pas du tout MVC ça !! voir https://www.google.fr/search?q=mvc&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjYoay1_ZLTAhXIrRoKHfaiCM4Q_AUICCgB&biw=1229&bih=548#tbm=isch&q=mvc+java&imgrc=QPgdEDnOC9Bu9M:
//    public void update(Observable o, Object o1) {
//        view.getPokerTable().setStatus(o1.toString()); //show the status
//
//        if (o1 == Status.INIT && model.getPlayers().size() >= 4) {
//            view.enableStartButton(true);
//        }
//
//        if (o1 == Status.BLIND) {
//            view.enableStartButton(false);
//            view.enableStopButton(true);
//            view.enableAllInButton(true);
//            view.enableAmountField(true);
//            view.enableBigBlindButton(true);
//            view.enableCallButton(true);
//            view.enableFoldButton(true);
//            view.enableRaiseButton(true);
//            view.enableSmallBlindButton(true);
//
//            view.setSmallBlindValue(Integer.toString(model.getSmallBlindValue()));
//
//            view.getPokerTable().setPot(Integer.toString(model.getPot()));
//            updatePlayers();
//
//        }
//        if (o1 == Status.PREFLOP) {
//            updatePlayers();
//
//        }
//        if (o1 == Status.FLOP) {
//            updatePlayers();
//
//        }
//        if (o1 == Status.TURN) {
//            updatePlayers();
//
//        }
//        if (o1 == Status.RIVER) {
//            updatePlayers();
//
//        }
//        if (o1 == Status.SHOWDOWN) {
//            updatePlayers();
//
//        }
//        if (o1 == Status.SPLITPOT) {
//            updatePlayers();
//
//        }
//        if (o1 == Status.END_MATCH) {
//            updatePlayers();
//
//        }
//        if (o1 == Status.END_GAME) {
//
//        }
//
//
//        /*
//         * @SRV update les différents éléments de la vue via la facade de la
//         * vue( PokerView), je mettrai probablement des updates dans chaque vue,
//         * qui seront appelés depuis la classe facade PokerView
//         */
//    }

//    private void updatePlayers() {
//
//        view.clearPlayersLayout();
//
//        for (Player player : model.getPlayers()) {
//            view.addPlayerInLayout(player);
//        }
//    }

}














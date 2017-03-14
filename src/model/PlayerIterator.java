package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author esiProfs
 */
public class PlayerIterator implements Iterator<Player> {

    /**
     *
     */
    protected List<Player> players;

    /**
     *
     */
    protected int current;

    /**
     *
     */
    protected int startIndex;

    /**
     *
     */
    protected int buttonIndex;

    /**
     *
     * @param players
     * @throws GameException
     */
    public PlayerIterator(List<Player> players) throws GameException {
        this.players = players;
        this.buttonIndex = findButton();
        this.current = 0;
        this.startIndex = buttonIndex;
    }

    /**
     *
     * @param other
     * @throws GameException
     */
    public PlayerIterator(PlayerIterator other) throws GameException {
        this.players = other.players;
        this.buttonIndex = findButton();
        this.current = 0;
        this.startIndex = other.buttonIndex;
    }

    /**
     *
     * @return @throws GameException
     */
    protected final int findButton() throws GameException {
        int index = -1;
        boolean button = false;
        Iterator<Player> iterator = players.iterator();
        while (!button && iterator.hasNext()) {
            Player next = iterator.next();
            button = next.hasButton();
            index++;
        }
        if (!button) {
            throw new GameException("Boutton non distribué");
        }
        return index;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = current < players.size();
        return hasNext;
    }

    @Override
    public Player next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int index = (current + startIndex) % players.size();

        current = findNext(current);
        return players.get(index);
    }

    /**
     *
     * @param build
     * @return
     */
    protected int findNext(int build) {
        int index;
        do {
            build++;
            index = (build + startIndex) % players.size();
        } while (hasNext() && !players.get(index).canPlay());
        return build;
    }

    /**
     *
     * @return
     */
    public boolean onlyOne() {
        Iterator<Player> iterator = players.iterator();
        int count = 0;
        while (count < 2 && iterator.hasNext()) {
            Player next = iterator.next();
            if (next.canPlay()) {
                count++;
            }
        }
        return count == 1;
    }

    List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}

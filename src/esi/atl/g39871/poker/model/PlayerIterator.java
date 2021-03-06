package esi.atl.g39871.poker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A player's iterator
 * 
 * @author g39871
 */
public class PlayerIterator implements Iterator<Player> {

  /**
   * a container for all players
   */
  protected List<Player> players;

  /**
   * the index of the current player
   */
  protected int current;

  /**
   * Index used to place a starting delimitor for searching and looking into the list of players
   */
  protected int startIndex;

  /**
   *
   */
  protected int buttonIndex;

  /**
   * Create a new instance of PlayerIterator
   *
   * @param players the list of players.
   * @throws GameException if the button was not given
   */
  public PlayerIterator(List<Player> players) throws GameException {
    this.players = players;
    this.buttonIndex = findButton();
    this.current = 0;
    this.startIndex = buttonIndex;
  }

  /**
   * Create a new instance of PlayerIterator based on an existying one .
   *
   * @param other the existying PlayerIterator
   * @throws GameException if the button was not given
   */
  public PlayerIterator(PlayerIterator other) throws GameException {
    this.players = other.players;
    this.buttonIndex = findButton();
    this.current = 0;
    this.startIndex = other.buttonIndex;
  }

  /**
   * Find the index of the player who have the button
   *
   * @return the player's index
   * @throws GameException if the button was not given
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
   * Find who will be the next player to play
   *
   * @param build the current player's index
   * @return the next player to play
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
   * Check if there's only one player remaining.
   *
   * @return true if it remains only one player, false otherwise
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

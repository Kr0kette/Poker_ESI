package esi.atl.g39871.poker.model;

import esi.atl.g39871.poker.database.DBManager;
import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.persistence.dto.GameHistoryDto;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.seldto.GameHistorySel;
import esi.atl.g39871.poker.seldto.PlayerSel;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Contains functions avaible for administrators.
 *
 * @author g39871
 */
public class FacadeDB {

  public static int getNewIdGame() {
    int id = 0;// todo a faire
    return id;
  }

  public static int addGameHistory(int idGame, String namePlayer, int gain, String handCategory)
      throws PokerModelException {
    int i;

    try {
      GameHistoryDto gameHistory = new GameHistoryDto(idGame, namePlayer, gain, handCategory);

      DBManager.startTransaction();
      i = GameHistoryBl.add(gameHistory);
      DBManager.validateTransaction();
      return i;

    } catch (PokerDbException pDB) {
      String msg = pDB.getMessage();
      try {
        DBManager.cancelTransaction();
      } catch (PokerDbException ex) {
        msg = ex.getMessage() + "\n" + msg;
      } finally {
        throw new PokerModelException("Unable to add the GameHistory! \n" + msg);
      }
    }
  }
  
  
  public static Collection<GameHistoryDto> getSelectedGamesHistory(GameHistorySel sel) throws PokerModelException {
    try {
      DBManager.startTransaction();
      Collection<GameHistoryDto> col = GameHistoryBl.find(sel);
      DBManager.validateTransaction();
      return col;
    } catch (PokerDbException eDB) {
      String msg = eDB.getMessage();
      try {
        DBManager.cancelTransaction();
      } catch (PokerDbException ex) {
        msg = ex.getMessage() + "\n" + msg;
      } finally {
        throw new PokerModelException("List of GameHistory is  not accesible! \n" + msg);
      }
    }

  }
  
  
  public static Collection<PlayerDto> getSelectedPlayers(PlayerSel sel) throws PokerModelException {
    try {
      DBManager.startTransaction();
      Collection<PlayerDto> col = PlayerBl.find(sel);
      DBManager.validateTransaction();
      return col;
    } catch (PokerDbException eDB) {
      String msg = eDB.getMessage();
      try {
        DBManager.cancelTransaction();
      } catch (PokerDbException ex) {
        msg = ex.getMessage() + "\n" + msg;
      } finally {
        throw new PokerModelException("List of players is  not accesible! \n" + msg);
      }
    }

  }
  
  

  /**
   * Adds a player with the given arguments to the database manager if it doesn't already exist
   *
   * @param name the player's name
   * @param money the player's money
   * @return id the added player's id
   * @throws esi.atl.g39871.poker.exception.PokerModelException when the add fails.
   */
  public static int addPlayer(String name, int money) throws PokerModelException {
    int i;
    try {
      Collection<PlayerDto> playerFound = PlayerBl.findByName(new PlayerSel(name));

      if (playerFound.isEmpty()) {
        PlayerDto player = new PlayerDto(name, money);
        DBManager.startTransaction();
        i = PlayerBl.add(player);
        DBManager.validateTransaction();

      } else {
        ArrayList<PlayerDto> al = new ArrayList<>(playerFound);
        i = al.get(0).getId();
      }
      return i;

    } catch (PokerDbException pDB) {
      String msg = pDB.getMessage();
      try {
        DBManager.cancelTransaction();
      } catch (PokerDbException ex) {
        msg = ex.getMessage() + "\n" + msg;
      } finally {
        throw new PokerModelException("Unable to add the player! \n" + msg);
      }
    }
  }

  

  /**
   * Returns the player by its id as a <code>PlayerDto</code>
   *
   * @param id the player's id to find
   * @return PlayerDto the player if found, null otherwise.
   * @throws esi.atl.g39871.poker.exception.PokerModelException
   */
  public static PlayerDto getPlayerById(int id) throws PokerModelException {
    try {
      DBManager.startTransaction();
      PlayerDto player = PlayerBl.findById(id);
      DBManager.validateTransaction();
      return player;
    } catch (PokerDbException eDB) {
      String msg = eDB.getMessage();
      try {
        DBManager.cancelTransaction();
      } catch (PokerDbException ex) {
        msg = ex.getMessage() + "\n" + msg;
      } finally {
        throw new PokerModelException("Unable to access the player! \n" + msg);
      }
    }
  }

  /**
   * update the player in the database manager with the given player
   *
   * @param player the player to update
   * @throws esi.atl.g39871.poker.exception.PokerModelException
   */
  public static void updatePlayer(PlayerDto player) throws PokerModelException {
    try {

      DBManager.startTransaction();
      PlayerBl.update(player);
      DBManager.validateTransaction();
    } catch (PokerDbException eDB) {
      String msg = eDB.getMessage();
      try {
        DBManager.cancelTransaction();
      } catch (PokerDbException ex) {
        msg = ex.getMessage() + "\n" + msg;
      } finally {
        throw new PokerModelException("impossible to update the player! \n" + msg);
      }
    }
  }

}

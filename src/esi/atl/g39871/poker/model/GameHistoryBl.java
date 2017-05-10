package esi.atl.g39871.poker.model;

import esi.atl.g39871.poker.database.GameHistoryDB;
import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.persistence.dto.GameHistoryDto;
import esi.atl.g39871.poker.seldto.GameHistorySel;
import java.util.Collection;

/**
 *
 * @author g39871
 */
public class GameHistoryBl {

  static GameHistoryDto findById(int id) throws PokerDbException {
    GameHistorySel sel = new GameHistorySel(id);
    Collection<GameHistoryDto> col = GameHistoryDB.getCollection(sel);
    if (col.size() == 1) {
      return col.iterator().next();
    } else {
      return null;
    }


  }


  static Collection<GameHistoryDto> find(GameHistorySel sel) throws PokerDbException {
    return GameHistoryDB.getCollection(sel);
  }
  


  static int add(GameHistoryDto gameHistory) throws PokerModelException {

    try {
      if (!gameHistory.isPersistant()) {
        return GameHistoryDB.insertDb(gameHistory);
      } else {
        throw new PokerModelException(
            "GameHistory: we can't make a persistant object persistant again !");
      }
    } catch (PokerDbException eDB) {
      throw new PokerModelException(eDB.getMessage());
    }

  }



  static void delete(GameHistorySel sel) throws PokerModelException {
    try {
      GameHistoryDB.deleteDb(sel);
    } catch (PokerDbException eDB) {
      throw new PokerModelException(eDB.getMessage());
    }
  }
}

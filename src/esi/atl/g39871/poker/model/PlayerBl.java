package esi.atl.g39871.poker.model;

import esi.atl.g39871.poker.database.PlayerDB;
import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.seldto.PlayerSel;
import java.util.Collection;

/**
 * This class consists of static utility methods for player's managing
 * @author g39871
 */
public class PlayerBl {


    static PlayerDto findById(int id) throws PokerDbException {
         PlayerSel sel = new PlayerSel(id);
        Collection<PlayerDto> col = PlayerDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }

        /*
         * peu de lignes suffiront
         */
    }
//
//    static Collection<PlayerDto> findAll() throws PokerDbException {
//        return PlayerDB.getAllPlayers();
//    }

    static Collection<PlayerDto> find(PlayerSel sel) throws PokerDbException {
        return PlayerDB.getCollection(sel);
    }

    static int add(PlayerDto player) throws PokerModelException {
        
        try {
            if (!player.isPersistant()) {
                return PlayerDB.insertDb(player);
            } else {
                throw new PokerModelException("Player: we can't make a persistant object persistant again !");
            }
        } catch (PokerDbException eDB) {
            throw new PokerModelException(eDB.getMessage());
        }

    }

    static void update(PlayerDto player) throws PokerModelException {
        try {
            PlayerDB.updateDb(player);
        } catch (PokerDbException eDB) {
            throw new PokerModelException(eDB.getMessage());
        }
    }

    static void delete(String name) throws PokerModelException {
        try {
            PlayerDB.deleteDb(name);
        } catch (PokerDbException eDB) {
            throw new PokerModelException(eDB.getMessage());
        }
    }
}



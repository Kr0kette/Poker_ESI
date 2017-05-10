package esi.atl.g39871.poker.model;

import esi.atl.g39871.poker.database.DBManager;
import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.seldto.PlayerSel;
import java.util.Collection;

/**
 * Contains functions avaible for administrators.
 * @author g39871
 */
public class AdminFacadeDB {
   
    
    /**
     * Adds the given player to the database manager 
     *
     * @param player the player to add
     * @return id the added player's id 
     * @throws esi.atl.g39871.poker.exception.PokerModelException when the add fail.
     */
    public static int addPlayer(PlayerDto player) throws PokerModelException {
        try {
            DBManager.startTransaction();
            int i = PlayerBl.add(player);
            DBManager.validateTransaction();
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
     * Returns the player by its name as a <code>PlayerDto</code>
     * @param id the player's id to find 
     * @return PlayerDto the player if found, null otherwise.
     * @throws esi.atl.g39871.poker.exception.PokerModelException
     */
    public static PlayerDto getPlayerById(int id) throws PokerModelException {
        try {
            DBManager.startTransaction();
            PlayerDto player =PlayerBl.findById(id);
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
            System.out.println("maj");
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

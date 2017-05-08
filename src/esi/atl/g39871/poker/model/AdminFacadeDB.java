package esi.atl.g39871.poker.model;

import esi.atl.g39871.poker.database.DBManager;
import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.exception.PokerModelException;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;

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
            int i = BrandBl.add(brand);
            DBManager.validateTransaction();
            return i;
        } catch (EsaleDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (EsaleDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new EsaleBusinessException("Ajout de marque impossible! \n" + msg);
            }
        }
    }
    
    
}

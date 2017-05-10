package esi.atl.g39871.poker.database;

import esi.atl.g39871.poker.exception.PokerDbException;

/**
 * Sequences's persistence's manager's access class
 *
 * @author g39871
 */
public class SequenceDB {

    public static final String PLAYER = "Player";

    public static final String GAMEHISTORY = "GameHistory";



    public static synchronized int getNextNum(String sequence) throws PokerDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            String query = "Update SEQUENCES set sValue = sValue+1 where id='" + sequence + "'";
            java.sql.PreparedStatement update = connexion.prepareStatement(query);
            update.execute();
            java.sql.Statement stmt = connexion.createStatement();
            query = "Select sValue FROM Sequences where id='" + sequence + "'";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("sValue");
                return nvId;
            } else {
                throw new PokerDbException("new sequence's No impossible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new PokerDbException("New sequence's No impossible!\n" + eSQL.getMessage());
        }
    }

}

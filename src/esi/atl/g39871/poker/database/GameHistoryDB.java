package esi.atl.g39871.poker.database;

import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.persistence.dto.GameHistoryDto;
import esi.atl.g39871.poker.seldto.GameHistorySel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g39871
 */
public class GameHistoryDB {

    public static void deleteDb(GameHistorySel sel) throws PokerDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from GameHistory where name=" + sel.getIdGame()); //todo a revoir la secu de la requete
        } catch (Exception ex) {
            throw new PokerDbException("GameHistory: deletion impossible \n" + ex.getMessage());
        }
    }

    public static List<GameHistoryDto> getCollection(GameHistorySel sel) throws PokerDbException {

        List<GameHistoryDto> al = new ArrayList<>();
        try {
            String query = "Select id, idGame, namePlayer, gain, handCategory FROM GameHistory ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where += " id = ? ";
            }
            if (sel.getIdGame() != 0) {
                if (!where.equals("")) {
                    where += " AND ";
                }
                where += " idGame = ? ";
            }

            if (sel.getNamePlayer() != null && !sel.getNamePlayer().equals("")) {
                if (!where.equals("")) {
                    where += " AND ";
                }
                where += "namePlayer = ?";
            }

            if (!where.equals("")) {
                where += " AND ";
            }
            where += " Gain = ? ";

            if (sel.getHandCategory() != null && !sel.getHandCategory().equals("")) {
                if (!where.equals("")) {
                    where += " AND ";
                }
                where += "handCategory = ? ";
            }

            if (where.length() != 0) {
                where = " where " + where;
                query += where;
            }

            query += " order by namePlayer";

            stmt = connexion.prepareStatement(query);

            int i = 1;
            if (sel.getId() != 0) {
                stmt.setInt(i, sel.getId());
                i++;
            }

            if (sel.getId() != 0) {
                stmt.setInt(i, sel.getIdGame());
                i++;
            }

            if (sel.getNamePlayer() != null && !sel.getNamePlayer().equals("")) {
                stmt.setString(i, sel.getNamePlayer());
                i++;
            }

            stmt.setInt(i, sel.getGain());
            i++;

            if (sel.getHandCategory() != null && !sel.getHandCategory().equals("")) {
                stmt.setString(i, sel.getHandCategory());
                i++;
            }

            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                al.add(new GameHistoryDto(rs.getInt("Id"), rs.getInt("idGame"), rs.getString("namePlayer"),
                        rs.getInt("gain"), rs.getString("handCategory")));

            }
        } catch (java.sql.SQLException eSQL) {
            throw new PokerDbException(
                    "GameHistory's instanciation impossible:\rSQLException: " + eSQL.getMessage());
        }

        return al;

    }

    public static int insertDb(GameHistoryDto gameHistory) throws PokerDbException {

        try {

            //todo ici il faut voir pour avoir une sequence sur l'id et sur idgame, idgame 
            int num = SequenceDB.getNextNum(SequenceDB.GAMEPLAYER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;

            insert = connexion
                    .prepareStatement("Insert into GameHistory(id,idGame,namePlayer,gain,handCategory) values(?, ?, ?, ?, ?)");
            insert.setInt(1, num);
            insert.setInt(2, gameHistory.getIdGame());
            insert.setString(3, gameHistory.getNamePlayer());
            insert.setInt(4, gameHistory.getGain());
            insert.setString(5, gameHistory.getHandCategory());
            insert.execute();
            return num;
        } catch (Exception ex) {
            throw new PokerDbException("gameHistory: add gameHistory impossible \r" + ex.getMessage());
        }
    }

}

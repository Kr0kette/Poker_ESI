package esi.atl.g39871.poker.database;

import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.persistence.dto.ReviewDto;
import esi.atl.g39871.poker.seldto.ReviewSel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g39871
 */
public class ReviewDB {
      public static void deleteDb(ReviewSel sel) throws PokerDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Review where idGame=" + sel.getIdGame() + "AND namePlayer=" +sel.getNamePlayer()); //todo a revoir la secu de la requete
        } catch (Exception ex) {
            throw new PokerDbException("Review: deletion impossible \n" + ex.getMessage());
        }
    }

    public static List<ReviewDto> getCollection(ReviewSel sel) throws PokerDbException {

        List<ReviewDto> al = new ArrayList<>();
        try {
            String query = "Select id, idGame, namePlayer, rating, details FROM Review ";
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

            if (sel.getRating() != 0) {
                if (!where.equals("")) {
                    where += " AND ";
                }

                where += " Rating = ? ";
            }

            if (sel.getDetails() != null && !sel.getDetails().equals("")) {
                if (!where.equals("")) {
                    where += " AND ";
                }
                where += "details = ? ";
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
            if (sel.getRating() != 0) {
                stmt.setInt(i, sel.getRating());
                i++;
            }

            if (sel.getDetails() != null && !sel.getDetails().equals("")) {
                stmt.setString(i, sel.getDetails());
                i++;
            }

            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                al.add(new ReviewDto(rs.getInt("Id"), rs.getInt("idGame"), rs.getString("namePlayer"),
                        rs.getInt("rating"), rs.getString("details")));

            }
        } catch (java.sql.SQLException eSQL) {
            throw new PokerDbException(
                    "Review's instanciation impossible:\rSQLException: " + eSQL.getMessage());
        }

        return al;

    }

    public static int insertDb(ReviewDto review) throws PokerDbException {

        try {

      
            int num = SequenceDB.getNextNum(SequenceDB.REVIEW);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;

            insert = connexion
                    .prepareStatement("Insert into Review(id,idGame,namePlayer,rating,details) values(?, ?, ?, ?, ?)");
            insert.setInt(1, num);
            insert.setInt(2, review.getIdGame());
            insert.setString(3, review.getNamePlayer());
            insert.setInt(4, review.getRating());
            insert.setString(5, review.getDetails());
            insert.execute();
            return num;
        } catch (Exception ex) {
            throw new PokerDbException("review: add review impossible \r" + ex.getMessage());
        }
    }

}

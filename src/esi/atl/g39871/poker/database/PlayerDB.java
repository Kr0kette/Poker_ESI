package esi.atl.g39871.poker.database;

import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.seldto.PlayerSel;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Player's persistance's manager's access class
 *
 * @author g39871
 */
public class PlayerDB {

//
//  public static List<PlayerDto> getAllPlayers() throws PokerDbException {
//      
//      List<PlayerDto> player = getCollection(new PlayerSel(0));
//      return player;
//
//    /*
//     * contentez- vous de faire appel à la méthode écrite à l'étape précédente en lui passant un
//     * paramètre adapté
//     */
//  }
    public static void updateDb(PlayerDto player) throws PokerDbException {
        try {
            java.sql.Connection connection = DBManager.getConnection();

            java.sql.PreparedStatement update;
          
            update = connection
                    .prepareStatement("Update player set " + "money=?, lastConnection=?" + "where name= ?");
            
            if (player.getMoney() == -1) { // quite equivallent to null 
                update.setNull(1, Types.INTEGER);
            } else {
                update.setInt(1, player.getMoney());
            }
            
            
            if (player.getLastConnection() == null) {
                update.setNull(2,Types.VARCHAR);
            } else {
                update.setString(2, player.getLastConnection().toString());
            }
            update.setString(3, player.getName());
            update.executeUpdate();
        } catch (Exception ex) {
            throw new PokerDbException("Player, unable to modify :\n" + ex.getMessage());
        }
    }

    public static int insertDb(PlayerDto player) throws PokerDbException {

        try {
            int num = SequenceDB.getNextNum(SequenceDB.PLAYER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;

            insert = connexion.prepareStatement("Insert into Player(id,name,money,lastConnection) values(?, ?, ?, ?)");
            insert.setInt(1, num);
            insert.setString(2, player.getName());
            insert.setInt(3, player.getMoney());
            insert.setString(4, player.getLastConnection().toString());
            insert.execute();
            return num;
        } catch (Exception ex) {
            throw new PokerDbException("Player: add player impossible \r" + ex.getMessage());
        }

    }

//  public static List<PlayerDto> getCollection(PlayerSel sel) throws PokerDbException {
//
//
//    List<PlayerDto> al = new ArrayList<>();
//    try {
//      String query = "Select id, name, money, lastConnection FROM Player ";
//      java.sql.Connection connexion = DBManager.getConnection();
//      java.sql.PreparedStatement stmt;
//      String where = "";
//      if (sel.getId() != 0) {
//        where += " id = ? ";
//      }
//      if (sel.getName()!= null && !sel.getName().equals("")) {
//        if (!where.equals("")) {
//          where += " AND ";
//        }
//        where += " name = ? ";
//      }
//
//      if (sel.getLastConnection()!= null) {
//        if (!where.equals("")) {
//          where += " AND lastConnection = ?";
//        }
//      }
//      
//      if(sel.getMoney()>=0){
//          where += "AND money = ? ";
//      }
//      
//
//      if (where.length() != 0) {
//        where = " where " + where;
//        query += where;
//      }
//      query += " order by name";
//      stmt = connexion.prepareStatement(query);
//
//      int i = 1;
//      if (sel.getId() != 0) {
//        stmt.setInt(i, sel.getId());
//        i++;
//      }
//
//      if (sel.getName() != null && !sel.getName().equals("")) {
//        stmt.setString(i, sel.getName() + "%");
//        i++;
//      }
//      java.sql.ResultSet rs = stmt.executeQuery();
// 
//
//      while (rs.next()) {
//        al.add(new PlayerDto(rs.getInt("Id"), rs.getString("name"),rs.getInt("money"), rs.getDate("lastConnection")));
//      }
//    } catch (java.sql.SQLException eSQL) {
//      throw new PokerDbException(
//          "Player's instanciation impossible:\rSQLException: " + eSQL.getMessage());
//    }
//
//    return al;
//
//  }
    public static List<PlayerDto> getCollection(PlayerSel sel) throws PokerDbException {

        List<PlayerDto> al = new ArrayList<>();
        try {
            String query = "Select id, name, money, lastConnection FROM Player ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where += " id = ? ";
            }
            if (sel.getName() != null && !sel.getName().equals("")) {
                if (!where.equals("")) {
                    where += " AND ";
                }
                where += " name = ? ";
            }

            if (sel.getLastConnection() != null) {
                if (!where.equals("")) {
                    where += " AND ";
                }
                where += "lastConnection = ?";
            }

            //todo voir plus pas dans la methode, s'il faut pas faire qq chose avec where like au lieu de where = 
            if (sel.getMoney() >= 0) {
                if (!where.equals("")) {
                    where += " AND ";
                }
                where += "money = ? ";
            }

            if (where.length() != 0) {
                where = " where " + where;
                query += where;
            }

            query += " order by name";

            stmt = connexion.prepareStatement(query);

            int i = 1;
            if (sel.getId() != 0) {
                stmt.setInt(i, sel.getId());
                i++;
            }

            /*
             * todo en fait ce if est utile dans le cas ou, plus haut on met where name like au lieu
             * de where name = . Ca permetrait de retrouver tous les joueurs avec un certain pattern
             * de nom, mais du coup on pourrait recupêrer le preparedstatement et rajouter le like %
             * dans une autre methode ou quoi, j'sais pas trop
             */
//            if (sel.getName() != null && !sel.getName().equals("")) {
//                stmt.setString(i, sel.getName() + "%");
//                i++;
//            }
            if (sel.getName() != null && !sel.getName().equals("")) {
                stmt.setString(i, sel.getName());
                i++;
            }

            if (sel.getLastConnection() != null) {
                stmt.setString(i, sel.getLastConnection().toString());
                i++;
            }

            if (sel.getMoney() >= 0) {
                stmt.setString(i, Integer.toString(sel.getMoney()));
                i++;
            }

            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                al.add(new PlayerDto(rs.getInt("Id"), rs.getString("name"), rs.getInt("money"), (new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH)).parse(rs.getString("lastConnection"))));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new PokerDbException(
                    "Player's instanciation impossible:\rSQLException: " + eSQL.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PlayerDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;

    }

    /**
     * Delete a player from the database
     *
     * @param name the player's name to delete
     * @throws PokerDbException
     */
    public static void deleteDb(String name) throws PokerDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from player where name=" + name);
        } catch (Exception ex) {
            throw new PokerDbException("Player: deletion impossible \n" + ex.getMessage());
        }
    }
}

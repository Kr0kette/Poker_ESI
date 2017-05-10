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
  // public static List<PlayerDto> getAllPlayers() throws PokerDbException {
  //
  // List<PlayerDto> player = getCollection(new PlayerSel(0));
  // return player;
  //
  // /*
  // * contentez- vous de faire appel à la méthode écrite à l'étape précédente en lui passant un
  // * paramètre adapté
  // */
  // }
  public static void updateDb(PlayerDto player) throws PokerDbException {
    try {
      java.sql.Connection connection = DBManager.getConnection();

      java.sql.PreparedStatement update;

      update = connection
          .prepareStatement("Update player set " + "money=money + ?, lastConnection=?" + "where name= ?");

        System.out.println(player.getMoney());
        update.setInt(1, player.getMoney());
      

      if (player.getLastConnection() == null) {
        update.setNull(2, Types.VARCHAR); //on pourrait utiliser comme dans les methode getCollection des i++ pour pas être obligé de fournir toutes les colonnes lors de l'update !
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

      insert = connexion
          .prepareStatement("Insert into Player(id,name,money,lastConnection) values(?, ?, ?, ?)");
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
        where += " name like ? ";
      }

      if (sel.getLastConnection() != null) {
        if (!where.equals("")) {
          where += " AND ";
        }
        where += "lastConnection = ?";
      }

      // todo voir plus pas dans la methode, s'il faut pas faire qq chose avec where like au lieu de
      // where =
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

      if (sel.getName() != null && !sel.getName().equals("")) {
        stmt.setString(i, sel.getName() + "%");
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
        al.add(new PlayerDto(rs.getInt("Id"), rs.getString("name"), rs.getInt("money"),
            (new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH))
                .parse(rs.getString("lastConnection"))));
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
   * Search only one player with exactly the given name (not case sensitive, which means that
   * 'PieRre' and 'pierre' is the SAME name)
   *
   * @param sel the given player
   * @return the player corresponding to the given player's name , otherwise empty colletion
   * @throws PokerDbException
   */
  public static List<PlayerDto> getCollectionByName(PlayerSel sel) throws PokerDbException {

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
      
        
       if (where.length() != 0) {
        where = " where " + where;
        query += where;
      }

      stmt = connexion.prepareStatement(query);

      int i = 1;
      if (sel.getId() != 0) {
        stmt.setInt(i, sel.getId());
        i++;
      }

      if (sel.getName() != null && !sel.getName().equals("")) {
        stmt.setString(i, sel.getName());
        i++;
      }
    

      java.sql.ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        al.add(new PlayerDto(rs.getInt("Id"), rs.getString("name"), rs.getInt("money"),
            (new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH))
                .parse(rs.getString("lastConnection"))));
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

package esi.atl.g39871.poker.database;

import esi.atl.g39871.poker.exception.PokerDbException;
import esi.atl.g39871.poker.persistence.dto.PlayerDto;
import esi.atl.g39871.poker.seldto.PlayerSel;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Player's persistance's manager's access class
 * @author g39871
 */
public class PlayerDB {
   



  public static List<PlayerDto> getAllPlayers() throws PokerDbException {
      
      List<PlayerDto> player = getCollection(new PlayerSel(0));
      return player;

    /*
     * contentez- vous de faire appel à la méthode écrite à l'étape précédente en lui passant un
     * paramètre adapté
     */
  }

  public static void updateDb(PlayerDto player) throws PokerDbException {
    try {
      java.sql.Connection connection = DBManager.getConnection();

      java.sql.PreparedStatement update;
      update = connection
          .prepareStatement("Update player set " + " name=?," + " lastConnection=? " + "where id= ?");
      update.setString(1, player.getName());
      if (player.getLastConnection()== null) {
        update.setNull(2, Types.VARCHAR);
      } else {
        update.setDate(2, (Date) player.getLastConnection());
      }
      update.setInt(3, player.getId());
      update.executeUpdate();
    } catch (Exception ex) {
      throw new PokerDbException("Marque, modification impossible:\n" + ex.getMessage());
    }
  }

  public static int insertDb(PlayerDto player) throws PokerDbException {
    // throw new PokerDbException("!!!!!!!!!!!!!!Fonctionnalité non encore implémentée
    // !!!!!!!!!!!!");
    try {
      int num = SequenceDB.getNextNum(SequenceDB.PLAYER);
      java.sql.Connection connexion = DBManager.getConnection();
      java.sql.PreparedStatement insert;

      insert = connexion.prepareStatement("Insert into Brand(id,name,money,lastConnection) values(?, ?, ?, ?)");
      insert.setInt(1, num);
      insert.setString(2, player.getName());
      insert.setInt(3, player.getMoney());
      insert.setDate(4, (Date) player.getLastConnection());
      insert.execute();
      return num;
    } catch (Exception ex) {
      throw new PokerDbException("Player: add player impossible \r" + ex.getMessage());
    }

  }

  public static List<PlayerDto> getCollection(PlayerSel sel) throws PokerDbException {


    List<PlayerDto> al = new ArrayList<>();
    try {
      String query = "Select id, name, lastConnection FROM Player ";
      java.sql.Connection connexion = DBManager.getConnection();
      java.sql.PreparedStatement stmt;
      String where = "";
      if (sel.getId() != 0) {
        where += " id = ? ";
      }
      if (sel.getName()!= null && !sel.getName().equals("")) {
        if (!where.equals("")) {
          where += " AND ";
        }
        where += " name like ? ";
      }

      if (sel.getLastConnection()!= null) {
        if (!where.equals("")) {
          where += " AND lastConnection = ?";
        }
      }
      
      if(sel.getMoney()>=0){
          where += "AND money = ? ";
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
      java.sql.ResultSet rs = stmt.executeQuery();
 

      while (rs.next()) {
        al.add(new PlayerDto(rs.getInt("Id"), rs.getString("name"),rs.getInt("money"), rs.getDate("lastConnection")));
      }
    } catch (java.sql.SQLException eSQL) {
      throw new PokerDbException(
          "Player's instanciation impossible:\rSQLException: " + eSQL.getMessage());
    }

    return al;

  }

  public static void deleteDb(int id) throws PokerDbException {
    try {
      java.sql.Statement stmt = DBManager.getConnection().createStatement();
      stmt.execute("Delete from player where id=" + id);
    } catch (Exception ex) {
      throw new PokerDbException("Player: deletion impossible \n" + ex.getMessage());
    }
  }
}



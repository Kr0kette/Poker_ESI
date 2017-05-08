package esi.atl.g39871.poker.database;

import esi.atl.g39871.poker.exception.PokerDbException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database manager Provides connection and transaction management tools.
 *
 * @author g39871
 */
public class DBManager {

  private static Connection connection;
  // todo private static MesSettingsDeConnexion dbChoisie;

  /**
   * Returns the established connection, otherwise, by establishing one
   *
   * @return the established connection.
   */
  public static Connection getConnection() throws PokerDbException {
    // db parameters
    String url = "jdbc:sqlite:Poker.sqlite";
    // create a connection to the database
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(url);
      } catch (SQLException ex) {
        throw new PokerDbException("Connection impossible: " + ex.getMessage());
      }
    }
    return connection;
  }

  /**
   * Starts a transaction
   */
  public static void startTransaction() throws PokerDbException {
    try {

      getConnection().setAutoCommit(false);
    } catch (SQLException ex) {
      throw new PokerDbException("Impossible to start the transaction: " + ex.getMessage());
    }
  }

  /**
   *
   * Starts a transaction by specifying its isolation's level.
   */
  public static void startTransaction(int isolationLevel) throws PokerDbException {
    try {
      getConnection().setAutoCommit(false);

      int isol = 0;
      switch (isolationLevel) {
        case 0:
          isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
          break;
        case 1:
          isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
          break;
        case 2:
          isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
          break;
        case 3:
          isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
          break;
        default:
          throw new PokerDbException("Degré d'isolation inexistant!");
      }
      getConnection().setTransactionIsolation(isol);
    } catch (SQLException ex) {
      throw new PokerDbException("Impossible de démarrer une transaction: " + ex.getMessage());
    }
  }

  /**
   * Validate the current transaction.
   */
  public static void validateTransaction() throws PokerDbException {
    try {
      getConnection().commit();
      getConnection().setAutoCommit(true);
    } catch (SQLException ex) {
      throw new PokerDbException("Impossible to validate the transaction: " + ex.getMessage());
    }
  }

  /**
   * Cancel the current transaction
   */
  public static void cancelTransaction() throws PokerDbException {
    try {
      getConnection().rollback();
      getConnection().setAutoCommit(true);
    } catch (SQLException ex) {
      throw new PokerDbException("Impossible to cancel the transaction: " + ex.getMessage());
    }
  }
}

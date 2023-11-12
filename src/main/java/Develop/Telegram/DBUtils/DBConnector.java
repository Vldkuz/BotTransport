package Develop.Telegram.DBUtils;

import Develop.Telegram.UserHolder.Session;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBConnector {

  public Connection connectToDB(String dbName) {
    Connection connect = null;

    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    try {
      connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName);

      if (connect != null) {
        System.out.println("Соединение с БД установлено");
      } else {
        System.out.println("Соединение с БД не установлено");
      }

    } catch (Exception e) {
      throw new RuntimeException("Не смогли получить доступ к БД");
    }

    return connect;
  }

  public void createTableSessions(Connection connect, String tableName) {
    Statement statement;

    try {
      String query = "create table if not exists " + tableName
          + " ( name text, chatID text, CONSTRAINT chatID unique (chatID), stations text[] );";
      statement = connect.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.out.println("Не получилось выполнить запрос sql");
    }
  }

  public void insertRowSession(Connection connect, String tableName, Session session,
      String chatId) {
    Statement statement;
    String stackStations = "";

    while (session.getInfoHolder().hasStation()) {
      stackStations += "'" + session.getInfoHolder().popStation() + "', ";
    }

    stackStations = stackStations.substring(0, stackStations.length() - 2);
    String query =
        "insert into " + tableName + "(name, chatID, stations) values (" + session.getInfoHolder()
            .getName() + "," + "'" + chatId + "'"
            + "," + "ARRAY [" + stackStations + "]) on conflict do nothing;";

    try {
      statement = connect.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.out.println("Не удалось добавить запись в БД");
    }
  }

  public Session readData(Connection conn, String tableName, String chatId, String keyAPIYandex) {
    Statement statement;
    ResultSet res = null;
    Session curSession = new Session(keyAPIYandex);

    try {
      String query = "select * from " + tableName + "where chatID = '" + chatId + "';";
      statement = conn.createStatement();
      res = statement.executeQuery(query);
      curSession.getInfoHolder().setName(res.getString("name"));

      Array stationsDB = res.getArray("stations");
      List<String> stationsRaw = (List<String>) stationsDB.getArray();

      int idx = 0;
      while (idx < stationsRaw.size()) {
        curSession.getInfoHolder().pushStation(stationsRaw.get(idx));
      }

    } catch (SQLException e) {
      System.out.println("Не удалось получить данные");
    }

    return curSession;
  }

  public boolean checkChatId(Connection connect, String tableName, String chatId)
      throws SQLException {
    Statement statement;
    ResultSet res = null;

    String query =
        "select exists (select chatID from " + tableName + " where chatID = '" + chatId + "')";
    statement = connect.createStatement();
    res = statement.executeQuery(query);
    res.next();
    return res.getBoolean("exists");
  }
}

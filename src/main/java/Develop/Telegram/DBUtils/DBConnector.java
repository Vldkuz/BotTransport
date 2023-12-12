package Develop.Telegram.DBUtils;

import Develop.Telegram.UserHolder.Session;
import Develop.Telegram.UserHolder.State;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

  public Connection connectDB;

  public DBConnector(String dbName, String datadaseURL, String portDatabase, String userDB, String userPasswd) {
    try {
      Class.forName("org.postgresql.Driver");
      String connection = String.format("jdbc:postgresql://%s:%s/%s",datadaseURL,portDatabase,dbName);

      connectDB = DriverManager.getConnection(connection, userDB,userPasswd);
      if (connectDB == null) {
        System.err.println("Не удалось подключится к БД");
      }
    } catch (Exception exception) {
      System.err.println("Произошла ошибка при подключении к БД");
      throw new RuntimeException("Ошибка при подключении к БД");
    }
  }

  public void createTableSessions(String tableName) {
    Statement statement;

    try {
      String query = String.format(
              "create table if not exists %s ( name text, chatID text, CONSTRAINT chatID unique (chatID), stations text[], state text, lastSource text, lastDestination text);",
              tableName);
      statement = connectDB.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.err.println("Не получилось выполнить запрос sql");
    }
  }

  public void insertRowSession(String tableName, Session session,
                               String chatId) {
    Statement statement;
    String stackStations = "";

    while (session.getInfoHolder().hasStation()) {
      stackStations += "'" + session.getInfoHolder().popStation() + "', ";
    }

    if (!stackStations.isEmpty()) {
      stackStations = stackStations.substring(0, stackStations.length() - 2);
    }

    String query = String.format(
            "insert into %s (name, chatID, stations, state, lastSource, lastDestination) values "
                    + "(%s , '%s', ARRAY [%s]::text[], '%s', '%s', '%s' )",
            tableName, session.getInfoHolder().getName(), chatId, stackStations, session.getState(),
            session.getInfoHolder().getLastSource(), session.getInfoHolder().getLastDestination());

    try {
      statement = connectDB.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.err.println("Не удалось добавить запись в БД");
    }
  }

  public void updateData(String tableName, Session session, String chatId) {
    Statement statement;
    String stackStations = "";

    while (session.getInfoHolder().hasStation()) {
      stackStations += "'" + session.getInfoHolder().popStation() + "', ";
    }

    if (!stackStations.isEmpty()) {
      stackStations = stackStations.substring(0, stackStations.length() - 2);
    }

    String query = String.format(
            "update %s set name = '%s', stations = ARRAY [%s]::text[], state = '%s', lastSource = '%s', lastDestination = '%s' where chatID = '%s'",
            tableName, session.getInfoHolder().getName(), stackStations, session.getState(),
            session.getInfoHolder().getLastSource(), session.getInfoHolder().getLastDestination(),
            chatId);
    try {
      statement = connectDB.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.err.println("Не удалось добавить запись в БД");
    }

  }

  public Session readData(String tableName, String chatId, String keyAPIYandex) {
    Statement statement;
    ResultSet res = null;
    Session curSession = new Session(keyAPIYandex);

    try {
      String query = String.format("select * from %s where chatID = '%s';", tableName, chatId);
      statement = connectDB.createStatement();
      res = statement.executeQuery(query);

      res.next();
      curSession.getInfoHolder().setName(res.getString("name"));
      curSession.getInfoHolder().setLastSource(res.getString("lastSource"));
      curSession.getInfoHolder().setLastDestination(res.getString("lastDestination"));

      Array stationsDB = res.getArray("stations");
      String[] array = (String[]) stationsDB.getArray();

      int idx = 0;
      while (idx < array.length) {
        curSession.getInfoHolder().pushStation(array[idx]);
        ++idx;
      }

      curSession.setState(State.valueOf(res.getString("state")));

    } catch (SQLException e) {
      System.err.println("Не удалось получить данные");
    }

    return curSession;
  }

  public boolean checkChatId(String tableName, String chatId)
          throws SQLException {
    Statement statement;
    ResultSet res = null;

    String query = String.format("select exists (select chatID from %s where chatID = '%s')",
            tableName, chatId);
    statement = connectDB.createStatement();
    res = statement.executeQuery(query);
    res.next();

    return res.getBoolean("exists");
  }
}
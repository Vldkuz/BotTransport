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

  public DBConnector(String dbName)
  {
    try {
      Class.forName("org.postgresql.Driver");
      connectDB = DriverManager.getConnection("jdbc:postgresql://"+System.getenv("DATABASE_URL") +":" + System.getenv("PORT_DB") + "/" + dbName, System.getenv("USER_DB"),System.getenv("USER_PASSWD"));
      if (connectDB == null) {
        System.err.println("Не удалось подключится к БД");
      }
    }
    catch (Exception exception) {
      System.err.println("Произошла ошибка при подключении к БД");
      throw new RuntimeException("Ошибка при подключении к БД");
    }
  }

  public void createTableSessions(String tableName) {
    Statement statement;

    try {
      String query = "create table if not exists " + tableName
          + " ( name text, chatID text, CONSTRAINT chatID unique (chatID), stations text[], state text);";
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

    if (!stackStations.isEmpty()) stackStations = stackStations.substring(0, stackStations.length() - 2);


    String query = "insert into " + tableName + " (name, chatID, stations, state) values (" + session.getInfoHolder().getName() + " ," + "'" + chatId + "'" + ", " + "ARRAY [" + stackStations + "]::text[], '" + session.getState() + "' )";

    try {
      statement = connectDB.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.err.println("Не удалось добавить запись в БД");
    }
  }

  public void updateData (String tableName, Session session, String chatId)
  {
    Statement statement;
    String stackStations = "";

    while (session.getInfoHolder().hasStation()) {stackStations += "'" + session.getInfoHolder().popStation() + "', ";}

    if (!stackStations.isEmpty())
      stackStations = stackStations.substring(0,stackStations.length() - 2);

    String query =
        "update " + tableName + " set name = '" + session.getInfoHolder().getName() + "'" + ", stations = " + "ARRAY [" + stackStations + "]::text[] ," + "state = '" + session.getState() + "'" + " where chatID = " +  "'" + chatId + "'";

    try {
      statement = connectDB.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {System.err.println("Не удалось добавить запись в БД");}

  }

  public Session readData(String tableName, String chatId, String keyAPIYandex) {
    Statement statement;
    ResultSet res = null;
    Session curSession = new Session(keyAPIYandex);

    try {
      String query = "select * from " + tableName + " where chatID = '" + chatId + "';";
      statement = connectDB.createStatement();
      res = statement.executeQuery(query);

      res.next();
      curSession.getInfoHolder().setName(res.getString("name"));

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

    String query = "select exists (select chatID from " + tableName + " where chatID = '" + chatId + "')";
    statement = connectDB.createStatement();
    res = statement.executeQuery(query);
    res.next();

    return res.getBoolean("exists");
  }
}

package Develop.Telegram.SessionHolder;

import Develop.Telegram.DBUtils.DBConnector;
import Develop.Telegram.UserHolder.Session;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class SessionUploader extends Thread {

  private boolean needUpload;
  private String APIYandexKey;
  private final HashMap<String, Session> activeSessionHolder = new HashMap<>();

  public SessionUploader(String APIYandexKey) {
    needUpload = true;
    this.APIYandexKey = APIYandexKey;
  }

  public SessionUploader() {
    needUpload = true;
  }

  public void disable() {
    needUpload = false;
  }

  public boolean isActive() {
    return needUpload;
  }

  @Override
  public void run() {
    while (needUpload) {
      // Получим список всех ключей
      Set<String> keys = activeSessionHolder.keySet();
      String sessionVictim = null;

      int minDescriptor = Integer.MAX_VALUE; // Будем искать минимум, а при запросе прибавлять к приоритету ;

      for (String key : keys) {
        int curDescriptor = activeSessionHolder.get(key).getPriority();

        if (curDescriptor < minDescriptor) {
          minDescriptor = curDescriptor;
          sessionVictim = key;
        }
      }

      if (sessionVictim != null && get(sessionVictim).getInfoHolder().hasStation()) {
        Session sessionToUp = activeSessionHolder.get(sessionVictim);
        DBConnector dbConnector = new DBConnector();
        Connection connect = dbConnector.connectToDB("Session");
        dbConnector.createTableSessions(connect, "UserInfo");

        Session sessVict = remove(sessionVictim);
        dbConnector.insertRowSession(connect, "UserInfo", sessVict, sessionVictim);

      }

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        uploadALLtoDatabase();
      }
    }
  }

  public synchronized Session get(String chatId) {
    if (activeSessionHolder.get(chatId) != null) {
      return activeSessionHolder.get(chatId);
    }

    DBConnector dbConnector = new DBConnector();
    Connection connect = dbConnector.connectToDB("Session");
    dbConnector.createTableSessions(connect, "UserInfo");

    boolean ans = false;
    try {
      ans = dbConnector.checkChatId(connect, "UserInfo", chatId);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    if (ans) {
      return dbConnector.readData(connect, "UserInfo", chatId, APIYandexKey);
    }
    ;

    Session curSession = new Session(APIYandexKey);
    put(chatId, curSession);
    return curSession;
  }

  public synchronized void put(String chatId, Session session) {
    activeSessionHolder.put(chatId, session);
  }

  public synchronized Session remove(String chatId)
  {
    return activeSessionHolder.remove(chatId);
  }

  public void uploadALLtoDatabase() {

  }
}

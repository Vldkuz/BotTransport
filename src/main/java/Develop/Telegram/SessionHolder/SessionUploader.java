package Develop.Telegram.SessionHolder;

import Develop.Telegram.DBUtils.DBConnector;
import Develop.Telegram.UserHolder.Session;
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

      if (sessionVictim != null) {
        Session sessionToUp = activeSessionHolder.get(sessionVictim);

        if  (!sessionToUp.isBlocked()) {
          DBConnector dbConnector = new DBConnector("Session");
          dbConnector.createTableSessions("UserInfo");

          Session sessVict = remove(sessionVictim);

          boolean isInDB = false;
          try {
            isInDB = dbConnector.checkChatId("UserInfo", sessionVictim);
          } catch (SQLException e) {System.err.println("Не удалось проверить в БД");}

          if (isInDB) {
            dbConnector.updateData("UserInfo", sessVict, sessionVictim);
          } else {
            dbConnector.insertRowSession("UserInfo", sessVict, sessionVictim);
          }
        }
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
      Session session = activeSessionHolder.get(chatId);
      session.addPriority();
      session.setBlocked(true);
      return session;
    }

    DBConnector dbConnector = new DBConnector("Session");
    dbConnector.createTableSessions("UserInfo");

    boolean ans = false;
    try {
      ans = dbConnector.checkChatId("UserInfo", chatId);}
    catch (SQLException e) {System.err.println("Не удалось подключиться к БД");}

    if (ans) {
      Session session  = dbConnector.readData("UserInfo", chatId, APIYandexKey);
      session.setBlocked(true);
      put(chatId,session);
      return session;
    }

    Session curSession = new Session(APIYandexKey);
    curSession.setBlocked(true);
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

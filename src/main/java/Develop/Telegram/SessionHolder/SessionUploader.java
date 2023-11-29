package Develop.Telegram.SessionHolder;

import Develop.Telegram.DBUtils.DBConnector;
import Develop.Telegram.UserHolder.Session;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUploader extends Thread {

  private boolean needUpload;
  private String APIYandexKey;
  private final ConcurrentHashMap<String, Session> activeSessionHolder = new ConcurrentHashMap<>();

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

      String sessionVictim = null;

      int minDescriptor = Integer.MAX_VALUE; // Будем искать минимум, а при запросе прибавлять к приоритету ;

      for (Map.Entry<String, Session> entry : activeSessionHolder.entrySet()) {
        Session curSession = entry.getValue();
        int curDescriptor = curSession.getPriority();


        if (curDescriptor < minDescriptor) {
          minDescriptor = curDescriptor;
          sessionVictim = entry.getKey();
        }
      }

      if (sessionVictim != null ) {
        DBConnector dbConnector = new DBConnector("Session");
        dbConnector.createTableSessions("UserInfo");

        Session sessVict = getORremoveAtomic(sessionVictim,true);

        boolean isInDB = false;
        try {
          isInDB = dbConnector.checkChatId("UserInfo", sessionVictim);
        } catch (SQLException e) {
          System.err.println("Не удалось проверить в БД");
        }

        if (isInDB) {
          dbConnector.updateData("UserInfo", sessVict, sessionVictim);
        } else {
          dbConnector.insertRowSession("UserInfo", sessVict, sessionVictim);
        }
      }

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        uploadALLtoDatabase();
      }
    }
  }


  public synchronized Session getORremoveAtomic(String chatId, boolean needRemove) {

    if (needRemove) {
      Session curSession = activeSessionHolder.get(chatId);

      while (curSession.getBlocked()) {
        try {Thread.sleep(10);} catch (InterruptedException ignored) {Thread.currentThread().interrupt();}
      }

      return activeSessionHolder.remove(chatId);
    }

    Session curSession = null;

    if (activeSessionHolder.containsKey(chatId)) {
      curSession = activeSessionHolder.get(chatId);
      curSession.addPriority();
    } else {
      DBConnector dbConnector = new DBConnector("Session");
      dbConnector.createTableSessions("UserInfo");

      boolean ans = false;
      try {
        ans = dbConnector.checkChatId("UserInfo", chatId);
      } catch (SQLException e) {
        System.err.println("Не удалось подключиться к БД");
      }

      if (ans) {
        curSession = dbConnector.readData("UserInfo", chatId, APIYandexKey);
        activeSessionHolder.put(chatId, curSession);
      }
    }

    if (curSession != null)
      return curSession;

    curSession = new Session(APIYandexKey);
    activeSessionHolder.put(chatId, curSession);
    return curSession;
  }

  public void uploadALLtoDatabase() {
    // Выгрузка всего в бд, если что-то пошло не так, то поток экстренно будится
  }
}

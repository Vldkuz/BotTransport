package Develop.Telegram.SessionHolder;

import Develop.Telegram.UserHolder.Session;

public class SessionHolder {
  private final SessionUploader sessionUploader;
  public SessionHolder(String keyAPIYandex, String dbName, String dbUrl, String dbPort, String dbUser, String dbPasswd) {
    sessionUploader = new SessionUploader(keyAPIYandex,dbName,dbUrl,dbPort,dbUser,dbPasswd);
    sessionUploader.start();
  }
  public Session get(String chatId) {return sessionUploader.getORremoveAtomic(chatId,false);}
  public void switchOFFUpload() {sessionUploader.disable();}
}
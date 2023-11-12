package Develop.Telegram.SessionHolder;

import Develop.Telegram.UserHolder.Session;

public class SessionHolder {
  private SessionUploader sessionUploader;
  public SessionHolder(String keyAPIYandex) {
    sessionUploader = new SessionUploader(keyAPIYandex);
    sessionUploader.start();
  }
  public Session get(String chatId) {return sessionUploader.get(chatId);}
  public void put(String chatId, Session curSession) {sessionUploader.put(chatId,curSession);}
}
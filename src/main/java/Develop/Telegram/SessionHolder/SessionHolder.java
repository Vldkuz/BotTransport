package Develop.Telegram.SessionHolder;

import Develop.Telegram.UserHolder.Session;

public class SessionHolder {
  private final SessionUploader sessionUploader;
  public SessionHolder(String keyAPIYandex) {
    sessionUploader = new SessionUploader(keyAPIYandex);
    sessionUploader.start();
  }
  public Session get(String chatId) {return sessionUploader.getORremoveAtomic(chatId,false);}
}
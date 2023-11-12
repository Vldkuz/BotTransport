package Develop.Telegram.UserHolder;

import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIYandex;
import Develop.KeyManager.KeyManagerWrapper;

public class Session {

  private State state = State.start; // Характеризует одно из состояний пользователя в enum (Будет выгружаться в БД)
  private InfoHolder infoHolder = new InfoHolder(); // В info будут лежать все данные по пользователю (Будет выгружаться в БД)
  private APIYandex api; // Объект, которые предоставляет функции взаимодействия с API
  private int priority = 0; // Создается с максимальным приоритетом

  public Session(String keyPointer) {
    try {
      this.api = new APIYandex(keyPointer);
    } catch (ValidationException e) {
      throw new RuntimeException("Ошибка в API ключе Яндекс");
      // При таком случае у нас недействительный API ключ в ресурсах, что говорит о том, что все функции программы недействительны
    }
  }
  public InfoHolder getInfoHolder() {
    return infoHolder;
  }

  public void setInfoHolder(InfoHolder infoHolder) {
    this.infoHolder = infoHolder;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public APIYandex getApiUser() {
    return api;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
}



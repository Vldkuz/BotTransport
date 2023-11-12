package Develop.Telegram.User;

import Develop.API.APIExceptions.ValidationException;
import Develop.API.APIYandex;
import Develop.KeyManager.KeyManagerWrapper;

public class Session {

  private State state = State.start; // Характеризует одно из состояний пользователя в enum
  private Info info; // В info будут лежать все данные по пользователю
  private APIYandex api;

  public Session() {
    KeyManagerWrapper keyHolder = new KeyManagerWrapper("/APISheduleKey");
    try {
      this.api = new APIYandex(keyHolder.getKey());
    } catch (ValidationException e) {
    }
  }


  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
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

}



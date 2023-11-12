package Develop.Telegram.bot;

import Develop.KeyManager.KeyManagerWrapper;
import Develop.Telegram.RequestHandler;
import Develop.Telegram.User.Session;
import java.util.HashMap;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

  private KeyManagerWrapper keyHolder = new KeyManagerWrapper("/APITelegramKey");
  private HashMap<Long, Session> sessionHashMap = new HashMap<>();

  @Override
  public String getBotUsername() {
    return "MainTransport_bot";
  }

  @Override
  public String getBotToken() {
    return keyHolder.getKey();
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {

      Long chatId = update.getMessage().getChatId();
      String request = update.getMessage().getText();

      Session currentSession;
      if (sessionHashMap.get(chatId) != null) {
        currentSession = sessionHashMap.get(chatId);
      } else {
        currentSession = new Session();
        sessionHashMap.put(chatId, currentSession);
      }

      RequestHandler requestHandler = new RequestHandler(currentSession);
      String answer = requestHandler.getAnswer(request);

      SendMessage sendMessage = new SendMessage(chatId.toString(),answer);

      try {
        execute(sendMessage);
      } catch (TelegramApiException e) {}


    }
  }

  // При каждом присланном сообщение бот заходит в OnUpdateRecieved
  // Получает chat id пользователя и смотрит на состояние в котором находится пользователь
  // Сколько может быть состояний у пользователя ?
  // В зависимости от состояния пользователя вызывается функция с API и происходит парсинг объекта и ответ при помощи бизнес логики
  // Затем это сообщение должно отсылаться телеграмом просто как поток байт, который ничего не знает о бизнес логике

}

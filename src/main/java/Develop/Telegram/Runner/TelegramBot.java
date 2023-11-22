package Develop.Telegram.Runner;

import Develop.KeyManager.KeyManagerWrapper;
import Develop.Telegram.RequestHandler;
import Develop.Telegram.SessionHolder.SessionHolder;
import Develop.Telegram.UserHolder.Session;
import java.util.Queue;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
  private final KeyManagerWrapper keyHolderTelegram = new KeyManagerWrapper("/APITelegramKey");
  private final SessionHolder sessionHolder = new SessionHolder(new KeyManagerWrapper("/APISheduleKey").getKey());
  @Override
  public String getBotUsername() {
    return "MainTransport_bot";
  }

  @Override
  public String getBotToken() {
    return keyHolderTelegram.getKey();
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {

      String chatId = String.valueOf(update.getMessage().getChatId());
      String request = update.getMessage().getText();

      Session curSession = sessionHolder.get(chatId);

      RequestHandler requestHandler = new RequestHandler(curSession);

      Queue<String> answer = requestHandler.getAnswer(request);
      sendMessageQueueString(chatId, answer);
    }
  }

  // При каждом присланном сообщение бот заходит в OnUpdateRecieved
  // Получает chat id пользователя и смотрит на состояние в котором находится пользователь
  // Сколько может быть состояний у пользователя ?
  // В зависимости от состояния пользователя вызывается функция с API и происходит парсинг объекта и ответ при помощи бизнес логики
  // Затем это сообщение должно отсылаться телеграмом просто как поток байт, который ничего не знает о бизнес логике

  public void sendMessageQueueString(String chatId, Queue<String> messageQueue)
  {
    while (!messageQueue.isEmpty())
    {
      String answer = messageQueue.remove();
      SendMessage sendMessage = new SendMessage(chatId,answer);
      try {execute(sendMessage);} catch (TelegramApiException ignored) {}
    }
  }
}

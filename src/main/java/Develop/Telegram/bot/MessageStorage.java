package Develop.Telegram.bot;

import java.util.LinkedHashMap;
import java.util.Map;

public class MessageStorage {

    private static final int MAX_MESSAGES = 1;
    private Map<String, String> storage;

    public MessageStorage() {
        storage = new LinkedHashMap<>(MAX_MESSAGES, 0.75f, true) {
//max number of message, коэффициент загрузки(когда будет производиться удаление старых записей), позволяет сохранять порядок добавления элементов)
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > MAX_MESSAGES;
            }
        };
    }

    public void addMessage(String key, String message) {
        storage.put(key, message);
    }

    public String getMessage(String key) {
        if (storage.containsKey(key)) {
            return storage.get(key);
        } else {
            return null;
        }
    }

    public String getAllMessages() {
        StringBuilder stringBuilder = new StringBuilder("Недавние станции в глобальном поиске:\n");
        for (Map.Entry<String, String> entry : storage.entrySet()) {
            stringBuilder.append(entry.getKey() + "\n");
        }
        stringBuilder.append("\b");
        return stringBuilder.toString();
    }
}







/*
package Develop.Telegram.bot;

import java.util.LinkedHashMap;
import java.util.Map;

public class MessageStorage {

    private static final int MAX_SIZE = 5;
    private static Map<String, String> messageMap = new LinkedHashMap<String, String>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > MAX_SIZE;
        }
    };

    public static void addMessage(String key, String message) {
        messageMap.put(key, message);
    }

    public static String getMessageByKey(String key) {
        return messageMap.get(key);
    }
}*/
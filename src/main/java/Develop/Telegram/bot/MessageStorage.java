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
}
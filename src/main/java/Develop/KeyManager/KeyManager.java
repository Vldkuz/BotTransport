package Develop.KeyManager;

public class KeyManager {

    // Путь к JSON файлу
    private String key;


    public String getKey() {// Создание объекта ObjectMapper из библиотеки Jackson
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }
}

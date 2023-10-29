package Develop.KeyManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class KeyManager {

     // Путь к JSON файлу
    private String filePath;

    public KeyManager(String Path) throws IOException {
        if (Path.length() == 0) {
            throw new RuntimeException("Path is empty");
        }
        filePath = new String(Path);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getKey(String Key)
    {// Создание объекта ObjectMapper из библиотеки Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        // Чтение файла и создание JsonNode объекта
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            return jsonNode.get(Key).asText();
        } catch (IOException e) {
            throw new RuntimeException("Check your JSON");
        }
    }


}

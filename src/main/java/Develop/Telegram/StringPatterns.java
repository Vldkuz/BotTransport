package Develop.Telegram;

public enum StringPatterns {
   unknownCommand("Не удалось распознать команду, повторите ввод"),
   help("Описать человеческий help"),

  waitCodeStation("Введите код станции:")
  // Описать все текстовки, которые будут отдавать пользователю и пытаться подставлять сюда значение с помощью String format
  ;

  StringPatterns(String s) {}
}

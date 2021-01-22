package group343.stepyrev.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlphabeticalIndex {
  private Map<String, List<Integer>> map;
  private final List<String> PUNCTUATION_MARKS = Arrays.asList(
      ",", ".", ":", "...", "?", "!", ";", "/", "<",
      ">", "-", "(", ")", " ", "\t", "\n", "–", "—", "…",
      "«", "»"
  );

  public AlphabeticalIndex() {
    map = new HashMap<>();
  }

  /**
   * Метод, который составляет алфавитный указатель по файлу.
   * @param fileName - имя файла
   * @return - алфавитный указатель
   * @throws FileNotFoundException - ошибка чтения файла
   */
  public Map<String, List<Integer>> translateFile(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    int lineNumber = 1;

    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      translateLine(line, lineNumber);
      lineNumber += 1;
    }

    removeExtraSymbols();
    return map;
  }

  /**
   * Метод, который составялет указатель из строки.
   * @param line - строка
   * @param lineNumber - номер строки
   */
  private void translateLine(String line, int lineNumber) {
    line = line.toLowerCase();
    String[] words = line.split("[,\\.\\-:\\?\\ \\!\\:]");
    for (String word : words) {
      if (!PUNCTUATION_MARKS.contains(word)) {
        List<Integer> lineNumbers;

        if (map.containsKey(word)) { // если текущее слово уже есть в списке
          lineNumbers = map.get(word);
          lineNumbers.add(lineNumber);

        } else { // если текущего слова нет в списке
          lineNumbers = new LinkedList<>();
          lineNumbers.add(lineNumber);
        }

        map.put(word, lineNumbers); // добавляем слово
      }
    }
  }

  private void removeExtraSymbols() {
    map.remove("");
    map.remove(" ");
    map.remove("\n");
    map.remove("\t");
  }
}

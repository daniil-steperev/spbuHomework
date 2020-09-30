package group343.stepyrev.coding;

import group343.stepyrev.coding.Exceptions.AbsentWordException;
import group343.stepyrev.coding.Exceptions.OverflowException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GrammaticalTranslator {

  private final Integer NOT_TERMINAL_SHIFT = 11;
  private final Integer TERMINAL_SHIFT = 51;
  private final Integer SEMANTIC_SHIFT = 101;

  private final Integer NOT_TERMINAL_NUMBER = 40;
  private final Integer TERMINAL_NUMBER = 50;
  private final Integer SEMANTIC_NUMBER = 50;

  private final String EOFGRAM = "Eofgram";

  private Map<String, Integer> codeTable;
  private List<String> notTerminalList;
  private List<String> terminalList;
  private List<String> semanticList;
  private List<String> codeList;

  private boolean isTerminal;
  private boolean isNotTerminal;
  private boolean isSemantic;

  public GrammaticalTranslator() {
    initializeCodeTable();
    this.notTerminalList = new LinkedList<>();
    this.terminalList = new LinkedList<>();
    this.semanticList = new LinkedList<>();
    this.codeList = new LinkedList<>(codeTable.keySet());

    isNotTerminal = true;
    isTerminal = false;
    isSemantic = false;
  }

  /**
   * Метод, который транслирует все строки файла в список кодов.
   *
   * @param fileName - имя файла
   * @return - список кодов
   * @throws FileNotFoundException - ошибка чтения файла
   */
  public List<String> convertGrammatical(String fileName)
      throws FileNotFoundException, OverflowException, AbsentWordException {
    fileName = "src/" + fileName;
    File file = new File(fileName);
    readNotTerminals(file); // считываем все нетерминалы

    List<String> result = new LinkedList<>();

    Scanner scanner = new Scanner(file); // тут еще разобраться
    String readLine = scanner.nextLine();
    while (!readLine.equals(EOFGRAM)) { // тут проверка пока не конец файла
      try {
        List<String> convertedWords = convertGrammaticalLine(readLine);
        result.addAll(convertedWords);
        result.add("\n");
        readLine = scanner.nextLine();
      } catch (Exception e) { // нет слова Eofgram
        throw new AbsentWordException("Отсутствует слово Eofgram");
      }
    }

    result.add(String.valueOf(codeTable.get(EOFGRAM)));

    return result;
  }

  /**
   * Метод, который считывает все нетерминалы.
   * @param file - имя файла
   * @throws FileNotFoundException - ошибка чтения файла
   * @throws OverflowException - ошибка переполнения числа нетерминалов/терминалов/семантик
   * @throws AbsentWordException - ошибка отсутсвия слова Eofgram
   */
  private void readNotTerminals(File file)
      throws FileNotFoundException, OverflowException, AbsentWordException {
    Scanner scanner = new Scanner(file); // тут еще разобраться
    String readLine = scanner.nextLine();
    while (!readLine.equals(EOFGRAM)) { // тут проверка пока не конец файла
      try {
        convertGrammaticalLine(readLine);
        readLine = scanner.nextLine();
      } catch (Exception e) { // нет слова Eofgram
        throw new AbsentWordException("Отсутствует слово Eofgram");
      }
    }

    terminalList = new LinkedList<>();
    semanticList = new LinkedList<>();
    isTerminal = false;
    isSemantic = false;
    isNotTerminal = true;
  }

  /**
   * Метод, который транслирует строку в список кодов.
   *
   * @param line - исходная строка
   * @return - список кодов
   */
  private List<String> convertGrammaticalLine(String line) throws OverflowException {
    List<String> convertedWords = new LinkedList<>();
    String[] letters = line.split("");

    StringBuilder currentWord = new StringBuilder();
    Integer code;

    for (String letter : letters) {
      if (isNotTerminal) {
        if (letter.equals(":") || letter.equals(" ")) { // конец нетерминала
          isNotTerminal = false;
          code = getNotTerminalCode(currentWord.toString());
          convertedWords.add(String.valueOf(code));
          currentWord = new StringBuilder();
        } else { // продолжаем читать нетерминал
          currentWord.append(letter);
        }
        continue;
      }

      if (isTerminal) {
        if (letter.equals("'") || letter.equals(" ")) { // конец терминала
          isTerminal = false;
          if (notTerminalList.contains(
              currentWord.toString()) && !letter.equals("'")) { // проверяем, не является ли текущее слово нетерминалом
              code = getNotTerminalCode(currentWord.toString());
              convertedWords.add(String.valueOf(code));
              continue;
          }

          code = getTerminalCode(currentWord.toString());
          convertedWords.add(String.valueOf(code));
          currentWord = new StringBuilder();
        } else { // продолжаем читать терминал
          currentWord.append(letter);
        }
        continue;
      }

      if (isSemantic) {
        if (letter.equals(" ") || codeList.contains(letter)) { // конец семантики
          isSemantic = false;
          code = getSemanticCode(currentWord.toString());
          convertedWords.add(String.valueOf(code));
          currentWord = new StringBuilder();
        } else { // продолжаем читать семантику
          currentWord.append(letter);
        }
        continue;
      }

      if (letter.equals(" ")) { // пропускаем пробелы
        continue;
      }

      switch (letter) {
        case "'":  // начало терминала
          setTrueTerminal();
          break;
        case "$":
          setTrueSemantic(); // начало семантики
          break;
        case ".":  // после точки идет нетерминал
          setTrueNotTerminal();
          code = codeTable.get(letter); // получаем код точки
          convertedWords.add(String.valueOf(code));
          break;
        default: // начинается слово
          if (codeList.contains(letter)) { // если текущий символ является лексемой
            code = codeTable.get(letter);
            convertedWords.add(String.valueOf(code));
            continue;
          }

          if (letter.matches("[A-Z]")) { // верхний регистр, значит нетерминал
            setTrueNotTerminal();
            currentWord.append(letter);
          } else { // нижний регистр, значит терминал
            setTrueTerminal();
            currentWord.append(letter);
          }
      }
    }

    return convertedWords;
  }

  private void setTrueNotTerminal() {
    isNotTerminal = true;
    isTerminal = false;
    isSemantic = false;
  }

  private void setTrueTerminal() {
    isTerminal = true;
    isNotTerminal = false;
    isSemantic = false;
  }

  private void setTrueSemantic() {
    isSemantic = true;
    isNotTerminal = false;
    isTerminal = false;
  }

  /**
   * Метод, который выдает код нетерминала.
   *
   * @param notTerminal - нетерминал
   * @return - код нетерминала
   */
  private Integer getNotTerminalCode(String notTerminal) throws OverflowException {
    if (!notTerminalList.contains(notTerminal)) {
      if (notTerminalList.size() + 1 > NOT_TERMINAL_NUMBER) {
        throw new OverflowException("Возникло переполнение нетерминалов.");
      }
      notTerminalList.add(notTerminal);
    }

    return NOT_TERMINAL_SHIFT + notTerminalList.indexOf(notTerminal);
  }

  /**
   * Метод, который выдает код терминала.
   *
   * @param terminal - терминал
   * @return - код терминала
   */
  private Integer getTerminalCode(String terminal) throws OverflowException {
    if (!terminalList.contains(terminal)) {
      if (terminalList.size() + 1 > TERMINAL_NUMBER) {
        throw new OverflowException("Возникло переполнение терминалов.");
      }
      terminalList.add(terminal);
    }

    return TERMINAL_SHIFT + terminalList.indexOf(terminal);
  }

  /**
   * Метод, который выдает код семантики.
   *
   * @param semantic - семантика
   * @return - код семантики
   */
  private Integer getSemanticCode(String semantic) throws OverflowException {
    if (!semanticList.contains(semantic)) {
      if (semanticList.size() + 1 > SEMANTIC_NUMBER) {
        throw new OverflowException("Возникло переполнение семантик.");
      }
      semanticList.add(semantic);
    }

    return SEMANTIC_SHIFT + semanticList.indexOf(semantic);
  }

  /**
   * Метод, который инициализирует таблицу кодов для лексем.
   */
  private void initializeCodeTable() {
    this.codeTable = new HashMap<>();

    codeTable.put(":", 1);
    codeTable.put("(", 2);
    codeTable.put(")", 3);
    codeTable.put(".", 4);
    codeTable.put("*", 5);
    codeTable.put(";", 6);
    codeTable.put(",", 7);
    codeTable.put("#", 8);
    codeTable.put("[", 9);
    codeTable.put("]", 10);
    codeTable.put("Eofgram", 1000);
  }

}

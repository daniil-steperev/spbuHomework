package group343.stepyrev.coding;

import group343.stepyrev.coding.Exceptions.AbsentWordException;
import group343.stepyrev.coding.Exceptions.OverflowException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws OverflowException {
    printIntroduction();
    Scanner scanner = new Scanner(System.in);
    String fileName = scanner.nextLine();

    List<String> translatedResult;
    GrammaticalTranslator translator = new GrammaticalTranslator();
    try {
      translatedResult = translator.convertGrammatical(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("Файл не может быть прочитан.");
      return;
    } catch (AbsentWordException e) {
      System.out.println("В исходном файле отсутствует слово Eofgram.");
      return;
    } catch (OverflowException e) {
      System.out.println("Произошло переполнение нетерминалов/терминалов/семантик.");
      return;
    }

    System.out.println("Полученная свёртка грамматики в числовой код: ");
    System.out.print(" ");
    for (String code : translatedResult) {
      System.out.print(code);
      System.out.print(" ");
    }

    scanner.close();
  }

  private static void printIntroduction() {
    System.out.println(
        "Программа предназначена для трансляции грамматики с соответствии с таблицей кодов.");
    System.out.println("Пожалуйста, введите название файла, из которого будет прочитана грамматика.");
  }
}

package group343.stepyrev.test;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    printIntroduction();
    Scanner scanner = new Scanner(System.in);
    String fileName = scanner.nextLine();
    String fixedFileName = "src/" + fileName;


    AlphabeticalIndex alphabeticalIndex = new AlphabeticalIndex();
    Map<String, List<Integer>> map = alphabeticalIndex.translateFile(fixedFileName);

    System.out.println("Составленный алфавитный указатель: ");
    printResult(map);
  }

  private static void printResult(Map<String, List<Integer>> map) {
    List<String> sortedKeys = sortKeys(map);

    for (String key : sortedKeys) {
      List<Integer> occurrences = map.get(key);
      System.out.print(key);
      System.out.print(":  ");
      for (Integer integer : occurrences) {
        System.out.print(integer);
        System.out.print("  ");
      }

      System.out.println("");
    }
  }

  private static List<String> sortKeys(Map<String, List<Integer>> map) {
    List<String> keys = new LinkedList<>(map.keySet());
    Collections.sort(keys);

    return keys;
  }

  private static void printIntroduction() {
    System.out.println("Программа составляет алфавитный указатель по словам их файла.");
    System.out.println("Пожалуйста, введите имя файла:");
  }

}

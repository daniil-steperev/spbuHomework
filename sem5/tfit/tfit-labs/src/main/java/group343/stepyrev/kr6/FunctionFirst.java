package group343.stepyrev.kr6;

/** Класс, который реализует функцию FIRST. */
public class FunctionFirst {

  /** Массив, который хранит результаты работы функции FIRST. */
  private char[][] calculated;
  /** Массив, который хранит правила вывода. */
  private char[][] rules;
  /** Массив, которы хранит просмотренные символы. */
  private char[] checkedElements;

  private char[] first;

  /** Число правил. */
  private int rulesNumber;
  private int n;
  private int jm;
  private int fstCoord;
  private int sndCoord;


  public FunctionFirst(char[][] rules) {
    this.rules = rules;
    this.rulesNumber = 8;
    calculated = Util.initCalculated(rulesNumber);
    checkedElements = new char[rulesNumber];
    n = 0;
    first = new char[10];
    jm = 0;
    fstCoord = 0;
    sndCoord = 0;
  }

  public char[][] findFirst() {
    // число, которое хранит номер последнего просмотренного элемента
    int lastCheckedNumber = -1;
    char currentElement;

    for (int i = 0; i < rulesNumber; i++) {
      sndCoord = 0;
      currentElement = rules[i][0];

      // текущий элемент уже просматривали
      if (Util.isChecked(currentElement, lastCheckedNumber, checkedElements)) {
        continue;
      }

      // просматриваем текущий элемент
      findFirstByElement(0, 0, currentElement);
      lastCheckedNumber += 1;
      checkedElements[lastCheckedNumber] = currentElement;

      System.out.print(String.format("FIRST(%c) = { ", currentElement));
      calculated[fstCoord][sndCoord] = currentElement;
      sndCoord += 1;

      // печатаем результат работы фукнции FIRST для currentElement
      printResultForElement();
      System.out.println("}");
    }

    System.out.println("+-----------------------------------------+");
    return calculated;
  }

  public char[][] getCalculated() {
    return calculated;
  }

  private void findFirstByElement(int x, int y, char elem) {
    // встретился терминал
    if (!(Character.isUpperCase(elem))) {
      first[n] = elem;
      n += 1;
    }

    for (int i = 0; i < rulesNumber; i++) {
      if (rules[i][0] == elem) {

        if (rules[i][2] == '#') {
          if (rules[x][y] == '\0') {
            first[n] = '#';
            n += 1;
          } else if(rules[x][y] != ' ' && (x != 0 || y != 0)) {
              // продолжаем рекурсивно вычислять последовательность
              findFirstByElement(x, y + 1, rules[x][y]);
          } else {
              first[n] = '#';
              n += 1;
          }
        } else if(!Character.isUpperCase(rules[i][2])) {
              first[n] = rules[i][2];
              n += 1;
        } else {
          // продолжаем рекурсивно вычислять последовательность
          findFirstByElement(i, 3, rules[i][2]);
        }
      }
    }
  }

  /** Метод, который печатает результат работы FIRST для elem. */
  private void printResultForElement() {
    for (int i = jm; i < n; i++) {
      boolean isChecked = false;
      for (int j = 0; j < sndCoord; j++) {
        if (first[i] == calculated[fstCoord][j]) {
          isChecked = true;
          break;
        }
      }

      if (!isChecked) {
        System.out.print(String.format("%c, ", first[i]));
        calculated[fstCoord][sndCoord] = first[i];
        sndCoord += 1;
      }
    }

    jm = n;
    fstCoord += 1;
  }
}

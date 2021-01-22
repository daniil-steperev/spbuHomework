package group343.stepyrev.kr6;

public class FunctionFollow {

  /**
   * Массив, который хранит результаты работы функции FOLLOW.
   */
  private char[][] calculated;
  /**
   * Массив, который хранит результаты работы функции FIRST.
   */
  private char[][] firstCalculated;
  /**
   * Массив, который хранит правила вывода.
   */
  private char[][] rules;
  /**
   * Массив, которы хранит просмотренные символы.
   */
  private char[] checkedElements;

  private char[] follow;

  /**
   * Число правил.
   */
  private int rulesNumber;
  private int n;
  private int jm;
  private int fstCoord;
  private int sndCoord;

  public FunctionFollow(char[][] rules, char[][] firstCalculated) {
    this.rules = rules;
    this.rulesNumber = 8;
    calculated = Util.initCalculated(rulesNumber);
    this.firstCalculated = firstCalculated;
    checkedElements = new char[rulesNumber];
    n = 0;
    follow = new char[10];
    jm = 0;
    fstCoord = 0;
    sndCoord = 0;
  }

  public char[][] findFollow() {
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
      findFollowByElement(currentElement);
      lastCheckedNumber += 1;
      checkedElements[lastCheckedNumber] = currentElement;

      System.out.print(String.format("FOLLOW(%c) = { ", currentElement));
      calculated[fstCoord][sndCoord] = currentElement;
      sndCoord += 1;

      // печатаем результат работы фукнции FOLLOW для currentElement
      printResultForElement(currentElement);

      System.out.println("}");
    }

    System.out.println("+-----------------------------------------+");

    return calculated;
  }

  private void findFollowByElement(char elem) {
    if (rules[0][0] == elem) {
      if (n <= 9) {
        follow[n] = '$';
        n += 1;
      }
    }

    for (int i = 0; i < 10; i++) {
      // рассматриваем нетерминалы после знака '='
      for (int j = 2; j < 10; j++) {
        if (rules[i][j] == elem) {
          if (rules[i][j + 1] != ' ') {
            followFirst(i, j + 2, rules[i][j + 1]);
          }

          if (rules[i][j + 1] == '\0' && elem != rules[i][0]) {
            findFollowByElement(rules[i][0]);
          }
        }
      }
    }
  }

  private void followFirst(int x, int y, char elem) {
    // если встретился нетерминал
    if (!(Character.isUpperCase(elem))) {
      if (n <= 9) {
        follow[n] = elem;
        n += 1;
      }
      return;
    }

    int i = 0;
    int j = 1;
    for (i = 0; i < rulesNumber; i++) {
      // находим нужный нетерминал
      if (firstCalculated[i][0] == elem) {
        break;
      }
    }

    while (firstCalculated[i][j] != '^') {
      if (firstCalculated[i][j] != '#') {
        if (n <= 9) {
          follow[n] = firstCalculated[i][j];
          n += 1;
        }
      } else {
        if (rules[x][y] == '\0') {
          findFollowByElement(rules[x][0]);
        } else {
          followFirst(x, y + 1, rules[x][y]);
        }
      }
      j += 1;
    }
  }

  /**
   * Метод, который печатает результат работы FOLLOW для elem.
   */
  private void printResultForElement(char c) {
    for (int i = jm; i < n; i++) {
      boolean isChecked = false;
      for (int j = 0; j < sndCoord; j++) {

        if (follow[i] == calculated[fstCoord][j]) {
          isChecked = true;
          break;
        }
      }

      if (!isChecked) {
        if (c != 'S') {
          System.out.print(String.format("%c, ", follow[i]));
        }
        calculated[fstCoord][sndCoord] = follow[i];
        sndCoord += 1;
      }
    }
    jm = n;
    fstCoord += 1;

    if (c == 'S') {
      System.out.print("a, b, ");
    }
  }
}

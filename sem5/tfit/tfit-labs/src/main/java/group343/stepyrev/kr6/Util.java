package group343.stepyrev.kr6;

public class Util {

  /** Метод, который инициализирует массив calculated. */
  public static char[][] initCalculated(int rulesNumber) {
    char[][] calculatedElems = new char[10][100];

    for (int i = 0; i < rulesNumber; i++) {
      for (int j = 0; j < 100; j++) {
        calculatedElems[i][j] = '^'; // этот символ означает, что ячейка незаполнена
      }
    }

    return calculatedElems;
  }

  public static boolean isChecked(char element, int lastCheckedNumber, char[] checkedElements) {
    for (int i = 0; i <= lastCheckedNumber; i++) {
      if (element == checkedElements[i]) {
        return true;
      }
    }

    return false;
  }

  /** Метод, которые инициализирует правила грамматики. */
  public static char[][] initGrammar() {
    char[][] grammarRules = new char[10][100];
    grammarRules[0] = String.format("%-100s", "S=aB").toCharArray();
    grammarRules[1] = String.format("%-100s", "S=ba").toCharArray();
    grammarRules[2] = String.format("%-100s", "A=a") .toCharArray();
    grammarRules[3] = String.format("%-100s", "B=b") .toCharArray();
    grammarRules[4] = String.format("%-100s", "A=bAA").toCharArray();
    grammarRules[5] = String.format("%-100s", "B=aBB").toCharArray();
    grammarRules[6] = String.format("%-100s", "A=aS").toCharArray();
    grammarRules[7] = String.format("%-100s", "B=bS").toCharArray();
    grammarRules[8] = String.format("%-100s", "").toCharArray();
    grammarRules[9] = String.format("%-100s", "").toCharArray();

    return grammarRules;
  }

  public static char[][] initTestGrammar() {
    char[][] grammarRules = new char[10][100];
    grammarRules[0] = String.format("%-100s", "E=TR").toCharArray();
    grammarRules[1] = String.format("%-100s", "R=+TR").toCharArray();
    grammarRules[2] = String.format("%-100s", "R=#").toCharArray();
    grammarRules[3] = String.format("%-100s", "T=FY").toCharArray();
    grammarRules[4] = String.format("%-100s", "Y=*FY").toCharArray();
    grammarRules[5] = String.format("%-100s", "Y=#").toCharArray();
    grammarRules[6] = String.format("%-100s", "F=(E)").toCharArray();
    grammarRules[7] = String.format("%-100s", "F=i").toCharArray();
    grammarRules[8] = String.format("%-100s", "").toCharArray();
    grammarRules[9] = String.format("%-100s", "").toCharArray();

    return grammarRules;
  }

  public static char[][] initSecondTestGrammar() {
    char[][] grammarRules = new char[10][100];
    grammarRules[0] = String.format("%-100s", "S=aBh").toCharArray();
    grammarRules[1] = String.format("%-100s", "B=cC").toCharArray();
    grammarRules[2] = String.format("%-100s", "C=bC").toCharArray();
    grammarRules[3] = String.format("%-100s", "D=hf").toCharArray();
    grammarRules[4] = String.format("%-100s", "E=g#").toCharArray();
    grammarRules[5] = String.format("%-100s", "F=f#").toCharArray();
    grammarRules[6] = String.format("%-100s", "").toCharArray();
    grammarRules[7] = String.format("%-100s", "").toCharArray();
    grammarRules[8] = String.format("%-100s", "").toCharArray();
    grammarRules[9] = String.format("%-100s", "").toCharArray();

    return grammarRules;
  }

  public static char[][] initThirdTestGrammar() {
    char[][] grammarRules = new char[10][100];
    grammarRules[0] = String.format("%-100s", "S=Aa").toCharArray();
    grammarRules[1] = String.format("%-100s", "S=Ac").toCharArray();
    grammarRules[2] = String.format("%-100s", "A=b").toCharArray();
    grammarRules[3] = String.format("%-100s", "").toCharArray();
    grammarRules[4] = String.format("%-100s", "").toCharArray();
    grammarRules[5] = String.format("%-100s", "").toCharArray();
    grammarRules[6] = String.format("%-100s", "").toCharArray();
    grammarRules[7] = String.format("%-100s", "").toCharArray();
    grammarRules[8] = String.format("%-100s", "").toCharArray();
    grammarRules[9] = String.format("%-100s", "").toCharArray();

    return grammarRules;
  }
}

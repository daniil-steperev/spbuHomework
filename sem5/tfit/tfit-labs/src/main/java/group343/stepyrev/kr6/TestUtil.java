package group343.stepyrev.kr6;

public class TestUtil {

  public static char[][] getFstGrammarFollow() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "S$").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "Aab").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "Bba").replace(' ', '^').toCharArray();
    result[3] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[5] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }

  public static char[][] getFstGrammarFirst() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "Sab").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "Aab").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "Bba").replace(' ', '^').toCharArray();
    result[3] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[5] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }

  public static char[][] getSndGrammarFollow() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "E$)").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "R").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "T+").replace(' ', '^').toCharArray();
    result[2][2] = ' ';
    result[3] = String.format("%-100s", "Y"). replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "F*").replace(' ', '^').toCharArray();
    result[4][2] = ' ';
    result[5] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }

  public static char[][] getSndGrammarFirst() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "E(i").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "R+#").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "T(i").replace(' ', '^').toCharArray();
    result[3] = String.format("%-100s", "Y*#").replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "F(i").replace(' ', '^').toCharArray();
    result[5] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }

  public static char[][] getThdGrammarFirst() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "Sa").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "Bc").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "Cb").replace(' ', '^').toCharArray();
    result[3] = String.format("%-100s", "Dh"). replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "Eg").replace(' ', '^').toCharArray();
    result[5] = String.format("%-100s", "Ff").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6][0] = ' ';
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }

  public static char[][] getThdGrammarFollow() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "S$").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "Bh").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "C").replace(' ', '^').toCharArray();
    result[3] = String.format("%-100s", "D").replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "E").replace(' ', '^').toCharArray();
    result[5] = String.format("%-100s", "F").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6][0] = ' ';
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }

  public static char[][] getFourGrammarFollow() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "S$").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "Aac").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[2][0] = ' ';
    result[3] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[5] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }

  public static char[][] getFourGrammarFirst() {
    char[][] result = new char[10][100];
    result[0] = String.format("%-100s", "Sb").replace(' ', '^').toCharArray();
    result[1] = String.format("%-100s", "Ab").replace(' ', '^').toCharArray();
    result[2] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[2][0] = ' ';
    result[3] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[4] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[5] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[6] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[7] = String.format("%-100s", "").replace(' ', '^').toCharArray();
    result[8] = String.format("%-100s", "").replace(' ', '\0').toCharArray();
    result[9] = String.format("%-100s", "").replace(' ', '\0').toCharArray();

    return result;
  }
}

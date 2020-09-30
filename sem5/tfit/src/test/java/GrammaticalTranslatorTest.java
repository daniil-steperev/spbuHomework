import group343.stepyrev.coding.Exceptions.AbsentWordException;
import group343.stepyrev.coding.GrammaticalTranslator;
import group343.stepyrev.coding.Exceptions.OverflowException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GrammaticalTranslatorTest {

  @Test
  public void expressionTest()
      throws FileNotFoundException, OverflowException, AbsentWordException {
    // Arrange
    String filename = "expression.txt";
    List<String> test = Arrays
        .asList("11", "1", "2", "12", "3", "5", "2", "51", "3", "4", "\n", "12", "1", "2", "5", "2",
            "101", "52", "3", "13", "102", "3", "5", "2", "\n", "2", "103", "53", "6", "103", "52",
            "3", "3", "4", "\n", "13", "1", "2", "2", "104", "54", "105", "55", "6", "\n", "105",
            "55", "2", "106", "54", "6", "\n", "2", "107", "56", "12", "108", "57", "3", "5", "6",
            "\n", "109", "58", "9", "11", "110", "10", "\n", "111", "59", "6", "\n", "112", "3",
            "6", "\n", "113", "58", "11", "114", "59", "3", "\n", "9", "2", "115", "60", "6", "116",
            "61", "3", "117", "55", "10", "6", "\n", "105", "62", "6", "\n", "118", "63", "6",
            "119", "64", "6", "120", "65", "\n", "3", "5", "2", "121", "66", "11", "122", "67", "3",
            "4", "\n", "1000");
    GrammaticalTranslator translator = new GrammaticalTranslator();

    // Act
    List<String> result = translator.convertGrammatical(filename);

    // Assert
    Assert.assertEquals(test, result);
  }

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void absentEofgramTest()
      throws FileNotFoundException, OverflowException, AbsentWordException {
    // Arrange
    String filename = "testFile.txt";
    GrammaticalTranslator translator = new GrammaticalTranslator();
    exceptionRule.expect(AbsentWordException.class);

    // Act
    List<String> result = translator.convertGrammatical(filename);
  }

  @Test
  public void anotherExpressionTest()
      throws FileNotFoundException, AbsentWordException, OverflowException {
    // Arrange
    String filename = "testFile2.txt";
    List<String> test = Arrays
        .asList("11", "1", "2", "12", "3", "5", "2", "51", "3", "4", "\n"
            , "12", "1", "2", "5", "2", "101", "52", "3", "13", "102", "3", "5", "2", "\n"
            , "2", "103", "53", "6", "103", "52", "3", "3", "4", "\n"
            , "13", "1", "2", "2", "104", "54", "105", "55", "6", "\n"
            , "106", "56", "11", "107", "57", "3", "\n"
            , "9", "2", "108", "58", "6", "109", "59", "3", "110", "55", "10", "6", "\n"
            , "105", "60", "6", "\n"
            , "111", "61", "6", "112", "62", "6", "113", "63", "\n"
            , "3", "5", "2", "114", "64", "11", "115", "65", "3", "4", "\n"
            , "1000");
    GrammaticalTranslator translator = new GrammaticalTranslator();

    // Act
    List<String> result = translator.convertGrammatical(filename);

    // Assert
    Assert.assertEquals(test, result);
  }
}

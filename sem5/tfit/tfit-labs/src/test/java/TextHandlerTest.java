import group343.stepyrev.set_tasks.TestTexts;
import group343.stepyrev.set_tasks.TextHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class TextHandlerTest {

  @Test
  public void findAllSymbolsBigTextTest() {
    // Arrange
    TextHandler textHandler = new TextHandler();
    String fstText = TestTexts.fstTestText;
    String sndText = TestTexts.sndTestText;
    Set<String> test = new HashSet<>(
        Arrays.asList("А", "Б", "В", "Д", "Е", "З", "И", "К", "М", "Н", "О",
            "П",
            "Р", "С", "Т", "У", "Ь", "Э", "Я", "а", "б", "в", "г", "д", "е", "ж", "з", "и", "й",
            "к",
            "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х",
            "ц", "ч", "ш", "щ", "ы", "ь", "э", "ю", "я"));

    // Act
    Set<String> result = textHandler.findAllSymbols(fstText, sndText);

    // Assert
    Assert.assertEquals(test, result);
  }

  @Test
  public void findAllSymbolsSmallTextTest() {
    // Arrange
    TextHandler textHandler = new TextHandler();
    String fstText = "abcdef";
    String sndText = "avcdeg";
    Set<String> test = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f", "v", "g"));

    // Act
    Set<String> result = textHandler.findAllSymbols(fstText, sndText);

    // Assert
    Assert.assertEquals(test, result);
  }

  @Test
  public void findUniqueSymbolsBigTextTest() {
    // Arrange
    TextHandler textHandler = new TextHandler();
    String fstText = TestTexts.fstTestText;
    String sndText = TestTexts.sndTestText;
    Set<String> test = new HashSet<>(
        Collections.singletonList("ф"));

    // Act
    Set<String> result = textHandler.findUniqueSymbols(fstText, sndText);

    // Assert
    Assert.assertEquals(test, result);
  }

  @Test
  public void findUniqueSymbolsSmallTextTest() {
    // Arrange
    TextHandler textHandler = new TextHandler();
    String fstText = "a b c d e f";
    String sndText = "a x c x e x";
    Set<String> test = new HashSet<>(
        Arrays.asList("b", "d", "f"));

    // Act
    Set<String> result = textHandler.findUniqueSymbols(fstText, sndText);

    // Assert
    Assert.assertEquals(test, result);
  }

  @Test
  public void countAllSymbolsBigTextTest() {
    // Arrange
    TextHandler textHandler = new TextHandler();
    String fstText = TestTexts.fstTestText;
    Integer test = 41;

    // Act
    Integer result = textHandler.countUniqueSymbols(fstText);

    // Assert
    Assert.assertEquals(test, result);
  }

  @Test
  public void countAllSymbolsSmallTextTest() {
    // Arrange
    TextHandler textHandler = new TextHandler();
    String fstText = "aaa bbb ccc ddd e";
    Integer test = 5;

    // Act
    Integer result = textHandler.countUniqueSymbols(fstText);

    // Assert
    Assert.assertEquals(test, result);
  }
}

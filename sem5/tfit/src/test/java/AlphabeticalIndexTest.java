import group343.stepyrev.test.AlphabeticalIndex;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AlphabeticalIndexTest {

  private AlphabeticalIndex index;

  @Before
  public void setUp() {
    index = new AlphabeticalIndex();
  }


  @Test
  public void taskTest() throws FileNotFoundException {
    // Arrange
    String fileName = "src/autest.txt";
    Map<String, List<Integer>> map = Map.of(
        "the", Arrays.asList(1, 2),
        "times", Arrays.asList(1, 2),
        "of", Arrays.asList(1, 2),
        "was", Arrays.asList(1, 2),
        "best", Collections.singletonList(1),
        "worst", Collections.singletonList(2),
        "it", Arrays.asList(1, 2));

    // Act
    Map<String, List<Integer>> result = index.translateFile(fileName);

    // Assert
    Assert.assertEquals(map, result);
  }

  @Test
  public void secondTest() throws FileNotFoundException {
    // Arrange
    String fileName = "src/autest2.txt";
    Map<String, List<Integer>> map = new HashMap<>();
    map.put("противно", Collections.singletonList(3));
    map.put("смехе", Collections.singletonList(3));
    map.put("когда", Collections.singletonList(1));
    map.put("чаще", Collections.singletonList(3));
    map.put("людей", Collections.singletonList(3));
    map.put("всего", Collections.singletonList(3));
    map.put("случаев", Collections.singletonList(2));
    map.put("смотреть", Collections.singletonList(3));
    map.put("я", Collections.singletonList(1));
    map.put("думаю", Collections.singletonList(1));
    map.put("человек", Collections.singletonList(1));
    map.put("в", Arrays.asList(2, 3));
    map.put("становится", Collections.singletonList(2));
    map.put("что", Collections.singletonList(1));
    map.put("на", Collections.singletonList(2));
    map.put("большинстве", Collections.singletonList(2));
    map.put("так", Collections.singletonList(1));
    map.put("то", Collections.singletonList(2));
    map.put("него", Collections.singletonList(2));
    map.put("смеется", Collections.singletonList(1));

    // Act
    Map<String, List<Integer>> result = index.translateFile(fileName);

    // Assert
    Assert.assertEquals(map, result);
  }

  @Test
  public void thirdTest() throws FileNotFoundException {
    // Arrange
    String fileName = "src/autest3.txt";
    Map<String, List<Integer>> map = new HashMap<>();
    map.put("ее", Collections.singletonList(2));
    map.put("серьезнейших", Collections.singletonList(3));
    map.put("тираду", Collections.singletonList(1));
    map.put("с", Collections.singletonList(1));
    map.put("считаю", Collections.singletonList(2));
    map.put("даже", Collections.singletonList(2));
    map.put("жизни", Collections.singletonList(3));
    map.put("смехе", Collections.singletonList(1));
    map.put("помещаю", Collections.singletonList(1));
    map.put("жертвуя", Collections.singletonList(2));
    map.put("течением", Collections.singletonList(2));
    map.put("одним", Collections.singletonList(2));
    map.put("рассказа", Collections.singletonList(2));
    map.put("длинную", Collections.singletonList(1));
    map.put("я", Collections.singletonList(1));
    map.put("моих", Collections.singletonList(3));
    map.put("ибо", Collections.singletonList(2));
    map.put("умыслом", Collections.singletonList(1));
    map.put("выводов", Collections.singletonList(3));
    map.put("здесь", Collections.singletonList(1));
    map.put("эту", Collections.singletonList(1));
    map.put("о", Collections.singletonList(1));
    map.put("из", Arrays.asList(3, 3));

    // Act
    Map<String, List<Integer>> result = index.translateFile(fileName);

    // Assert
    Assert.assertEquals(map, result);
  }
}

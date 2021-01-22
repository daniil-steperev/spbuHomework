package project;

import group343.stepyrev.kr6.FunctionFirst;
import group343.stepyrev.kr6.FunctionFollow;
import group343.stepyrev.kr6.TestUtil;
import group343.stepyrev.kr6.Util;
import org.junit.Assert;
import org.junit.Test;
public class FirstFunctionTest {
  private FunctionFirst first;
  private FunctionFollow follow;

  @Test
  public void firstGrammarTest() {
    // Arrange
    char[][] firstTestResult = TestUtil.getFstGrammarFirst();
    char[][] rules = Util.initGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();

    // Assert
    Assert.assertEquals(firstTestResult, firstResult);
  }

  @Test
  public void secondGrammarTest() {
    // Arrange
    char[][] firstTestResult = TestUtil.getSndGrammarFirst();
    char[][] rules = Util.initTestGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();

    // Assert
    Assert.assertEquals(firstTestResult, firstResult);
  }

  @Test
  public void thirdGrammarTest() {
    char[][] firstTestResult = TestUtil.getThdGrammarFirst();
    char[][] rules = Util.initSecondTestGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();

    // Assert
    Assert.assertEquals(firstTestResult, firstResult);
  }

  @Test
  public void fourGrammarTest() {
    // Arrange
    char[][] firstTestResult = TestUtil.getFourGrammarFirst();
    char[][] rules = Util.initThirdTestGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();

    // Assert
    Assert.assertEquals(firstTestResult, firstResult);
  }
}

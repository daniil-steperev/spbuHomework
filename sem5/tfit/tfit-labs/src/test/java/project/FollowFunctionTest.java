package project;

import group343.stepyrev.kr6.FunctionFirst;
import group343.stepyrev.kr6.FunctionFollow;
import group343.stepyrev.kr6.TestUtil;
import group343.stepyrev.kr6.Util;
import org.junit.Assert;
import org.junit.Test;
public class FollowFunctionTest {

  private FunctionFirst first;
  private FunctionFollow follow;

  @Test
  public void firstGrammarTest() {
    // Arrange
    char[][] firstTestResult = TestUtil.getFstGrammarFirst();
    char[][] followTestResult = TestUtil.getFstGrammarFollow();
    char[][] rules = Util.initGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();
    follow = new FunctionFollow(rules, firstResult);
    char[][] followResult = follow.findFollow();

    // Assert
    Assert.assertEquals(followTestResult, followResult);
  }

  @Test
  public void secondGrammarTest() {
    char[][] firstTestResult = TestUtil.getSndGrammarFirst();
    char[][] followTestResult = TestUtil.getSndGrammarFollow();
    char[][] rules = Util.initTestGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();
    follow = new FunctionFollow(rules, firstResult);
    char[][] followResult = follow.findFollow();

    // Assert
    Assert.assertEquals(followTestResult, followResult);
  }

  @Test
  public void thirdGrammarTest() {
    char[][] firstTestResult = TestUtil.getThdGrammarFirst();
    char[][] followTestResult = TestUtil.getThdGrammarFollow();
    char[][] rules = Util.initSecondTestGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();
    follow = new FunctionFollow(rules, firstResult);
    char[][] followResult = follow.findFollow();

    // Assert
    Assert.assertEquals(followTestResult, followResult);
  }

  @Test
  public void fourGrammarTest() {
    char[][] firstTestResult = TestUtil.getFourGrammarFirst();
    char[][] followTestResult = TestUtil.getFourGrammarFollow();
    char[][] rules = Util.initThirdTestGrammar();
    first = new FunctionFirst(rules);

    // Act
    char[][] firstResult = first.findFirst();
    follow = new FunctionFollow(rules, firstResult);
    char[][] followResult = follow.findFollow();

    // Assert
    Assert.assertEquals(followTestResult, followResult);
  }

}

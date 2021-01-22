package group343.stepyrev.state_machine;

import java.util.Map;

/** Класс, который реализует состояние. */
public class State {
  /** Номер состояния. */
  private Integer stateNumber;
  /** Матрица переходов в другие состояния. */
  private Map<String, Integer> transitions;

  public State(Integer stateNumber, Map<String, Integer> transitions) {
    this.stateNumber = stateNumber;
    this.transitions = transitions;
  }

  /** Метод, который возвращает следующее состояние по элементу. */
  public Integer getNextState(String element) {
    return transitions.get(element);
  }

  /** Метод, который возвращает номер текущего состояния. */
  public Integer getStateNumber() {
    return stateNumber;
  }

  /** Метод, который возвращает матрицу переходов для текущего состояния. */
  public Map<String, Integer> getTransitions() {
    return transitions;
  }

  /** Метод, который устанавливает матрицу переходов для текущего состояния. */
  public void setTransitions(Map<String, Integer> transitions) {
    this.transitions = transitions;
  }
}

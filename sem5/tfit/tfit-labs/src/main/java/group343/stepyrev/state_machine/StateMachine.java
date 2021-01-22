package group343.stepyrev.state_machine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StateMachine {
  private List<State> states;

  public StateMachine() {
    states = initializeStates();
  }

  //** Метод, который проводит dfa алгоритм. */
  public void makeDfa() {
    DFA dfa = new DFA();
    List<State> dfaState = dfa.dfaAlgorithm(this);

    boolean isMinimal = isMinimalStateMachine(dfaState);
    if (isMinimal) {
      System.out.println("Конечный автомат является минимальным!");
      return;
    }

    System.out.println("Конечный автомат не является минимальным.");
    System.out.println("Построенный минимальный автомат: ");

    System.out.println("   d   .   e   ±   ε");
    for (int i = 0; i < dfaState.size(); i++) {
      System.out.print("q" + i + " ");
      State curState = getStateByNumber(i, dfaState);
      printTransitions(curState.getTransitions());
      System.out.println(" ");
    }
  }

  /** Метод, который печатает заголовок таблицы*/
  private void printTransitions(Map<String, Integer> transitions) {
    printTransitionByKey(transitions, "d");
    printTransitionByKey(transitions, ".");
    printTransitionByKey(transitions, "e");
    printTransitionByKey(transitions, "±");
    printTransitionByKey(transitions, "ε");
  }

  /** Метод, который печатает строку таблицы. */
  private void printTransitionByKey(Map<String, Integer> transitions, String key) {
    if (transitions.get(key) != null) {
      System.out.print(transitions.get(key));
    } else {
      System.out.print("  ");
    }
    System.out.print(" ");
  }

  /** Метод, который проверяет, является ли текущий автомат минимальным. */
  private boolean isMinimalStateMachine(List<State> stateList) {
    for (State state : states) {
      State dfaState = getStateByNumber(state.getStateNumber(), stateList);
      if (state.getStateNumber() != 0 && (dfaState == null || state.getTransitions().size() != dfaState.getTransitions().size())) {
        return false;
      }
    }

    return true;
  }

  /** Метод, который возвращает состояние и матрицу переходов для него по номеру. */
  private State getStateByNumber(Integer number, List<State> stateList) {
    for (State state : stateList) {
      if (state.getStateNumber().equals(number)) {
        return state;
      }
    }

    return null;
  }

  /** Метод, который инициализирует начальное состояние автомата. */
  private List<State> initializeStates() {
    List<State> states = new LinkedList<>();
    Map<String, Integer> transitions = new HashMap<>();

    // q0
    transitions.put("d", 1);
    states.add(new State(0, transitions));

    // q1
    transitions.put("d", 1);
    transitions.put(".", 2);
    transitions.put("e", 3);
    transitions.put("ε", null);
    states.add(new State(1, transitions));

    // q2
    transitions = new HashMap<>();
    transitions.put("d", 4);
    states.add(new State(2, transitions));

    // q3
    transitions = new HashMap<>();
    transitions.put("d", 5);
    transitions.put("±", 6);
    states.add(new State(3, transitions));

    // q4
    transitions = new HashMap<>();
    transitions.put("d", 4);
    transitions.put("e", 3);
    transitions.put("ε", null);
    states.add(new State(4, transitions));

    // q5
    transitions = new HashMap<>();
    transitions.put("d", 5);
    transitions.put("ε", null);
    states.add(new State(5, transitions));

    // q6
    transitions = new HashMap<>();
    transitions.put("d", 5);
    states.add(new State(6, transitions));

    return states;
  }

  /** Метод, который возвращает все состояния автомата. */
  public List<State> getStates() {
    return states;
  }
}

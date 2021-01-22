package group343.stepyrev.state_machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** Класс, который реализует алгоритм DFA. */
public class DFA {
  private List<State> states;

  /** Метод, который реализует алгоритм DFA. */
  public List<State> dfaAlgorithm(StateMachine machine) {
    states = machine.getStates();
    removeUnreachableStates();
    return splitTransitions();
  }

  /** Метод, который разделяет все состояния на конечные и неконечные. */
  private List<State> splitTransitions() {
    List<State> finalStates = findFinalStates();
    List<State> notFinalStates = new ArrayList<>(states);
    notFinalStates.removeAll(finalStates);

    removeSimilarPaths(finalStates);
    removeSimilarPaths(notFinalStates);

    finalStates.addAll(notFinalStates);

    return finalStates;
  }

  /** Метод, который убирает одинаковые переходы. */
  private void removeSimilarPaths(List<State> stateList) {
    for (State state : stateList) {
      List<State> curStateList = new ArrayList<>(stateList);
      curStateList.remove(state);

      Map<String, Integer> oldTransitions = state.getTransitions();
      Map<String, Integer> newTransitions = new HashMap<>();
      for (Map.Entry<String, Integer> entry : oldTransitions.entrySet()) {
        String value = entry.getKey();
        Integer result = entry.getValue();
        if (!isTransitionRepeats(value, result, curStateList)) {
          newTransitions.put(value, result);
        }
      }

      state.setTransitions(newTransitions);
    }
  }

  /** Метод, который проверяет, повторяется ли переход. */
  private boolean isTransitionRepeats(String value, Integer result, List<State> stateList) {
    Map<String, Integer> transitions;
    for (State state : stateList) {
      transitions = state.getTransitions();
      Integer resultValue = transitions.get(value);
      if (resultValue != null && resultValue.equals(result)) {
        return true;
      }
    }

    return false;
  }

  /** Метод, который убирает недостижимые вершины. */
  private void removeUnreachableStates() {
    State startState = states.get(0);
    states = getAllReachableStates(startState);
  }

  /** Метод, который выдает все достижимые вершины.*/
  private List<State> getAllReachableStates(State startState) {
    LinkedList<Integer> queue = new LinkedList<>();
    List<State> reachedStates = new ArrayList<>();
    queue.push(startState.getStateNumber());

    while (queue.size() != 0) {
      Integer lastStateNumber = queue.pop();
      pushAllNeighbours(queue, reachedStates, lastStateNumber);
    }

    return reachedStates;
  }

  /** Метод, который добавляет всех непроверенных соседей в очередь. */
  private void pushAllNeighbours(LinkedList<Integer> queue, List<State> stateList,
      Integer lastStateNumber) {
    State state = getStateByNumber(lastStateNumber, states);
    assert state != null;
    Map<String, Integer> transitions = state.getTransitions();

    for (Map.Entry<String, Integer> entry : transitions.entrySet()) {
      Integer stateNumber = entry.getValue();
      State newState = getStateByNumber(stateNumber, states);
      if (newState == null) {
        continue;
      }

      if (!isContainSuchState(stateList, newState)) {
        stateList.add(newState);
        queue.push(stateNumber);
      }

    }
  }

  /** Метод, который проверяет, содержится ли данное состояние в списке. */
  private boolean isContainSuchState(List<State> stateList, State curState) {
    if (stateList.size() == 0) {
      return false;
    }

    for (State state : stateList) {
      if (state.getStateNumber().equals(curState.getStateNumber())) {
        return true;
      }
    }

    return false;
  }

  /** Метод, который находит все конечные состояния. */
  private List<State> findFinalStates() {
    List<State> finalStates = new LinkedList<>();

    for (State state : states) {
      int reachableStateNumber = countReachableStates(state);
      if (reachableStateNumber == 1) {
        finalStates.add(state);
      }
    }

    return finalStates;
  }

  /** Метод, который считает все достижимые состояния из текущего. */
  private int countReachableStates(State state) {
    return getAllReachableStates(state).size();
  }

  /** Метод, который возвращает состояние по номеру. */
  private State getStateByNumber(Integer number, List<State> stateList) {
    for (State state : stateList) {
      if (state.getStateNumber().equals(number)) {
        return state;
      }
    }

    return null;
  }
}

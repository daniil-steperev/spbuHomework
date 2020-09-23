package group343.stepyrev.set_tasks;

import java.lang.reflect.Array;
import java.util.*;

public class TextHandler {
    private final List<String> PUNCTUATION_MARKS = Arrays.asList(
            ",", ".", ":", "...", "?", "!", ";", "/", "<",
            ">", "-", "(", ")", " ", "\t", "\n", "–", "—", "…",
            "«", "»"
    );

    public Set<String> findAllSymbols(String firstText, String secondText) {
        Set<String> firstSymbols = splitIntoSymbols(firstText);
        Set<String> secondSymbols = splitIntoSymbols(secondText);
        firstSymbols.addAll(secondSymbols);

        return firstSymbols;
    }

    /**
     * Метод, который разделяет исходный текст на символы.
     * @param text - исходный текст
     * @return - множество символов
     */
    public Set<String> splitIntoSymbols(String text) {
        List<String> symbolsWithRepeat = new ArrayList<>();
        symbolsWithRepeat.addAll(Arrays.asList(text.split("")));
        symbolsWithRepeat.removeIf(PUNCTUATION_MARKS::contains);

        return new HashSet<>(symbolsWithRepeat);
    }

    /**
     * Метод, который возвращает все символы, которые встречаются в первом тексте, но не встречаются во втором
     * @param firstText - первое множество символов
     * @param secondText - второе множество симолов
     * @return - символы, которые встречаются в первом тексте, но не встречаются во втором
     */
    public Set<String> findUniqueSymbols(String firstText, String secondText) {
        Set<String> firstSymbols = splitIntoSymbols(firstText);
        Set<String> secondSymbols = splitIntoSymbols(secondText);

        firstSymbols.removeAll(secondSymbols);

        return firstSymbols;
    }

    /**
     * Метод, который возвращает число уникальных символов в тексте.
     * @param text - данный текст
     * @return - число уникальных символов
     */
    public Integer countUniqueSymbols(String text) {
        return splitIntoSymbols(text).size();
    }
}

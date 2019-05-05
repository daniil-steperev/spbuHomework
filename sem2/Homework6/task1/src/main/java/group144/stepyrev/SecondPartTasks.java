package group144.stepyrev;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().filter(string -> string.contains(sequence)).collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random randomGenerator = new Random();
        int MAX_RANDOM_LIMIT = 9999999;

        return (double) IntStream.range(1, MAX_RANDOM_LIMIT).filter(shot -> {
            double TARGET_RADIUS = 0.5;
            double x = randomGenerator.nextDouble() % 1;
            double y = randomGenerator.nextDouble() % 1;

            x = abs(x) - TARGET_RADIUS;
            y = abs(y) - TARGET_RADIUS;

            return (x * x) + (y * y) <= (TARGET_RADIUS * TARGET_RADIUS);
        }).count() / MAX_RANDOM_LIMIT;
    }

    private static double abs(double number) {
        if (number < 0) {
            number = -number;
        }

        return number;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream()
                .max(Comparator.comparing(writer -> writer.getValue().stream().mapToInt(String::length).sum()))
                .get().getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(order -> order.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue + newValue));
    }
}

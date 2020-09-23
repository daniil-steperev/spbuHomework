package group343.stepyrev.set_tasks;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static TextHandler textHandler;

    public static void main(String[] args) throws IOException {
        textHandler = new TextHandler();

        printIntroduction();
        int taskNumber = getTask();
        while (taskNumber != 0) {
            switch (taskNumber) {
                case 1:
                    findAllSymbols();
                    break;
                case 2:
                    findUniqueSymbols();
                    break;
                case 3:
                    countAllSymbols();
                    break;
            }

            taskNumber = getTask();
        }

        System.out.println("Программа завершила работу.");
    }

    private static void countAllSymbols() {
        String text = getText();

        int numberOfSymbols = textHandler.countUniqueSymbols(text);
        System.out.println(String.format("Количество символов в тексте: %d", numberOfSymbols));
    }

    private static void findAllSymbols() throws IOException {
        StringPair texts = getTwoTexts();
        String fstText = texts.getFst();
        String sndText = texts.getSnd();

        Set<String> allSymbols = textHandler.findAllSymbols(fstText, sndText);

        System.out.println("Все символы, встречающиеся в двух текстах: ");
        System.out.println(allSymbols.toString());
    }

    private static void findUniqueSymbols() throws IOException {
        StringPair texts = getTwoTexts();
        String fstText = texts.getFst();
        String sndText = texts.getSnd();

        Set<String> uniqueSymbols = textHandler.findUniqueSymbols(fstText, sndText);
        System.out.println("Все символы, встречающиеся в первом тексте, но отсутствующие во втором: ");
        System.out.println(uniqueSymbols.toString());
    }

    private static StringPair getTwoTexts() throws IOException {
        System.out.println("Введите первый текст: ");
        String fstText = getText();
        System.out.println("Введите второй текст: ");
        String sndText = getText();

        return new StringPair(fstText, sndText);
    }

    private static Integer getTask() {
        Scanner scanner = new Scanner(System.in);
        String task = "";

        while (!isCorrectTaskNumber(task)) {
            task = scanner.next();
        }

        return Integer.parseInt(task);
    }

    private static String getText() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static boolean isCorrectTaskNumber(String task) {
        try {
            int taskInt = Integer.parseInt(task);
            return (taskInt >= 0 && taskInt <= 3);
        } catch (Exception e) {
            System.out.println("Пожалуйста, введите число от 1 до 3, если вы хотите продоллжить работу с текстами. " +
                    "Для завершения работы программы введите 0.");
            return false;
        }
    }

    private static void printIntroduction() {
        System.out.println("Программа работы с множествами.");
        System.out.println("Доступны следующие функции: ");
        System.out.println("1) найти все символы, которые встречаются в двух заданных текстах;");
        System.out.println("2) определить символы, которые встречаются в первом тексте, но не встречаются во втором;");
        System.out.println("3) определить, сколько различных символов в тексте.");
    }
}

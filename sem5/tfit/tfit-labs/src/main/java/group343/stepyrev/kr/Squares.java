package group343.stepyrev.kr;

import java.util.Scanner;

public class Squares {

  public static void main(String[] args) {
    // инициализируем сканнер, которым будем считывать число n с консоли
    Scanner scanner = new Scanner(System.in);

    // запрашиваем у пользователя целое число
    System.out.println("Пожалуйста, введите сторону квадрата n: ");
    // считываем сторону квадрата
    int n = scanner.nextInt();

    // считаем число прямоугольников по выведенной формуле
    int result = n * n * (n + 1) * (n + 1) / 4;

    // выводим результат
    System.out.println(String.format("Число прямоугольников, которые можно насчитать "
        + "внутри квадрата %d * %d, равно: %d.", n, n, result));
  }
}

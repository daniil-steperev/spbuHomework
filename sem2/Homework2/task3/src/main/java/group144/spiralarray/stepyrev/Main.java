package group144.spiralarray.stepyrev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws WrongInputException {
        try {
            Matrix matrix = new Matrix();

            System.out.println("To print matrix in console input 0");
            System.out.println("To write matrix in file input 1");
            Scanner scanner = new Scanner(System.in);
            int answer = scanner.nextInt();

            while (answer != 0 && answer != 1) {
                System.out.println("Input correct data");
                answer = scanner.nextInt();
            }

            if (answer == 0) {
                ConsoleWriter consoleWriter = new ConsoleWriter();
                consoleWriter.write(matrix);
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String fileName = "";

                System.out.println("Input fileName");

                fileName = reader.readLine();
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(matrix);
            }
        } catch (WrongInputException e) {
            System.out.println("Size of matrix should be odd and positive!");
            throw new WrongInputException();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

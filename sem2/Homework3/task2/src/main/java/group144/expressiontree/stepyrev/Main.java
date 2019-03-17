package group144.expressiontree.stepyrev;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, WrongInputException {
        String fileName = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input file name: ");

        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            System.out.println("File name can not be read!");
            throw new IOException();
        }

        reader.close();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String expression = fileReader.readLine();

            ExpressionTree expressionTree = new ExpressionTree(expression, 0);
            System.out.println("Result = " + expressionTree.calculate());
            System.out.print("Builded expression tree: ");
            expressionTree.print();

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File can not be opened!");
            throw new FileNotFoundException();
        } catch (WrongInputException e) {
            System.out.println("Wrong expression was inputted!");
            throw new WrongInputException();
        }
    }
}

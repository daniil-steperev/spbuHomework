package group144.spiralarray.stepyrev;

import java.util.Scanner;

/**
 * A class that represents a matrix in a table view
 */
public class Matrix {
    private int[][] matrix;
    private int size;

    public Matrix() throws WrongInputException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of matrix: ");
        int size = scanner.nextInt();
        if (size % 2 == 0 || size < 0) {
            throw new WrongInputException("Size should be odd and positive!");
        }

        this.size = size;
        int matrix[][] = new int[size][size];

        System.out.println("");
        System.out.print("Enter your matrix: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    matrix[i][j] = scanner.nextInt();
                } catch (Exception e) {
                    throw new WrongInputException("Wrong number inputted!");
                }
            }
        }

        this.matrix = matrix;
    }

    public Matrix(int[][] matrix, int size) throws WrongInputException {
        for (int i = 0; i < size; i++) {
            if (matrix[i].length != size) {
                throw new WrongInputException("Wrong inputted matrix!");
            }
        }
        this.matrix = matrix;

        if (size % 2 == 0) {
            throw new WrongInputException("Size of matrix should be odd!");
        }
        this.size = size;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return size;
    }
}

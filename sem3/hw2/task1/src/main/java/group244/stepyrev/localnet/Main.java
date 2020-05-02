package group244.stepyrev.localnet;

import group244.stepyrev.localnet.StandartSystems.DOS;
import group244.stepyrev.localnet.StandartSystems.Linux;
import group244.stepyrev.localnet.StandartSystems.MacOS;
import group244.stepyrev.localnet.StandartSystems.Windows;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static int computerNumber;
    private static Random random = new Random();

    private static int getFirstInfected() {
        System.out.println("Enter the number of first infected computer: ");
        int number = scanner.nextInt();

        while (!isCorrectFirstInfectedNumber(number)) {
            System.out.println("Enter correct number: ");
            number = scanner.nextInt();
        }

        return number;
    }

    private static boolean isCorrectFirstInfectedNumber(int number) {
        return number >= 0 && number <= computerNumber - 1;
    }

    private static Virus getVirus() {
        System.out.println("Enter the probabilities of system infection (double number between 0.0 and 1.0: ");
        System.out.println("Enter Windows probability: ");
        double infectWin = scanner.nextDouble();
        while (!isCorrectProbability(infectWin)) {
            System.out.println("Enter the correct probability: ");
            infectWin = scanner.nextDouble();
        }

        System.out.println("Enter Linux probability: ");
        double infectLin = scanner.nextDouble();
        while (!isCorrectProbability(infectLin)) {
            System.out.println("Enter the correct probability: ");
            infectLin = scanner.nextDouble();
        }

        System.out.println("Enter Mac OS probability: ");
        double infectMac = scanner.nextDouble();
        while (!isCorrectProbability(infectMac)) {
            System.out.println("Enter the correct probability: ");
            infectMac = scanner.nextDouble();
        }

        System.out.println("Enter DOS probability: ");
        double infectDos = scanner.nextDouble();
        while (!isCorrectProbability(infectDos)) {
            System.out.println("Enter the correct probability: ");
            infectDos = scanner.nextDouble();
        }

        return new MainVirus(infectWin, infectWin, infectMac, infectDos);
    }

    private static boolean isCorrectProbability(double number) {
        return (number >= 0.0) && (number <= 1.0);
    }

    private static boolean[][] getConnections() {
        boolean[][] connections = new boolean[computerNumber][computerNumber];

        System.out.println("Enter the connections between computers: ");
        System.out.print("Enter the number of connected computers by pairs (first one, second one)");
        System.out.print(" without spaces. If you want to stop, enter -1 -1: ");
        int firstComputer;
        int secondComputer;
        while (true) {
            firstComputer = scanner.nextInt();
            while (!isValidComputerNumber(firstComputer)) {
                System.out.println("Enter valid number of first computer: ");
                firstComputer = scanner.nextInt();
            }

            secondComputer = scanner.nextInt();
            while (!isValidComputerNumber(secondComputer)) {
                System.out.println("Enter valid number of the second computer: ");
                secondComputer = scanner.nextInt();
            }

            if (firstComputer == -1) {
                System.out.println();
                return connections;
            }

            connections[firstComputer][secondComputer] = true;
        }
    }

    private static boolean isValidComputerNumber(int number) {
        return number <= computerNumber - 1 && number >= -1;
    }

    private static ArrayList<Computer> getComputers() {
        ArrayList<Computer> computers = new ArrayList<>();

        System.out.println("Enter the number of computers: ");
        computerNumber = scanner.nextInt();

        System.out.println("Enter the operation system of each one: ");
        for (int i = 0; i < computerNumber; i++) {
            System.out.print("Computer ");
            System.out.print(i);
            System.out.println(": ");
            printOS();

            int choice = scanner.nextInt();
            OS os = getOS(choice);
            computers.add(new Computer(os));
        }

        System.out.println();
        return computers;
    }

    private static OS getOS(int number) {
        while (true) {
            switch (number) {
                case 1:
                    return new Windows();
                case 2:
                    return new Linux();
                case 3:
                    return new MacOS();
                case 4:
                    return new DOS();
                default:
                    System.out.println("Enter correct number, please.");
                    number = scanner.nextInt();
                    break;
            }
        }
    }

    private static void printOS() {
        System.out.println("1 - Windows");
        System.out.println("2 - Linux");
        System.out.println("3 - Mac OS");
        System.out.println("4 - DOS");
    }

    private static LocalNetwork getNetwork() {
        ArrayList<Computer> computers = getComputers();
        boolean[][] connections = getConnections();
        Virus virus = getVirus();
        int firstInfected = getFirstInfected();

        return new LocalNetwork(computers, connections, virus, firstInfected);
    }

    public static void main(String[] args) {
        scanner = new Scanner(new InputStreamReader(System.in));
        LocalNetwork network = getNetwork();

        int step = 1;
        while (step == 1) {
            network.makeTurn();
            System.out.println(network.getComputersStatus());

            System.out.println("Enter 1 to continue, other else to stop: ");
            step = scanner.nextInt();
        }

        callGoodByeMessage(network);

        scanner.close();
    }

    private static void callGoodByeMessage(LocalNetwork network) {
        if (network.isAllInfected()) {
            System.out.println("Ha-ha! Smart viruses will enslave this world and silly people!");
        } else {
            System.out.println("That time you were better that we thought... Beware of us little people!");
        }
    }

    /** A virus that infects only windows, linux, mac and dos. */
    private static class MainVirus implements Virus {
        private double infectWin;
        private double infectLin;
        private double infectMac;
        private double infectDOS;

        private MainVirus(double infectWin, double infectLin, double infectMac, double infectDOS) {
            this.infectWin = infectWin;
            this.infectLin = infectLin;
            this.infectMac = infectMac;
            this.infectDOS = infectDOS;
        }

        /**
         * A method that checks if the virus has infected Windows.
         * @param windows - a windows OS
         * @return - true if virus has infected, false otherwise
         */
        public boolean infect(Windows windows) {
            return random.nextDouble() < infectWin;
        }

        /**
         * A method that checks if the virus has infected Linux.
         * @param linux - a linux OS
         * @return - true if virus has infected, false otherwise
         */
        public boolean infect(Linux linux) {
            return random.nextDouble() < infectLin;
        }

        /**
         * A method that checks if the virus has infected MacOS.
         * @param macOS - a MacOS
         * @return - true if virus has infected, false otherwise
         */
        public boolean infect(MacOS macOS) {
            return random.nextDouble() < infectMac;
        }

        /**
         * A method that checks if the virus has infected DOS.
         * @param dos - a DOS
         * @return - true if virus has infected, false otherwise
         */
        public boolean infect(DOS dos) {
            return random.nextDouble() < infectDOS;
        }
    }
}

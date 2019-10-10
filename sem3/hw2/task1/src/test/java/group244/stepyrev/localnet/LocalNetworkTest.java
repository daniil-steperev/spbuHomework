package group244.stepyrev.localnet;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalNetworkTest {
    /** A list of computers that is used in tests. */
    private ArrayList<Computer> computers;
    /** A matrix of connections of the computers that are used in tests. */
    private boolean[][] connections;
    /** A virus that is used in tests. */
    private Virus virus;
    /** A network that is used in tests. */
    private LocalNetwork network;
    /** A constant that represents the number of test repeats. */
    private final int TEST_NUMBER = 1000;

    @Test
    public void getConnectionsTest() throws IOException {
        initializeComputers();
        initializeConnections();
        network = new LocalNetwork(computers, connections, new SasserVirus(), 1);

        assertEquals(network.getConnections(), "false true true false true\n" +
                "true false true true true\n" +
                "true true false false true\n" +
                "false true false false false\n" +
                "true false true false false");
    }

    @Test
    public void getComputersStatusNonInfectedTest() throws IOException {
        initializeComputers();
        network = new LocalNetwork(computers, connections, new SasserVirus(), 1);
        assertEquals(network.getComputersStatus(), "Computer 0 is not infected.\n" +
                "Computer 1 is infected.\n" +
                "Computer 2 is not infected.\n" +
                "Computer 3 is not infected.\n" +
                "Computer 4 is not infected.");
    }

    @Test
    public void infectCorrectnessTest() throws IOException {
        initializeComputers();
        initializeConnections();
        virus = new SasserVirus();
        network = new LocalNetwork(computers, connections, virus, 0);

        assertEquals(network.getComputersStatus(), "Computer 0 is infected.\n" +
                "Computer 1 is not infected.\n" +
                "Computer 2 is not infected.\n" +
                "Computer 3 is not infected.\n" +
                "Computer 4 is not infected.");

        network.makeTurn();
        assertEquals(network.getComputersStatus(), "Computer 0 is infected.\n" +
                "Computer 1 is infected.\n" +
                "Computer 2 is infected.\n" +
                "Computer 3 is not infected.\n" +
                "Computer 4 is infected.");

        network.makeTurn();
        assertEquals(network.getComputersStatus(), "Computer 0 is infected.\n" +
                "Computer 1 is infected.\n" +
                "Computer 2 is infected.\n" +
                "Computer 3 is infected.\n" +
                "Computer 4 is infected.");
    }

    @Test
    public void infectWinDOSCorrectnessTest() throws IOException {
        initializeComputers();
        initializeConnections();
        virus = new ConfickerVirus();
        network = new LocalNetwork(computers, connections, virus, 0);

        assertEquals(network.getComputersStatus(), "Computer 0 is infected.\n" +
                "Computer 1 is not infected.\n" +
                "Computer 2 is not infected.\n" +
                "Computer 3 is not infected.\n" +
                "Computer 4 is not infected.");

        network.makeTurn();
        assertEquals(network.getComputersStatus(), "Computer 0 is infected.\n" +
                "Computer 1 is not infected.\n" +
                "Computer 2 is not infected.\n" +
                "Computer 3 is not infected.\n" +
                "Computer 4 is infected.");

        for (int i = 0; i < TEST_NUMBER; i++) { // computer "3" should not be infected because it doesn't connect to infected ones
            network.makeTurn();
            assertEquals(network.getComputersStatus(), "Computer 0 is infected.\n" +
                    "Computer 1 is not infected.\n" +
                    "Computer 2 is not infected.\n" +
                    "Computer 3 is not infected.\n" +
                    "Computer 4 is infected.");
        }

    }


    /** A method that adds a computer to computers list. */
    private void initializeComputers() {
        computers = new ArrayList<>();
        computers.add(new Computer(OS.WINDOWS));
        computers.add(new Computer(OS.LINUX));
        computers.add(new Computer(OS.MACOS));
        computers.add(new Computer(OS.DOS));
        computers.add(new Computer(OS.DOS));
    }


    /**
     * A method that initializes a connections.
     *
     *   1 2 3 4 5
     * 1 0 1 1 0 1
     * 2 1 0 1 1 1
     * 3 1 1 0 0 1
     * 4 0 1 0 0 0
     * 5 1 0 1 0 0
     */
    private void initializeConnections() throws IOException {
        connections = new boolean[5][5];
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/connections.txt"));
        String line;
        String[] lineElements;

        for (int i = 0; i < 5; i++) {
            line = reader.readLine();
            lineElements = line.split(" ");

            for (int j = 0; j < 5; j++) {
                if (lineElements[j].equals("1")) {
                    connections[i][j] = true;
                }
            }
        }

        reader.close();
    }

    /** A virus that infects only windows and dos with 100% probability. */
    private class ConfickerVirus implements Virus {
        @Override
        public boolean tryInfect(OS os) {
            switch (os) {
                case WINDOWS:
                    return infectWindows();
                case DOS:
                    return infectDOS();
                default:
                    return false;
            }
        }

        @Override
        public boolean infectWindows() {
            return true;
        }

        @Override
        public boolean infectLinux() {
            return false;
        }

        @Override
        public boolean infectMacos() {
            return false;
        }

        @Override
        public boolean infectDOS() {
            return true;
        }
    }

    /** A virus that infects all computers with 100% probability. */
    private class SasserVirus implements Virus {

        @Override
        public boolean tryInfect(OS os) {
            return true;
        }

        @Override
        public boolean infectWindows() {
            return true;
        }

        @Override
        public boolean infectLinux() {
            return true;
        }

        @Override
        public boolean infectMacos() {
            return true;
        }

        @Override
        public boolean infectDOS() {
            return true;
        }
    }
}
package group244.stepyrev.localnet;

import java.util.ArrayList;

/** A class that represents a local network. */
public class LocalNetwork {
    /** A list of all computers. */
    private ArrayList<Computer> computers;

    /** An adjacency matrix of the computer's connections. */
    private boolean[][] computerLinks;

    /** A virus. */
    private Virus virus;

    /**
     *  A constructor of the local network class.
     * @param computers - a list of computers
     * @param matrix - a matrix with computers connections
     * @param virus - a nimbdaVirus
     * @param index - an index of first infected computer
     */
    public LocalNetwork(ArrayList<Computer> computers, boolean[][] matrix, Virus virus, int index) {
        computers.get(index).infect();

        this.computers = computers;
        this.computerLinks = matrix;
        this.virus = virus;
    }

    /**
     * A method that makes a turn.
     *
     * To make a turn means to try to infect non infected computers, which is connected to infected ones.
     */
    public void makeTurn() {
        ArrayList<Computer> infected = getInfectedComputers();

        for (int i = 0; i < infected.size(); i++) {
            ArrayList<Computer> nonInfected = getNonInfectedLinkedComputers(infected.get(i));

            for (int j = 0; j < nonInfected.size(); j++) {
                nonInfected.get(j).tryInfect(virus);
            }
        }
    }

    /**
     * A method that returns a list of infected computers.
     * @return - a list of infected computers.
     */
    private ArrayList<Computer> getInfectedComputers() {
        ArrayList<Computer> infected = new ArrayList<>();

        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).isInfected()) {
                infected.add(computers.get(i));
            }
        }

        return infected;
    }

    /**
     * A method that returns non infected computers linked with the numbered one.
     * @param computer - the computer, which linked ones are searching
     * @return - a list with non infected computers
     */
    private ArrayList<Computer> getNonInfectedLinkedComputers(Computer computer) {
        ArrayList<Computer> nonInfected = new ArrayList<>();
        int number = 0;
        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).equals(computer)) {
                number = i;
                break;
            }
        }


        for (int j = 0; j < computers.size(); j++) {
            if (computerLinks[number][j] && !computers.get(j).isInfected()) {
                nonInfected.add(computers.get(j));
            }
        }

        return nonInfected;
    }

    /**
     * A method that returns a status of the computers.
     * @return - a status of the computers
     */
    public String getComputersStatus() {
        StringBuilder status = new StringBuilder();

        for (int i = 0 ; i < computers.size(); i++) {
            status.append("Computer ");
            status.append(i);
            if (computers.get(i).isInfected()) {
                status.append(" is infected.");
            } else {
                status.append(" is not infected.");
            }

            if (i != computers.size() - 1) {
                status.append("\n");
            }
        }

        return status.toString();
    }

    /**
     * A method that returns a string with connections.
     * @return - a connection string
     */
    public String getConnections() {
        StringBuilder connections = new StringBuilder();

        for (int i = 0; i < computers.size(); i++) {
            for (int j = 0; j < computers.size(); j++) {
                connections.append(computerLinks[i][j]);

                if (j != computers.size() - 1) {
                    connections.append(" ");
                }
            }

            if (i != computers.size() - 1) {
                connections.append("\n");
            }
        }

        return connections.toString();
    }
}

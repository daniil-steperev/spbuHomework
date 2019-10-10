package group244.stepyrev.localnet;

/** A class that represents a computer. */
public class Computer {
    /** An operational system of the computer. */
    private OS os;
    /** A flag that represents if the computer is infected. */
    private boolean isInfected;

    /** A constructor of the class. */
    public Computer(OS os) {
        this.os = os;
        isInfected = false;
    }

    /**
     * A method that tries to infect the computer.
     * @param nimbdaVirus - a nimbdaVirus that tries to infect the computer.
     * @return - true if computer was infected, false otherwise.
     */
    public boolean tryInfect(Virus nimbdaVirus) {
        if (isInfected) {
            return true;
        }

        if (nimbdaVirus.tryInfect(os)) {
            isInfected = true;
            return true;
        }

        return false;
    }

    /** A method that infects the computer. */
    public void infect() {
        isInfected = true;
    }

    /** A method that returns if the computer is infected. */
    public boolean isInfected() {
        return isInfected;
    }
}

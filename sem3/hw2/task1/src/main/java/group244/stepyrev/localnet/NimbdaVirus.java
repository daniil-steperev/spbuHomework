package group244.stepyrev.localnet;

import java.util.Random;

/** A class that represents a nimbda virus.
 *
 *  Probability of the infection depends on the operational system of the computer.
 * */
public class NimbdaVirus implements Virus {
    /** A random generator.*/
    private Random random = new Random();

    /** A probability of window computer infection. */
    private final double WIN_PROBABILITY = 0.35;
    /** A probability of linux computer infection. */
    private final double LIN_PROBABILITY = 0.25;
    /** A probability of mac computer infection. */
    private final double MAC_PROBABILITY = 0.15;
    /** A probability of dos computer infection. */
    private final double DOS_PROBABILITY = 0.29;


    /**
     * A method that tries to infect the computer.
     * @param os - an operational system of the computer
     * @return - true if computer was infected, false otherwise.
     */
    @Override
    public boolean tryInfect(OS os) {
        switch (os) {
            case WINDOWS:
                return infectWindows();
            case LINUX:
                return infectLinux();
            case MACOS:
                return infectMacos();
            case DOS:
                return infectDOS();
            default:
                return false;
        }
    }

    /**
     * A method that tries to infect the Windows computer.
     *
     * The probability of an infection is 35%.
     * @return - true of computer was infected, false otherwise.
     */
    @Override
    public boolean infectWindows() {
        return random.nextDouble() < WIN_PROBABILITY;
    }

    /**
     * A method that tries to infect the Linux computer.
     *
     * The probability of an infection is 25%.
     * @return - true if computer was infected, false otherwise.
     */
    @Override
    public boolean infectLinux() {
        return random.nextDouble() < LIN_PROBABILITY;
    }

    /**
     * A method that tries to infect the Macos computer.
     *
     * The probability of an infection is 15%.
     * @return - true if computer was infected, false otherwise.
     */
    @Override
    public boolean infectMacos() {
        return random.nextDouble() < MAC_PROBABILITY;
    }

    /**
     * A method that tries to infect the DOS computer.
     *
     * The probability of an infection is 39%.
     * @return - true if computer was infected, false otherwise.
     */
    @Override
    public boolean infectDOS() {
        return random.nextDouble() < DOS_PROBABILITY;
    }
}

package group244.stepyrev.localnet;

/** An interface that represents a computer virus. */
public interface Virus {
    /** A method that tries to infect the computer. */
    boolean tryInfect(OS os);

    /** A method that tries to infect the Windows computer. */
    boolean infectWindows();
    /** A method that tries to infect the Linux computer. */
    boolean infectLinux();
    /** A method that tries to infect the macOS computer. */
    boolean infectMacos();
    /** A method that tries to infect the DOS computer. */
    boolean infectDOS();
}

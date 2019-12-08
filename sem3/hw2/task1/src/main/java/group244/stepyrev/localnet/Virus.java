package group244.stepyrev.localnet;

import group244.stepyrev.localnet.StandartSystems.DOS;
import group244.stepyrev.localnet.StandartSystems.Linux;
import group244.stepyrev.localnet.StandartSystems.MacOS;
import group244.stepyrev.localnet.StandartSystems.Windows;

/** An interface that represents a computer virus. */
public interface Virus {
    /** A method that tries to infect a computer with DOS. */
    boolean infect(DOS os);

    /** A method that tries to infect a computer with Linux. */
    boolean infect(Linux os);

    /** A method that tries to infect a computer with MacOS. */
    boolean infect(MacOS os);

    /** A method that tries to infect a computer with Windows. */
    boolean infect(Windows os);
}

package group244.stepyrev.localnet.StandartSystems;

import group244.stepyrev.localnet.OS;
import group244.stepyrev.localnet.Virus;

/** A class that represents a Windows os. */
public class Windows implements OS {
    /* {@inheritDoc} */
    public boolean tryToInfect(Virus virus) {
        return virus.infect(this);
    }
}

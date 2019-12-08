package group244.stepyrev.localnet.StandartSystems;

import group244.stepyrev.localnet.OS;
import group244.stepyrev.localnet.Virus;

/** A class that represents a MacOS os. */
public class MacOS implements OS {
    /* {@inheritDoc} */
    @Override
    public boolean tryToInfect(Virus virus) {
        return virus.infect(this);
    }
}

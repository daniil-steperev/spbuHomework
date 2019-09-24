package group144.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    @Test
    public void addTest() {
        Application application = new Application();
        String[] strings = {"abc aa", "asd sa asda"};
        application.add(strings);

        assertEquals("abc aa asd sa asda ", application.printSet());
    }

}
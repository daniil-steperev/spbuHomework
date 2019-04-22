package group144.stepyrev;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClassBuilderTest {
    private static ClassBuilder classBuilder = new ClassBuilder();

    @Test
    public void printStructureTest() throws IOException {
        String result = classBuilder.printStructure(group144.stepyrev.test.TwoTuple.class);
        String expected = "package group144.stepyrev.buildClass;\n" +
                "\n" +
                "public class TwoTuple <A, B> extends Object  {\n" +
                "\tpublic final Object first = null;\n" +
                "\tpublic final Object second = null;\n" +
                "\t\n" +
                "\tpublic TwoTuple(A arg0, B arg1) { }\n" +
                "\n" +
                "\tpublic String toString ()  {\n" +
                "\t\treturn null;\n" +
                "\t}\n" +
                "}\n\n\t";
        assertEquals(expected, result);
    }

    @Test
    public void diffStructureTest() throws IOException {
        classBuilder.printStructure(ClassBuilder.class);
        assertTrue(classBuilder.diffClasses(ClassBuilder.class, group144.stepyrev.buildClass.ClassBuilder.class));
    }
}
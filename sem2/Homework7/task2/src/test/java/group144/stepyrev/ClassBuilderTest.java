package group144.stepyrev;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.jupiter.api.Assertions.*;

class ClassBuilderTest {
    private static ClassBuilder classBuilder = new ClassBuilder();

    @Test
    public void printStructureTest() throws IOException, ClassNotFoundException {
        ClassLoader printedClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> printedClass = printedClassLoader.loadClass("group144.stepyrev.testClasses.TwoTuple");
        String result = classBuilder.printStructure(printedClass);
        String expected = "package group144.stepyrev.buildClass;\n" +
                "\n" +
                "public class TwoTuple<A, B> extends Object  {\n" +
                "\tpublic final Object first = null;\n" +
                "\tpublic final Object second = null;\n" +
                "\t\n" +
                "\tpublic TwoTuple(A arg0, B arg1) { }\n" +
                "\n" +
                "\tpublic String toString ()  {\n" +
                "\t\treturn null;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "\t";
        assertEquals(expected, result);
    }

    @Test
    public void diffStructureTrueTest() throws IOException, ClassNotFoundException {
        ClassLoader printedClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> printedClass = printedClassLoader.loadClass("group144.stepyrev.ClassBuilder");
        classBuilder.printStructure(printedClass);

        ClassLoader buildClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> buildClass = buildClassLoader.loadClass("group144.stepyrev.buildClass.ClassBuilder");
        assertTrue(classBuilder.diffClasses(printedClass, buildClass));
    }

    @Test
    public void diffStructureDifficultTest() throws IOException, ClassNotFoundException {
        ClassLoader printedClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> printedClass = printedClassLoader.loadClass("group144.stepyrev.testClasses.DifficultClass");
        classBuilder.printStructure(printedClass);

        ClassLoader buildClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> buildClass = buildClassLoader.loadClass("group144.stepyrev.buildClass.DifficultClass");
        assertTrue(classBuilder.diffClasses(printedClass, buildClass));
    }

    @Test
    public void diffStructureFalseTest() throws IOException, ClassNotFoundException {
        ClassLoader printedClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> printedClass = printedClassLoader.loadClass("group144.stepyrev.testClasses.TwoTupleMoreMethod");
        classBuilder.printStructure(printedClass);

        ClassLoader buildClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> buildClass = buildClassLoader.loadClass("group144.stepyrev.buildClass.TwoTuple");
        assertFalse(classBuilder.diffClasses(printedClass, buildClass));
    }
}
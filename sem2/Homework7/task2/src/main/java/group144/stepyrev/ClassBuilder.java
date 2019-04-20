package group144.stepyrev;

import java.io.*;
import java.lang.reflect.*;

/** A class that builds a file with a text of inputted file. */
public class ClassBuilder {
    public static void main(String[] args) throws IOException {
        ClassBuilder classBuilder = new ClassBuilder();
        FileWriter fileOutput = new FileWriter("src//buildClass.java", true);
        classBuilder.printStructure(fileOutput, String.class);
        fileOutput.close();
    }

    /**
     * A method that builds a file with a text of class.
     * @param clazz - a class which should be build in the file
     */
    public void printStructure(FileWriter file, Class clazz) throws IOException {
        file.write("import java.io.*;\n");
        file.write("import java.util.*;\n\n");
        getStructure(file, clazz);
    }

    private void getStructure(FileWriter fileOutput, Class clazz) throws IOException {
        writeClassDeclaration(fileOutput, clazz);
        fileOutput.write("{\n\t");

        writeFields(fileOutput, clazz);
        writeConstructors(fileOutput, clazz);
        writeMethods(fileOutput, clazz);
        writeInnerClasses(fileOutput, clazz);

        fileOutput.write("\n}");
        fileOutput.write("\n\n\t");
    }

    private void writeClassDeclaration(FileWriter file, Class clazz) throws IOException {
        writeClassModifiers(file, clazz);
        file.write("class ");
        writeClassNameWithParameters(file, clazz); // space here
        writeSuperclass(file, clazz); // space here
        writeInterfaces(file, clazz); // space here
    }

    private void writeClassModifiers(FileWriter file, Class clazz) throws IOException {
        if (clazz.getModifiers() != 0) {
            file.write(Modifier.toString(clazz.getModifiers()) + " ");
        }
    }

    private void writeClassNameWithParameters(FileWriter file, Class clazz) throws IOException {
        file.write(clazz.getSimpleName());
        file.write(" ");
        if (clazz.getTypeParameters().length != 0) {
            TypeVariable[] parameters = clazz.getTypeParameters();
            file.write("<");
            for (int i = 0; i < parameters.length; i++) {
                file.write(parameters[i].getName());

                if (i != parameters.length - 1) {
                    file.write(", ");
                }
            }

            file.write("> ");
        }
    }

    private void writeSuperclass(FileWriter file, Class clazz) throws IOException {
        if (clazz.getSuperclass() != null) {
            file.write("extends ");
            writeClassNameWithParameters(file, clazz.getSuperclass());
        }
    }

    private void writeInterfaces(FileWriter file, Class clazz) throws IOException {
        if (clazz.getInterfaces().length != 0) {
            file.write("implements ");
            Class[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                file.write(interfaces[i].getSimpleName());
                if (i != interfaces.length - 1) {
                    file.write(", ");
                }
            }
        }

        file.write(" ");
    }

    private void writeFields(FileWriter file, Class clazz) throws IOException {
        if (clazz.getDeclaredFields().length != 0) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                writeOneField(file, fields[i]);
                file.write("\n\t");
            }
        }
    }

    private void writeOneField(FileWriter file, Field field) throws IOException {
        if (field.getModifiers() != 0) {
            file.write(Modifier.toString(field.getModifiers()));
            file.write(" ");
        }

        file.write(field.getType().getSimpleName() + " " + field.getName() + " = ");
        writeType(file, field.getType());
        file.write("\n");
    }

    private void writeConstructors(FileWriter file, Class clazz) throws IOException {
        file.write("\n\t");
        if (clazz.getDeclaredConstructors().length != 0) {
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for (int i = 0; i < constructors.length; i++) {
                writeOneConstructor(file, clazz, constructors[i]);
                file.write("\n\n\t");
            }
        }
    }

    private void writeOneConstructor(FileWriter file, Class clazz, Constructor constructor) throws IOException {
        if (constructor.getModifiers() != 0) {
            file.write(Modifier.toString(constructor.getModifiers()) + " ");
        }

        file.write(clazz.getSimpleName());
        file.write("(");
        Parameter[] parameters = constructor.getParameters();
        writeParameters(file, parameters);
        file.write(") ");
        file.write("{ }");
    }

    private void writeMethods(FileWriter file, Class clazz) throws IOException {
        if (clazz.getDeclaredMethods().length != 0) {
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                writeOneMethod(file, methods[i]);

                if (i != methods.length - 1) {
                    file.write("\n\n\t");
                }
            }
        }
    }

    private void writeOneMethod(FileWriter file, Method method) throws IOException {
        if (method.getModifiers() != 0) {
            file.write(Modifier.toString(method.getModifiers()) + " ");
        }

        file.write(method.getReturnType().getSimpleName() + " ");
        file.write(method.getName() + " (");
        Parameter[] parameters = method.getParameters();
        writeParameters(file, parameters);
        file.write(") ");
        writeExceptions(file, method);

        file.write("{");
        if (!method.getReturnType().getSimpleName().equals("void")) {
            file.write("\n\t\t");
            file.write("return ");
            writeType(file, method.getReturnType());
            file.write("\n\t");
        } else {
            file.write(" ");
        }

        file.write("}");
    }

    private void writeType(FileWriter file, Type type) throws IOException {
        switch (type.getTypeName()) {
            case "Integer":
                file.write("Integer.valueOf(0);");
                break;
            case "Boolean":
                file.write("true;");
                break;
            case "String":
                file.write("String.valueOf(\"\");");
                break;
            case "Double":
                file.write("Double.valueOf(0.0);");
                break;
            case "Character":
                file.write("Character.valueOf('x');");
                break;
            case "Byte":
                file.write("Byte.valueOf(0);");
                break;
            case "Long":
                file.write("Long.valueOf(0);");
                break;
            case "Short":
                file.write("Short.valueOf(0);");
                break;
            case "Float":
                file.write("Float.valueOf(0);");
                break;
            case "int":
                file.write("0;");
                break;
            case "boolean":
                file.write("true;");
                break;
            case "double":
                file.write("0.0;");
                break;
            case "char":
                file.write("'x';");
                break;
            case "byte":
                file.write("0;");
                break;
            case "long":
                file.write("0;");
                break;
            case "short":
                file.write("0;");
                break;
            case "float":
                file.write("0;");
                break;
            default:
                file.write("null;");
                break;
        }
    }

    private void writeExceptions(FileWriter file, Method method) throws IOException {
        if (method.getExceptionTypes().length != 0) {
            file.write("throws ");
            Class[] exceptions = method.getExceptionTypes();
            for (int i = 0; i < exceptions.length; i++) {
                file.write(exceptions[i].getSimpleName());
                if (i != exceptions.length - 1) {
                    file.write(", ");
                }
            }
        }
        file.write(" ");
    }

    private void writeParameters(FileWriter file, Parameter[] parameters) throws IOException {
        for (int i = 0; i < parameters.length; i++) {
            file.write(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());
            if (i != parameters.length - 1) {
                file.write(", ");
            }
        }
    }

    private void writeInnerClasses(FileWriter file, Class clazz) throws IOException {
        if (clazz.getDeclaredClasses().length != 0) {
            file.write("\n\t");
            Class<?>[] classes = clazz.getDeclaredClasses();
            for (int i = 0; i < classes.length; i++) {
                getStructure(file, classes[i]);

                if (i != classes.length -1) {
                    file.write("\n\n\t");
                }
            }
        }
    }

    /**
     * A method that print in the console difference between two classes.
     * @param firstClazz - a first class
     * @param secondClazz - a second class
     */
    public void diffClasses(Class firstClazz, Class secondClazz) throws IOException {
        FileWriter fileOutput = new FileWriter("src//diffClasses.txt");
        getDifference(fileOutput, firstClazz, secondClazz);

        BufferedReader reader = new BufferedReader(new FileReader("src//diffClasses.txt"));
        while (reader.ready()) {
            System.out.println(reader.readLine());
        }

        fileOutput.close();
        reader.close();
    }

    private void getDifference(FileWriter fileOutput, Class firstClazz, Class secondClazz) throws IOException {
        writeDifferenceInFields(fileOutput, firstClazz, secondClazz);
        writeDifferenceInMethods(fileOutput, firstClazz, secondClazz);
        writeDifferenceInInnerClasses(fileOutput, firstClazz, secondClazz);
    }

    private void writeDifferenceInFields(FileWriter file, Class firstClazz, Class secondClazz) throws IOException {
        Field[] firstClassFields = firstClazz.getDeclaredFields();
        Field[] secondClassFields = secondClazz.getDeclaredFields();

        for (int i = 0; i < firstClassFields.length; i++) {
            Field curField = firstClassFields[i];

            boolean contains = false;
            for (int j = 0; j < secondClassFields.length; j++) {
                Field secondField = secondClassFields[j];

                if (curField.getName().equals(secondField.getName())) {
                    if (!isFieldEquals(curField, secondField)) {
                        writeOneField(file, curField);
                        file.write("\n");
                        writeOneField(file, secondField);
                        file.write("\n");
                        contains = true;
                        break;
                    } else {
                        contains = true;
                        break;
                    }
                }
            }

            if (!contains) {
                writeOneField(file, curField);
                file.write("\n");
            }
        }

        for (int i = 0; i < secondClassFields.length; i++) {
            Field curField = secondClassFields[i];

            boolean contains = false;
            for (int j = 0; j < firstClassFields.length; j++) {
                Field firstField = firstClassFields[j];

                if (curField.getName().equals(firstField.getName())) {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                writeOneField(file, curField);
                file.write("\n");
            }
        }
    }

    private boolean isFieldEquals(Field firstField, Field secondField) {
        return firstField.getType().getSimpleName().equals(secondField.getType().getSimpleName()) &&
                Modifier.toString(firstField.getModifiers()).equals(Modifier.toString(secondField.getModifiers()));

    }

    private void writeDifferenceInMethods(FileWriter file, Class firstClass, Class secondClass) throws IOException {
        Method[] firstClassMethods = firstClass.getDeclaredMethods();
        Method[] secondClassMethods = secondClass.getDeclaredMethods();

        for (int i = 0; i < firstClassMethods.length; i++) {
            Method curMethod = firstClassMethods[i];

            boolean contains = false;
            for (int j = 0; j < secondClassMethods.length; j++) {
                Method secondMethod = secondClassMethods[j];

                if (!isMethodEquals(curMethod, secondMethod)) {
                    writeOneMethod(file, curMethod);
                    file.write("\n");
                    writeOneMethod(file, secondMethod);
                    file.write("\n");

                    contains = true;
                    break;
                } else {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                writeOneMethod(file, curMethod);
                file.write("\n");
            }
        }

        for (int i = 0; i < secondClassMethods.length; i++) {
            Method curMethod = secondClassMethods[i];

            boolean contains = false;
            for (int j = 0; j < firstClassMethods.length; j++) {
                Method firstMethod = firstClassMethods[j];

                if (curMethod.getName().equals(firstMethod.getName())) {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                writeOneMethod(file, curMethod);
            }
        }
    }

    private boolean isMethodEquals(Method firstMethod, Method secondMethod) {
        return firstMethod.getReturnType().getSimpleName().equals(secondMethod.getReturnType().getSimpleName()) &&
                firstMethod.getExceptionTypes().equals(secondMethod.getExceptionTypes()) &&
                Modifier.toString(firstMethod.getModifiers()).equals(Modifier.toString(secondMethod.getModifiers()));
    }

    private void writeDifferenceInInnerClasses(FileWriter file, Class firstClass, Class secondClass) throws IOException {
        if (firstClass.getDeclaredClasses().length != 0 && secondClass.getDeclaredClasses().length != 0) {
            Class[] firstInnerClasses = firstClass.getDeclaredClasses();
            Class[] secondInnerClasses = secondClass.getDeclaredClasses();

            for (int i = 0; i < firstInnerClasses.length; i++) {
                Class curClass = firstInnerClasses[i];

                for (int j = 0; j < secondInnerClasses.length; j++) {
                    Class curSecondClass = secondInnerClasses[j];
                    getDifference(file, curClass, curSecondClass);
                }
            }
        } else if (firstClass.getDeclaredClasses().length != 0 && secondClass.getDeclaredClasses().length == 0) {
            Class[] innerClasses = firstClass.getDeclaredClasses();
            for (int i = 0; i < innerClasses.length; i++) {
                getStructure(file, innerClasses[i]);
            }
        } else if (firstClass.getDeclaredClasses().length == 0 && secondClass.getDeclaredClasses().length != 0) {
            Class[] innerClasses = secondClass.getDeclaredClasses();
            for (int i = 0; i < innerClasses.length; i++) {
                getStructure(file, innerClasses[i]);
            }
        }
    }
}

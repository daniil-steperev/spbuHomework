package group144.stepyrev;

import java.io.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;


/** A class that builds a file with a text of inputted file. */
public class ClassBuilder {
    public static void main(String[] args) throws IOException {
        ClassBuilder classBuilder = new ClassBuilder();
        classBuilder.printStructure(String.class);
    }

    /**
     * A method that builds a file with a text of class.
     * @param clazz - a class which should be build in the file
     */
    public String printStructure(Class clazz) throws IOException {
        StringBuilder buildClass = new StringBuilder();
        FileWriter fileWriter = new FileWriter("src\\main\\java\\group144\\stepyrev\\buildClass\\" + clazz.getSimpleName() + ".java");

        buildClass.append("package group144.stepyrev.buildClass;\n\n");
        getStructure(buildClass, clazz);

        fileWriter.write(buildClass.toString());
        fileWriter.close();

        return buildClass.toString();
    }

    private void getStructure(StringBuilder buildClass, Class clazz) {
        writeClassDeclaration(buildClass, clazz);
        buildClass.append("{\n\t");

        writeFields(buildClass, clazz);
        writeConstructors(buildClass, clazz);
        writeMethods(buildClass, clazz);
        writeInnerClasses(buildClass, clazz);

        buildClass.append("\n}");
        buildClass.append("\n\n\t");
    }

    private void writeClassDeclaration(StringBuilder buildClass, Class clazz) {
        writeClassModifiers(buildClass, clazz);
        buildClass.append("class ");
        writeClassNameWithParameters(buildClass, clazz); // space here
        writeSuperclass(buildClass, clazz); // space here
        writeInterfaces(buildClass, clazz); // space here
    }

    private void writeClassModifiers(StringBuilder buildClass, Class clazz) {
        if (clazz.getModifiers() != 0) {
            buildClass.append(Modifier.toString(clazz.getModifiers()) + " ");
        }
    }

    private void writeClassNameWithParameters(StringBuilder buildClass, Class clazz) {
        buildClass.append(clazz.getSimpleName());
        buildClass.append(" ");
        if (clazz.getTypeParameters().length != 0) {
            TypeVariable[] parameters = clazz.getTypeParameters();
            buildClass.append("<");
            for (int i = 0; i < parameters.length; i++) {
                buildClass.append(parameters[i].getName());

                if (i != parameters.length - 1) {
                    buildClass.append(", ");
                }
            }

            buildClass.append("> ");
        }
    }

    private void writeSuperclass(StringBuilder buildClass, Class clazz) {
        if (clazz.getSuperclass() != null) {
            buildClass.append("extends ");
            writeClassNameWithParameters(buildClass, clazz.getSuperclass());
        }
    }

    private void writeInterfaces(StringBuilder buildClass, Class clazz) {
        if (clazz.getInterfaces().length != 0) {
            buildClass.append("implements ");
            Class[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                buildClass.append(interfaces[i].getName());
                if (i != interfaces.length - 1) {
                    buildClass.append(", ");
                }
            }
        }

        buildClass.append(" ");
    }

    private void writeFields(StringBuilder buildClass, Class clazz) {
        if (clazz.getDeclaredFields().length != 0) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                writeOneField(buildClass, fields[i]);
                buildClass.append("\n\t");
            }
        }
    }

    private void writeOneField(StringBuilder buildClass, Field field) {
        if (field.getModifiers() != 0) {
            buildClass.append(Modifier.toString(field.getModifiers()));
            buildClass.append(" ");
        }

        buildClass.append(field.getType().getSimpleName() + " " + field.getName() + " = ");
        writeType(buildClass, field.getType());
    }

    private void writeConstructors(StringBuilder buildClass, Class clazz) {
        buildClass.append("\n\t");
        if (clazz.getDeclaredConstructors().length != 0) {
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for (int i = 0; i < constructors.length; i++) {
                writeOneConstructor(buildClass, clazz, constructors[i]);
                buildClass.append("\n\n\t");
            }
        }
    }

    private void writeOneConstructor(StringBuilder buildClass, Class clazz, Constructor constructor) {
        if (constructor.getModifiers() != 0) {
            buildClass.append(Modifier.toString(constructor.getModifiers()) + " ");
        }

        buildClass.append(clazz.getSimpleName());
        buildClass.append("(");
        Parameter[] parameters = constructor.getParameters();
        writeParameters(buildClass, parameters);
        buildClass.append(") ");
        buildClass.append("{ }");
    }

    private void writeMethods(StringBuilder buildClass, Class clazz) {
        if (clazz.getDeclaredMethods().length != 0) {
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                writeOneMethod(buildClass, methods[i]);

                if (i != methods.length - 1) {
                    buildClass.append("\n\n\t");
                }
            }
        }
    }

    private void writeOneMethod(StringBuilder buildClass, Method method) {
        if (method.getModifiers() != 0) {
            buildClass.append(Modifier.toString(method.getModifiers()) + " ");
        }

        buildClass.append(method.getReturnType().getSimpleName() + " ");
        buildClass.append(method.getName() + " (");
        Parameter[] parameters = method.getParameters();
        writeParameters(buildClass, parameters);
        buildClass.append(") ");
        writeExceptions(buildClass, method);

        buildClass.append("{");
        if (!method.getReturnType().getSimpleName().equals("void")) {
            buildClass.append("\n\t\t");
            buildClass.append("return ");
            writeType(buildClass, method.getReturnType());
            buildClass.append("\n\t");
        } else {
            buildClass.append(" ");
        }

        buildClass.append("}");
    }

    private void writeType(StringBuilder buildClass, Type type) {
        switch (type.getTypeName()) {
            case "Integer":
                buildClass.append("Integer.valueOf(0);");
                break;
            case "Boolean":
                buildClass.append("true;");
                break;
            case "String":
                buildClass.append("String.valueOf(\"\");");
                break;
            case "Double":
                buildClass.append("Double.valueOf(0.0);");
                break;
            case "Character":
                buildClass.append("Character.valueOf('x');");
                break;
            case "Byte":
                buildClass.append("Byte.valueOf(0);");
                break;
            case "Long":
                buildClass.append("Long.valueOf(0);");
                break;
            case "Short":
                buildClass.append("Short.valueOf(0);");
                break;
            case "Float":
                buildClass.append("Float.valueOf(0);");
                break;
            case "int":
                buildClass.append("0;");
                break;
            case "boolean":
                buildClass.append("true;");
                break;
            case "double":
                buildClass.append("0.0;");
                break;
            case "char":
                buildClass.append("'x';");
                break;
            case "byte":
                buildClass.append("0;");
                break;
            case "long":
                buildClass.append("0;");
                break;
            case "short":
                buildClass.append("0;");
                break;
            case "float":
                buildClass.append("0;");
                break;
            default:
                buildClass.append("null;");
                break;
        }
    }

    private void writeExceptions(StringBuilder buildClass, Method method) {
        if (method.getExceptionTypes().length != 0) {
            buildClass.append("throws ");
            Class[] exceptions = method.getExceptionTypes();
            for (int i = 0; i < exceptions.length; i++) {
                buildClass.append(exceptions[i].getName());
                if (i != exceptions.length - 1) {
                    buildClass.append(", ");
                }
            }
        }
        buildClass.append(" ");
    }

    private void writeParameters(StringBuilder buildClass, Parameter[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            buildClass.append(parameters[i].getParameterizedType().getTypeName() + " " + parameters[i].getName());
            if (i != parameters.length - 1) {
                buildClass.append(", ");
            }
        }
    }

    private void writeInnerClasses(StringBuilder buildClass, Class clazz) {
        if (clazz.getDeclaredClasses().length != 0) {
            buildClass.append("\n\t");
            Class<?>[] classes = clazz.getDeclaredClasses();
            for (int i = 0; i < classes.length; i++) {
                getStructure(buildClass, classes[i]);

                if (i != classes.length -1) {
                    buildClass.append("\n\n\t");
                }
            }
        }
    }

    /**
     * A method that print in the console difference between two classes.
     * @param firstClazz - a first class
     * @param secondClazz - a second class
     */
    public boolean diffClasses(Class firstClazz, Class secondClazz) {
        StringBuilder diffClasses = new StringBuilder();
        getDifference(diffClasses, firstClazz, secondClazz);

        if (diffClasses.length() == 0) {
            System.out.println("Classes are equal!");
            return true;
        }

        System.out.println(diffClasses);
        return false;
    }

    private void getDifference(StringBuilder diffClasses, Class firstClazz, Class secondClazz) {
        writeDifferenceInFields(diffClasses, firstClazz, secondClazz);
        writeDifferenceInMethods(diffClasses, firstClazz, secondClazz);
        writeDifferenceInInnerClasses(diffClasses, firstClazz, secondClazz);
    }

    private void writeDifferenceInFields(StringBuilder diffClasses, Class firstClazz, Class secondClazz) {
        Field[] firstClassFields = firstClazz.getDeclaredFields();
        Field[] secondClassFields = secondClazz.getDeclaredFields();

        for (int i = 0; i < firstClassFields.length; i++) {
            Field curField = firstClassFields[i];

            boolean contains = false;
            for (int j = 0; j < secondClassFields.length; j++) {
                Field secondField = secondClassFields[j];

                if (curField.getName().equals(secondField.getName())) {
                    if (!isFieldEquals(curField, secondField)) {
                        writeOneField(diffClasses, curField);
                        diffClasses.append("\n");
                        writeOneField(diffClasses, secondField);
                        diffClasses.append("\n");
                        contains = true;
                        break;
                    } else {
                        contains = true;
                        break;
                    }
                }
            }

            if (!contains) {
                writeOneField(diffClasses, curField);
                diffClasses.append("\n");
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
                writeOneField(diffClasses, curField);
                diffClasses.append("\n");
            }
        }
    }

    private boolean isFieldEquals(Field firstField, Field secondField) {
        return firstField.getType().getSimpleName().equals(secondField.getType().getSimpleName()) &&
                Modifier.toString(firstField.getModifiers()).equals(Modifier.toString(secondField.getModifiers()));

    }

    private void writeDifferenceInMethods(StringBuilder diffClasses, Class firstClass, Class secondClass) {
        Method[] firstClassMethods = firstClass.getDeclaredMethods();
        Method[] secondClassMethods = secondClass.getDeclaredMethods();
        StringBuilder addedMethods = new StringBuilder();

        for (int i = 0; i < firstClassMethods.length; i++) {
            Method curMethod = firstClassMethods[i];

            boolean contains = false;
            for (int j = 0; j < secondClassMethods.length; j++) {
                Method secondMethod = secondClassMethods[j];

                if (curMethod.getName().equals(secondMethod.getName())) {
                    if (!isMethodEquals(curMethod, secondMethod)) {
                        writeOneMethod(addedMethods, curMethod);
                        addedMethods.append("\n");
                        writeOneMethod(addedMethods, secondMethod);
                        addedMethods.append("\n");
                    } else {
                        contains = true;
                        break;
                    }
                }
            }

            if (!contains && addedMethods.length() != 0) {
                diffClasses.append(addedMethods);
                diffClasses.append("\n");
            } else if (!contains) {
                writeOneMethod(diffClasses, curMethod);
                diffClasses.append("\n");
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
                writeOneMethod(diffClasses, curMethod);
            }
        }
    }

    private boolean isMethodEquals(Method firstMethod, Method secondMethod) {
        return firstMethod.getReturnType().getSimpleName().equals(secondMethod.getReturnType().getSimpleName()) &&
                isExceptionsEqual(firstMethod, secondMethod) &&
                Modifier.toString(firstMethod.getModifiers()).equals(Modifier.toString(secondMethod.getModifiers())) &&
                isParametersEqualInMethods(firstMethod, secondMethod);
    }

    private boolean isExceptionsEqual(Method first, Method second) {
        List<Class<?>> firstExceptions = Arrays.asList(first.getExceptionTypes());
        List<Class<?>> secondExceptions = Arrays.asList(second.getExceptionTypes());

        if (firstExceptions.size() != secondExceptions.size()) {
            return false;
        }

        for (Class<?> element : firstExceptions) {
            if (!secondExceptions.contains(element)) {
                return false;
            }
        }
        return true;
    }

    private boolean isParametersEqualInMethods(Method first, Method second) {
        Class<?>[] firstParemeters = first.getParameterTypes();
        Class<?>[] secondParameters = second.getParameterTypes();

        if (firstParemeters.length != secondParameters.length) {
            return false;
        }

        for (int i = 0; i < firstParemeters.length; i++) {
            if (!firstParemeters[i].equals(secondParameters[i])) {
                return false;
            }
        }
        return true;
    }

    private void writeDifferenceInInnerClasses(StringBuilder diffClasses, Class firstClass, Class secondClass) {
        if (firstClass.getDeclaredClasses().length != 0 && secondClass.getDeclaredClasses().length != 0) {
            Class[] firstInnerClasses = firstClass.getDeclaredClasses();
            Class[] secondInnerClasses = secondClass.getDeclaredClasses();

            for (int i = 0; i < firstInnerClasses.length; i++) {
                Class curClass = firstInnerClasses[i];

                for (int j = 0; j < secondInnerClasses.length; j++) {
                    Class curSecondClass = secondInnerClasses[j];
                    getDifference(diffClasses, curClass, curSecondClass);
                }
            }
        } else if (firstClass.getDeclaredClasses().length != 0 && secondClass.getDeclaredClasses().length == 0) {
            Class[] innerClasses = firstClass.getDeclaredClasses();
            for (int i = 0; i < innerClasses.length; i++) {
                getStructure(diffClasses, innerClasses[i]);
            }
        } else if (firstClass.getDeclaredClasses().length == 0 && secondClass.getDeclaredClasses().length != 0) {
            Class[] innerClasses = secondClass.getDeclaredClasses();
            for (int i = 0; i < innerClasses.length; i++) {
                getStructure(diffClasses, innerClasses[i]);
            }
        }
    }
}

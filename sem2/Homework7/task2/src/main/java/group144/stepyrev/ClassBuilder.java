package group144.stepyrev;

import java.io.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/** A class that builds a file with a text of inputted file. */
public class ClassBuilder {
    public static void main(String[] args) throws IOException {
        ClassBuilder classBuilder = new ClassBuilder();
        classBuilder.printStructure(ClassBuilder.class);
    }

    /**
     * A method that builds a file with a text of class.
     * @param clazz - a class which should be built in the file
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

    /**
     * A method that writes a class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that should be built
     */
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

    /**
     * A method that writes a declaration of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that declaration should be built
     */
    private void writeClassDeclaration(StringBuilder buildClass, Class clazz) {
        writeClassModifiers(buildClass, clazz);
        buildClass.append("class ");
        writeClassNameWithParameters(buildClass, clazz);
        buildClass.append(" "); // space here
        writeSuperclass(buildClass, clazz); // space here
        writeInterfaces(buildClass, clazz); // space here
    }

    /**
     * A method that writes a modifiers of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that modifiers should be built
     */
    private void writeClassModifiers(StringBuilder buildClass, Class clazz) {
        if (clazz.getModifiers() != 0) {
            buildClass.append(Modifier.toString(clazz.getModifiers()) + " ");
        }
    }

    /**
     * A method that writes a class name and it's parameters.
     *
     * If class does not have any parameters, nothing adds.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that name and parameters should be built
     */
    private void writeClassNameWithParameters(StringBuilder buildClass, Class clazz) {
        buildClass.append(clazz.getSimpleName());
        if (clazz.getTypeParameters().length != 0) {
            TypeVariable[] parameters = clazz.getTypeParameters();
            buildClass.append("<");
            for (int i = 0; i < parameters.length; i++) {
                buildClass.append(parameters[i].getName());

                if (i != parameters.length - 1) {
                    buildClass.append(", ");
                }
            }

            buildClass.append(">");
        }
    }

    /**
     * A method that writes the superclass of the class.
     *
     * Adds word 'extends'.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that superclass should be built
     */
    private void writeSuperclass(StringBuilder buildClass, Class clazz) {
        if (clazz.getSuperclass() != null) {
            buildClass.append("extends ");
            writeClassNameWithParameters(buildClass, clazz.getSuperclass());
            buildClass.append(" ");
        }
    }

    /**
     * A method that writes interfaces of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that interfaces should be built
     */
    private void writeInterfaces(StringBuilder buildClass, Class clazz) {
        if (clazz.getInterfaces().length != 0) {
            buildClass.append("implements ");
            Class[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                writeOneInterface(buildClass, interfaces[i]);

                if (i != interfaces.length - 1) {
                    buildClass.append(", ");
                }
            }
        }

        buildClass.append(" ");
    }

    /**
     * A method that writes one interface.
     * @param buildClass - a string builder with current class
     * @param interfaceClass - an interface that should be written
     */
    private void writeOneInterface(StringBuilder buildClass, Class interfaceClass) {
        buildClass.append(interfaceClass.getName());
        if (interfaceClass.getTypeParameters().length != 0) {
            buildClass.append("<");
            TypeVariable[] parameters = interfaceClass.getTypeParameters();
            for (int i = 0; i < parameters.length; i++) {
                buildClass.append(parameters[i].getName());

                if (i != parameters.length - 1) {
                    buildClass.append(", ");
                }
            }
            buildClass.append(">");
        }
    }

    /**
     * A method that writes fields of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that fields should be built
     */
    private void writeFields(StringBuilder buildClass, Class clazz) {
        if (clazz.getDeclaredFields().length != 0) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                writeOneField(buildClass, fields[i]);
                buildClass.append("\n\t");
            }
        }
    }

    /**
     * A method that writes one field.
     *
     * This method also initializing the field (there is an error when field modifier is 'final').
     * @param buildClass - a string builder with current class
     * @param field - a field that should be built
     */
    private void writeOneField(StringBuilder buildClass, Field field) {
        if (field.getModifiers() != 0) {
            buildClass.append(Modifier.toString(field.getModifiers()));
            buildClass.append(" ");
        }

        buildClass.append(field.getType().getSimpleName());
        buildClass.append(" ");
        buildClass.append(field.getName());
        buildClass.append(" = ");
        writeType(buildClass, field.getType());
    }

    /**
     * A method that prints constructors of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that constructors should be built
     */
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

    /**
     * A method that writes one constructor of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that constructors should be built
     * @param constructor - a constructor that should be built
     */
    private void writeOneConstructor(StringBuilder buildClass, Class clazz, Constructor constructor) {
        if (constructor.getModifiers() != 0) {
            buildClass.append(Modifier.toString(constructor.getModifiers()));
            buildClass.append(" ");
        }

        buildClass.append(clazz.getSimpleName());
        buildClass.append("(");
        Parameter[] parameters = constructor.getParameters();
        writeParameters(buildClass, parameters);
        buildClass.append(") ");
        buildClass.append("{ }");
    }

    /**
     * A method that writes methods of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that methods should be built
     */
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

    /**
     * A method that writes one method of the class.
     *
     * This method also writes 'return ' + currentTypeNullElement.
     * In case of 'void' the method just writes '{ }'.
     * @param buildClass - a string builder with current class
     * @param method - a method that should be built
     */
    private void writeOneMethod(StringBuilder buildClass, Method method) {
        if (method.getModifiers() != 0) {
            buildClass.append(Modifier.toString(method.getModifiers()));
            buildClass.append(" ");
        }

        buildClass.append(method.getReturnType().getSimpleName());
        buildClass.append(" ");
        buildClass.append(method.getName());
        buildClass.append(" (");
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

    /**
     * A method that writes a common element of the type.
     *
     * This method is useful in case of method and fields building.
     * @param buildClass - a string builder with current class
     * @param type - a type that element should be built
     */
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

    /**
     * A method that writes the exceptions of the class.
     * @param buildClass - a string builder with current class
     * @param method - a method that exceptions should be built
     */
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

    /**
     * A method that writes parameters.
     *
     * This method is useful in case of building constructors, methods, class.
     * @param buildClass - a string builder with current class
     * @param parameters - an array with parameters that should be built
     */
    private void writeParameters(StringBuilder buildClass, Parameter[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            buildClass.append(parameters[i].getParameterizedType().getTypeName());
            buildClass.append(" ");
            buildClass.append(parameters[i].getName());
            if (i != parameters.length - 1) {
                buildClass.append(", ");
            }
        }
    }

    /**
     * A method that writes inner classes of the class.
     * @param buildClass - a string builder with current class
     * @param clazz - a class that inner classes should be built
     */
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

        if (diffClasses.length() == 0 || isSpecialCase(firstClazz, diffClasses)) {
            System.out.println("Classes are equal!");
            return true;
        }

        System.out.println(diffClasses);
        return false;
    }

    /**
     * A method that checks if the build class has a 'special case'.
     *
     * There is a problem when we builds inner classes to the buildClass file adds a special string:
     * 'final' + *className* + ' this$0$ = null;'.
     * However, buildClass equals inputted class (that's why this method is useful).
     * @param clazz - a class that was built
     * @param buildClass - a string builder with current class
     * @return - true if this case is special, false otherwise
     */
    private boolean isSpecialCase(Class clazz, StringBuilder buildClass) { // this adds in case of inner class
        StringBuilder specialCase = new StringBuilder("final ");
        specialCase.append(clazz.getSimpleName() + " ");
        specialCase.append("this$0$ = null;");
        specialCase.append((char) 10);
        return specialCase.toString().equals(buildClass.toString());
    }

    /**
     * A method that gets difference between two classes.
     * @param diffClasses - a string builder with difference of two comparable classes
     * @param firstClazz - a first comparable class
     * @param secondClazz - a second comparable class
     */
    private void getDifference(StringBuilder diffClasses, Class firstClazz, Class secondClazz) {
        writeDifferenceInFields(diffClasses, firstClazz, secondClazz);
        writeDifferenceInMethods(diffClasses, firstClazz, secondClazz);
        writeDifferenceInInnerClasses(diffClasses, firstClazz, secondClazz);
    }

    /**
     * A method that writes difference between two classes in fields.
     * @param diffClasses - a string builder with difference of two comparable classes
     * @param firstClazz - a first comparable class
     * @param secondClazz - a second comparable class
     */
    private void writeDifferenceInFields(StringBuilder diffClasses, Class firstClazz, Class secondClazz) {
        Field[] firstClassFields = firstClazz.getDeclaredFields();
        Field[] secondClassFields = secondClazz.getDeclaredFields();

        for (Field curField : firstClassFields) {
            boolean contains = false;
            for (Field secondField : secondClassFields) {
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

        for (Field curField: secondClassFields) {
            boolean contains = false;
            for (Field firstField : firstClassFields) {
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

    /**
     * A method that checks if the fields are equal.
     * @param firstField - a first comparable field
     * @param secondField - a second comparable field
     * @return - true if fields are equal, false otherwise
     */
    private boolean isFieldEquals(Field firstField, Field secondField) {
        return firstField.getType().getSimpleName().equals(secondField.getType().getSimpleName()) &&
                Modifier.toString(firstField.getModifiers()).equals(Modifier.toString(secondField.getModifiers()));

    }

    /**
     * A method that writes difference of two classes in methods.
     *
     * This method compares all methods from first class to all from the second one. If there is a method with such name
     * but different from the original, it writes both of them to the diffClasses. If there is not, writes only first.
     * @param diffClasses - a string builder with difference of two comparable classes
     * @param firstClass - a first comparable class
     * @param secondClass - a second comparable class
     */
    private void writeDifferenceInMethods(StringBuilder diffClasses, Class firstClass, Class secondClass) {
        Method[] firstClassMethods = firstClass.getDeclaredMethods();
        Method[] secondClassMethods = secondClass.getDeclaredMethods();
        StringBuilder addedMethods = new StringBuilder();

        for (Method curMethod : firstClassMethods) {
            boolean contains = false;
            for (Method secondMethod : secondClassMethods) {
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

        for (Method curMethod : secondClassMethods) {
            boolean contains = false;
            for (Method firstMethod : firstClassMethods) {
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

    /**
     * A method that checks if two methods are equal.
     * @param firstMethod - a first comparable method.
     * @param secondMethod - a second comparable method.
     * @return - true if equal, false otherwise
     */
    private boolean isMethodEquals(Method firstMethod, Method secondMethod) {
        return firstMethod.getReturnType().getSimpleName().equals(secondMethod.getReturnType().getSimpleName()) &&
                isExceptionsEqual(firstMethod, secondMethod) &&
                Modifier.toString(firstMethod.getModifiers()).equals(Modifier.toString(secondMethod.getModifiers())) &&
                isParametersEqualInMethods(firstMethod, secondMethod);
    }

    /**
     * A method that checks if the exceptions of two methods are equal.
     *
     * The order of the exceptions is not important that's why we should check 'contains'.
     * @param first - a first comparable method
     * @param second - a second comparable method
     * @return - true if equal, false otherwise
     */
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

    /**
     * A method that checks if the parameters of two methods are equal.
     *
     * The order of the exceptions IS important that's why we should check element by element.
     * @param first - a first comparable method
     * @param second - a second comparable method
     * @return - true if equal, false otherwise
     */
    private boolean isParametersEqualInMethods(Method first, Method second) {
        Class<?>[] firstParameters = first.getParameterTypes();
        Class<?>[] secondParameters = second.getParameterTypes();

        if (firstParameters.length != secondParameters.length) {
            return false;
        }

        for (int i = 0; i < firstParameters.length; i++) {
            if (!firstParameters[i].equals(secondParameters[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method that writes a difference of the inner classes between two methods.
     *
     * If the there are not any inner class from one class in another, the method builds the inner class.
     * @param diffClasses - a string builder with difference of two comparable classes
     * @param firstClass - a first comparable class
     * @param secondClass - a second comparable class
     */
    private void writeDifferenceInInnerClasses(StringBuilder diffClasses, Class firstClass, Class secondClass) {
        if (firstClass.getDeclaredClasses().length != 0 && secondClass.getDeclaredClasses().length != 0) {
            Class[] firstInnerClasses = firstClass.getDeclaredClasses();
            Class[] secondInnerClasses = secondClass.getDeclaredClasses();

            for (Class curClass : firstInnerClasses) {
                for ( Class curSecondClass : secondInnerClasses) {
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

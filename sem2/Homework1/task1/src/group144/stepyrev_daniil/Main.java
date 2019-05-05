package group144.stepyrev_daniil;

public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push(5);
        stack.push(2);
        stack.push(1312);
        stack.push(21231);

        System.out.print("Stack: ");
        stack.print();
        System.out.println("");

        System.out.println("Length of stack = " + stack.getLength());

        System.out.println("Popped element = " + stack.pop());

        System.out.print("Stack: ");
        stack.print();
        System.out.println("");

        System.out.println("Length of stack = " + stack.getLength());
        System.out.println("Popped element = " + stack.pop());

        System.out.println("Pushed 1 to the stack.");
        stack.push(1);

        System.out.print("Stack: ");
        stack.print();
    }
}

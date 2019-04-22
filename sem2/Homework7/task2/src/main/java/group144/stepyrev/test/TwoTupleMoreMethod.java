package group144.stepyrev.test;

public class TwoTupleMoreMethod<A,B> {
    public final A first;
    public final B second;

    public TwoTupleMoreMethod(A a, B b) { first = a; second = b; }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public void oneMoreMethod() {}
}

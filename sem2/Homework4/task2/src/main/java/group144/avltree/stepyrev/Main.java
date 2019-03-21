package group144.avltree.stepyrev;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.add(5);
        tree.add(4);
        tree.add(3);
        System.out.println(tree);
    }
}

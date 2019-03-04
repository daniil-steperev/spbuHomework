package group144.steperev_daniil;

public class Main {
    public static void main(String[] args) {
        List list = new List();

        list.add(5);
        list.add(13);
        list.add(123);
        list.add(62);
        list.add(14562);
        list.add(984);

        System.out.println("Lenth of list = " + list.getLength());
        System.out.print("List: ");
        list.printList();
        System.out.println("");
        System.out.println("Remove third element");
        list.remove(2);
        list.printList();
        System.out.println("");
        System.out.println("Find 62 in list, if it present return index (>= 0) = " + list.find(62));
        System.out.println("Added 5 in list.");
        list.add(5);
        System.out.print("List: ");
        list.printList();
        System.out.println("");
        System.out.println("Added 4 to the second position (numerating from 0).");
        list.add(4, 2);
        System.out.print("List: ");
        list.printList();
    }
}

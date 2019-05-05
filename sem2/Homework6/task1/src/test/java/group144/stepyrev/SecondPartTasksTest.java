package group144.stepyrev;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;


public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        List<String> strings = Arrays.asList("abc", "def", "I love Java", "klm", "abracadabra", "Idea is the best", "" +
                "rrasa", "vsadvadasda");

        assertEquals(Arrays.asList("abc", "abracadabra"), SecondPartTasks.findQuotes(strings, "ab"));
        assertEquals(Arrays.asList("I love Java", "Idea is the best"), SecondPartTasks.findQuotes(strings, "I"));
        assertEquals(new LinkedList(), SecondPartTasks.findQuotes(strings, "xxxxx"));
    }

    @Test
    public void testPiDividedBy4() {
        double PI = (double) 22 / (double) 7;
        assertEquals(PI / 4, SecondPartTasks.piDividedBy4(), 0.001);
    }

    @Test
    public void testFindPrinter() {
        List<String> dostaevskyBooks = Arrays.asList("Prestuplenie i nakazanie", "Idiot", "Bratya Karamazovi");
        List<String> turgenevBooks = Arrays.asList("Otci i deti", "Asya", "Zapiski ohotnika");
        List<String> bulgakovBooks = Arrays.asList("Master i Margarita", "Sobachye serdce", "Dni Turbinih");

        Map<String, List<String>> compositions = new HashMap<>();
        compositions.put("dostaevsky", dostaevskyBooks);
        compositions.put("turgenev", turgenevBooks);
        compositions.put("bulgakov", bulgakovBooks);

        assertEquals("dostaevsky", SecondPartTasks.findPrinter(compositions));
    }

    @Test
    public void testCalculateGlobalOrder() {
        List<Map<String, Integer>> orders = new ArrayList<>();

        Map<String, Integer> magnitOrder = new HashMap<>();
        magnitOrder.put("Apple", 100);
        magnitOrder.put("Bread", 1500);
        magnitOrder.put("Banana", 1000000);

        Map<String, Integer> sparOrder = new HashMap<>();
        sparOrder.put("Apple", 140);
        sparOrder.put("Banana", 20);
        sparOrder.put("Meat", 1500000);

        Map<String, Integer> krasnoyeBeloyeOrder = new HashMap<>();
        krasnoyeBeloyeOrder.put("Vodka", 150000);
        krasnoyeBeloyeOrder.put("Wine", 1000);
        krasnoyeBeloyeOrder.put("Schoolboy", 228);

        orders.add(magnitOrder);
        orders.add(sparOrder);
        orders.add(krasnoyeBeloyeOrder);

        Map<String, Integer> calculatedOrder = new HashMap<>();
        calculatedOrder.put("Apple", 240);
        calculatedOrder.put("Bread", 1500);
        calculatedOrder.put("Banana", 1000020);
        calculatedOrder.put("Meat", 1500000);
        calculatedOrder.put("Schoolboy", 228);
        calculatedOrder.put("Vodka", 150000);
        calculatedOrder.put("Wine", 1000);

        assertEquals(calculatedOrder, SecondPartTasks.calculateGlobalOrder(orders));
    }
}
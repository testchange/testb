package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {


        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            double entry = (double) lld1.removeFirst();
//            System.out.println(entry);
            assertEquals("Should have the same value", i, entry, 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            double entry = (double) lld1.removeLast();
//            System.out.println(entry);
            assertEquals("Should have the same value", i, entry, 0.0);
        }


    }


    @Test
    public void resizeDown() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i <= 99; i++) {
            double entry = (double) lld1.removeFirst();
            assertEquals("Should have the same value", i, entry, 0.0);
        }

    }


    @Test
    public void EqualsTest(){
        ArrayDeque<Integer> ald1 = new ArrayDeque();
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
            ald1.addLast(i);
        }

        assertEquals(lld1.equals(ald1), true);
        assertEquals(ald1.equals(lld1), true);
    }

    @Test
    /* Test iterator */
    public void IteratorTest(){
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
        }

        int expected = 0;
        for (int i : lld1){
            assertEquals("Should have the same value",i, expected,0.0);
            expected += 1;
        }

        Iterator<Integer> test = lld1.iterator();
        for(int i = 0; i < 100; i++){
//            System.out.println(i);
            assertEquals("Should have the same value", (int) test.next(), i);
        }

        Iterator<Integer> test1 = lld1.iterator();
        int k = 0;
        while(test1.hasNext()){
            assertEquals("Should have the same value", (int) test1.next(), k);
            k += 1;
        }

    }

    @Test
    public void random(){

        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            int randVal = StdRandom.uniform(0, 100);
            if (operationNumber == 0) {
                // addLast
//                System.out.println("call add last");
                L.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
//                System.out.println("call add first");
                L.addFirst(randVal);
//                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
//                System.out.println("call remove first");
                L.removeFirst();
            }
        }
    }

    @Test
    public void testLast(){
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        int N = 7;
        for (int i = 0; i < N; i += 1) {
            L.addLast(3);
        }
        assertEquals("Should be the same: ", (int) L.get(0),3 );

    }


    @Test
    public void testLastFirst(){
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        int N = 7;
        for (int i = 0; i < N; i += 1) {
            L.addLast(3);
        }

        assertEquals("Should be the same: ", (int) L.removeFirst(),3 );
        assertEquals("Should be the same: ", (int) L.removeLast(),3 );

    }

    @Test
    public void randomAddRemoveEmpty(){
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            int randVal = StdRandom.uniform(0, 100);
            if (operationNumber == 0) {
                // addLast
//                System.out.println("call add last");
                L.get(0);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
//                System.out.println("call add first");
                L.addLast(randVal);
//                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
//                System.out.println("call remove first");
                L.removeLast();
            }
        }
    }


    @Test
    public void getTest(){
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(0);
        L.get(0);
        L.addLast(2);
        L.get(0);
        L.get(1);
        L.addFirst(5);
        assertEquals("Should be",(int) L.get(2), 2);

    }
}

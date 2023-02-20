package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void test(){
        AListNoResizing<Integer> test = new AListNoResizing<>();
        BuggyAList<Integer> test2 = new BuggyAList<>();

        test.addLast(4);
        test2.addLast(4);

        test.addLast(5);
        test2.addLast(5);

        test.addLast(6);
        test2.addLast(6);

        for(int i = 0; i < 3 ; i++){
            assertEquals(test.removeLast(), test2.removeLast());
        }

    }

    @Test
    public void random(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
//                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if (L.size() > 0) {
                    assertEquals(L.getLast(),B.getLast());
                }
            } else if (operationNumber == 3) {
                if (L.size() > 0){
                    assertEquals(L.removeLast(), B.removeLast());
                }
            }
        }
    }
}

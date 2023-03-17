package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    
    @Test
    public void compareTest() {
        MaxArrayDeque<Integer> test = new MaxArrayDeque<>(Integer::compareTo);
        for(int i = 0; i < 99; i ++){
            test.addLast(i);
        }

        assertEquals("Should be this: ",(int) test.max(), 98);

    }

//    @Test
//    public void comparatorTest() {
//        MaxArrayDeque<Dog> test = new MaxArrayDeque<>(Dog.getSizeComparator());
//        for(int i = 0; i < 99; i ++){
//            test.addLast(new Dog(i, Integer.toString(i) + "test"));
//        }
//
//        assertEquals("Should be this: ",98, ((Dog) test.max()).size);
//
//        MaxArrayDeque<Dog> test1 = new MaxArrayDeque<>(Dog.getNameComparator());
//        test1.addLast(new Dog(1, "a"));
//        test1.addLast(new Dog(2,"b"));
//        test1.addLast(new Dog(3, "c"));
//        test1.addLast(new Dog(3, "z"));
//        assertEquals("Should be this: ","z", ((Dog) test1.max()).name);
//    }
}

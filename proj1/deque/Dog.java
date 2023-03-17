package deque;

import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private int size;
    private String name;

    public Dog(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }

    private static class SizeComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.size - b.size;
        }
    }

    public static Comparator<Dog> getSizeComparator() {
        return new SizeComparator();
    }


}

package deque;

import java.util.Comparator;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private int size;
    public T[] items;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

//    //why need static
//    private static class ReverseCompare implements Comparator<T> {
//
//        @Override
//        public <T extends Comparable<T>> int compare(T o1, T o2) {
//            return o1.compareTo(o2);
//        }
//    }

    @Override
    public boolean equals(Object obj) {
        if((obj instanceof ArrayDeque)&& ((Deque) obj).size() == size()){
            for(int i = 0; i < size; i++){
                if (((ArrayDeque<?>) obj).get(i) != get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new AIterator();
    }

    private class AIterator implements Iterator<T>{

        private int index;
        private int count;

        public AIterator(){
            index = (nextFirst + 1) % items.length;
        }

        @Override
        public T next() {
            count += 1;
            T result = get(index);
            index = (index + 1) % items.length;
            return result;
        }

        @Override
        public boolean hasNext() {
            return count < size();
        }
    }

    private void resize(){
        if (size + 1 > items.length){
            T[] tmp = (T[]) new Object[size + 5];
            int curr = (nextFirst + 1) % this.items.length ;

            for(int i = 0; i < size; i++){
                tmp[i] = items[curr];
                curr = (curr + 1) % this.items.length ;
            }
            items = tmp;
            nextFirst = items.length - 1;
            nextLast  = size;
        }
    }

    private void resize_down(){
        T[] tmp = (T[]) new Object[items.length / 2];
        int curr = (nextFirst + 1) % this.items.length ;

        for(int i = 0; i < size; i++){
            tmp[i] = items[curr];
            curr = (curr + 1) % this.items.length ;
        }
        items = tmp;
        nextFirst = items.length - 1;
        nextLast  = size;
    }

    @Override
    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        size += 1;
        nextFirst = (nextFirst - 1) % this.items.length;
    }

    @Override
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        size += 1;
        nextLast = (nextLast + 1) % this.items.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int curr = nextFirst + 1;
        for(int i = 0; i < size; i++){
            System.out.println(items[curr]);
            curr = (curr + 1) % this.items.length ;
        }
    }

    @Override
    public T removeLast() {
        //resize when usasge is
        if (size == 0){
            return null;
        }
        double usage = (double) (size-1)/(items.length);
        if (items.length >= 16 && usage < 0.25){
            resize_down();
        }
        int index = (nextLast - 1) % items.length;
        T to_be_removed = items[index];
        items[index] = null;
        nextLast = index;
        size -= 1;
        return to_be_removed;
    }

    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        double usage = (double) (size-1)/(items.length);
        if (items.length >= 16 && usage < 0.25){
            resize_down();
        }
        int index = (nextFirst + 1) % items.length;
        T to_be_removed = items[index];
        items[index] = null;
        nextFirst = index;
        size -= 1;
        return to_be_removed;
    }

    @Override
    public T get(int index) {
        return items[index];
    }
}

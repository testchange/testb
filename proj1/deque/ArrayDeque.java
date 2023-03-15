package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if((obj instanceof Deque)&& ((Deque) obj).size() == size()){
            for(int i = 0; i < size; i++){
                if (get(i).equals(((Deque) obj).get(i)) == false){
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
            count = 0;
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
            T[] tmp = (T[]) new Object[size * 2];
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
        if (size == 0){
            nextFirst = 0;
            nextLast = 1;
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = Math.floorMod((nextFirst - 1), this.items.length);
    }

    @Override
    public void addLast(T item) {
        resize();
        if (size == 0){
            nextFirst = Math.floorMod(-1, this.items.length);;
            nextLast = 0;
        }
        items[nextLast] = item;
        size += 1;
        nextLast = Math.floorMod((nextLast + 1), this.items.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int curr = Math.floorMod((nextFirst + 1), this.items.length);
        for(int i = 0; i < size; i++){
            System.out.println(items[curr]);
            curr = (curr + 1) % this.items.length ;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        double usage = (double) (size-1)/(items.length);
        if (items.length >= 16 && usage < 0.25){
            resize_down();
        }
        int index = Math.floorMod((nextLast - 1), this.items.length);
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
        int starting = Math.floorMod((nextFirst + 1), this.items.length);
        int i =  Math.floorMod((index + starting), this.items.length);
        return items[i];
    }
}

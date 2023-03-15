package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private Node<T> first;
    private int size;

    private static class Node<T> {
        private T item;
        private Node next;
        private Node prev;

        //check constructor
        public Node(T elem){
            item = elem;
            //add sentinel node
            next = null;
            prev = null;
        }

//        public void addLast(T first){
//        }
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Deque) && ((Deque) obj).size() == size()){
            for(int i = 0; i < size(); i++){
                //check if the get items are also equal
                if (get(i).equals(((Deque) obj).get(i)) == false){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator(){
        return new LLIterator(first);
    }

    private class LLIterator implements Iterator<T> {

        private Node<T> ll;
        private int idx;

        public LLIterator(Node<T> entry){
            ll = entry;
            idx = 0;
        }

        @Override
        public T next() {
            idx += 1;
            ll = ll.next;
            return get(idx-1);
        }

        @Override
        public boolean hasNext() {
            return idx < size();
        }
    }

    //constructor
    public LinkedListDeque() {
        first = new Node(null);
        first.next = first;
        first.prev = first;
        size = 0;
    }

    public void addFirst(T elem){
        Node<T> entry = new Node(elem);
        Node<T> tmp = this.first.next;
        // convention start with the existing node prev
        tmp.prev = entry;
        // adjust the entry connection
        entry.prev = this.first; //connect with the sentinel
        entry.next = tmp;
        // end with the sentinel node connection
        this.first.next = entry;
        size += 1;
    }

    public void addLast(T elem){
        Node<T> entry = new Node(elem);
        Node<T> _last = this.first.prev;
        _last.next = entry;
        entry.next = this.first;
        entry.prev = _last;
        this.first.prev = entry;
        size += 1;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (size == 0){
            System.out.println("Empty Deque");
            return;
        }
        Node<T> curr = this.first.next;
        String accumulator = curr.item.toString();
        for(int i = 1; i < size; i ++){
            accumulator = accumulator + " " + curr.item.toString();
            curr = curr.next;
        }
        accumulator = accumulator + "\n";
        System.out.println(accumulator);
    }

    public T removeFirst(){
        //guard clause forbidden
        if (size == 0){
            return null;
        }
        Node<T> to_be_removed = this.first.next;
        Node<T> to_be_first = to_be_removed.next;
        first.next = to_be_first;
        to_be_first.prev = first;
        //nullify
        to_be_removed.next = null;
        to_be_removed.prev = null;
        size -= 1;
        return to_be_removed.item;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        Node<T> to_be_removed = this.first.prev;
        Node<T> to_be_last = to_be_removed.prev;
        first.prev = to_be_last;
        to_be_last.next = first;
        //nullify
        to_be_removed.next = null;
        to_be_removed.prev = null;
        size -=1;
        return to_be_removed.item;
    }

    public T get(int index){
        Node<T> curr = first.next;
        T result = null;
        for(int i = 0; i <= index; i ++) {
            result = curr.item;
            curr = curr.next;
        }
        return result;
    }

    public T getRecursive(int index) {
        return (T) helper(first.next, index);
    }

    private T helper(Node<T> first, int index){
        if (index == 0){
            return first.item;
        }
        return (T) helper(first.next, index-1);
    }

}

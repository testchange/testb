package deque;

public class LinkedListDeque<T> implements Deque<T>{

    private Node<T> first;
    public int size;

    public static class Node<T> {
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

    public boolean isEmpty(){
        if(size == 0){
            return true;
        } else {
            return false;
        }
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
            curr = curr.next;
            result = curr.item;
        }
        return result;
    }



}

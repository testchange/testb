package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }

    public T max(){
        if (size() == 0){
            return null;
        }
        T first = get((nextFirst + 1) % items.length);
        for(T i : this){
            if (comp.compare(first, i) < 0){
                first = i;
            }
        }
        return first;
    }

    public T max(Comparator<T> c){
        if (size() == 0){
            return null;
        }
        T first = get((nextFirst + 1) % items.length);
        for(T i : this){
            if (c.compare(first, i) < 0){
                first = i;
            }
        }
        return first;
    }

}

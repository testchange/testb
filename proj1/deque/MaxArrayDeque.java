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
        T first = get(0);
        for(int i = 0; i < this.size(); i++){
            T k = get(0);
            if (comp.compare(first, k) < 0){
                first = k;
            }
        }
        return first;
    }

    public T max(Comparator<T> c){
        if (size() == 0){
            return null;
        }
        T first = get(0);
        for(int i = 0; i < this.size(); i++){
            T k = get(0);
            if (c.compare(first, k) < 0){
                first = k;
            }
        }
        return first;
    }

}

package Chapter1to5;

import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private int size;
    private T items[];

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        if (size() > 0) {
            for (T t : items) {
                if (t.equals(x)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("Can't add null");
        }
        if (!contains(x)) {
            items[size] = x;
            size++;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if(this==other)
        {
            return true;
        }
        if (other == null) {
            return false;
        }
        if(other.getClass()!=this.getClass())
        {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("{");
        for (T item : this) {
            returnString.append(item.toString());
            returnString.append(",");
        }
        returnString.append("}");
        return returnString.toString();
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        System.out.println(s);
    }
}
public class ArrayDeque<T> {
    private T[] items; //数据
    private int head;
    private int tail;
    private int size;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        head = 0;
        tail = items.length - 1;
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }
    private int plusOne(int index, int module) {
        index %= module;  //防止数据溢出
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }
    private void newSize(int newLength) {
        T[] newItems = (T[]) new Object[newLength];
        int temp = head;
        for (int i = 0; i <= size; i++) {
            newItems[i] = items[temp];
            temp = plusOne(temp, items.length);
        }
        items = newItems;
        tail = size;
        head = 0;
    }
    public void addFirst(T t) {
        head = minusOne(head);
        items[head] = t;
        if (size == items.length - 1) {
            newSize(items.length * 2);
        }
        size += 1;
    }
    public void addLast(T t) {
        tail = plusOne(tail, items.length);
        items[tail] = t;
        if (size == items.length - 1) {
            newSize(items.length * 2);
        }
        size += 1;
    }
    public T removeFirst() {
        if (size == items.length / 4 && size >= 16) {
            newSize(items.length / 2);
        }
        if (size == 0) {
            return null;
        }
        T ret = items[head];
        items[head] = null;
        head = plusOne(head, items.length);
        size -= 1;
        return ret;
    }
    public T removeLast() {
        if (size == items.length / 4 && size >= 16) {
            newSize(items.length / 2);
        }
        if (size == 0) {
            return null;
        }
        T ret = items[tail];
        items[tail] = null;
        tail = minusOne(tail);
        size -= 1;
        return ret;
    }
    public void printDeque() {
        int temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(items[temp] + " ");
            temp = plusOne(temp, items.length);
        }
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, items.length);
        }
        return items[ptr];
    }
}

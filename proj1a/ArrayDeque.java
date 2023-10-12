public class ArrayDeque<T> {
    private T[] items;//数据
    private int head;
    private int tail;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        head = 0;
        tail = -1;
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
        int tem1 = head;
        for (int i = 0; i <size; i++) {
            newItems[i] = items[head];
            head = plusOne(head, items.length);
        }
        items = newItems;
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
        T ret = items[head];
        items[head] = null;
        if (size == 0) {
            return null;
        }
        if (size == items.length / 4 && size >= 16) {
            newSize(items.length / 2);
        }
        head = plusOne(head, items.length);
        size -= 1;
        return ret;
    }
    public T removeLast() {
        T ret = items[tail];
        items[tail] = null;
        if (size == 0) {
            return null;
        }
        if (size == items.length / 4 && size >= 16) {
            newSize(items.length / 2);
        }
        tail = minusOne(tail);
        size -= 1;
        return ret;
    }
    public void printDeque()
    {
        int temp=head;
        for(int i=0;i<size;i++)
        {
            System.out.print(items[temp]+" ");
            temp=plusOne(temp, items.length);
        }
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = head;
        for (int i = 0; i <=size; i++) {
            ptr = plusOne(ptr, items.length);
        }
        return items[ptr];
    }
}

public class ArrayDeque<T> {
    private T[] items;
    private int head;  // 头指针
    private int size;  // 大小
    private int tail;  // 尾指针

    private int length;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 4;
        tail = 4;
        length = 8;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = head;
        int ptr2 = length;
        while (ptr1 != tail) {
            newArray[ptr2] = items[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        head = length;
        tail = ptr2;
        items = newArray;
        length *= 2;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = head;
        int ptr2 = length / 4;
        while (ptr1 != tail) {
            newArray[ptr2] = items[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        head = length / 4;
        tail = ptr2;
        items = newArray;
        length /= 2;
    }

    public void addFirst(T item) {
        if (size == length - 1) {
            grow();
        }
        head = minusOne(head);
        items[head] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == length - 1) {
            grow();
        }
        items[tail] = item;
        tail = plusOne(tail, length);
        size++;
    }

    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T ret = items[head];
        head = plusOne(head, length);
        size--;
        return ret;
    }

    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T ret = items[tail];
        items[tail] = null;
        tail = minusOne(tail);
        size--;
        return ret;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return items[ptr];
    }

    public void printDeque() {
        int ptr = head;
        while (ptr != tail) {
            System.out.print(items[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
}
//    public void addFirst(T t)
//    {
//        if(size>= items.length)
//        {
//            resize(items.length*2);
//        }
//        items[head]=t;
//        size+=1;
//        if((head-=1)<0)
//        {
//            head= items.length-1;
//        }
//    }

//    public void addLast(T t)
//    {
//        if(tail>= items.length)
//        {
//            resize(items.length*2);
//        }
//        items[tail]=t;
//        size+=1;
//        if((tail+=1)< items.length)
//        {
//            head= items.length-1;
//        }
//    }
//
//    public void printDeque()
//    {
//        if(head<=0)
//        {
//            for(int i=0;i<Math.abs(head);i++)
//            {
//                System.out.println(items[items.length+head+i]);
//            }
//            for(int i=0;i)
//        }
//    }
//
//
//    public T get(int i)
//    {
//         return items[i];
//    }
//
//}
//    public void removeFirst()
//    {
//        if(size<items.length*0.25&&size>=16)
//        {
//            resize(items.length/2);
//        }
//        items[index]=null;
//        index-=1;
//    }

//    private void resize(int size)
//    {
//        T[] newItems=(T []) new Object[size];
//        //如何 head!=0 说明存在跨数组的情况
//        if(head!=0)
//        {
//            System.arraycopy(items,head,newItems,0,items.length-head);
//            System.arraycopy(items,0,newItems,items.length-head,head);
//        }
//        System.arraycopy(items,0,newItems,0,Math.max(head,tail));
//        items=newItems;
//    }

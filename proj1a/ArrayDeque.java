public class ArrayDeque<T>{
    private T[] items;
    private int head;  // 头指针 一般为下标0
    private int size;  // 大小
    private int tail;  // 尾指针
// TODO 调整resize 统一整合数组 head=0 tail=size  -->重新检查一下所有代码
    public ArrayDeque()
    {
        items=(T []) new Object[8];
        size=0;
        head=0;
        tail=0;
    }
    private void resize(int size)
    {
        T[] newItems=(T []) new Object[size];

        System.arraycopy(items,0,newItems,0,Math.max(head,tail));
        items=newItems;
    }
    public void addFirst(T t)
    {
        if(size>= items.length)
        {
            resize(items.length*2);
        }
        if((head-=1)<0)
        {
            head= items.length-1;
        }
        items[head]=t;
        size+=1;
    }

    public void addLast(T t)
    {
        if(tail>= items.length)
        {
            resize(items.length*2);
        }
        items[tail]=t;
        tail+=1;
        size+=1;
    }
    public void removeFirst()
    {
        if(size<items.length*0.25&&size>=16)
        {
            resize(items.length/2);
        }
        items[index]=null;
        index-=1;
    }
    public int size()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public T get(int i)
    {
         return items[i];
    }

}

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
        tail=1;
    }
    private void resize(int size)
    {
        T[] newItems=(T []) new Object[size];
        //如何 head!=0 说明存在跨数组的情况
        if(head!=0)
        {
            System.arraycopy(items,head,newItems,0,items.length-head);
            System.arraycopy(items,0,newItems,items.length-head,head);
        }
        System.arraycopy(items,0,newItems,0,Math.max(head,tail));
        items=newItems;
    }
    public void addFirst(T t)
    {
        if(size>= items.length)
        {
            resize(items.length*2);
        }
        items[head]=t;
        size+=1;
        if((head-=1)<0)
        {
            head= items.length-1;
        }
    }
    public void addLast(T t)
    {
        if(tail>= items.length)
        {
            resize(items.length*2);
        }
        items[tail]=t;
        size+=1;
        if((tail+=1)< items.length)
        {
            head= items.length-1;
        }
    }

    public void printDeque()
    {
        if(head<=0)
        {
            for(int i=0;i<Math.abs(head);i++)
            {
                System.out.println(items[items.length+head+i]);
            }
            for(int i=0;i)
        }
    }
//    public void removeFirst()
//    {
//        if(size<items.length*0.25&&size>=16)
//        {
//            resize(items.length/2);
//        }
//        items[index]=null;
//        index-=1;
//    }
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

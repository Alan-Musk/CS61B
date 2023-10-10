public class ArrayDeque<T>{
    private T[] items;
    private  int index;

    public ArrayDeque()
    {
        items=(T []) new Object[8];
        index=0;
    }
    private void resize(int size)
    {
        T[] newItems=(T []) new Object[size];
        System.arraycopy(items,0,newItems,0,index);
        items=newItems;
    }
    public void add(T t)
    {
        if(index>= items.length)
        {
            resize(items.length*2);
        }
        items[index]=t;
        index+=1;
    }
    public void remove()
    {
        if(index<items.length*0.25&&index>=16)
        {
            resize(items.length/2);
        }
        items[index]=null;
        index-=1;
    }
    public int size()
    {
        return index;
    }
    public T get(int i)
    {
         return items[i];
    }

}

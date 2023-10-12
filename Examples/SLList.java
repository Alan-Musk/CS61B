public class SLList<T> {
    public class IntNode {
        private  T item;
        private IntNode next;

        public IntNode(T i, IntNode n)
        {
            item=i;
            next=n;
        }
    }
    private IntNode first;
    private IntNode sentinel;
    private int size;

    public SLList()
    {
        sentinel=new IntNode(null,null);
        size=0;
    }
    public SLList(T x) {
        sentinel=new IntNode(null,null);
        sentinel.next=new IntNode(x,null);
        size = 1;
    }
    public void addFirst(T x)
    {
        sentinel.next=new IntNode(x,sentinel.next);
        this.first=new IntNode(x,this.first);
        size+=1;
    }
    public T getFirst()
    {
        return sentinel.next.item;
    }
    public  void addLast(T x)
    {
        size+=1;
        IntNode p=sentinel;
        while(p.next!=null)
        {
            p=p.next;
        }
        p.next=new IntNode(x,null);
    }
    public T get(int index)
    {
        if(index<0||index>size)
        {
            return null;
        }
        IntNode t=sentinel;
        for(int i=0;i<index;i++)
        {
            t=t.next;
        }
        return t.item;
    }
    public  int size()
    {
        return size;
    }
    public static void main(String[] args) {
        SLList L=new SLList();
        L.addLast(5);
        L.addFirst(10);
        System.out.println( L.getFirst());
    }
}

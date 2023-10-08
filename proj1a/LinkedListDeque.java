public class LinkedListDeque<T>{
    // Helper class
    public class Node
    {
        public T item;
        public Node prev;
        public Node next;

        public Node(Node p,T i,Node n)
        {
            this.item=i;
            this.prev=p;
            this.next=n;
        }
    }

    public int size;
    public Node sentinel;

    public  LinkedListDeque()
    {
        sentinel=new Node(null,null,null);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
        size=0;
    }

    // Adds an item of type T to the front of the deque
    public void addFirst(T item)
    {
        Node temp=new Node(sentinel,item,sentinel.next);
        sentinel.next.prev=temp;
        sentinel.next=temp;
        size+=1;
    }
    // Adds an item of type T to the back of the deque
    public void addLast(T item)
    {
        Node temp=new Node(sentinel.prev,item,sentinel);
        sentinel.prev.next=temp;
        sentinel.prev=temp;
        size+=1;
    }

    // Returns true if deque is empty, false otherwise
    public boolean isEmpty()
    {
        if(size==0)
        {
            return true;
        }
        return false;
    }
    // Returns the number of items in the deque.
    public int size()
    {
        return size;
    }

    // Prints the items in the deque from first to last,separated by a space
    public void printDeque(){
        Node current=sentinel.next;
        while(current.item!=null)
        {
            System.out.print(current.item);
            current=current.next;
        }
        System.out.println();
    }
    // Removes and returns the item at the front of the deque. If no such item exists returns null
    public T removeFirst()
    {
        if(size()==0)
        {
            return null;
        }
        T res=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        size-=1;
        return res;
    }
    // Removes and returns the item at the back of the deque. If no such item exists, return null
    public T removeLast()
    {
        if(size()==0)
        {
            return null;
        }
        T res=sentinel.prev.item;
        sentinel.prev=sentinel.prev.prev;
        size-=1;
        return res;
    }
    //Gets the item at the given index
    public T get(int index)
    {
        Node current=sentinel;
        while(index!=0)
        {
            current=current.next;
            index-=1;
        }
        return current.item;
    }
}

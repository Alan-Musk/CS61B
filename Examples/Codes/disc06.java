import java.util.concurrent.TimeoutException;

public class disc06 {
    public class Node
    {
        public Integer value;
        public Node prev;

        public Node(Integer v,Node p)
        {
            value=v;
            prev=p;
        }
    }
    private Node top;
    public boolean isEmpty()
    {
        return top==null;
    }
    public void push(Integer num)
    {
        top=new Node(num,top);
    }
    public Integer pop()
    {
        Integer ans=top.value;
        top=top.prev;
        return ans;
    }
    public Integer peek()
    {
        return top.value;
    }

    public static void main(String[] args) {
        try {
            disc06 test=new disc06();
            test.pop();
        }catch (NullPointerException e1)
        {
            System.out.println(3/2);
        }
    }
}

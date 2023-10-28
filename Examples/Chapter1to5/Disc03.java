package Chapter1to5;

import static org.junit.Assert.assertEquals;

public class Disc03 {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }
    private IntNode first;

    public void addFirst(int x) {
        first=new IntNode(x,first);
    }
    public void insert(int item,int position)
    {
        if(first==null||position==0)
        {
            addFirst(item);
            return;
        }
        IntNode currentNode=first;
        while(position>1&&currentNode.next!=null){
            position--;
            currentNode=currentNode.next;
        }
        IntNode newNode=new IntNode(item,currentNode.next);
        currentNode.next=newNode;
    }
    public void reverseIteratively()
    {
        IntNode frontOfReversed=null;
        IntNode nextNodeToAdd=first;
        while(nextNodeToAdd!=null)
        {
            IntNode remainderOfOriginal=nextNodeToAdd.next;
            nextNodeToAdd.next=frontOfReversed;
            frontOfReversed=nextNodeToAdd;
            nextNodeToAdd=remainderOfOriginal;
        }
        first=frontOfReversed;
    }
    public void reverseRecursively()
    {
        first=reverseRecursivelyHelper(first,null);
    }
    private IntNode reverseRecursivelyHelper(IntNode current,IntNode prev)
    {
        if(current==null)
        {
            return prev;
        }
        IntNode next=current.next;
        current.next=prev;
        return reverseRecursivelyHelper(next,current);
    }
    public static int[] insertArr(int[] arr,int item,int position)
    {
        if (position>=arr.length-1)
        {
            arr[arr.length-1]=item;
            return arr;
        }
        for(int i=arr.length-2;i>=position;i--)
        {
            arr[i+1]=arr[i];
        }
        arr[position]=item;
        return arr;
    }
    public static void reverse(int[] arr)
    {
        int i=0,j=arr.length-1;
        while(i<j)
        {
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i+=1;j-=1;
        }
    }
    public static int[] replicate(int[] arr)
    {
        int size=0;
        for(int i=0;i<arr.length;i++)
        {
            size+=arr[i];
        }
        int[] repArray=new int[size];
        for(int i=0,index=0;i< arr.length;i++)
        {
            for(int j=arr[i];j>0;j--,index++)
            {
                repArray[index]=arr[i];
            }
        }
        return repArray;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{3,2,1};
        arr=replicate(arr);
        System.out.println();
    }
}

public class Sort {

    private static void sort(String[] x,int start)
    {
        if(start==x.length)
        {
            return;
        }
        int smallestIndex=findSmallest(x,start);
        swap(x,start,smallestIndex);
        sort(x,start+1);
    }
    public static void sort(String[] x)
    {
        sort(x,0);
    }
    public static  int findSmallest(String[] x,int start)
    {
        int smallestIndex=start;
        for(int i=0;i<x.length;i+=1)
        {
            if(x[i].compareTo(x[smallestIndex])<0)
            {
                smallestIndex=i;
            }
        }
        return smallestIndex;
    }

    public static void swap(String[] x,int a,int b)
    {
        String temp=x[a];
        x[a]=x[b];
        x[b]=temp;
    }
}

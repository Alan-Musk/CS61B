public class Sort {
    public static void sort(String[] x)
    {

    }
    public static  String findSmallest(String[] x)
    {
        String smallest=x[0];
        for(int i=0;i<x.length;i+=1)
        {
            if(x[i].compareTo(smallest)<0)
            {
                smallest=x[i];
            }
        }
        return smallest;
    }
}

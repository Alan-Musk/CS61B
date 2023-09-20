public class exercise_4 {
    public static void windowPosSum(int[] a,int n)
    {
        for(int i=0;i<a.length;i++)
        {
            if(a[i]>=0)
            {
                int sum=0;
                for(int j=i;j<=(i+n)&&j<a.length;j++)
                {
                    sum+=a[j];
                }
                a[i]=sum;
            }
        }
    }

    public static void main(String[] args) {
        int[] a={1,2,-3,4,5,4};
        int n=3;
        windowPosSum(a,n);
        for(int i=0;i<a.length;i++)
        {
            System.out.println(a[i]);
        }
    }
}

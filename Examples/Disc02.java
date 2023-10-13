import org.junit.Test;

public class Disc2 {
    public static IntList square(IntList L)
    {
        if(L==null){
            return null;
        }
        IntList rest=square(L.rest);
        IntList M=new IntList(L.first*L.first,rest);
        return M;
    }
    public static IntList squareMutative(IntList L)
    {
        IntList B=L;
        while(B!=null)
        {
            B.first*=B.first;
            B=B.rest;
        }
        return L;
    }
    @Test
    public void test()
    {
        IntList num = new IntList(4, null);
        num = new IntList(3, num);
        num = new IntList(2, num);
        num = new IntList(1, num);
        square(num);
        squareMutative(num);
        System.out.println();
    }
}

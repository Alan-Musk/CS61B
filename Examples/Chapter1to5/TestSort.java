package Chapter1to5;

import org.junit.Test;
import static org.junit.Assert.*;
public class TestSort {
    @Test
    public  void testSort()
    {
        String[] input={"i","hava","an","egg"};
        String[] expected={"an","egg","have","i"};
        Sort.sort(input);

       assertEquals(expected,input );
    }
    @Test
    public  void testFindSmallest(){
        String[] input={"i","have","an","egg"};
        int excepted=1;

        int actual=Sort.findSmallest(input,0);
        assertEquals(excepted,actual);

        String[] input2={"there","are","many","pigs"};
        int expected2=2;

        int actual2=Sort.findSmallest(input2,2);
        assertEquals(expected2,actual2);
    }
    @Test
    public  void testSwap()
    {
        String[] input={"i","hava","an","egg"};
        int a=0;
        int b=2;
        String[] expected={"an","hava","i","egg"};
        Sort.swap(input,a,b);
        assertEquals(expected,input);
    }
}

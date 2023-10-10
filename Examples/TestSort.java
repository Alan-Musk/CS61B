public class TestSort {
    public static  void testSort()
    {
        String[] input={"i","hava","an","egg"};
        String[] expected={"an","egg","have","i"};
        Sort.sort(input);

       org.junit.Assert.assertEquals(expected,input );
    }

    public static  void testFindSmallest(){
        String[] input={"i","have","an","egg"};
        int excepted=1;

        int actual=Sort.findSmallest(input,0);
        org.junit.Assert.assertEquals(excepted,actual);

        String[] input2={"there","are","many","pigs"};
        int expected2=2;

        int actual2=Sort.findSmallest(input2,2);
        org.junit.Assert.assertEquals(expected2,actual2);
    }

    public static  void testSwap()
    {
        String[] input={"i","hava","an","egg"};
        int a=0;
        int b=2;
        String[] expected={"an","hava","i","egg"};
        Sort.swap(input,a,b);
        org.junit.Assert.assertEquals(expected,input);
    }
    public static void main(String[] args) {
        testSwap();
    }
}

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
        String excepted="an";

        String actual=Sort.findSmallest(input);
        org.junit.Assert.assertEquals(excepted,actual);

    }
    public static void main(String[] args) {
        testFindSmallest();
    }
}

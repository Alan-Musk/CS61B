public class TexX implements  IntUnaryFunction{
    public int apply(int x)
    {
        return 10*x;
    }
    public static int do_twice(IntUnaryFunction f,int x)
    {
        return f.apply(f.apply(x));
    }
}

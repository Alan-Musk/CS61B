public class CE1b {
    public static  void drawTriangle(int n)
    {

        for (int i=1;i<=n;i++)
        {
            for(int j=0;j<i;j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        drawTriangle(10);
    }

}

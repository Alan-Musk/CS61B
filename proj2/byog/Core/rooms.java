package byog.Core;

public class rooms {
    private int height;
    private int width;

    //按照中心上下左右生成
    private int coreX;
    private int coreY;
    public rooms(int height,int width,int coreX,int coreY)
    {
        this.height=height;
        this.width=width;
        this.coreX=coreX;
        this.coreY=coreY;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }
    public int getCoreX()
    {
        return coreX;
    }
    public int getCoreY()
    {
        return coreY;
    }
}

package byog.Core;

public class rooms {
    private int height;
    private int width;
    private Position position;
    public rooms(int height,int width,int coreX,int coreY)
    {
        this.height=height;
        this.width=width;
        position=new Position(coreX,coreY);
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
        return position.getX();
    }
    public int getCoreY()
    {
        return position.getY();
    }

}

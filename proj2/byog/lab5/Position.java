package byog.lab5;

import byog.TileEngine.TETile;

public class position {
    private int x;
    private int y;

    public position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public int getY()
    {
        return y;
    }
    public int getX()
    {
        return x;
    }

}

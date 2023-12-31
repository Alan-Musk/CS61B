package byog.lab5;
import byog.Core.Position;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t)
    {

        if(s<2)
        {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for(int yi=0;yi<2*s;yi+=1)
        {
            int thisRowY=p.getY()+yi;
            int xRowStart=p.getX()+hexRowOffset(s,yi);
            Position rowStartP=new Position(xRowStart,thisRowY);
            int rowWidth=hexRowWidth(s,yi);
            addRow(world,rowStartP,rowWidth,t);
        }
    }

    public static int hexRowWidth(int s,int i)
    {
        int effectiveI=i;
        if(i>=s)
        {
            effectiveI=2*s-1-effectiveI;
        }
        return s+s*effectiveI;
    }
    public static int hexRowOffset(int s,int i)
    {
        int effectiveI=i;
        if(i>=s)
        {
            effectiveI=2*s-1-effectiveI;
        }
        return -effectiveI;
    }

    public static void addRow(TETile[][] world, Position p,int width,TETile t)
    {
        for(int xi=0;xi<width;xi+=1)
        {
            int xCoord=p.getX()+xi;
            int yCoord=p.getY();
            world[xCoord][yCoord]=TETile.colorVariant(t,32,32,32,new Random());
        }
    }
}

package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] square;
    private static final int OPEN=1;
    private static final int BLOCK=0;
    private WeightedQuickUnionUF wUF;
    private int openSitesCount;

    // create N-by-N grid, with all sites initially block
    public Percolation(int N) {
        try {
            square=new int[N][N];
            for (int i = 0; i < square.length; i++) {
                for (int j = 0; j < square[0].length; j++) {
                    square[i][j]=BLOCK;
                }
            }
            wUF=new WeightedQuickUnionUF(N*N);
        } catch (IllegalArgumentException e)
        {
            System.out.println("N不能小于等于0");
        }
    }
    private int xyTo1D(int row,int col)
    {
        return row*square.length+col;
    }
    // open the site (row,col) if it is not open already
    public void open(int row, int col) {
        if(!isValid(row,col))
        {
            throw new IllegalArgumentException("参数错误");
        }
        if(square[row][col]!=OPEN){
            square[row][col]=OPEN;
            openSitesCount++;
            int current=xyTo1D(row,col);
            if(isOpen(row+1,col)) wUF.union(current,xyTo1D(row+1,col));
            if(isOpen(row-1,col)) wUF.union(current,xyTo1D(row-1,col));
            if(isOpen(row,col+1)) wUF.union(current,xyTo1D(row,col+1));
            if(isOpen(row,col-1)) wUF.union(current,xyTo1D(row,col-1));
        }
    }
    // 判断数组的边界
    private boolean isValid(int row,int col)
    {
        return row>=0 && row <square.length && col>=0 && col< square[row].length;
    }
    // is the site (row,col) open?
    public boolean isOpen(int row, int col) {
        if(isValid(row,col)&&this.square[row][col]==OPEN) return true;
        return false;
    }
    // is the site (row, col) full?
    public boolean isFull(int row,int col)
    {
        if(isValid(row,col)&&this.square[row][col]==BLOCK) return true;
        return false;
    }
    // number of open sites
    public int numberOfOpenSites()
    {
        return openSitesCount;
    }


    // does the system percolate?
    public boolean percolates()
    {
        for (int i = 0; i <square[0].length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                if(wUF.connected(0, square.length-1)&&wUF.connected(i,j))
                {
                    return true;
                }
            }
        }
        return false;
    }
    // use the unit testing
    public static void main(String[] args) {
        Percolation test=new Percolation(10);
    }
}

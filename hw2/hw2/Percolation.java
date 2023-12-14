package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] square;
    private static final int OPEN=1;
    private static final int BLOCK=0;
    // create N-by-N grid, with all sites initially block
    public Percolation(int N) {
        try {
            square=new int[N][N];
            for (int i = 0; i < square.length; i++) {
                for (int j = 0; j < square[0].length; j++) {
                    square[i][j]=BLOCK;
                }
            }
        } catch (IllegalArgumentException e)
        {
            System.out.println("N不能小于等于0");
        }
    }

    // open the site (row,col) if it is not open already
    public void open(int row, int col) {
        this.square[row][col]=OPEN;
    }

    // is the site (row,col) open?
    public boolean isOpen(int row, int col) {

        return this.square[row][col]==OPEN;
    }
    // is the site (row, col) full?
    public boolean isFull(int row,int col)
    {
        return this.square[row][col]==BLOCK;
    }
    // number of open sites
    public int numberOfOpenSites()
    {
        return 1;
    }
    // does the system percolate?
    public boolean percolates()
    {
        return true;
    }
    // use the unit testing
    public static void main(String[] args) {

    }
}

package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] square;     //方格
    private static final int OPEN = 1;
    private static final int BLOCK = 0;
    private WeightedQuickUnionUF wUF;
    private int openSitesCount; // Open节点总计数

    private int topVirtual; //顶部虚拟节点
    private int bottomVirtual; //底部虚拟节点

    // create N-by-N grid, with all sites initially block
    public Percolation(int N) {
        try {
            topVirtual = N * N; //设置顶部虚拟节点
            bottomVirtual = N * N + 1; // 设置底部虚拟节点
            wUF = new WeightedQuickUnionUF(N * N + 2);
            // 初始化时链接到虚拟节点
            for (int i = 0; i < N; i++) {
                wUF.union(xyTo1D(0, i), topVirtual);
                wUF.union(xyTo1D(N - 1, i), bottomVirtual);
            }
            square = new int[N][N];
            for (int i = 0; i < square.length; i++) {
                for (int j = 0; j < square[0].length; j++) {
                    square[i][j] = BLOCK;
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("N不能小于等于0");
        }
    }

    private int xyTo1D(int row, int col) {
        return row * square.length + col;
    }

    // open the site (row,col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("参数错误");
        }
        if (square[row][col] != OPEN) {
            square[row][col] = OPEN;
            openSitesCount++;
            int current = xyTo1D(row, col);
            if (isOpen(row + 1, col)) wUF.union(current, xyTo1D(row + 1, col));
            if (isOpen(row - 1, col)) wUF.union(current, xyTo1D(row - 1, col));
            if (isOpen(row, col + 1)) wUF.union(current, xyTo1D(row, col + 1));
            if (isOpen(row, col - 1)) wUF.union(current, xyTo1D(row, col - 1));
        }
    }

    // 判断数组的边界
    private boolean isValid(int row, int col) {
        return row >= 0 && row < square.length && col >= 0 && col < square[row].length;
    }

    // is the site (row,col) open?
    public boolean isOpen(int row, int col) {
        if (isValid(row, col) && this.square[row][col] == OPEN) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isValid(row, col) && this.square[row][col] == BLOCK) return true;
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }


    // does the system percolate?
    public boolean percolates() {
        return wUF.connected(topVirtual, bottomVirtual);
    }

    // use the unit testing
    public static void main(String[] args) {
        Percolation test = new Percolation(10);
    }
}

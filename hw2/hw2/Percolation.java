package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grids;     //方格
    private static final int OPEN = 1;
    private static final int BLOCK = 0;
    private WeightedQuickUnionUF wUF; // 用于渗透检查
    private WeightedQuickUnionUF fullUF; //用于isfull()的检查 仅连接到顶部虚拟节点
    private int openSitesCount; // Open节点总计数

    private int topVirtual; //顶部虚拟节点
    private int bottomVirtual; //底部虚拟节点

    // create N-by-N grid, with all sites initially block
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        grids = new int[N][N];
        topVirtual = N * N; //设置顶部虚拟节点
        bottomVirtual = N * N + 1; // 设置底部虚拟节点
        wUF = new WeightedQuickUnionUF(N * N + 2);
        fullUF = new WeightedQuickUnionUF(N * N + 1);
        // 初始化时链接到虚拟节点
        for (int i = 0; i < N; i++) {
            fullUF.union(xyTo1D(0, i), topVirtual);
            wUF.union(xyTo1D(0, i), topVirtual);
            wUF.union(xyTo1D(N - 1, i), bottomVirtual);
        }
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[0].length; j++) {
                grids[i][j] = BLOCK;
            }
        }
    }

    private int xyTo1D(int row, int col) {
        return row * grids.length + col;
    }

    // open the site (row,col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("参数错误");
        }
        if (grids[row][col] != OPEN) {
            grids[row][col] = OPEN;
            openSitesCount++;
            int current = xyTo1D(row, col);
            if (isOpen(row + 1, col)) intoUF(current, row + 1, col);
            if (isOpen(row - 1, col)) intoUF(current, row - 1, col);

            if (isOpen(row, col + 1)) intoUF(current, row, col + 1);
            if (isOpen(row, col - 1)) intoUF(current, row, col - 1);
        }
    }

    private void intoUF(int current, int row, int col) {
        wUF.union(current, xyTo1D(row, col));
        fullUF.union(current, xyTo1D(row, col));
    }

    // 判断数组的边界
    private boolean isValid(int row, int col) {
        return row >= 0 && row < grids.length && col >= 0 && col < grids[row].length;
    }

    // is the site (row,col) open?
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            return false;
        }
        if (grids[row][col] == OPEN) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            return false;
        }
        return fullUF.connected(xyTo1D(row, col), topVirtual);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }


    // does the system percolate?
    public boolean percolates() {
        if (openSitesCount == 0) {
            return false;
        }
        return wUF.connected(topVirtual, bottomVirtual);
    }

    // use the unit testing
    public static void main(String[] args) {
        Percolation test = new Percolation(1);
    }
}

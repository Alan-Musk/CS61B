package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double values[];
    private Percolation percolation;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 && T <= 0) {
            throw new IllegalArgumentException();
        }
        values = new double[T];
        for (int i = 0; i < T; i++) {
            percolation = pf.make(N);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            values[i] = percolation.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(values);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(values);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.pow(values.length, 1 / 2);
    }

    // high endpoint of 95% condidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.pow(values.length, 1 / 2);
    }
}

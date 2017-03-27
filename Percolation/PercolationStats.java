/**
 * Created by user on 2017/3/27.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private double[] threshold;
    private int row,col,openCount;



    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        openCount = 0;
        threshold = new double[trials];

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Arguments out of bound");

        for(int i = 0; i != trials; i ++){
            Percolation pl = new Percolation(n);
            do {
                row     = StdRandom.uniform(1, n+1);
                col  = StdRandom.uniform(1, n+1);
                if (pl.isOpen(row, col))
                    continue;
                pl.open(row, col);
                openCount++;
            } while (!pl.percolates());

            threshold[i] = (double) openCount / (n * n);
            openCount = 0;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()  {
        return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        PercolationStats pls = new PercolationStats(Integer.parseInt(args[0]),
            Integer.parseInt(args[1]));

//        PercolationStats pls = new PercolationStats(2000,100000);

        System.out.printf("mean                     = %f\n", pls.mean());
        System.out.printf("stddev                   = %f\n", pls.stddev());
        System.out.printf("95%% confidence Interval  = [%f, %f]\n",
                pls.confidenceLo(), pls.confidenceHi());
    }
}

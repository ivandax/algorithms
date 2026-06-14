import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds = {};

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }
        int i = 0;
        thresholds = new double[trials];
        while (i < trials) {
            double threshold = runPercolationTrial(n);
            thresholds[i] = threshold;
            i++;
        }
    }

    private double runPercolationTrial(int n) {
        boolean foundThreshold = false;
        double threshold = 0;
        Percolation percolation = new Percolation(n);
        while (!foundThreshold) {
            int row = StdRandom.uniformInt(1, n + 1);
            int col = StdRandom.uniformInt(1, n + 1);
            percolation.open(row, col);
            boolean isFull = percolation.isFull(row, col);
            if (isFull) {
                boolean percolates = percolation.percolates();
                if (percolates) {
                    threshold = (double) percolation.numberOfOpenSites() / (n * n);
                    foundThreshold = true;
                }
            }
        }
        return threshold;
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

            PercolationStats stats = new PercolationStats(n, trials);

            StdOut.println("mean = " + stats.mean());
            StdOut.println("stddev = " + stats.stddev());
            StdOut.println("95% confidence interval = " + "[" + String.format("%.16f",
                    stats.confidenceLo()) + ", " + String.format("%.16f", stats.confidenceHi()) + "]");

        } else {
            throw new IllegalArgumentException("Expected 2 args");
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - stddev();
    }

    public double confidenceHi() {
        return mean() + stddev();
    }
}

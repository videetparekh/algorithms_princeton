import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private final double mean;
    private final double stddev;
    private final double confidencelo;
    private final double confidencehi;



    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        double[] trialOutput = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates())
                perc.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
            trialOutput[i] = perc.numberOfOpenSites()/((double) n*n);
        }

        mean            = StdStats.mean(trialOutput);
        stddev          = StdStats.stddev(trialOutput);
        confidencelo    = mean - (CONFIDENCE_95*stddev/Math.sqrt(trials));
        confidencehi    = mean + (CONFIDENCE_95*stddev/Math.sqrt(trials));
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidencelo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidencehi;
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        System.out.printf("mean                         = %f \n", ps.mean());
        System.out.printf("stddev                       = %f \n", ps.stddev());
        System.out.printf("95%% Confidence Interval     = [%f, %f] \n", ps.confidenceLo(), ps.confidenceHi());
    }
}
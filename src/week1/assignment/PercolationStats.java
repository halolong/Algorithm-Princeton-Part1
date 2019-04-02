package week1.assignment;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95= 1.96;
    private int n = 0;
    private int trials = 0;
    private double[] openSiteFractionArr;
    private double mean = 0, stddev = 0, confidenceHi = 0, confidenceLo = 0;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        this.openSiteFractionArr = new double[trials];
        runModelAndGetStatResult();
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }

    // test client (described below)
    public static void main(String[] args) {
        String[] nums = new String[]{"200", "100"};
        initPercolationStatModel(nums);
    }


    /**
     *  -----下面是定义的private方法-----
     */
    private static void initPercolationStatModel(String[] nums){
        if (nums.length != 2) {
            throw new IllegalArgumentException();
        }

        int n = Integer.parseInt(nums[0]);
        int trail = Integer.parseInt(nums[1]);

        PercolationStats percolationStats = new PercolationStats(n, trail);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceHi()
        + ", " + percolationStats.confidenceLo() + "]");
    }

    private void runModelAndGetStatResult() {
        double numberOfSites = this.n * this.n;

        for (int i = 0; i < this.trials; i++) {
            int openSite = runPercolationModel();
            double openSiteFraction = (double) openSite / numberOfSites;
            this.openSiteFractionArr[i] = openSiteFraction;
        }

        this.mean = StdStats.mean(openSiteFractionArr);
        this.stddev = StdStats.stddev(openSiteFractionArr);
        this.confidenceHi = this.mean + CONFIDENCE_95 * stddev / Math.sqrt(trials);
        this.confidenceLo = this.mean - CONFIDENCE_95 * stddev / Math.sqrt(trials);
    }

    private int runPercolationModel() {
        Percolation model = new Percolation(this.n);
        while (!model.percolates()) {
            int row = StdRandom.uniform(1, this.n + 1);
            int col = StdRandom.uniform(1, this.n + 1);
            model.open(row, col);
        }
        return model.numberOfOpenSites();
    }
}
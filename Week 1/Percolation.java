import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int nsize;
    private boolean[] open;
    private final WeightedQuickUnionUF grid;
    private int openCount = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        this.nsize = n;
        this.open = new boolean[n*n + 2];
        grid = new WeightedQuickUnionUF(n*n + 2);
    }

    private int generateElementIndex(int row, int col) {
        return (row-1)*nsize + col;
    }

    private boolean isValid(int row, int col) {
        if (row >= 1 && row <= nsize) {
            if (col >= 1 && col <= nsize) {
                return true;
            }
        }
        return false;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) throw new IllegalArgumentException();

        int index = generateElementIndex(row, col);

        if (open[index]) return;

        open[index] = true;
        openCount++;

        // Connect top row open sites with top node
        if (row == 1) grid.union(index, 0);

        // Connect bottom row open sites with bottom node
        if (row == nsize) grid.union(index, nsize*nsize + 1);

        //
        if (row > 1 && isOpen(row-1, col))
            grid.union(index, generateElementIndex(row-1, col));    // Point above


        if (row < nsize && isOpen(row+1, col))
            grid.union(index, generateElementIndex(row+1, col));      // Point below

        if (col > 1 && isOpen(row, col-1))
            grid.union(index, generateElementIndex(row, col-1));  // Point to left

        if (col < nsize && isOpen(row, col+1))
            grid.union(index, generateElementIndex(row, col+1));  // Point to left
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) throw new IllegalArgumentException();
        return open[generateElementIndex(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) throw new IllegalArgumentException();
        return grid.connected(generateElementIndex(row, col), 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(0, nsize*nsize+1);
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(4);
        perc.open(1, 2);
        perc.open(1, 3);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(3, 2);
        perc.open(3, 3);
        perc.open(4, 3);
        System.out.println(perc.numberOfOpenSites());
        System.out.println(perc.percolates());
    }
}

/**
 * Created by user on 2017/3/27.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {

    private WeightedQuickUnionUF model;
    private boolean[] status;
    private int dimension;
    private int count;
    private int num_opensites = 0;

//    private int current_index;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        dimension = n;
        count = n * n;
        model = new WeightedQuickUnionUF(count+2);
        status = new boolean[count+2];
        for (int i = 0; i != count; i++) {
            status[i] = false;
        }
        status[0] = true; // top vitual site
        status[count+1] = true; // bottom vitual site
    }

    // current index of the grid using row and col
    private int index(int row,int col){
        return (row-1) * dimension + col;
    }

    // open site (row, col) if it is not open already
    public  void open(int row, int col) {

        if (row < 1 || row > dimension || col < 1 || col > dimension ){
            throw new java.lang.IndexOutOfBoundsException();
        }

        if (isOpen(row,col)) return;
        else {
            if (row!=1 && isOpen(row-1,col)) { // top side
                model.union(index(row,col),index(row-1,col));
            }

            if (row!=dimension && isOpen(row+1,col)) { // bottom side
                model.union(index(row,col),index(row+1,col));
            }

            if (col!=1 && isOpen(row,col-1)) { // left side
                model.union(index(row,col),index(row,col-1));
            }

            if (col!=dimension && isOpen(row,col+1)) { // right side
                model.union(index(row,col),index(row,col+1));
            }

            if (row == 1){
                model.union(index(row, col),0);
            }

            if (row == dimension) {
                model.union(index(row, col),count+1);
            }

            status[index(row,col)] = true; // open

            num_opensites ++;
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return status[index(row,col)];  
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return model.connected(index(row,col),0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return num_opensites;
    }

    // does the system percolate?
    // We say the system percolates if there is a full site in the bottom row.
    public boolean percolates() {
        return model.connected(0,count + 1);
    }

   // public static void main(String[] args)   // test client (optional)
}

package week1.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /**
     *  n = 3
     *  其数组sites
     *
     *  [top]    [ ]    [ ]    [ ]
     *  [ ]      [1]     [2]    [3]
     *  [ ]      [4]     [5]    [6]
     *  [bottom] [7]     [8]    [9]
     *
     *  实际的则是 1~9这一块是操作的。
     */
    private int n;
    private int numOfOpenSites = 0;
    private boolean[][] sites;
    private WeightedQuickUnionUF ufModel;//这个用来检测是否percolate
    private WeightedQuickUnionUF ufModelForFullTest; //这个用来检测是否full  防止backwash （这个是由下面的虚拟节点造成的，所以只能新建一个来检测，
    // 如果是用上面那个ufModel 这样会导致，一些点不能full的回流，因为其连通性）
    private int topNodeIndex, bottomNodeIndex;

    // 构造函数
    public Percolation(int n) {               // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IndexOutOfBoundsException("N should larger than 0");
        }
        this.n = n;
        this.sites = new boolean[n + 1][n + 1];
        this.ufModel = new WeightedQuickUnionUF(n * n + 2); // 这里加2是因为在乎其topIndex和bottomIndex
        this.ufModelForFullTest = new WeightedQuickUnionUF(n * n + 1); // 只关心bottomIndex
        this.topNodeIndex = 0; // 作为上面的点 virtual point
        this.bottomNodeIndex = n * n + 1; //作为底部连接的点 virtual point
        connectTopAndBottomNode();
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (isOpen(row, col)) {
            return;
        }
        sites[row][col] = true;
        numOfOpenSites++;

        //连接上下左右可以连接的点
        int siteInUfModel = getSiteInUfModel(row, col);

        // 上方
        connectAdjacentNode(siteInUfModel, row - 1, col);

        // 下方
        connectAdjacentNode(siteInUfModel, row + 1, col);

        // 左方
        connectAdjacentNode(siteInUfModel, row, col - 1);

        // 右方
        connectAdjacentNode(siteInUfModel, row, col + 1);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (! isValidateValue(row, col)) {
            throw new IllegalArgumentException();
        }
        return sites[row][col];
    }

    // is site (row, col) full? 这里的full表示该点是否能够连接到第一行
    // A full site is an open site that
    // can be connected to an open site in the top
    // row via a chain of neighboring (left, right, up, down)
    // open sites.
    public boolean isFull(int row, int col)
    {
        if (!isValidateValue(row, col)) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        int siteToCheck = getSiteInUfModel(row, col);
        return ufModelForFullTest.connected(topNodeIndex, siteToCheck);
    }

    // number of open sites
    public int numberOfOpenSites()
    {
        return numOfOpenSites;
    }

    // does the system percolate?
    // We say the system percolates
    // if there is a full site in the bottom row.
    public boolean percolates()
    {
        if (n == 1) {
            return isOpen(1, 1);
        }
        return ufModel.connected(topNodeIndex, bottomNodeIndex);
    }

    /**
     *  ---- 单独加的private方法 ----
     */
    // 初始化连接上面的定点和第一层，以及连接最后一层
    private void connectTopAndBottomNode() {
        // 连接顶点和第一层
        for (int i = 1; i <= n; i++) {
            ufModel.union(topNodeIndex, i);
            ufModelForFullTest.union(topNodeIndex, i);
        }

        // 连接最后一行到底部点
        for (int i = n * (n - 1) + 1; i <= n * n; i++) {
            ufModel.union(bottomNodeIndex, i);
        }
    }

    // 用来进行连接操作的：先转换成需要的index 然后union
    private void connectAdjacentNode(int siteInUfModel, int adjacentNodeRow, int adjacentNodeCol) {
        if (isValidateValue(adjacentNodeRow, adjacentNodeCol) && isOpen(adjacentNodeRow, adjacentNodeCol)) {
            int siteToConnect = getSiteInUfModel(adjacentNodeRow, adjacentNodeCol);
            union(siteInUfModel, siteToConnect);
        }
    }

    // 验证其行数和列数符合情况
    private boolean isValidateValue(int row, int col) {
        if (row > n || row <= 0 || col > n || col <= 0) {
            return false;
        }
        return true;
    }

    // 返回其具体的位置
    private int getSiteInUfModel (int row, int col) {
        return (row - 1) * n + col;
    }

    // 进行union操作
    private void union(int p, int q) {
        ufModel.union(p, q);
        ufModelForFullTest.union(p, q);
    }

    // test client (optional)
    public static void main(String[] args)
    {
        In in = new In("http://coursera.cs.princeton.edu/algs4/testing/percolation/input20.txt");
        int n = in.readInt();

        Percolation percolation = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolation.open(i, j);
            System.out.println("isFull: " + percolation.isFull(i, j));
            System.out.println("percolate: " + percolation.percolates());
        }
        System.out.println(n);
    }
}

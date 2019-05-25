package week3;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    //Compare a to b
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    //Swap a with b
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1; // 有利于后面先'--'和 '++'操作
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }

            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);// swap with partitioning item
        return j;
    }

    private static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // time: O(NlogN) space: O(1)
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // find the Kth largest number of array a
    // average time: O(n)
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;

        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k){
                hi = j - 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }

    public static void main(String[] args) {
        Comparable[] a = {10,9,8,7,6,5,4,3,2,1};
        sort(a);
        for (Comparable in : a) {
            System.out.println(in);
        }
        Comparable res = select(a, 5);
        System.out.println(res);
        System.out.println(res.getClass().getName());
    }
}

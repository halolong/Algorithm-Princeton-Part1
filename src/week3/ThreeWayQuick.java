package week3;


import edu.princeton.cs.algs4.StdRandom;

public class ThreeWayQuick {

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i,  int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        sort(a, lo, hi);
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi, i = lo + 1;
        Comparable v = a[lo];

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        // numbers between lt and i are same
        // and i - gt are undecided.
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Comparable[] a = {'A','A','A','A','A','A','E','E','C','B'};
        sort(a);
        for (Comparable in : a) {
            System.out.println(in);
        }
    }
}

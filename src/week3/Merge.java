package week3;

public class Merge {

    private static boolean isSorted(Comparable[] nums, int i, int j) {
        for (int k = i+1; k <= j; k++) {
            if (less(nums[k], nums[k-1])) return false;
        }
        return true;
    }
    private static boolean less (Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] nums, int i, int j) {
        Comparable temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }


    /**
     * 属于自底向上的排序
     * @param a
     */
    private static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int size = 1; size < N; size = size + size) {
            for (int lo = 0; lo < N - size; lo += size + size) {
                merge(a, aux, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
            }
        }
    }
    /**
     * 属于自顶向下
     * @param a
     * @param aux
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }


    public static void main(String[] args) {
        Comparable[] a = {3,1,5,32,7,2,4,6,88,15,5656};

        Comparable[] aux = new Comparable[a.length];
//        sort(a, aux, 0, a.length - 1);
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}

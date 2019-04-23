package week2.sort;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {

    public void sort(Comparable[] num) {
        int N = num.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(i + 1); // 生成 0, i的数字
            exch(num, i, r);
        }
    }
    public static void exch(Comparable[] nums, int i, int j) {
        Comparable temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}

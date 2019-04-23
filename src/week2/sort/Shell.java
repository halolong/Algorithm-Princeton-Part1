package week2.sort;

public class Shell {

    /**
     * 首先通过一个函数确定其增长趋势，然后反着来进行对比以及交换
     * @param nums
     */
    public static void sort(Comparable[] nums) {
        int N = nums.length;

        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40...
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(nums[j], nums[j - h]); j -= h) {
                    exch(nums, j, j - h);
                }
            }
            h = h / 3;
        }
    }
        // 用来交换
    public static void exch(Comparable[] nums, int i, int j) {
        Comparable temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static boolean less (Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] nums = {10,9,8,7,6,5,4,3,2,1};
        sort(nums);
        for (Integer in: nums) {
            System.out.println(in);
        }
    }
}

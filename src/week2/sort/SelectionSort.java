package week2.sort;

public class SelectionSort{

    /**
     * 利用Comparable来实现其选择排序
     * @param nums
     */
    public static void sort(Comparable[] nums) {
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            exch(nums, i, min);
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
        Integer[] nums = {1,323,12,53,3,11,32};
        sort(nums);
        for (Integer in: nums) {
            System.out.println(in);
        }
    }
}

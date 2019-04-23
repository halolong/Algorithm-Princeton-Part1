package week2.sort;

public class Insertion {

    public static void sort(Comparable[] nums) {
        int N = nums.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(nums[j], nums[j - 1])) {
                    exch(nums, j, j - 1);
                }
            }
        }
    }


    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] nums, int i, int j) {
        Comparable temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] nums = {1,323,12,53,3,11,32};
        sort(nums);
        for (Integer in: nums) {
            System.out.println(in);
        }
    }
}

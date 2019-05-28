package week4;


public class Heap <T extends Comparable<T>>{


    public Heap() {

    }

    public  void sort(T[] nums) {
        int N = nums.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(nums, k, N);
        }
        while (N > 1) {
            exch(nums, 1, N--);
            sink(nums, 1, N);
        }
    }

    public  void sink(T[] nums, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(nums, j, j+1))
                j++;
            if (!less(nums, k, j))
                break;
            exch(nums, k, j);
            k = j;
        }
    }

    // 这里 减1的操作：相当于依然按照以前那样计算，只是映射空间的时候 对应到了0~n-1
    public  boolean less(T[] nums, int i, int j) {
        return nums[i-1].compareTo(nums[j-1]) < 0;
    }

    public void exch(T[] nums, int i, int j) {
        T temp = nums[i-1];
        nums[i-1] = nums[j-1];
        nums[j-1] = temp;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        Integer[] nums = {10,4,12,2,8};
        heap.sort(nums);
        for (Integer out : nums) {
            System.out.println(out);
        }
    }
}

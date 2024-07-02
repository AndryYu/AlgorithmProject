import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 */
public class alg_6KthLargestElement {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println("2th Largest Element " + findKthLargest4(nums, 2));

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("2th Largest Element " + findKthLargest4(nums, 4));
    }

    /**
     * 使用priorityQueue 优先队列
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (k > nums.length || k == 0) {
            return 0;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int num : nums) {
            priorityQueue.offer(num);
        }

        if (priorityQueue.isEmpty()) {
            return 0;
        }

        int result = priorityQueue.poll();
        int index = 1;
        while (index != k) {
            result = priorityQueue.poll();
            index++;
        }

        return result;
    }

//    排序方法的演示
//      1）插入排序（直接插入排序、希尔排序）
//      2）交换排序（冒泡排序、快速排序）
//      3）选择排序（直接选择排序、堆排序）
//      4）归并排序
//      5）分配排序（基数排序）

    /**
     * 插入排序（直接插入排）
     * 插入排序的基本操作是将一个记录插入到已经排好的有序表中，从而得到一个新的、记录数增1的有序表。
     * 要排序的表本身就是有序的，此时只有数据比较，没有数据移动，最好时间复杂度为O(n)。 平均时间复杂度O(n^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest2(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }

        if (size == 1) {
            return nums[0];
        }

        for (int i = 1; i < size; i++) {
            int temp = nums[i];
            int j = i - 1;
            //将当前值key和前面的值进行比较，如果前面的值<key 则将值往后移1位
            while (j >= 0 && nums[j] < temp) {
                nums[j + 1] = nums[j];
                j--;
            }
            //在大于当前值key的下一个位置，插入当前值key
            nums[j + 1] = temp;
        }
        System.out.println("alg_6KthLargestElement.findKthLargest2: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 插入排序（希尔排序）
     * 希尔排序是一种改进的插入排序算法，它的基本思想是将待排序的数组按照一定的间隔进行分组，对每组使用插入排序算法进行排序，然后缩小间隔，再对分组进行排序，直到间隔为1为止。
     * 逐渐减小间隔大小的方法有助于提高排序过程的效率，可以减少比较和交换的次数。这是希尔排序算法的一个关键特点。
     * 希尔排序最好时间复杂度和平均时间复杂度都是O(nlogN)，最坏时间复杂度为O(n^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest3(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }

        //gap代表步进数
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                int temp = nums[i];
                int j = i;
                //对gap间隔的数据进行插入排序
                while (j >= gap && nums[j] > nums[j - gap]) {
                    nums[j] = nums[j - gap];
                    j -= gap;
                }
                nums[j] = temp;
            }
        }
        System.out.println("alg_6KthLargestElement.findKthLargest3: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 交换排序（冒泡排序）
     * 依次比较相邻的两个数，将小数放在前面，大数放在后面
     * 时间复杂度为O(n^2)
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest4(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }
        for (int i = 0; i < size -1; i++) {
            for (int j = i+1; j < size; j++) {
                if (nums[j]> nums[i]){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        System.out.println("alg_6KthLargestElement.findKthLargest4: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 交换排序（快速排序）
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest5(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }
        quickSort(nums, 0, size-1);
        System.out.println("alg_6KthLargestElement.findKthLargest5: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    private static void quickSort(int[] nums, int start, int end){
        int middle = nums[(start + end)/2];
        int left = start;
        int right = end;
        while (left < right){
            while(left < right && nums[right] < middle){
                right --;
            }
            nums[left] = nums[right];
            while(left < right && nums[left] < middle){
                right --;
            }
            nums[left] = nums[right];
        }
    }

    /**
     * 选择排序（直接选择排序）
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest6(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }

        System.out.println("alg_6KthLargestElement.findKthLargest6: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 选择排序（堆排序）
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest7(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }

        System.out.println("alg_6KthLargestElement.findKthLargest7: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 归并排序
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest8(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }

        System.out.println("alg_6KthLargestElement.findKthLargest8: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 分配排序（基数排序）
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest9(int[] nums, int k) {
        int result = 0;
        int size = nums.length;
        if (k > size || k == 0) {
            return result;
        }

        System.out.println("alg_6KthLargestElement.findKthLargest9: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }
}

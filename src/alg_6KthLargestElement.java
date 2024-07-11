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
        System.out.println("2th Largest Element " + findKthLargest(nums, 2));
        System.out.println("2th Largest Element " + findKthLargest9(nums, 2));

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("2th Largest Element " + findKthLargest(nums, 4));
        System.out.println("2th Largest Element " + findKthLargest9(nums, 4));
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
     *
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
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (nums[j] > nums[i]) {
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
     * 其原理如下：对于给定的一组记录，选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
     * 此时基准元素在其排好序的正确位置,然后再用同样的方法递归地排序划分的两部分，直到序列中的所有记录均有序为止。
     * 最好时间复杂度和平均时间复杂度都是O(nlogN)，最坏时间复杂度为O(n^2)
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
        quickSort(nums, 0, size - 1);
        System.out.println("alg_6KthLargestElement.findKthLargest5: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 注意项：
     * 1.基准数 通常选择第一个元素或者最后一个元素
     * 2.坐标右标相等，基准元素要设置在其排好序的正确位置
     */
    private static void quickSort(int[] nums, int start, int end) {
        if (start > end)
            return;
        //第一个数作为基准数
        int middle = nums[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && nums[right] <= middle) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] >= middle) {
                left++;
            }
            nums[right] = nums[left];
        }
        //坐标和右标相等 将基准数赋值给相等标
        nums[left] = middle;
        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    /**
     * 选择排序（直接选择排序）
     * 其基本原理如下：对于给定的一组记录，经过第一轮比较后得到最小的记录，然后将该记录的位置与第一个记录的位置交换；接着对不包括第一个记录以外的其他记录进行第二次比较，
     * 得到最小记录并与第二个位置记录交换；重复该过程，知道进行比较的记录只剩下一个为止。
     * 时间复杂度为O(n^2)
     * 与冒泡排序区别：
     * 选择排序不是每次都移动，记录下标，最后再移动
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
        for (int i = 0; i < size - 1; i++) {
            int selectIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (nums[j] > nums[selectIndex]) {
                    selectIndex = j;
                }
            }
            if (selectIndex != i) {
                int temp = nums[i];
                nums[i] = nums[selectIndex];
                nums[selectIndex] = temp;
            }
        }

        System.out.println("alg_6KthLargestElement.findKthLargest6: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 选择排序（堆排序）
     * 基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
     * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
     * <p>
     * 非叶节点最大值： n/2 - 1
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

        nums = buildMinHeap(nums);

        for (int i = size - 1; i > 1; i--) {
            //将第一位数跟堆最后一个数据调换
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            adjustHeap(nums, 0, i);
        }

        System.out.println("alg_6KthLargestElement.findKthLargest7: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    /**
     * 创建小顶堆
     *
     * @param nums
     * @return
     */
    private static int[] buildMinHeap(int[] nums) {
        // 从最后一个节点nums.length-1的父节点（nums.length-1-1）/2开始，直到根节点0，反复调整堆
        for (int i = (nums.length - 2) / 2; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        return nums;
    }

    /**
     * 完全二叉树：
     * 每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
     * 或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
     *
     * @param nums
     * @param k
     * @param length
     */
    private static void adjustHeap(int[] nums, int k, int length) {
        int temp = nums[k];

        // i为初始化为节点k的左子节点，沿节点较大的子节点向下调整
        for (int i = 2 * k + 1; i < length - 1; i = 2 * i + 1) {
            // 取节点较小的子节点的下标
            if (i < length && nums[i] > nums[i + 1]) {
                i++; // 如果节点的右子节点<左子节点，则取右子节点的下标
            }

            // 根节点 <=左右子节点中关键字较小者，调整结束
            if (temp <= nums[i]) {
                break;
            } else {
                // 根节点 <左右子节点中关键字较小于者
                nums[k] = nums[i]; // 将左右子节点中较小值array[i]调整到双亲节点上
                k = i; // 【关键】修改k值，以便继续向下调整
            }
        }
        nums[k] = temp; // 被调整的结点的值放人最终位置
    }

    /**
     * 归并排序
     * 基本原理如下：对于给定的一组记录，利用递归与分治技术将数据序列划分成为越来越小的半子表，在对半子表排序，
     * 最后再用递归方法将排好序的半子表合并成为越来越大的有序序列。
     * <p>
     * 归并排序的最好，最坏，平均时间复杂度均为O(nlogn):
     * 每次合并操作的平均时间复杂度为O(n)，而完全二叉树的深度为|log2n|。总的平均时间复杂度为O(nlogn)。
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

        mergeSort(nums, 0, size - 1);

        System.out.println("alg_6KthLargestElement.findKthLargest8: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }


    private static void mergeSort(int[] nums, int start, int end) {
        //拆分到每个数组merge都只剩下一个
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(nums, start, middle);
            mergeSort(nums, middle + 1, end);
            merge(nums, start, middle, end);
        }
    }

    private static void merge(int[] nums, int start, int middle, int end){
        //用于存储归并后的临时数组
        int[] temp = new int[end - start + 1];

        int i = start;
        int j = middle + 1;

        int index = 0;
        //遍历两个数组取出小的数字放入临时数组
        while (i <= middle && j <= end) {
            //把小的数据放入临时数组中,并让下标向后移1
            if (nums[i] >= nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }
        //处理多余的数据
        while (j <= end) {
            temp[index++] = nums[j++];
        }
        while (i <= middle) {
            temp[index++] = nums[i++];
        }
        //把临时数组中的数据重新存入原数组
        System.arraycopy(temp, 0, nums, start, temp.length);
    }

    /**
     * 分配排序（基数排序）
     * 在基数排序中，没有任何元素的比较和交换，元素只是在每一轮中从一个队列移动到另一个队列。对于给定的基数，遍历数据的轮次是一个常数，它与排序关键字的数目无关。
     * 基数排序算法的时间复杂度为O(n)
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
        radixSort(nums);
        System.out.println("alg_6KthLargestElement.findKthLargest9: " + Arrays.toString(nums));
        result = nums[k - 1];
        return result;
    }

    private static void radixSort(int[] arr) {
        // 存储数组中的最大数字
        int max = Integer.MIN_VALUE;//将max值初始为Integer类型的最小值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        // 计算数字是几位数
        int maxlength = (max + "").length();//(max+"")将最大值转换成String类型,再得到它的长度

        // 用于临时存储数据的数组(二维数组)
        int[][] temp = new int[10][arr.length];
        // 根据最大长度来决定比较次数
        for (int i = 0, n = 1; i < maxlength; i++, n *= 10) {
            int[] count = new int[10];
            // 把每一个数字分别计算余数
            for (int value : arr) {
                int remainder = value / n % 10;
                //二维数组，第一位是余数，第二位存放相应数量
                temp[remainder][count[remainder]++] = value;
            }

            //记录取的元素需要放的位置
            int index = 0;
            //把数据取出来，后面再进行高位求余
            for (int k = 0; k < count.length; k++) {
                //记录数量的数组中,若不为0才取
                if (count[k] != 0) {
                    //取出元素
                    for (int l = 0; l < count[k]; l++) {
                        arr[index++] = temp[k][l];
                    }
                    count[k] = 0;
                }
            }
        }
    }
}

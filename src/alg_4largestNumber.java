import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 *
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 *
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class alg_4largestNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 2};
        System.out.println("largestNum1:" + largestNumber4(nums));

        nums = new int[]{3, 30, 34, 5, 9};
        System.out.println("largestNum2:" + largestNumber4(nums));

        nums = new int[]{34323,3432};
       // System.out.println("largestNum3:" + largestNumber(nums));  failed
        System.out.println("largestNum3:" + largestNumber4(nums));

        nums = new int[]{0,0};
        // System.out.println("largestNum3:" + largestNumber(nums));  failed
        System.out.println("largestNum4:" + largestNumber4(nums));
    }

    /**
     * 第一次 通过排序 比较首位 不够就比较次位
     * @param nums  数组
     * @return  在第三种情况下验证失败
     */
    public static String largestNumber(int[] nums) {
        int size = nums.length;
        if (size <= 1) {
            return String.valueOf(nums[0]);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int firstNumI = Integer.parseInt(String.valueOf(nums[i]).substring(0, 1));
                int firstNumJ = Integer.parseInt(String.valueOf(nums[j]).substring(0, 1));
                int index = 1;
                while (true) {
                    if (firstNumI < firstNumJ) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        break;
                    } else if (firstNumI == firstNumJ) {
                        int IntI = nums[i];
                        int IntJ = nums[j];
                        int pow = (int) Math.pow(10, index);

                        if (IntI / pow == 0) {
                            firstNumI = Integer.parseInt(String.valueOf(nums[i]).substring(index - 1, index));
                            firstNumJ = Integer.parseInt(String.valueOf(nums[j]).substring(index, index + 1));
                            index++;
                            continue;
                        }

                        if (IntJ / pow == 0) {
                            firstNumI = Integer.parseInt(String.valueOf(nums[i]).substring(index, index + 1));
                            firstNumJ = Integer.parseInt(String.valueOf(nums[j]).substring(index - 1, index));
                            index++;
                            continue;
                        }

                        firstNumI = Integer.parseInt(String.valueOf(nums[i]).substring(index, index + 1));
                        firstNumJ = Integer.parseInt(String.valueOf(nums[j]).substring(index, index + 1));
                        index++;
                    } else {
                        break;
                    }
                }
            }
        }

        StringBuilder buffer = new StringBuilder();
        for (int num : nums) {
            buffer.append(num);
        }
        return buffer.toString();
    }

    /**
     * 将数字转成字符串，利用compareTo 比较ascii码大小，从而实现排序
     * @param nums  数组
     * @return
     */
    public static String largestNumber2(int[] nums) {
        String[] strValue = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strValue[i] = String.valueOf(nums[i]);
        }
        /**
         * 如果compare方法返回负数，那么第一个参数在排序顺序中位于第二个参数之前。
         * 如果compare方法返回零，那么两个参数的排序顺序没有变化。
         * 如果compare方法返回正数，那么第一个参数在排序顺序中位于第二个参数之后。
         */
        Arrays.sort(strValue, (o1, o2) -> (o2+o1).compareTo(o1+o2));

        StringBuilder buffer = new StringBuilder();
        for (String s : strValue) {
            buffer.append(s);
        }
        if (buffer.charAt(0) == '0'){
            return "0";
        }
        return buffer.toString();
    }

    /**
     * 与2原理一样都是依赖compareTo 比较ascii码 但利用了priorityQueue优先队列，实现自动排序
     * @param nums
     * @return
     */
    public static String largestNumber3(int[] nums) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        for (int num : nums) {
            priorityQueue.offer(String.valueOf(num));
        }
        StringBuilder buffer = new StringBuilder();
        while (!priorityQueue.isEmpty()){
            buffer.append(priorityQueue.poll());
        }

        if (buffer.charAt(0) == '0'){
            return "0";
        }
        return buffer.toString();
    }

    /**
     * 使用Arrays.sort排序，计算大于数据的10的倍数，比较谁在前面更多一些
     * @param nums
     * @return
     */
    public static String largestNumber4(int[] nums) {
        Integer[] numsData = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsData[i] = nums[i];
        }
        /**
         * 如果compare方法返回负数，那么第一个参数在排序顺序中位于第二个参数之前。
         * 如果compare方法返回零，那么两个参数的排序顺序没有变化。
         * 如果compare方法返回正数，那么第一个参数在排序顺序中位于第二个参数之后。
         */
        Arrays.sort(numsData, (x, y) -> {
            long sx = 10, sy = 10;
            while (y >= sx) {
                sx *= 10;
            }
            while (x >= sy) {
                sy *= 10;
            }
            return (int) (sy * y + x - sx * x - y);
        });

        StringBuilder buffer = new StringBuilder();
        for (int s : numsData) {
            buffer.append(s);
        }
        if (buffer.charAt(0) == '0'){
            return "0";
        }
        return buffer.toString();
    }
}

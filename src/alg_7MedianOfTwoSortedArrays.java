/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class alg_7MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println("Median Of Two Sorted Arrays:" + findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println("Median Of Two Sorted Arrays:" + findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{3};
        nums2 = new int[]{-2, -1};
        System.out.println("Median Of Two Sorted Arrays:" + findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{};
        nums2 = new int[]{};
        System.out.println("Median Of Two Sorted Arrays:" + findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 解法一  二分查找
     *  size(left) = (m+n+1)/2 不分奇偶数
     *  红线左边的所有元素数组 《= 红线右边的所有元素
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        //需要处理空数组情况
        if (m == 0){
            if (n == 0){
                return 0;
            }
            if (n%2 == 0){
                return (nums2[(n-1)/2] + nums2[(n+1)/2])/2d;
            }else{
                return nums2[n/2];
            }
        }
        int totalLeft = (m + n + 1) / 2;
        //left right是nums1查询中位数的区间范围
        int left = 0;
        int right = m;

        int media1 = 0, media2 = 0;
        while (left<=right) {
            //前一部分 包括 nums1[0..i-1]和nums2[0..j-1]
            //后一部分 包括 nums1[i..m]和nums2[j..n]
            int i = (left + right + 1)/2;
            int j = totalLeft - i;

            int nums1_left = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1_right = i == m ? Integer.MAX_VALUE : nums1[i];
            int nums2_left = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2_right = j == n ? Integer.MAX_VALUE : nums2[j];

            //二分查找法  步进1/2
            if (nums1_left > nums2_right) {
                right = i - 1;
            } else if (nums2_left > nums1_right) {
                left = i + 1;
            } else {
                media1 = Math.max(nums1_left, nums2_left);
                media2 = Math.min(nums2_right, nums1_right);
                break;
            }
        }
        double result;
        if ((m + n) % 2 == 0) {
            //偶数
            result = (media1 + media2) /2d;
        } else {
            //奇数
            result = media1;
        }
        return  result;
    }
}

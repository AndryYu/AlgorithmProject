import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,3};
        int val = 3;
        int k = removeElement2(nums, val);

        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("k:" + k);

    }

    public static int removeElement(int[] nums, int val) {
        int k = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val){
                nums[k] = nums[i];
                k ++;
            }
        }

        return k;
    }

    public static int removeElement2(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }



}
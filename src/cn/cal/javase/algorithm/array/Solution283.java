package cn.cal.javase.algorithm.array;

import java.util.Arrays;

/**
 * LeetCode 283. 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class Solution283 {
    public void moveZeroes(int[] nums) {
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[idx++] = num;
            }
        }
        while (idx < nums.length) {
            nums[idx++] = 0;
        }
    }

    // 测试方法
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(nums));
        new Solution283().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}

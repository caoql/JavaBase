package cn.cal.javase.algorithm.array.p1;

import java.util.Arrays;

/**
 * LeetCode 645. 错误的集合
 * https://leetcode-cn.com/problems/set-mismatch/
 */
public class Solution645 {

    // TODO 这个是最优解法,要关注它的暴力解法到最优解法的演化
    public int[] findErrorNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 2};
        int[] ret = new Solution645().findErrorNums(nums);
        System.out.println(Arrays.toString(ret));

        nums = new int[]{1, 1};
        ret = new Solution645().findErrorNums(nums);
        System.out.println(Arrays.toString(ret));

    }

}

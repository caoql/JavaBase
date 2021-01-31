package cn.cal.javase.algorithm.array.p1;

/**
 * LeetCode 485. 最大连续1的个数
 * https://leetcode-cn.com/problems/max-consecutive-ones/
 */
public class Solution485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        // param check
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("param error");
        }
        int max = 0;
        int cur = 0;
        for (int num : nums) {
            cur = num == 1 ? cur + 1 : 0;
            max = Math.max(max, cur);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        int ret = new Solution485().findMaxConsecutiveOnes(nums);
        System.out.println(ret);
    }
}

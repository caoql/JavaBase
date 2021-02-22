package cn.cal.javase.algorithm.array.p1;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 287. 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 */
public class Solution287 {
    // TODO 寻找最优解法
    public int findDuplicate(int[] nums) {
        // param check
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        for (int num : nums) {
            if (cache.containsKey(num)) {
                cache.put(num, cache.get(num) + 1);
            } else {
                cache.put(num, 1);
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (cache.get(i) != null && cache.get(i) > 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 2};
        int ret = new Solution287().findDuplicate(nums);
        System.out.println(ret);
    }
}

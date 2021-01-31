package cn.cal.javase.algorithm.array.p1;

/**
 * LeetCode 769. 最多能完成排序的块
 * https://leetcode-cn.com/problems/max-chunks-to-make-sorted/
 */
public class Solution769 {

    // 看不懂可以看下官方题解，还是比较好理解的
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ret = 0;
        int curMax = 0;
        for (int i = 0; i < arr.length; i++) {
            curMax = Math.max(curMax, arr[i]);
            if (curMax == i) {
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1, 0};
        int ret = new Solution769().maxChunksToSorted(arr);
        System.out.println(ret);

        arr = new int[]{1, 0, 2, 3, 4};
        ret = new Solution769().maxChunksToSorted(arr);
        System.out.println(ret);
    }
}

package cn.cal.javase.algorithm.array.p2;

import java.util.Arrays;

/**
 * LeetCode 566. 重塑矩阵
 * https://leetcode-cn.com/problems/reshape-the-matrix/
 */
public class Solution566 {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2}, {3, 4}};
        int r = 1, c = 4;
        int[][] ret = new Solution566().matrixReshape(nums, r, c);
        System.out.println(Arrays.toString(ret));

        r = 2;
        ret = new Solution566().matrixReshape(nums, r, c);
        System.out.println(Arrays.toString(ret));
    }
}

package cn.cal.javase.algorithm.search;

/**
 * 旋转数组
 * LeetCode 153. 寻找旋转排序数组中的最小值
 * // TODO
 */
public class RotatedSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 4, 5, 1, 2};
        int r1 = findMin(nums1);
        System.out.println(r1);
    }

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else {
                right = mid;
            }
        }
        return nums[left];
    }

}

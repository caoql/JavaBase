package cn.cal.javase.algorithm.search;

/**
 * 二分查找相关算法
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = new int[]{1, 4, 7, 7, 7, 9, 10};
        int r1 = binarySearch(array, 4);
        System.out.println(r1);

        int r2 = binarySearchLowerBound(array, 7);
        System.out.println(r2);

        int r3 = binarySearchUpperBound(array, 7);
        System.out.println(r3);

        int r4 = binarySearchLastLowerBound(array, 7);
        System.out.println(r4);

        int r5 = binarySearchNearUpperBound(array, 7);
        System.out.println(r5);

    }

    // 1. 最基本的二分查找 [] 解决不重复的问题，能得到固定的结果
    public static int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0, high = array.length - 1, mid;
        // 循环的判定条件
        while (low <= high) {
            // 为了防止数值溢出
            mid = low + (high - low) / 2;
            // 当 array[mid]不等于target时，high = mid - 1或low = mid + 1
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 2. 查找目标值区域的左边界/查找与目标值相等的第一个位置/查找第一个不小于目标值数的位置
    public static int binarySearchLowerBound(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0, high = array.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < array.length && array[low] == target) {
            return low;
        } else {
            return -1;
        }
    }

    // 3. 查找目标值区域的右边界/查找与目标值相等的最后一个位置/查找最后一个不大于目标值数的位置
    public static int binarySearchUpperBound(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0, high = array.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (high >= 0 && array[high] == target) {
            return high;
        } else {
            return -1;
        }
    }

    // 4. 查找最后一个小于目标值的数/查找比目标值小但是最接近目标值的数
    // 此题以可由第 2 题变形而来，我们已经找到了目标值区域的下（左）边界，那么再往左退一位，即low - 1，就是最后一个小于目标值的数。其实low - 1也是退出循环后high的值，因为此时 high刚好等于low - 1，它小于low，所以 while 循环结束。我们只要判断high是否超出边界即可。
    public static int binarySearchLastLowerBound(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0, high = array.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return high < 0 ? -1 : high;
    }

    // 5. 查找第一个大于目标值的数/查找比目标值大但是最接近目标值的数
    // 此题以可由第 3 题变形而来，我们已经找到了目标值区域的上（右）边界，那么再往右进一位，即high + 1，就是第一个大于目标值的数。其实high + 1也是退出循环后low的值，因为此时 low刚好等于high + 1，它大于high，所以 while 循环结束。我们只要判断low是否超出边界即可。
    public static int binarySearchNearUpperBound(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0, high = array.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low < array.length ? low : -1;
    }


}

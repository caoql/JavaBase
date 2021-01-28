package cn.cal.javase.datastructure.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> sortList = new ArrayList<>();
        sortList.add(2);
        sortList.add(3);
        sortList.add(1);
        Collections.sort(sortList);
        System.out.println(sortList);

        int[] sortArray = {2, 3, 1};
        int[] clone = sortArray.clone();
        Arrays.sort(sortArray);
        print(sortArray);
        print(clone);

        Integer[] sortArray2 = {2, 7, 6, 8, 1, 3, 5, 4};
        legacyMergeSort(sortArray2);
        print(sortArray2);
    }

    private static void print(int[] array) {
        System.out.print("{");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("}");
    }

    private static void print(Object[] array) {
        System.out.print("{");
        for (Object i : array) {
            System.out.print(i + " ");
        }
        System.out.println("}");
    }

    private static void legacyMergeSort(Object[] a) {
        Object[] aux = (Object[])a.clone();
        mergeSort(aux, a, 0, a.length, 0);
    }

    private static void mergeSort(Object[] src, Object[] dest, int low, int high, int off) {
        int length = high - low;
        int destLow;
        int destHigh;
        if (length < 7) {
            for(destLow = low; destLow < high; ++destLow) {
                for(destHigh = destLow; destHigh > low && ((Comparable)dest[destHigh - 1]).compareTo(dest[destHigh]) > 0; --destHigh) {
                    swap(dest, destHigh, destHigh - 1);
                }
            }

        } else {
            destLow = low;
            destHigh = high;
            low += off;
            high += off;
            int mid = low + high >>> 1;
            mergeSort(dest, src, low, mid, -off);
            mergeSort(dest, src, mid, high, -off);
            if (((Comparable)src[mid - 1]).compareTo(src[mid]) <= 0) {
                System.arraycopy(src, low, dest, destLow, length);
            } else {
                int i = destLow;
                int p = low;

                for(int q = mid; i < destHigh; ++i) {
                    if (q < high && (p >= mid || ((Comparable)src[p]).compareTo(src[q]) > 0)) {
                        dest[i] = src[q++];
                    } else {
                        dest[i] = src[p++];
                    }
                }

            }
        }
    }

    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

}

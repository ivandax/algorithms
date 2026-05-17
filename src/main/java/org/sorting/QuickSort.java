package org.sorting;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSort {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // Swaps a[i] and a[j]
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // partitions an array in 2 so that each part is greater or lower than element k
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            // find item on left to swap
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }
            // find item on right to swap
            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            //check if points cross and swap
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); //Swap with partitioning item
        return j;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        System.out.println("Quicksort");
        Integer[] arr = {6, 9, 5, 2, 4, 1, 8, 11, 7, 12, 3};
        sort(arr);
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(arr));
    }
}

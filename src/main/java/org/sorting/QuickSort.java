package org.sorting;

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

    public static void main(String[] args) {
        Integer[] arr = {6, 3, 5, 2, 4, 1};
    }
}

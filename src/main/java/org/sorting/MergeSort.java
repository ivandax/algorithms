package org.sorting;

public class MergeSort {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // Swaps a[i] and a[j]
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // first move all to aux
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    // uses n Log n compares at worst
    // uses 6 * n log n array accesses
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 5, 2, 4, 6};
        Integer[] aux = new Integer[6];
        merge(arr, aux, 0, 2, 5);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        Integer[] arr2 = {1, 5, 3, 7, 2, 4, 8, 6};
        sort(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
    }
}

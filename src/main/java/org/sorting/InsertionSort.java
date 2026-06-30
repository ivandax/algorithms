package sorting;

import edu.princeton.cs.algs4.StdRandom;

public class InsertionSort {

    // About 1/4 times n^2 so it's quadratic
    // Second proposition for sort()
    // If the array is already ordered, makes N-1 compares
    // But if it's in descending order, then it takes more time than selection sort.
    public static <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static class Shuffle {

        public static <T extends Comparable<T>> void run(T[] a) {
            int N = a.length;
            for(int i = 0; i < N; i++){
                int r = StdRandom.uniformInt(i + 1);
                exch(a, i, r);
            }
        }

        private static <T extends Comparable<T>> boolean less(T v, T w) {
            return v.compareTo(w) < 0;
        }

        private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
            T temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSum {

    private static int binarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;

            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int count_brute_force(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int count_binary_search(int[] a) {
        Arrays.sort(a);

        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int key = -(a[i] + a[j]);
                int foundIndex = binarySearch(a, key);
                if(foundIndex > -1){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        StdOut.println("This is working");
        int[] a = In.readInts(args[0]);
        StdOut.println("a" + Arrays.toString(a));

        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(count_brute_force(a));
        double time = stopwatch.elapsedTime();
        StdOut.println("Elapsed time for three sum with brute force: " + time);

        Stopwatch stopwatch2 = new Stopwatch();
        StdOut.println(count_brute_force(a));
        double time2 = stopwatch2.elapsedTime();
        StdOut.println("Elapsed time for three sum with binary search: " + time2);
    }
}

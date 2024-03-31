package org.stacksandqueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length == 2) {
            int k = Integer.parseInt(args[0]);
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
            while (!StdIn.isEmpty()) {
                String item = StdIn.readString();
                randomizedQueue.enqueue(item);
            }
            int count = 0;
            for (String item : randomizedQueue) {
                if (count < k) {
                    StdOut.println(item);
                }
            }
        } else {
            throw new IllegalArgumentException("Expected 2 args");
        }
    }
}

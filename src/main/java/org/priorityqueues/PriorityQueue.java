package org.priorityqueues;

import java.util.Arrays;

public class PriorityQueue<Key extends Comparable<Key>> {
    // Definition: We can add items to the queue
    // When removing items we either remove the max or min
    // This means the items are comparable

    // Based on binary trees
    // Completed binary trees are full at least except for the last level
    // This allows us to represent the binary tree as an array
    private Key[] pq; // store items at indices 1 to n
    private int N; // number of items in priority queue


    public PriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private void swim(int k) {
        // less(k/2, k) means the parent is less than the child
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if(j < N && less(j, j+1)) j++;
            // Check if the item is not less than it's largest children to break.
            if(!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // Inserts element at the end and swim it up in case it is larger than its parent
    public void insert(Key x){
        pq[++N] = x;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        return max;
    }

    public void simplePrint() {
        for(Key a : pq) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        System.out.println("Priority queue");
        PriorityQueue myPriorityQueue = new PriorityQueue(10);
        myPriorityQueue.insert(1);
        myPriorityQueue.insert(2);
        myPriorityQueue.insert(3);
        myPriorityQueue.insert(4);
        myPriorityQueue.simplePrint();
    }


}

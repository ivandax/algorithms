package org.stacksandqueues;

public interface Queue<T> {

    void enqueue(T item);

    T dequeue();

    boolean isEmpty();

    void printAsArray();
}

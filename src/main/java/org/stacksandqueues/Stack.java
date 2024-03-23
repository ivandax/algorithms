package org.stacksandqueues;

public interface Stack<T> {

    void push(T item);

    T pop();

    boolean isEmpty();

    void printAsArray();
}

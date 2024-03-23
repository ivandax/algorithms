package org.stacksandqueues;

public interface StackInterface<T> {

    void push(T item);

    T pop();

    boolean isEmpty();

    void printAsArray();
}

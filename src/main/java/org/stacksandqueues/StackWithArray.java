package org.stacksandqueues;

// Implements Stack using array instead of linked list
public class StackWithArray<T> implements StackInterface<T> {

    private T[] s;
    private int N = 0;

    public StackWithArray() {
        s = (T[]) new Object[1];
    }

    // We double size when array is 100% full
    @Override
    public void push(T item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    // We half size when array is 25% full
    @Override
    public T pop() {
        T item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public void printAsArray() {
        for (T item : s) {
            System.out.println(item);
        }
    }
}

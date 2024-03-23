package org.stacksandqueues;

// Implements Stack using array instead of linked list
public class StackOfStringsArray implements Stack<String> {

    private final String[] s;
    private int N = 0;

    public StackOfStringsArray(int capacity) {
        s = new String[capacity];
    }

    @Override
    public void push(String item) {
        s[N++] = item;
    }

    @Override
    public String pop() {
        String item = s[--N];
        s[N] = null;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public void printAsArray() {
        for(String item: s){
            System.out.println(item);
        }
    }
}

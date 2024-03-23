package org.stacksandqueues;

// Implements Stack using Linked list (node)
public class Stack<T> implements StackInterface<T> {

    private class Node {
        T item;
        Node next;
    }

    Node first = null;

    @Override
    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    @Override
    public T pop() {
        T item = first.item;
        first = first.next;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void printAsArray() {
        Node current = this.first;
        while(current != null){
            System.out.println(current.item);
            current = current.next;
        }
    }
}

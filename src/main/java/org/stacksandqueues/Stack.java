package org.stacksandqueues;

import java.util.Iterator;

// Implements Stack using Linked list (node)
public class Stack<T> implements Iterable<T> {

    private class Node {
        T item;
        Node next;
    }

    Node first = null;

    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public T pop() {
        T item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void printAsArray() {
        // This is possible because the Stack implements the iterator
        for(T item : this){
            System.out.println(item);
        }
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next(){
            T item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }
}

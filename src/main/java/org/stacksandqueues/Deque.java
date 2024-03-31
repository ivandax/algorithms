package org.stacksandqueues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;

        Node previous;
    }

    private Node first = null;
    private Node last = null;

    private int size = 0;

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
    }

    private void preventIfEmpty(
    ) {
        if (size() == 0) {
            throw new NoSuchElementException("Error: Deque is empty");
        }
    }


    public void addLast(Item item) {
        validateItem(item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public void addFirst(Item item) {
        validateItem(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (oldFirst != null) {
            oldFirst.previous = first;
        }
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        } else if (oldFirst.next == null) {
            last = oldFirst;
        }
        size++;
    }

    public Item removeFirst() {
        preventIfEmpty();
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        preventIfEmpty();
        Item item = last.item;
        last = last.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return item;
    }


    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Error: Deque is empty");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        Integer removedFirst = deque.removeFirst();
        Integer removedLast = deque.removeLast();
        StdOut.println(removedFirst);
        StdOut.println(removedLast);
    }
}

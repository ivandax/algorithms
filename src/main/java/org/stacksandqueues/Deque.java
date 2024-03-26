package org.stacksandqueues;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    Node first = null;
    Node last = null;


    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if(isEmpty()){
            last = first;
        }
        else {
            last = oldFirst;
        }
    }

    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        return item;
    }


    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }
}

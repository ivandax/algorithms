package org.stacksandqueues;

// Implements Stack using Linked list (node)
public class Queue<Item> {

    private class Node {
        Item item;
        Node next;
    }

    Node first = null;
    Node last = null;

    public void enqueue(Item item) {
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

    public Item dequeue() {
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

    public void printAsArray() {
        Node current = this.first;
        while(current != null){
            System.out.println(current.item);
            current = current.next;
        }
    }
}

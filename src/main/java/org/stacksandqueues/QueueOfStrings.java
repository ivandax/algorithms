package org.stacksandqueues;

// Implements Stack using Linked list (node)
public class QueueOfStrings implements Queue<String> {

    private static class Node {
        String item;
        Node next;
    }

    Node first = null;
    Node last = null;

    @Override
    public void enqueue(String item) {
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

    @Override
    public String dequeue() {
        String item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
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

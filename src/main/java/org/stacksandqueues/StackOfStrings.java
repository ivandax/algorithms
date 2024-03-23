package org.stacksandqueues;

public class StackOfStrings implements Stack<String> {

    private static class Node {
        String item;
        Node next;
    }

    Node first = null;

    @Override
    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    @Override
    public String pop() {
        String item = first.item;
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

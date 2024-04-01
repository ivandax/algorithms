package org.example.stacksandqueues;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.stacksandqueues.Deque;

import java.util.Iterator;


public class DequeTest {
    public static String dequeToString(Deque<Integer> deque) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    @Test()
    public void testAddingLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        assertEquals("[1]", dequeToString(deque));

        deque.addLast(2);
        assertEquals("[1,2]", dequeToString(deque));

        deque.addLast(3);
        assertEquals("[1,2,3]", dequeToString(deque));
    }

    @Test()
    public void testAddingFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        assertEquals("[1]", dequeToString(deque));

        deque.addFirst(2);
        assertEquals("[2,1]", dequeToString(deque));

        deque.addFirst(3);
        assertEquals("[3,2,1]", dequeToString(deque));
    }

    @Test()
    public void testRemovingLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals("[1,2,3]", dequeToString(deque));

        deque.removeLast();
        assertEquals("[1,2]", dequeToString(deque));

        deque.removeLast();
        assertEquals("[1]", dequeToString(deque));

        deque.removeLast();
        assertEquals("[]", dequeToString(deque));

    }

    @Test()
    public void testRemovingFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals("[1,2,3]", dequeToString(deque));

        deque.removeFirst();
        assertEquals("[2,3]", dequeToString(deque));

        deque.removeFirst();
        assertEquals("[3]", dequeToString(deque));

        deque.removeFirst();
        assertEquals("[]", dequeToString(deque));

    }

    @Test()
    public void testRemovingAndAdding() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals("[1,2,3]", dequeToString(deque));

        deque.addFirst(9);
        deque.addFirst(8);
        deque.addFirst(7);

        assertEquals("[7,8,9,1,2,3]", dequeToString(deque));

        deque.removeLast();
        deque.removeLast();
        deque.removeLast();

        assertEquals("[7,8,9]", dequeToString(deque));

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        assertEquals("[]", dequeToString(deque));
    }

    @Test()
    public void emptyToNonEmptyUsingFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals("[2,1]", dequeToString(deque));
        deque.removeFirst();
        assertEquals("[1]", dequeToString(deque));
        deque.removeFirst();
        assertEquals("[]", dequeToString(deque));
        deque.addLast(9);
        assertEquals("[9]", dequeToString(deque));
    }

    @Test()
    public void emptyToNonEmptyUsingLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals("[1,2]", dequeToString(deque));
        deque.removeLast();
        assertEquals("[1]", dequeToString(deque));
        deque.removeLast();
        assertEquals("[]", dequeToString(deque));
        deque.addFirst(9);
        assertEquals("[9]", dequeToString(deque));
    }

    @Test()
    public void longRandomTest() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals("[1,2]", dequeToString(deque));
        System.out.println(deque.iterator());
        deque.addFirst(3);
        assertEquals("[3,1,2]", dequeToString(deque));
        deque.addFirst(4);
        assertEquals("[4,3,1,2]", dequeToString(deque));
        deque.removeFirst();
        assertEquals("[3,1,2]", dequeToString(deque));
        deque.addLast(7);
        assertEquals("[3,1,2,7]", dequeToString(deque));
        deque.addFirst(11);
        assertEquals("[11,3,1,2,7]", dequeToString(deque));
        deque.removeLast();
        assertEquals("[11,3,1,2]", dequeToString(deque));
        deque.removeLast();
        assertEquals("[11,3,1]", dequeToString(deque));
        deque.removeLast();
        assertEquals("[11,3]", dequeToString(deque));
        deque.removeFirst();
        assertEquals("[3]", dequeToString(deque));
        deque.removeFirst();
        assertEquals("[]", dequeToString(deque));
    }

}

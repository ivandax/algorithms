
package org.example.stacksandqueues;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.stacksandqueues.ArrayQueue;

import java.util.Iterator;

public class ArrayQueueTest {
    public static String queueToString(ArrayQueue<Integer> queue) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Iterator<Integer> iterator = queue.iterator();
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
    public void testEnqueueAndDequeue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(1);
        assertEquals("[1]", queueToString(queue));

        queue.enqueue(2);
        assertEquals("[2,1]", queueToString(queue));

        queue.enqueue(3);
        assertEquals("[3,2,1]", queueToString(queue));

        queue.dequeue();
        assertEquals("[3,2]", queueToString(queue));

        queue.dequeue();
        assertEquals("[3]", queueToString(queue));

        queue.dequeue();
        assertEquals("[]", queueToString(queue));
    }

    @Test()
    public void testEnqueueAndDequeueLarge() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        assertEquals("[8,7,6,5,4,3,2,1]", queueToString(queue));

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("[8]", queueToString(queue));

        queue.enqueue(9);
        queue.enqueue(10);

        assertEquals("[10,9,8]", queueToString(queue));

        queue.dequeue();

        assertEquals("[10,9]", queueToString(queue));
    }

}

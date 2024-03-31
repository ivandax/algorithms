
package org.example.stacksandqueues;

import org.junit.Test;
import org.stacksandqueues.RandomizedQueue;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class RandomizedQueueTest {
    public static String queueToString(RandomizedQueue<Integer> queue) {
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

    public static ArrayList<Integer> queueToArray(RandomizedQueue<Integer> queue) {
        ArrayList<Integer> array = new ArrayList<>();
        for (Integer num : queue) {
            array.add(num);
        }
        return array;
    }

    @Test()
    public void testEnqueueAndDequeue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queueToArray(queue));
        //        assertEquals(5, queueToArray(queue).size());


        queue.dequeue();
        queue.dequeue();
        System.out.println(queueToArray(queue));

//        assertEquals(3, queueToArray(queue).size());

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println(queueToArray(queue));

//        assertEquals("[]", queueToString(queue));
    }

}

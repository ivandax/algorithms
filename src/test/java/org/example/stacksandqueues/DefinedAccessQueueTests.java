
package org.example.stacksandqueues;

import org.junit.Test;
import org.stacksandqueues.DefinedAccessQueue;

import java.util.ArrayList;
import java.util.Iterator;

public class DefinedAccessQueueTests {
    public static String queueToString(DefinedAccessQueue<Integer> queue) {
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

    public static ArrayList<Integer> queueToArray(DefinedAccessQueue<Integer> queue) {
        ArrayList<Integer> array = new ArrayList<>();
        for (Integer num : queue) {
            array.add(num);
        }
        return array;
    }

    @Test()
    public void testEnqueueAndDequeue() {
        DefinedAccessQueue<Integer> queue = new DefinedAccessQueue<>();
        queue.enqueue(1);
        queue.showState();
        System.out.println(queueToString(queue));

        queue.enqueue(2);
        queue.showState();
        System.out.println(queueToString(queue));

        queue.enqueue(3);
        queue.showState();
        System.out.println(queueToString(queue));

        queue.enqueue(4);
        queue.showState();
        System.out.println(queueToString(queue));

        queue.enqueue(5);
        queue.showState();
        System.out.println(queueToString(queue));

        queue.dequeue(1);
        queue.showState();
        System.out.println(queueToString(queue));

    }

}

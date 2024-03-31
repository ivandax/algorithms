package org.stacksandqueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Implements queue using array
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public RandomizedQueue() {
        capacity = 0;
        array = (Item[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
    }

    private void preventIfEmpty(
    ) {
        if (size() == 0) {
            throw new NoSuchElementException("Error: Randomized queue is empty");
        }
    }

    private void resize(int newCapacity) {
        Item[] newArray = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % capacity];
        }
        array = newArray;
        capacity = newCapacity;
        front = 0;
        rear = size - 1;
    }

    public void enqueue(Item item) {
        validateItem(item);
        if (size == capacity) {
            resize(2 * capacity + 1);
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    private int getRandomIndex() {
        if (rear == front) {
            return 0;
        }
        return StdRandom.uniformInt(rear - front) + front + 1;
    }

    public int size() {
        return size;
    }

    public Item dequeue() {
        preventIfEmpty();
        int indexToRemove = getRandomIndex();
        Item data;
        if (indexToRemove == front) {
            data = array[front];
            front = (front + 1) % capacity;
        } else if (indexToRemove == rear) {
            data = array[rear];
            rear = (rear - 1) % capacity;
        } else {
            data = array[indexToRemove];
        }
        size--;
        if (size <= capacity / 4) {
            resize(capacity / 2);
        }
        return data;
    }

    public Item sample() {
        preventIfEmpty();
        int index = getRandomIndex();
        return array[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private Item[] getCopy(Item[] array, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= array.length || startIndex > endIndex) {
            System.out.println(startIndex + " " + endIndex + " " + array.length);
            throw new IllegalArgumentException("Invalid indices.");
        }

        int length = endIndex - startIndex + 1;
        Item[] copiedArray = (Item[]) new Object[length];

        for (int i = startIndex; i <= endIndex; i++) {
            copiedArray[i - startIndex] = array[i];
        }

        return copiedArray;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int current;
        private int count;
        Item[] randomizedArray;

        public ReverseArrayIterator() {
            System.out.println("new iterator - front " + front + " size: " + size + " capacity " + capacity);
            current = (front + size - 1) % capacity;
            count = 0;
            if (array.length > 1) {
                randomizedArray = getCopy(array, front, rear);
            } else {
                randomizedArray = array;
            }
            StdRandom.shuffle(randomizedArray);
        }

        public boolean hasNext() {
            return count < size;
        }

        public Item next() {
            if (size() == 0) {
                throw new NoSuchElementException("Error: Randomized queue is empty");
            }
            System.out.println(current);

            Item item = randomizedArray[current];
            current = (current - 1 + capacity) % capacity;
            count++;
            return item;
        }


        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        Integer removedFirst = randomizedQueue.dequeue();
        Integer removedLast = randomizedQueue.dequeue();
        StdOut.println(removedFirst);
        StdOut.println(removedLast);
    }

}

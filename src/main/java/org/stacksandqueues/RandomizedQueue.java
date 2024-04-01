package org.stacksandqueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Implements queue using array
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;
    private int capacity;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
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
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void enqueue(Item item) {
        validateItem(item);
        if (size == array.length) {
            resize(2 * array.length);
        }
        array[size++] = item;
    }

    public int size() {
        return size;
    }

    public Item dequeue() {
        preventIfEmpty();
        int randomIndex = StdRandom.uniformInt(size);
        Item item = array[randomIndex];
        array[randomIndex] = array[size - 1];
        array[size - 1] = null;
        size--;
        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    public Item sample() {
        preventIfEmpty();
        int index = StdRandom.uniformInt(size);
        return array[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private Item[] getCopy(Item[] fromArray, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= fromArray.length || startIndex > endIndex) {
            throw new IllegalArgumentException("Invalid indices.");
        }

        int length = endIndex - startIndex + 1;
        Item[] copiedArray = (Item[]) new Object[length];

        for (int i = startIndex; i <= endIndex; i++) {
            copiedArray[i - startIndex] = fromArray[i];
        }

        return copiedArray;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int current;
        private Item[] randomizedArray;

        public ReverseArrayIterator() {
            current = 0;
            if (size > 1) {
                System.out.println(array.length);
                randomizedArray = getCopy(array, 0, size - 1);
                StdRandom.shuffle(randomizedArray);
            } else {
                randomizedArray = array;
            }

        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Error: Randomized queue is empty");
            }

            Item item = randomizedArray[current];
            current++;
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

package org.stacksandqueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Implements queue using array
public class ArrayQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ArrayQueue() {
        capacity = 0;
        array = (Item[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    private void resize(int newCapacity){
        Item[] newArray = (Item[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            // front + i to start where data starts... it could be like [null, null, 4, 2, 7, null]
            newArray[i] = array[(front + i) % capacity];
        }
        array = newArray;
        capacity = newCapacity;
        front = 0;
        rear = size - 1;
    }

    public void enqueue(Item item) {
        if(size == capacity){
            resize(2 * capacity + 1);
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item data = array[front];
        front = (front + 1) % capacity;
        size--;
        if (size <= capacity / 4) {
            resize(capacity / 2);
        }
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator ();
    }

    private class ReverseArrayIterator  implements Iterator<Item> {
        private int current;
        private int count;

        public ReverseArrayIterator() {
            current = (front + size - 1) % capacity;
            count = 0;
        }

        public boolean hasNext() {
            return count < size;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = array[current];
            current = (current - 1 + capacity) % capacity;
            count++;
            return item;
        }


        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }


}

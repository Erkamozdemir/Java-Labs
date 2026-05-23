package CS102_Sec3_Asgn6_Ozdemir_Erkam.Data_Structures;

import CS102_Sec3_Asgn6_Ozdemir_Erkam.Exceptions.EmptyQueueException;

public class Queue<T> {

    private T[] values;
    private int capacity;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public Queue(int capacity) {
        this.capacity = capacity;
        this.values = (T[]) new Object[capacity];
        this.rear = 0;
        this.front = 0;
        this.size = 0;
    }

    public void enqueue(T value) {
        if (isFull()) {
            resize();
        }
        values[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T value = values[front];
        values[front] = null;
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return values[front];
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        T[] newValues = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newValues[i] = values[(front + i) % capacity];
        }
        values = newValues;
        capacity = newCapacity;
        front = 0;
        rear = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}

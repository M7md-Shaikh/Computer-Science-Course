package application;

import java.util.Arrays;
public class List<T> {
    private Object[] elements;
    private int size;
    private int capacity;

    public List() {
        this.capacity = 10;
        this.elements = new Object[this.capacity];
        this.size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public void insert(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    public int size() {
        return size;
    }

    // Checks if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Clears the list
    public void clear() {
        size = 0; // Set size to 0
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            Object[] newElements = new Object[capacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }
}

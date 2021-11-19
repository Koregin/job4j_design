package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (this.container.length < this.size + 1) {
            this.container = Arrays.copyOf(this.container, this.container.length * 2);
        }
        this.container[this.size] = value;
        this.size++;
        this.modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        int removeIndex = Objects.checkIndex(index, this.size);
        T oldItem = this.container[removeIndex];
        this.container[removeIndex] = newValue;
        return oldItem;
    }

    @Override
    public T remove(int index) {
        int removeIndex = Objects.checkIndex(index, this.size);
        T removeItem = this.container[removeIndex];
        System.arraycopy(
                this.container,
                removeIndex + 1,
                this.container,
                removeIndex,
                this.size - removeIndex - 1
        );
        this.container[size - 1] = null;
        this.size--;
        this.modCount++;
        return removeItem;
    }

    @Override
    public T get(int index) {
        return this.container[Objects.checkIndex(index, this.size)];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }
}

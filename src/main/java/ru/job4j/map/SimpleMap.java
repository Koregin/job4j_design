package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count < capacity) {
            int index = indexFor(hash(key.hashCode()));
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        table = Arrays.copyOf(table, table.length * 2);
        capacity *= 2;
    }

    @Override
    public V get(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            if (table[index].key.equals(key)) {
                result = true;
            }
        }
        return result ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            if (table[index].key.equals(key)) {
                table[index] = null;
                result = true;
                count--;
                modCount++;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[point] == null && point < capacity - 1) {
                    point++;
                }
                return point < capacity - 1;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean result = contains(value);
        if (!result) {
            set.add(value);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

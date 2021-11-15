package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data[findEvenIndex(index)] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = findEvenIndex(index);
        return data[index++];
    }

    private Integer findEvenIndex(Integer index) {
        Integer newIndex = index;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                newIndex = i;
                break;
            }
        }
        return newIndex;
    }
}

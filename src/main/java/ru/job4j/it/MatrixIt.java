package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean res = false;
        for (int i = row; i < data.length; i++) {
            if (data[i].length != 0) {
                row = i;
                break;
            }
        }
        if (data[row].length != 0) {
            if (row < data.length - 1 || (row == data.length - 1 && column < data[row].length)) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int temprow = row;
        int tempcol = column;
        if (column == data[row].length - 1 && row + 1 < data.length) {
            row++;
            column = 0;
        } else {
            column++;
        }
        return data[temprow][tempcol];
    }
}

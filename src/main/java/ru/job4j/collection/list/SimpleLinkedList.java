package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    transient int modCount = 0;

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<E>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> result = first;
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int point = 0;
            private int checkcurr = 0;
            private int expectedModCount = modCount;
            private Node<E> result = first;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (point > checkcurr) {
                    result = result.next;
                    checkcurr = point;
                }
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                point++;
                return result.item;
            }
        };
    }
}

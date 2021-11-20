package ru.job4j.generics;

public class Node<T> {
    private T data;
    private Node<T> next;

    public Node() {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }
}

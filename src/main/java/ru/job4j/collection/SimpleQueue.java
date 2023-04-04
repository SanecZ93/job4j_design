package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int sizeOut = 0;
    private int sizeIn = 0;

    public T poll() {
        if (sizeIn == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeOut > 0) {
            sizeOut--;
            return out.pop();
        }
        while (sizeIn > 0) {
            out.push(in.pop());
            sizeIn--;
            sizeOut++;
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
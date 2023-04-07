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
        while (sizeIn > 0) {
            out.push(in.pop());
            sizeOut++;
            sizeIn--;
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        while (sizeOut > 0) {
            in.push(out.pop());
            sizeIn++;
            sizeOut--;
        }
        in.push(value);
        sizeIn++;
    }
}
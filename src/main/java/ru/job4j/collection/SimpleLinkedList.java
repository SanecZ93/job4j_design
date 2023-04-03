package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> last;

    @Override
    public void add(E value) {
        final Node<E> temp = last;
        final  Node<E> newNode = new Node<>(last, value, null);
        last = newNode;
        if (temp == null) {
            head = newNode;
        } else {
            temp.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current;
        if (index <= this.size / 2) {
            current = head;
            for (int a = 0; a < index; a++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int a = this.size - 1; a > index; a--) {
                current = current.prev;
            }
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                E item = null;
                if (hasNext()) {
                    item = current.item;
                    current = current.next;
                }
                return item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;

        }
    }
}
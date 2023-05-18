package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(key);
        if (count >= table.length * LOAD_FACTOR) {
            expand();
            index = indexFor(key);
        }
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(K key) {
        int hash = key == null ? 0 : hash(key.hashCode());
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity += 8;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> tab : table) {
            if (tab == null) {
                continue;
            }
            newTable[indexFor(tab.key)] = tab;
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = key == null ? 0 : indexFor(key);
        if (table[index] != null) {
            if ((key != null && table[index].key != null) && table[index].key.hashCode() == key.hashCode()
                    && (table[index].key == key || key.equals(table[index].key))) {
             value = table[index].value;
            }
        }
        if (key == null && table[0] != null && table[0].key == null) {
            value = table[0].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int index = key == null ? 0 : indexFor(key);
        boolean rsl = get(key) != null;
        if (rsl) {
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            private final int size = count;

            private int element = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return element < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[index] == null) {
                    index++;
                }
                element++;
                return table[index++].key;
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
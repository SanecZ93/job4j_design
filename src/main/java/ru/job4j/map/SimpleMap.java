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
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(hashCode(key)));
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hashCode(K key) {

        return key == null ? 0 : key.hashCode();
    }

    private int hash(int hashCode) {

        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {

        return hash & (capacity - 1);
    }

    private boolean equalsKey(K key) {
        int index = indexFor(hash(hashCode(key)));
        return  (table[index] != null
                && (key != null && table[index].key != null)
                && table[index].key.hashCode() == key.hashCode()
                && key.equals(table[index].key))
                || (key == null && table[0] != null && table[0].key == null);
    }

    private void expand() {
        capacity += 8;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> tab : table) {
            if (tab != null) {
                newTable[indexFor(hash(hashCode(tab.key)))] = tab;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(hashCode(key)));
        return equalsKey(key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = equalsKey(key);
        if (rsl) {
            int index = indexFor(hash(hashCode(key)));
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;

            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
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
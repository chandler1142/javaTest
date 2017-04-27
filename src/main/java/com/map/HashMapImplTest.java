package com.map;

import java.util.*;

/**
 * Created by i325622 on 3/31/17.
 */
public class HashMapImplTest<K, V> implements Map<K, V> {

    private final List table = new ArrayList();

    transient int size;

    class Node<K,V > implements Map.Entry<K,V> {
        K key;
        V value;
        int hashcode;
        Node<K,V> next;

        public Node(K key, V value, int hashcode, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hashcode = hashcode;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (hashcode != node.hashcode) return false;
            if (!key.equals(node.key)) return false;
            if (!value.equals(node.value)) return false;
            return next.equals(node.next);

        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + value.hashCode();
            result = 31 * result + hashcode;
            result = 31 * result + next.hashCode();
            return result;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}

package com.shpp.p2p.cs.vsamchenko.exam.assignment17;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * My implementation of the HashMap class. MyHashMap is built on an array nodes. The index is determined by a key hash.
 *
 * @param <K> key data type.
 * @param <V> value data type.
 */
public class MyHashMap<K, V> {

    /**
     * The number of elements in MyHashMap.
     */
    private int size;

    /**
     * Array size. The default value is 16, although it can be set when creating an object.
     */
    private int capacity = 16;

    /**
     * The value of filling the array. By default, it is 75% of the array size and is 12.
     */
    private int fillFactor = 12;

    /**
     * Default value of array filling. Set to the initial level.
     */
    private final int DEFAULT_CAPACITY;

    /**
     * An array in which nodes are stored.
     */
    private Node[] nodesArray;

    /**
     * Standard constructor.
     */
    public MyHashMap() {
        DEFAULT_CAPACITY = capacity;
        nodesArray = new Node[capacity];
    }

    /**
     * The constructor to which arguments are passed.
     *
     * @param capacity   array size.
     * @param fillFactor the value of filling the array.
     */
    public MyHashMap(int capacity, int fillFactor) {
        if (capacity < 1 || fillFactor < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.fillFactor = fillFactor;
        DEFAULT_CAPACITY = capacity;
        nodesArray = new Node[capacity];
    }

    /**
     * A method that adds a node to in MyHashMap.
     *
     * @param key   the value that serves as the key. Unique for each node.
     * @param value the value that is bound to the key.
     */
    public void put(K key, V value) {
        checkKey(key);
        int hash = key.hashCode();
        Node<K, V> newNode = new Node<>(hash, key, value, null);
        add(newNode);
        checkSizeForAdd();
    }

    /**
     * A method that returns a value by key.
     *
     * @param key the value that serves as the key. Unique for each node.
     * @return the value that is bound to the key. If there is no such key it return null.
     */
    public V get(K key) {
        checkKey(key);
        int hash = key.hashCode();
        int index = Math.abs(hash % capacity);
        Node actualNode = searchNeededNodeForAdd(index, key);
        if (actualNode == null || !actualNode.key.equals(key)) {
            return null;
        }
        return (V) actualNode.value;
    }

    /**
     * A method that shows whether MyHashMap has an element with such a key.
     *
     * @param key the key being checked.
     * @return boolean value si is such a key.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * A method that indicates whether MyHashMap has an element with that value.
     *
     * @param value the value being checked.
     * @return boolean value si is such a value.
     */
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (nodesArray[i] != null) {
                Node actualNode = nodesArray[i];
                if (Objects.equals(actualNode.value, value)) {
                    return true;
                }
                while (actualNode.next != null) {
                    actualNode = actualNode.next;
                }
            }
        }
        return false;
    }

    /**
     * A method that removes elements from MyHashMap.
     *
     * @param key the key of the element to be deleted.
     */
    public void remove(K key) {
        checkKey(key);
        int hash = key.hashCode();
        int index = Math.abs(hash % capacity);

        // Check if the wrong key was passed.
        if (get(key) == null) {
            return;
        }

        // Part of the method is when there is only one node in the cell that we need.
        if (nodesArray[index] != null && nodesArray[index].next == null && nodesArray[index].key.equals(key)) {
            nodesArray[index] = null;
            size--;
            checkSizeForRemove();
            return;
        }

        // Part of the method when the cell has many nodes and we need the first node.
        if (nodesArray[index] != null && nodesArray[index].next != null && nodesArray[index].key.equals(key)) {
            nodesArray[index] = nodesArray[index].next;
            size--;
            checkSizeForRemove();
            return;
        }

        // Part of the method is when there are many nodes in the cell and we need a node in the middle or end.
        Node actualNode = searchNeededNodeForRemove(index, key);
        // When the node is over.
        if (actualNode.next.next == null) {
            actualNode.next = null;
            size--;
            checkSizeForRemove();
            return;
        }
        // When the node is inside.
        actualNode.next = actualNode.next.next;
        size--;
        checkSizeForRemove();
    }

    /**
     * A method that completely cleanses MyHashMap.
     */
    public void clear() {
        nodesArray = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * A method that returns the number of elements in MyHashMap.
     *
     * @return the number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * A method that returns a list of all keys.
     *
     * @return list of all keys.
     */
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        Node[] temporaryArray = nodesArray.clone();

        for (int i = 0; i < capacity; i++) {
            if (temporaryArray[i] != null) {
                while (temporaryArray[i].next != null) {
                    result.add((K) temporaryArray[i].key);
                    temporaryArray[i] = temporaryArray[i].next;
                }
                result.add((K) temporaryArray[i].key);
            }
        }
        return result;
    }

    /**
     * A private method that adds a node to an array.
     *
     * @param newNode node to add.
     */
    private void add(Node<K, V> newNode) {
        int index = Math.abs(newNode.hash % capacity);
        if (nodesArray[index] == null) {
            nodesArray[index] = newNode;
            size++;
            return;
        }
        Node actualNode = searchNeededNodeForAdd(index, newNode.key);

        if (actualNode.key.equals(newNode.key)) {
            actualNode.value = newNode.value;
            return;
        }

        if (actualNode.next == null) {
            actualNode.next = newNode;
            size++;
            return;
        }
        actualNode.value = newNode.value;
    }

    /**
     * A method that searches for the desired node to add a new node.
     *
     * @param index in which cell is the node.
     * @param key   the value that serves as the key. Unique for each node.
     * @return the needed node.
     */
    private Node searchNeededNodeForAdd(int index, K key) {
        Node actualNode = nodesArray[index];
        if (actualNode == null) {
            return null;
        }

        while (actualNode.next != null) {
            if (actualNode.key.equals(key)) {
                return actualNode;
            }
            actualNode = actualNode.next;
        }
        return actualNode;
    }

    /**
     * A method that searches for the desired node to delete a node.
     *
     * @param index in which cell is the node.
     * @param key   the value that serves as the key. Unique for each node.
     * @return the previous node before the desired one.
     */
    private Node searchNeededNodeForRemove(int index, K key) {
        Node actualNode = nodesArray[index];

        while (!actualNode.next.key.equals(key)) {
            actualNode = actualNode.next;
        }
        return actualNode;
    }

    /**
     * A method that expands an array when it is filled.
     */
    private void checkSizeForAdd() {
        if (size > fillFactor) {
            capacity += capacity;
            fillFactor += fillFactor;
            arrayRestructuring(capacity, fillFactor, capacity / 2);
        }
    }

    /**
     * A method that reduces an array at its small size.
     */
    private void checkSizeForRemove() {
        if (size < fillFactor / 2 && capacity >= DEFAULT_CAPACITY) {
            capacity = capacity / 2;
            fillFactor = fillFactor / 2;
            arrayRestructuring(capacity, fillFactor, capacity * 2);
        }
    }

    /**
     * A method that rearranges an array to the desired size.
     *
     * @param capacity    right size array.
     * @param fillFactor  the required fill factor.
     * @param oldCapacity old size.
     */
    private void arrayRestructuring(int capacity, int fillFactor, int oldCapacity) {
        MyHashMap<Object, Object> newHashMap = new MyHashMap<>(capacity, fillFactor);

        for (int i = 0; i < oldCapacity; i++) {
            if (nodesArray[i] != null) { // when one element
                if (nodesArray[i].next == null) {
                    newHashMap.put(nodesArray[i].key, nodesArray[i].value);
                    nodesArray[i] = null;
                } else { // when many elements
                    Node actualNode = nodesArray[i];
                    while (actualNode.next.next != null) {
                        actualNode = actualNode.next;
                    }
                    newHashMap.put(actualNode.next.key, actualNode.next.value);
                    actualNode.next = null;
                }
                i--;
            }
        }
        nodesArray = newHashMap.getNodesArray();
    }

    /**
     * A method that returns an array of nodes.
     *
     * @return an array of nodes.
     */
    private Node[] getNodesArray() {
        return nodesArray;
    }

    /**
     * A method that checks whether a node has not been entered null.
     *
     * @param key the value that serves as the key. Unique for each node.
     */
    private void checkKey(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }


    /**
     * The class in which my node is presented in which data is stored.
     *
     * @param <K> key data type.
     * @param <V> value data type.
     */
    private static class Node<K, V> {

        /**
         * key hash code.
         */
        private final int hash;

        /**
         * element key.
         */
        private final K key;

        /**
         * element value.
         */
        private V value;

        /**
         * link to the next item.
         */
        private Node<K, V> next;

        /**
         * Node constructor.
         *
         * @param hash  hash key code.
         * @param key   node key.
         * @param value node value.
         * @param next  link to the next node.
         */
        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}

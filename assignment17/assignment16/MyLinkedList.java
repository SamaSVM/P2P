package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment16;

import java.util.Objects;

/**
 * My implementation of the LinkedList class. MyLinkedList is a list of objects.
 * Each node in the list has a link to the previous and next item.
 *
 * @param <T> data type.
 */
public class MyLinkedList<T> {

    /**
     * list size.
     */
    private int size;

    /**
     * the first item in the list.
     */
    private Node<T> first;

    /**
     * the last item in the list.
     */
    private Node<T> last;

    /**
     * A method that appends a node to the top of the list.
     *
     * @param element the value of the first node.
     */
    public void addFirst(T element) {
        if (element == null) {
            return;
        }
        if (first == null) {
            first = new Node<T>(null, element, null);
        } else {
            Node<T> newNode = new Node<T>(null, element, first);
            first.prev = newNode;
            first = newNode;
            if (size == 1) {
                last = first.next;
            }
        }
        size++;
    }

    /**
     * A method that appends a node to the end of a list.
     *
     * @param element the value of the last node.
     */
    public void addLast(T element) {
        if (element == null) {
            return;
        }
        switch (size) {
            case 0:
                addFirst(element);
                break;
            case 1:
                last = new Node<T>(first, element, null);
                first.next = last;
                size++;
                break;
            default:
                Node<T> newNode = new Node<T>(last, element, null);
                last.next = newNode;
                last = newNode;
                size++;
        }
    }

    /**
     * A method that adds a node to a list by a specific index.
     *
     * @param index   under what index to add a node.
     * @param element the value of the node that we add.
     */
    public void add(int index, T element) {
        if (element == null) {
            return;
        }
        checkIndex(index);
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }

        Node<T> actualNode = first;
        for (int i = 0; i < index; i++) {
            actualNode = actualNode.next;
        }
        Node<T> newNode = new Node<T>(actualNode.prev, element, actualNode);

        actualNode.prev.next = newNode;
        actualNode.prev = newNode;

        size++;
    }

    /**
     * A method that searches the list.
     *
     * @param element the object you are looking for.
     * @return the index under which the object is in the list, if the object does not exist then returns -1.
     */
    public int indexOf(T element) {
        Node<T> actualNode = first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, actualNode.value)) {
                return i;
            }
            actualNode = actualNode.next;
        }
        return -1;
    }

    /**
     * A method that completely clears the list.
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * The method that determines whether a transmitted item is listed.
     *
     * @param element object searched in the list.
     * @return if the object present in the list is returned true and if not then false.
     */
    public boolean contains(T element) {
        return indexOf(element) > -1;
    }

    /**
     * A method that replaces the value of the first node in the list with the passed value.
     *
     * @param element element that is set to the first.
     */
    private void setFirst(T element) {
        if (element == null) {
            return;
        }
        checkSize();
        first.value = element;
    }

    /**
     * A method that replaces the value of the last node in the list with the passed value.
     *
     * @param element element that is set to the last.
     */
    private void setLast(T element) {
        if (element == null) {
            return;
        }
        checkSize();
        if (size == 1) {
            setFirst(element);
            return;
        }
        last.value = element;
    }

    /**
     * A method that replaces the value of a particular list node with a passed value.
     *
     * @param index   under which index to change the node from the list.
     * @param element the value of the new node.
     */
    public void set(int index, T element) {
        if (element == null) {
            return;
        }
        checkIndex(index);
        if (index == size) {
            setLast(element);
            return;
        }
        if (index == 0) {
            setFirst(element);
            return;
        }

        Node<T> actualNode = first;
        for (int i = 0; i < index; i++) {
            actualNode = actualNode.next;
        }
        actualNode.value = element;
    }

    /**
     * A method that returns the value of the first node but does not delete it.
     *
     * @return value of the first node.
     */
    public T peekFirst() {
        checkSize();
        return first.value;
    }

    /**
     * A method that returns the value of the last node but does not delete it.
     *
     * @return value of the last node.
     */
    public T peekLast() {
        checkSize();
        return last.value;
    }

    /**
     * A method that returns the value of a node below the index but does not delete it.
     *
     * @param index node index which value to return.
     * @return node value.
     */
    public T peek(int index) {
        checkIndex(index);
        if(index == 0){
            return peekFirst();
        }
        if(index == size){
            return peekLast();
        }

        Node<T> actualNode = first;
        for (int i = 0; i < index; i++) {
            actualNode = actualNode.next;
        }
        return actualNode.value;
    }

    /**
     * A method that returns the value of the first node and deletes it.
     *
     * @return the value of the first node.
     */
    public T pollFirst() {
        checkSize();
        Node<T> actualNode = first;
        switch (size) {
            case 1:
                first = null;
                size--;
                return actualNode.value;
            default:
                first = first.next;
                first.prev = null;
                size--;
                return actualNode.value;
        }

    }

    /**
     * A method that returns the value of the last node and deletes it.
     *
     * @return the value of the last node.
     */
    public T pollLast() {
        checkSize();
        switch (size) {
            case 1:
                return pollFirst();
            default:
                Node<T> actualNode = last;
                last = last.prev;
                last.next = null;
                size--;
                return actualNode.value;
        }
    }

    /**
     * A method that returns the value of a node below a certain index and deletes it.
     *
     * @param index the index of the item you want to delete and return its value.
     * @return node value.
     */
    public T poll(int index) {
        checkIndex(index);
        if (size == 1) {
            return pollFirst();
        }
        Node<T> actualNode = first;
        for (int i = 0; i < index; i++) {
            actualNode = actualNode.next;
        }
        if (actualNode.next == null) {
            actualNode.prev.next = null;
            return pollLast();
        } else {
            if (actualNode.prev != null)
                actualNode.prev.next = actualNode.next;
        }
        if (actualNode.prev == null) {
            actualNode.next.prev = null;
            return pollFirst();
        } else {
            actualNode.next.prev = actualNode.prev;
        }
        size--;
        return actualNode.value;
    }

    /**
     * A method that returns the size of a list.
     *
     * @return size of a list.
     */
    public int size() {
        return size;
    }

    /**
     * A method that verifies that the index being transmitted does not exceed the size of the list.
     *
     * @param index the index being checked.
     */
    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * A method that checks to see if the list is empty.
     */
    private void checkSize() {
        if (size == 0) {
            throw new NullPointerException();
        }
    }

    /**
     * The class that implements the list node.
     *
     * @param <T> data type.
     */
    private static class Node<T> {
        /**
         * The value of the node.
         */
        private T value;
        /**
         * The next node element.
         */
        private Node<T> next;
        /**
         * Previous node element.
         */
        private Node<T> prev;

        /**
         * List node constructor.
         *
         * @param prev  previous node.
         * @param value node value.
         * @param next  the next node.
         */
        private Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}

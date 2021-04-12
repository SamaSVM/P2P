package com.shpp.p2p.cs.vsamchenko.exam.assignment16;

/**
 * My implementation of the Queue class. The class is implemented on the basis of MyLinkedList.
 * Limits its functionality. Implements the principle “first in — first out”.
 * The general principle of the class is that the elements are added to
 * the beginning of the queue and taken from the end.
 *
 * @param <T> data type.
 */
public class MyQueue<T> {
    /**
     * MyLinkedList in which the whole queue is stored.
     */
    private final MyLinkedList<T> list = new MyLinkedList<>();

    /**
     * A method that adds an element to the beginning of a queue.
     *
     * @param element element that is passed to the method.
     */
    public void add(T element) {
        list.addFirst(element);
    }

    /**
     * A method that returns an element from the end of a queue and deletes it.
     *
     * @return the last element.
     */
    public T poll() {
        return list.pollLast();
    }

    /**
     * A method that returns an element from the end of a queue.
     *
     * @return the last element.
     */
    public T peek() {
        return list.peekLast();
    }

    /**
     * A method that returns the size of a queue.
     * @return size of a queue.
     */
    public int size() {
        return list.size();
    }

    /**
     * The method that returns the boolean value or whether there are elements in the queue.
     * @return boolean value.
     */
    public boolean empty() {
        return list.size() > 0;
    }
}

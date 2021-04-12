package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment16;

import java.util.EmptyStackException;
import java.util.Objects;

/**
 * My implementation of the Stack class. The basic principle is "last in - first out", that is,
 * we add and take elements from the end of the queue.
 *
 * @param <T> data type.
 */
public class MyStack<T> {
    /**
     * Stack size.
     */
    private int size;

    /**
     * A stack node that references a previous item.
     */
    private Node<T> last;

    /**
     * A method that adds elements to a stack.
     *
     * @param element the value we put in the stack.
     */
    public void push(T element) {
        last = new Node<T>(size == 0 ? null : last, element);
        size++;
    }

    /**
     * A method that returns the last stack element and deletes it.
     *
     * @return last stack element.
     */
    public T pop() {
        checkSize();
        Node<T> actualNode = last;
        if (last.prev == null) {
            last = null;
        } else {
            last = last.prev;
        }
        size--;
        return actualNode.value;
    }

    /**
     * A method that returns the last element of the stack but does not delete it.
     *
     * @return last element.
     */
    public T peek() {
        checkSize();
        return last.value;
    }

    /**
     * A method that returns a boolean value or the stack has elements.
     * @return boolean value.
     */
    public boolean empty() {
        return size > 0;
    }

    /**
     * The method that returns the index as a far transmitted element is from the top of the stack.
     * If there is no such element returns -1.
     *
     * @param element element which method is looking for.
     * @return how far the element is from the top.
     */
    public int search(T element) {
        Node<T> actualNode = last;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, actualNode.value)) {
                return i + 1;
            }
            actualNode = actualNode.prev;
        }
        return -1;
    }

    /**
     * A method that returns the size of the stack.
     * @return size of the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Check whether the stack has elements.
     */
    private void checkSize() {
        if (size == 0)
            throw new EmptyStackException();
    }

    /**
     * The class that represents the node that makes up the stack.
     * The node retains the value and link to the previous node.
     *
     * @param <T> data type.
     */
    private static class Node<T> {
        /**
         * The value of the node.
         */
        private final T value;
        /**
         * Previous node element.
         */
        private final Node<T> prev;

        /**
         * The node in which the value and the link to the previous element are stored.
         *
         * @param prev link to the previous node.
         * @param value the value stored in the node.
         */
        private Node(Node<T> prev, T value) {
            this.prev = prev;
            this.value = value;
        }
    }
}

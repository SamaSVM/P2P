package com.shpp.p2p.cs.vsamchenko.exam.assignment17;

import com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment16.MyLinkedList;

/**
 * This class is my implementation of the PriorityQueue class. Sorting is based on Binary Heap.
 * All elements are stored in MyLinkedList.
 *
 * @param <T> data type.
 */
public class MyPriorityQueue<T> {

    /**
     * MyLinkedList in which all nodes are stored.
     */
    private MyLinkedList<Node> listQueue = new MyLinkedList<>();

    /**
     * A method that adds a passed item to a MyLinkedList.
     *
     * @param element value stored in MyLinkedList.
     */
    public void add(T element) {
        int weight = findWeight(element);
        listQueue.addLast(new Node(element, weight));
        sortListForAdd();
    }

    /**
     * The method that returns the element with the highest priority but does not delete it.
     *
     * @return node value.
     */
    public T peek() {
        checkSize();
        return (T) listQueue.peekFirst().value;
    }

    /**
     * The method that returns the element with the highest priority and deletes it.
     *
     * @return node value.
     */
    public T poll() {
        checkSize();
        Node actualNode = listQueue.peekFirst();

        if (listQueue.size() == 1) {
            listQueue.pollFirst();
        }

        if (listQueue.size() > 1) {
            listQueue.set(0, listQueue.pollLast());
            sortListForPoll(1);
        }
        return (T) actualNode.value;
    }

    /**
     * A method that checks to see if a passed item is in the queue.
     *
     * @param element node value.
     * @return boolean value whether there is a passed element in the queue.
     */
    public boolean contains(T element) {
        return listQueue.contains(new Node(element, findWeight(element)));
    }


    /**
     * A method that returns the size of a queue.
     *
     * @return the size of a queue.
     */
    public int size() {
        return listQueue.size();
    }

    /**
     * The method that returns whether the queue is empty.
     *
     * @return boolean value or queue is empty.
     */
    public boolean isEmpty() {
        return listQueue.size() == 0;
    }

    /**
     * A method that completely clears the queue.
     */
    public void clear() {
        listQueue = new MyLinkedList<>();
    }

    /**
     * @param element
     * @return
     */
    private int findWeight(T element) {
        if (element instanceof String) {
            Character firstChar = ((String) element).charAt(0);
            return firstChar.hashCode();
        }
        return element.hashCode();
    }

    /**
     * A method that sorts the queue from the last element to the first.
     */
    private void sortListForAdd() {
        if (listQueue.size() == 1) {
            return;
        }
        int index = listQueue.size();
        while (index != 1) {
            if (listQueue.peek(index - 1).weight < listQueue.peek(index / 2 - 1).weight) {
                Node interimNode = listQueue.peek(index - 1);
                listQueue.set(index - 1, listQueue.peek(index / 2 - 1));
                listQueue.set(index / 2 - 1, interimNode);
            }
            index = index / 2;
        }
    }

    /**
     * A method that sorts the queue from the specified item to the last.
     *
     * @param index element from which to sort.
     */
    private void sortListForPoll(int index) {
        if (index * 2 <= listQueue.size() &&
                listQueue.peek(index - 1).weight > listQueue.peek(index * 2 - 1).weight) {
            sortNodes(index - 1, index * 2 - 1);
            sortListForPoll(index * 2);
        }

        if (index * 2 + 1 <= listQueue.size() &&
                listQueue.peek(index - 1).weight > listQueue.peek(index * 2).weight) {
            sortNodes(index - 1, index * 2);
            sortListForPoll(index * 2 + 1);
        }
    }

    /**
     * A method that replaces nodes with each other.
     *
     * @param root the root of the node.
     * @param heir the knot of the node.
     */
    private void sortNodes(int root, int heir) {
        Node interimNode = listQueue.peek(root);
        listQueue.set(root, listQueue.peek(heir));
        listQueue.set(heir, interimNode);
    }

    /**
     * A method that checks whether the queue size is not 0.
     */
    private void checkSize() {
        if (listQueue.size() == 0) {
            throw new NullPointerException();
        }
    }

    /**
     * The class that implements the list node.
     *
     * @param <T> data type.
     */
    private static class Node<T> implements Comparable<Node> {

        /**
         * The value stored in the node.
         */
        private T value;

        /**
         * Weight value.
         */
        private int weight;

        /**
         * A constructor that creates node-type objects.
         *
         * @param value
         * @param weight
         */
        private Node(T value, int weight) {
            this.weight = weight;
            this.value = value;
        }

        /**
         * A method that allows you to compare two objects of this class.
         *
         * @param element The element with which the node is compared.
         * @return if the transmitted element is higher then the value is negative.
         */
        @Override
        public int compareTo(Node element) {
            return element.weight - weight;
        }
    }
}

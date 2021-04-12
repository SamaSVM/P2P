package com.shpp.p2p.cs.vsamchenko.exam.assignment16;

import java.util.Arrays;
import java.util.Objects;

/**
 * My implementation of the ArrayList class. This class implements an extensible array and has additional functionality.
 *
 * @param <T> data type.
 */
public class MyArrayList<T> {
    /**
     * An array in which objects are stored.
     */
    private Object[] elements;

    /**
     * List size.
     */
    private int size;

    /**
     * The initial value of the array.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * A constructor that creates a new array of size "DEFAULT_CAPACITY".
     */
    public MyArrayList() {
        elements = new Object [DEFAULT_CAPACITY];
    }

    /**
     * A constructor that creates a new array with the specified size.
     * @param sizeArray array size.
     */
    public MyArrayList(int sizeArray) {
        elements = new Object [sizeArray];
    }

    /**
     * A method that adds an element to the end of a list.
     * @param element the element being transmitted.
     */
    public void add(T element) {
        if (size == elements.length){
            increaseCapacity();
        }
        elements[size] = element;
        size++;
    }

    /**
     * A method that adds an element to an array for a specific index.
     * @param index array index where to add an element.
     * @param element which object to add.
     */
    public void add(int index, T element) {
        checkIndex(index);
        if (size + 1 >= elements.length){
            increaseCapacity();
        }
        for (int i = 0; i < size - index + 1; i++) {
            elements[size - i + 1] = elements[size - i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * A method that removes an object from a sheet under a specific index.
     * @param index the index of the item to be deleted.
     */
    public void remove(int index) {
        checkIndex(index);
        arrayReduction(index);
    }

    /**
     * A method that removes a specific object from the list.
     * @param o object to delete.
     */
    public void remove(Object o){
        int index = -1;
        for(int i = 0; i < size; i++){
            if(elements[i].equals(o)){
                index = i;
            }
        }
        if(index == -1){
            throw new NullPointerException();
        }
        arrayReduction(index);
    }

    /**
     * A method that completely clears the list.
     */
    public void clear(){
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * A method that replaces a letter item with another.
     * @param index the index of the element being replaced.
     * @param element object to be replaced.
     */
    public void set(int index, T element){
        checkIndex(index);
        elements[index] = element;
    }

    /**
     * A method that returns an element of an array under a certain index.
     * @param index array element index.
     * @return array element.
     */
    public T get(int index){
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * The method that determines whether the transmitted object is present in the array.
     * @param element the object you are looking for.
     * @return boolean value or object present.
     */
    public boolean contains(T element){
        return indexOf(element) > -1;
    }

    /**
     * A method that returns the size of an array.
     * @return size array.
     */
    public int size(){
        return size;
    }

    /**
     * A method that determines whether an array is empty.
     * @return boolean value whether there are objects in the array.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * A method that determines the position of an object that is passed in an array.
     * @param element the object they are looking for.
     * @return if the object is present then its index, if not then -1.
     */
    public int indexOf(T element){
        for (int i = 0; i < size; i++) {
            if(Objects.equals(element, elements[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * A method that extends the original array if it runs out of space.
     */
    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, (int) (size * 1.5 + 1)) ;
    }

    /**
     * A method that shifts all elements by one to the left.
     * @param index from which place to start the shift.
     */
    private void arrayReduction(int index) {
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size] = null;
        size--;
    }

    /**
     * A method that checks whether an index is out of range.
     * @param index the index being checked.
     */
    private void checkIndex(int index){
        if(index > size || index < 0 || size <= 0){
            throw new IndexOutOfBoundsException();
        }
    }
}

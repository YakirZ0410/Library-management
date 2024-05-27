//Author: Yakir Zindani
//ID: 207872664
package HW1;
/**
 * Represents a singly data structure.
 *
 * @param <T> the type of elements stored in the data structure **/
public class DataStructure<T> {
    private Object array[];
    private int capacity = 10;
    private int size = 0;
    /**
     * Default constructor for the HW1.DataStructure class.
     */
    public DataStructure() {
        array = new Object[capacity];
    }
    /**
     * Inserts a new node in the index location of the data structure.
     * This should be done in O(n) operations.
     *
     * @param data the data to be inserted
     * @param index the index the item should be inserted at.
     */
    public void add(T data, int index) {
        if (index < 0)
            System.out.println("The index should be positive");
        else
        {
            while (size() == capacity || index > size())
            {
                doublingArray();
            }

            array[size() + 1] = array [size()];

            for (int i = size(); i > index; i--)
                array[i] = array[i - 1];

            array[index] = data;
            size++;
        }
    }
    /**
     * Inserts a new node at the end of the data structure.
     * @param data the data to be inserted
     */
    public void addToEnd(T data) {

        if (size == capacity) {
            doublingArray();
        }
        array[size++] = data;
    }
    /**
     * Deletes a node with the given data from the data structure.
     * This should be done in O(n) operations.
     * @param data the data to be deleted
     */
    public void delete(T data) {

        int location = 0;
        boolean flag = false;

        for (int i = 0; i < size() ; i++)
        {
            if (array[i] == data)
            {
                array[i] = null;
                location = i;
                flag = true;
                break;
            }
        }
        if (flag)
        {
            for (int i = location; i < size() ; i++)
            {
                array[i] = array [i+1];
            }
            --size;
        }
        else System.out.println("the data does not exist");
    }
    /**
     * Searches for a node with the given data in the data structure.
     * This should be done in O(n) operations.
     * @param data the data to be searched
     * @return true if the data is found, false otherwise
     */
    public boolean contains(T data) {

        for (int i = 0; i < size(); i ++)
            if (this.array[i].equals(data))
                return true;

        return false;
    }
    /**
     * Gets the size of the data structure.
     *
     * @return the number of elements in the data structure
     */
    public int size() {

        return size;
    }
    /**
     * Doubles the capacity of the array.
     * If the current capacity is not enough to accommodate new elements,
     * this method will be called to increase the capacity.
     * This method copies the existing elements to a new array with double the capacity.
     * This operation has linear complexity.
     */
    private void doublingArray() {

        Object[] newArray = new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity *= 2;
    }
    /**
     * Retrieves the element at the specified index from the array.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public Object getData(int index) {
        if (index >= size()) {
            System.out.println("Index out of bounds");
            return null;
        }
        else if (index < 0) {
            System.out.println("The index should be positive");
            return  null;
        }
        return array[index];
    }
    /**
     * Return the string value of the elements in a data structure from beginning to end, separated by commas
     */
    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < size(); i++) {
            str += array[i];
            if (i < size() - 1) {
                str += ", ";
            }
        }
        str += "]";
        return str;
    }
}
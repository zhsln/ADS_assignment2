import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private T[] array;

    /**
     * Size of arraylist.
     */
    private int size;

    /**
     * Argument constructor.
     */
    public MyArrayList() {
        array = (T[]) new Object[5];
        size = 0;
    }

    /**
     * Method to add element to the arraylist.
     * Adds elements to the end by default.
     * @param item specific element.
     */
    @Override
    public void add(T item) {
        if (size >= array.length)
            increaseBuffer();

        array[size++] = item;
    }

    /**
     * Method to replace element with specific index to another element.
     * @param index specific index.
     * @param item new element.
     */
    @Override
    public void set(int index, T item) {
        checkIndex(index);
        array[index] = item;
    }

    /**
     * Method to add new element to the specific index in arraylist.
     * @param index index of the element in array.
     * @param item specific element.
     */
    @Override
    public void add(int index, T item) {
        checkIndex(index);

        T[] newArray = (T[]) new Object[array.length + 1]; // increasing size of array by 1 unit.

        for (int i = 0; i < index; i++) // Coping elements before the index.
            newArray[i] = array[i];

        newArray[index] = item;

        for (int i = 0; i < size; i++) // Coping elements after the index.
            newArray[i + 1] = array[i];

        size++;
        array = newArray; // Changing reference.
    }

    /**
     * Method to add new element to the first index of the arraylist.
     * @param item specific element.
     */
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    /**
     * Method to add new element to the end of the arraylist.
     * @param item specific element.
     */
    @Override
    public void addLast(T item) {
        add(size, item);
    }

    /**
     * Method to increase buffer of arraylist by 2 times.
     */
    private void increaseBuffer() {
        T[] newArray = (T[]) new Object[array.length*2]; // increasing size of array.
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];  // Coping elements.

        array = newArray; // Changing reference.
    }

    /**
     * Method to retrieve element in specific index of the arraylist.
     * @param index
     * @return element with specific index
     */
    @Override
    public T get(int index) {
        checkIndex(index);

        return array[index];
    }

    /**
     * Method to check does arraylist have specific index or not.
     * @param index specific index.
     * @throws IndexOutOfBoundsException if index out of bounds of arraylist.
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds.");
    }

    /**
     * This method check is index correct to add element there.
     * Method was created, because checkIndex() is not relevant for checking is it possible to add element,
     * because of the condition ( index >= size).
     * @param index index of the element of the array.
     * @throws IndexOutOfBoundsException if index out of bounds of arraylist.
     */
    private void checkIndexToAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
    }

    /**
     * Method to check is arraylist empty or not.
     * @throws NoSuchElementException if arraylist is empty.
     */
    private void isEmpty() {
        if (size == 0)
            throw new NoSuchElementException("Arraylist is empty");
    }

    /**
     * Method to retrieve first element of the arraylist.
     * @return first element of the arraylist.
     */
    @Override
    public T getFirst() {
        isEmpty();

        return (T) array[0];
    }

    /**
     * Method to retrieve last element of the arraylist.
     * @return last element of the arraylist.
     */
    @Override
    public T getLast() {
        isEmpty();

        return (T) array[size - 1];
    }

    /**
     * Method to remove element with specific index.
     * @param index specific index.
     */
    @Override
    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) // Shift elements to the left.
            array[i] = array[i + 1];

        array[size - 1] = null; // Decreasing size of array.
        size--;
    }

    /**
     * Method to remove first element of the arraylist.
     */
    @Override
    public void removeFirst() {
        isEmpty();

        remove(0);
    }

    /**
     * Method to remove last element of the arraylist.
     */
    @Override
    public void removeLast() {
        isEmpty();

        remove(size);
    }

    /**
     * Method to sort elements in arraylist in ascending order, using bubble sort.
     */
    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i - 1; j++)
                if (((Comparable<T>) array[j]).compareTo((T) array[j + 1]) > 0) {
                    T temp = (T) array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }

    /**
     * Method to find first index of specific object.
     * @param object specific object
     * @return index or -1 if object is not found.
     */
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++)
            if (array[i].equals(object))
                return i;

        return -1;
    }

    /**
     * Method to find last index of specific object.
     * @param object specific object
     * @return index or -1 if object is not found.
     */
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--)
            if (array[i].equals(object))
                return i;

        return -1;
    }

    /**
     * Method to check does object exists or not.
     * @param object specific object
     * @return True or False.
     */
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * Method to copy elements of arraylist to array.
     * @return new array.
     */
    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];


        for (int i = 0; i < size; i++) // Copy elements to the new array.
            newArray[i] = array[i];

        return newArray;
    }


    /**
     * Method to clear arraylist (make it empty).
     */
    @Override
    public void clear() {
        isEmpty();

        array= (T[]) new Object[5];
        size = 0;
    }

    /**
     * Method to find size of arraylist.
     * @return size of arraylist
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }
    private class MyIterator implements Iterator<T>{
        private int cursor = 0;

        /**
         * Method to check is next element exist or not.
         * @return True if exists or False if not.
         */
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * Method to
         * @return
         */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return (T) array[cursor++];
        }
    }
}

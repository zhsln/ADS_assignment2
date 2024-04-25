import java.util.*;

/**
 * Class of double linked list.
 * @param <E>
 */
public class MyLinkedList<E> implements MyList<E> {
    private static class MyNode<E> {
        E data;
        MyNode<E> next;
        MyNode<E> prev;

        MyNode(E data) {
            setData(data);
            this.next = null; // Connection with next and previous nodes is null by default.
            this.prev = null;
        }

        /**
         * Data setter.
         * @param data some data.
         */
        private void setData(E data) {
            this.data = data;
        }
    }

    /**
     * Entry point.
     */
    private MyNode<E> head;

    /**
     * Last node.
     */
    private MyNode<E> tail;

    /**
     * Number of elements in the list.
     */
    private int size;

    /**
     * No parameter argument.
     */
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Node getter.
     * @param index index of node.
     * @return node.
     */
    private MyNode<E> getNote(int index) {
        if (index < (size >> 1)) {
            MyNode<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;

            return x;
        } else {
            MyNode<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;

            return x;
        }
    }

    /**
     * Node getter.
     * @param index index of node.
     * @return node.
     * @throws IndexOutOfBoundsException if index is out of bounds.
     */
    private MyNode<E> getNode(int index) {
        checkElementIndex(index);

        MyNode<E> current;
        if (index < size / 2) { // If index is from 1st part of list, we search from the start.
            current = head;
            for (int i = 0; i < index; i++) // To retrieve value from node, we should get values of previous nodes.
                current = current.next;

        } else { // If index is from 2nd part of list, we search from the end.
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.prev;

        }

        return current;
    }

    /**
     * Method to add new element to the end when tail is stored.
     * @param item element.
     */
    @Override
    public void add(E item) {
        addLast(item); // Add new element to the end by default.
    }

    /**
     * Method to change some node to node with new element.
     * @param index index of node.
     * @param item new element.
     * @throws IndexOutOfBoundsException if index is out of bounds.
     */
    @Override
    public void set(int index, E item) {
        MyNode<E> node = getNode(index);
        node.data = item;
    }

    /**
     * Method to add element to specific index;
     * @param index preferable index.
     * @param item an element.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    @Override
    public void add(int index, E item) {
        checkElementIndex(index);

        if (index == 0) // 0 is the index of first element.
            addFirst(item);
        else if (index == size) // index of last element equals to value of size.
            addLast(item);
        else {
            MyNode<E> newNode = new MyNode<>(item);
            MyNode<E> current = getNode(index);
            MyNode<E> prevNode = current.prev;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = current;
            current.prev = newNode;
            size++;
        }
    }

    /**
     * Method to add element at the beginning of linked list.
     * @param item element.
     */
    @Override
    public void addFirst(E item) {
        if (head == null) {
            add(item);

            return;
        }

        MyNode<E> newNode = new MyNode<>(item); // 1) Item became a node.
        newNode.next = head; // 2) Link node with head.
        head = newNode; // 3) New node became a head.
        size++; // 4) Definitely, size is increased.
    }

    /**
     * Method to add element to the end of the linked list.
     * @param item element.
     */
    @Override
    public void addLast(E item) {
        MyNode<E> newNode = new MyNode<>(item);

        if (head == null)
            head = tail = newNode; // I found this from the lecture.
        else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    /**
     * Method to retrieve element from node.
     * @param index index of element
     * @return data (element) from node.
     */
    @Override
    public E get(int index) {
        MyNode<E> current = head;

        for (int i = 0; i < index; i++)
            current = current.next;

        return current.data;
    }

    /**
     * Method to get first element from linked list.
     * @return data (element) from node.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    @Override
    public E getFirst() {
        checkElementIndex(0);
        return get(0);
    }

    /**
     * Method to get last element from linked list.
     * @return data (element) from node.
     * @throws NoSuchElementException if tail of the linked list is null.
     */
    @Override
    public E getLast() {
        final MyNode<E> last = tail;
        if (last == null)
            throw new NoSuchElementException();
        return last.data;
    }

    /**
     * Method to remove node with specific index.
     * @param index specific index of node.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    @Override
    public void remove(int index) {
        checkElementIndex(index);
        if (getNode(index) != null)
            unlink(getNode(index));
    }

    /**
     * Method to unlink node from next and previous.
     * @param node node.
     */
    private void unlink(MyNode<E> node) {
        final E element = node.data;
        final MyNode<E> next = node.next;
        final MyNode<E> prev = node.prev;

        if (prev == null)
            head = next;
        else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null)
            tail = prev;
        else {
            next.prev = prev;
            node.next = null;
        }

        node.data = null;
        size--;
    }

    /**
     * Method to remove first element in linked list. Method does not return deleted element.
     * @throws IndexOutOfBoundsException if head of linked list is null.
     */
    @Override
    public void removeFirst() {
        final MyNode<E> first = head;
        if (first == null)
            throw new NoSuchElementException();

        unlinkFirst(first);
    }

    private void unlinkFirst(MyNode<E> first) {
        final E element = first.data;
        final MyNode<E> next = first.next;
        first.data = null;
        first.next = null;
        first = next;
        if (next == null)
            tail = null;
        else
            next.prev = null;
        size--;
    }

    /**
     * Method to remove last element in linked list.
     * @throws NoSuchElementException if tail of the linked list is null.
     */
    @Override
    public void removeLast() {
        final MyNode<E> last = tail;
        if (last == null)
            throw new NoSuchElementException();

        unlinkLast(last);
    }

    private void unlinkLast(MyNode<E> last) {
        final E element = last.data;
        final MyNode<E> prev = last.prev;
        last.data = null;
        last.prev = null; // help GC
        last = prev;
        if (prev == null)
            head = null;
        else
            prev.next = null;
        size--;
    }

    /**
     * Method to sort linked list by bubble sort.
     * @throws UnsupportedOperationException if elements are not comparable.
     */
    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (compare(getNode(j).data, getNode(j + 1).data) > 0) {
                    E temp = getNode(j).data;
                    getNode(j).data = getNode(j + 1).data;
                    getNode(j + 1).data = temp;
                }
            }
        }
    }

    /**
     * Method to compare two nodes.
     * @param a first node.
     * @param b second node.
     * @throws UnsupportedOperationException if elements are not comparable.
     * @return one of the nodes, which is bigger.
     */
    private int compare(E a, E b) {
        if (a instanceof Comparable && b instanceof Comparable) {
            return ((Comparable<E>) a).compareTo(b);
        } else {
            throw new UnsupportedOperationException("Elements are not comparable");
        }
    }

//    @Override
//    public void sort(Comparator<? super E> comparator) {
//        Object[] a = this.toArray();
//        Arrays.sort(a, (Comparator) c);
//        ListIterator<E> i = this.listIterator();
//        for (Object e : a) {
//            i.next();
//            i.set((E) e);
//        }
//    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index out of bounds.");
    }

    /**
     *  Method tells if the argument is the index of an existing element.
     * @return True if exists and False if not.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Method to check is linked list empty or not
     * @throws NoSuchElementException if empty.
     */
    private void isEmpty() {
        throw new NoSuchElementException("Linked list is empty");
    }

    /**
     * Method to find index of specific element.
     * @param object an element.
     * @return index of element if found and -1 if not.
     */
    @Override
    public int indexOf(Object object) {
        int index = 0;
        MyNode<E> current = head;
        while (current != null) {
            if (object.equals(current.data))
                return index;

            current = current.next;
            index++;
        }

        return -1;
    }

    /**
     * Method to find index of last repeating specific element by searching it from the end of the linked list.
     * @param object an element.
     * @return last index of element if found and -1 if not found.
     */
    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        MyNode<E> current = tail;
        while (current != null) {
            if (object.equals(current.data))
                return index;

            current = current.prev;
            index--;
        }

        return -1;
    }

    /**
     * Method to check is specific object exists in linked list or not.
     * @param object specific element.
     * @return True if exists and False if not.
     */
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * Method to convert linked list to array.
     * @return array with elements from linked list.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyNode<E> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }

        return array;
    }

    /**
     * Method to clear linked list.
     */
    @Override
    public void clear() {
        for (MyNode<E> x = head; x != null; ) {
            MyNode<E> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * Size getter.
     * @return size of linked list.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }
    private class MyIterator implements Iterator<E> {
        MyNode<E> cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            E nextItem = cursor.data;
            cursor = cursor.next;
            return nextItem;
        }
    }
}

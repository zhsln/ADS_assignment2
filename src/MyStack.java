import java.util.NoSuchElementException;

public class MyStack<T> {
    private MyList<T> list;

    public MyStack() {
        this.list = new MyArrayList<>();
    }

    /**
     * Adds the element at the top of the stack.
     * @param item an element.
     */
    public T push(T item) {
        list.add(item);

        return item;
    }

    /**
     * Method that retrieves and deletes the topmost element of the stack
     * @return deleted element.
     */
    public T pop() {
        if (empty())
            throw new NoSuchElementException("Stack is empty");

        T item = list.getLast();
        list.removeLast();

        return item;
    }

    /**
     * Method to retrieve last element of the stack.
     * @return a reference to the topmost element of the stack.
     */
    public T peek() {
        if (empty())
            throw new NoSuchElementException("Stack is empty");

        return list.getLast();
    }

    /**
     * Returns whether the stack is empty
     * @return True if empty and False if not.
     */
    public boolean empty() {
        return list.size() == 0;
    }

    /**
     * Method to search specific element in the stack.
     * @param o specific element.
     * @return index of specific element.
     */
    public int search(Object o) {
        int i = list.lastIndexOf(o);

        if (i >= 0)
            return list.size() - i;

        return -1;
    }

    /**
     * Method to clear stack.
     */
    public void clear() {
        list.clear();
    }
}
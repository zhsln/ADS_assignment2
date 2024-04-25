import java.util.NoSuchElementException;

public class MyQueue<E> {
    private MyLinkedList<E> list;

    public MyQueue() {
        this.list = new MyLinkedList<>();
    }

    public E enqueue(E item) {
        list.addLast(item);

        return item;
    }

    public E dequeue() {
        E item = peek();
        list.removeFirst();

        return item;
    }

    /**
     * Method to remove an element.
     * @param index index of the element.
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Method to retrieve reference of the front element.
     * @return Returns a reference to the front element of the queue
     */
    public E peek() {
        if (empty())
            throw new NoSuchElementException("Queue is empty");

        return list.getFirst();
    }

    /**
     * Method that returns whether the queue is empty
     * @return True or false.
     */
    public boolean empty() {
        return list.size() == 0;
    }
}

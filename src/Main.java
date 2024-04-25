import java.util.*;

public class Main {
    public static void main(String[] args) {
        testMyArrayList();

        System.out.println();

        testMyLinkedList();
    }

    private static void testMyArrayList() {
        MyList<Integer> arrayList = new MyArrayList<>();

        // Adding elements
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        // Testing methods for getting elements
        System.out.println("ArrayList: " + Arrays.toString(arrayList.toArray()));
        System.out.println("First element: " + arrayList.getFirst());
        System.out.println("Last element: " + arrayList.getLast());
        System.out.println("Element at index 1: " + arrayList.get(1));

        // Testing removal methods
        arrayList.remove(1);
        System.out.println("Removing element at index 1: " + Arrays.toString(arrayList.toArray()));
        arrayList.removeFirst();
        System.out.println("Removing first element: " + Arrays.toString(arrayList.toArray()));
        arrayList.removeLast();
        System.out.println("Removing last element: " + Arrays.toString(arrayList.toArray()));

        // Testing set and size methods
        arrayList.add(0, 5);
        arrayList.set(0, 10);
        System.out.println("Result of setting elements: " + Arrays.toString(arrayList.toArray()));
        System.out.println("Size: " + arrayList.size());
    }

    private static void testMyLinkedList() {
        MyList<Integer> linkedList = new MyLinkedList<>();

        // Adding elements
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        // Testing methods for getting elements
        System.out.println("LinkedList: " + Arrays.toString(linkedList.toArray()));
        System.out.println("First element: " + linkedList.getFirst());
        System.out.println("Last element: " + linkedList.getLast());
        System.out.println("Element at index 1: " + linkedList.get(1));

        // Testing removal methods
        linkedList.remove(1);
        System.out.println("Removing element at index 1: " + Arrays.toString(linkedList.toArray()));
        linkedList.removeFirst();
        System.out.println("Removing first element: " + Arrays.toString(linkedList.toArray()));
        linkedList.removeLast();
        System.out.println("Removing last element: " + Arrays.toString(linkedList.toArray()));

        // Testing set and size methods
        linkedList.add(0, 5);
        linkedList.set(0, 10);
        System.out.println("Result of setting elements: " + Arrays.toString(linkedList.toArray()));
        System.out.println("Size of LinkedList: " + linkedList.size());
    }
}
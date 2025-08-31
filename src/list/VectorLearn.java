package list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class VectorLearn {
    public static void main(String[] args) {
        // About Vector :
        // 1. Vector is a legacy class (introduced in Java 1.0).
        // 2. Implements List, so it supports all ArrayList-like methods.
        // 3. Unlike ArrayList, Vector is SYNCHRONIZED (thread-safe).
        // 4. Slower than ArrayList in single-threaded apps (due to synchronization).
        // 5. Still useful in multithreaded scenarios.

        // Create Vector
        Vector<String> vector = new Vector<>();

        // 1. add(E e) → add element at end
        vector.add("Apple");
        vector.add("Banana");

        // 2. add(int index, E element) → insert at given position
        vector.add(1, "Mango"); // [Apple, Mango, Banana]

        // 3. get(int index) → fetch element
        String fruit = vector.get(0); // Apple

        // 4. set(int index, E element) → update element
        vector.set(1, "Orange"); // [Apple, Orange, Banana]

        // 5. remove(Object o) → remove by value
        vector.remove("Banana"); // [Apple, Orange]

        // 6. remove(int index) → remove by index
        vector.remove(0); // [Orange]

        // 7. size() → number of elements
        int size = vector.size(); // 1

        // 8. contains(Object o) → check existence
        boolean hasOrange = vector.contains("Orange"); // true

        // 9. isEmpty() → check if empty
        boolean empty = vector.isEmpty(); // false

        // 10. clear() → remove all elements
        vector.clear();

        // Add again for further demo
        vector.add("Dog");
        vector.add("Cat");
        vector.add("Elephant");

        // 11. indexOf(Object o) → first occurrence
        int index = vector.indexOf("Cat"); // 1

        // 12. lastIndexOf(Object o) → last occurrence
        vector.add("Dog");
        int lastIndex = vector.lastIndexOf("Dog"); // 3

        // 13. subList(from, to) → portion of list
        System.out.println(vector.subList(1, 3)); // [Cat, Elephant]

        // 14. Collections.sort()
        Collections.sort(vector); // [Cat, Dog, Dog, Elephant]

        // 15. Iteration (for-each)
        for (String item : vector) {
            System.out.println(item);
        }

        // -------------------------
        // Extra Vector-specific methods
        // -------------------------

        // 16. firstElement() → get first element
        String first = vector.firstElement(); // Cat

        // 17. lastElement() → get last element
        String last = vector.lastElement(); // Elephant

        // 18. capacity() → current capacity of vector
        int capacity = vector.capacity(); // 10 -> [by default 10]

        // 19. ensureCapacity(int minCapacity) → increase capacity if needed
        vector.ensureCapacity(20); // now capacity -> 20

        // 20. trimToSize() → trim unused capacity
        vector.trimToSize(); // now capacity -> 4

        // 21. addElement(E obj) → legacy add method (similar to add)
        vector.addElement("Tiger"); // [Cat, Dog, Dog, Elephant, Tiger]

        // 22. removeElement(Object obj) → legacy remove by value
        vector.removeElement("Dog"); // [Cat, Dog, Elephant, Tiger]

        // 23. removeAllElements() → legacy clear()
        vector.removeAllElements();

        // capacity increment during constructor call
        Vector<Integer> vectorList = new Vector<>(10, 3); // here initial capacity is 10, whenever it reaches the max capacity it will increase the capacity by 3

        // Arraylist vs Vector
        // Vector is thread safe, one operation can be performed one at a time, so it's slower that arraylist
        // when we works only with single thread then better to use arraylist

        ArrayList<Integer> arrayList=new ArrayList<>();
        Thread t1=new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                arrayList.add(i);
            }
        });
        Thread t2=new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                arrayList.add(i);
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(arrayList.size()); // it won't be 2000 -> that's the issue with arraylist, it's not thread safe
    }
}

package list;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListLearn {
    public static void main(String[] args) {
        // Create LinkedList
        List<String> list=new LinkedList<>();

        // 1. add(E e) → add element at end
        list.add("Apple");
        list.add("Banana");

        // 2. add(int index, E element) → insert at given position
        list.add(1,"Mango"); // [Apple, Mango, Banana]

        // 3. get(int index) → fetch by index
        String fruit=list.get(2); // Banana

        // 4. set(int index, E element) → update element
        list.set(2,"Orange"); // [Apple, Mango, Orange]

        // 5. remove(Object o) → remove by value
        list.remove("Mango"); // [Apple, Orange]

        // 6. remove(int index) → remove by index
        list.remove(0); // [Orange]

        // removeIf() [need to pass Predicate]
        list.removeIf(s-> s.startsWith("m"));

        // 7. size()
        int size= list.size(); // 1

        // 8. contains(Object o)
        boolean hasMango = list.contains("Mango"); // false

        // 9. isEmpty()
        boolean empty = list.isEmpty(); // false

        // 10. clear()
        list.clear(); // []

        // Add again for further demo
        list.add("Dog");
        list.add("Cat");
        list.add("Dog");
        list.add("Elephant");

        // 11. indexOf(Object o)
        int index=list.indexOf("Cat"); // 1

        // 12. lastIndexOf(Object o)
        int lastIndexOfDog = list.lastIndexOf("Dog"); // 2

        // 13. subList(from, to)
        List<String> subList = list.subList(1, 3); // [Cat, Dog]

        // 14. Collections.sort()
        Collections.sort(list); // [Cat, Dog, Dog, Elephant]

        // 15. List sort
        list.sort(null);

        // 16. Iteration
        for (String item : list) {
            System.out.println(item);
        }

        // 17. toArray() → convert to array
        Object[] array = list.toArray();
        String[] array1 = list.toArray(new String[0]);

        // 18. addFirst(E e) → insert at beginning
        list.addFirst("Zebra"); // [Zebra, Cat, Dog, Dog, Elephant]

        // 19. addLast(E e) → insert at end
        list.addLast("Monkey"); // [Zebra, Cat, Dog, Dog, Elephant, Monkey]

        // 20. getFirst() → fetch head
        String first = list.getFirst(); // Zebra

        // 21. getLast() → fetch tail
        String last = list.getLast(); // Monkey

        // 22. removeFirst()
        String removedFirst = list.removeFirst(); // Zebra

        // 23. removeLast()
        String removedLast = list.removeLast(); // Monkey


        // -------------------------
        // Extra LinkedList methods (Deque support)
        // -------------------------

        LinkedList<String> ql=new LinkedList<>(list);

        // 24. peek() → check first without removing
        String firstElement = ql.peek(); // Cat => [Cat, Dog, Dog, Elephant]

        // 25. peekFirst() -> firstElement & peekLast() -> lastElement
        String peekFirst = ql.peekFirst(); // Cat
        String peekLast = ql.peekLast(); // Elephant

        // 26. poll() → fetch and remove first element
        String polled = ql.poll(); // Cat => [Dog, Dog, Elephant]

        // 27. poolFirst() -> return & remove firstElement & peekLast() -> return & remove lastElement
        String polledFirst = ql.pollFirst(); // Dog => [Dog, Elephant]
        String polledLast = ql.pollLast(); // Elephant = > [Dog]

        // 28. offer(E e) → add element at end (like add, but for queues)
        ql.offer("Tiger"); // [Dog, Tiger]

        // 29. offerFirst(E e) → add at beginning
        ql.offerFirst("Horse"); // [Horse, Dog, Tiger]

        // 30. offerLast(E e) → add at end
        ql.offerLast("Deer"); // [Horse, Dog, Tiger, Deer]

        System.out.println(ql);
    }
}

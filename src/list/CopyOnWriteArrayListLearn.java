package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListLearn {
    public static void main(String[] args) {
        // -------------------------
        // About CopyOnWriteArrayList
        // -------------------------
        // 1. Thread-safe variant of ArrayList (from java.util.concurrent).
        // 2. Safe for concurrent read operations (no ConcurrentModificationException).
        // 3. On write (add/remove), it creates a new copy of the array.
        // 4. Best for read-mostly, write-rarely scenarios.
        // 5. Slower for frequent modifications.

        // Create CopyOnWriteArrayList
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();

        // 1. add(E e) → add element
        list.add("Apple");

        // 2. addAll(Collection<? extends E> c) → bulk add
        list.addAll(List.of("Mango", "Banana", "Orange"));

        // 3. get(int index) → fetch element
        String fruit = list.get(1); // Mango

        // 4. set(int index, E element) → update element
        list.set(1, "Grapes"); // [Apple, Grapes, Banana, Orange]

        // 5. remove(Object o) → remove element
        list.remove("Mango"); // [Apple, Grapes, Banana, Orange]

        // 6. size() → number of elements
        int size = list.size();

        // 7. contains(Object o) → check existence
        boolean hasApple = list.contains("Apple"); //true

        // 8. isEmpty() → check if list is empty
        boolean empty = list.isEmpty(); // false

        // 9. iterator() → safe iteration (no ConcurrentModificationException)
        Iterator<String> it = list.iterator();
        for (String item: list) {
            System.out.println("Iterating: " + item);
            // Even if we modify during iteration, no error occurs
            if(item.equals("Banana")) {
                list.add("NewFruit"); // Safe add, though it will not reflect immediately
            }
        }
    /*  Iterating: Apple
        Iterating: Grapes
        Iterating: Banana
        Iterating: Orange */

        System.out.println("After iteration: "+list); // After iteration: [Apple, Grapes, Banana, Orange, NewFruit]

        // 10. addIfAbsent(E e) → add only if not already present
        list.addIfAbsent("Apple"); // Won't add duplicate
        list.addIfAbsent("Pineapple"); // Adds since not present
        // [Apple, Grapes, Banana, Orange, NewFruit, Pineapple]

        // 11. addAllAbsent(Collection<? extends E> c) → add all unique elements
        list.addAllAbsent(List.of("Grapes", "Cherry", "Guava")); // Grapes won't add as already present

        System.out.println(list); // [Apple, Grapes, Banana, Orange, NewFruit, Pineapple, Cherry, Guava]

        // Now example with multi thread:
        List<String> sharedList=new ArrayList<>(List.of("item1","item2","item3"));
        Thread readerThread = new Thread(()->{
            try {
                for (int i=0;i<1000000;i++){
                    for(String item:sharedList){
                        System.out.println("Reading: "+item);
                        Thread.sleep(100);
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception while reading "+e);
            }
        });
        Thread writerThread = new Thread(()->{
            try {
                Thread.sleep(200);
                sharedList.add("item4");
                System.out.println("added item 4 in the list");

                Thread.sleep(200);
                sharedList.remove("item1");
                System.out.println("removed item 1 in the list");
            } catch (Exception e) {
                System.out.println("Exception while writing "+e);
            }
        });
        readerThread.start();
        writerThread.start();

        // if we use normal arraylist we will get this error:
        // Exception while reading java.util.ConcurrentModificationException
        // So to avoid such condition we use CopyOnWriteArrayList
    }
}

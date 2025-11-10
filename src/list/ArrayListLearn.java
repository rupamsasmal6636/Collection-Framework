package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListLearn {
    public static void main(String[] args) {
        // Create ArrayList
        ArrayList<String> list=new ArrayList<>(); // default capacity will be 10

        // we can also declare with initial capacity
        ArrayList<Integer> listWithInitialCapacity=new ArrayList<>(20);

        // 1. add(E e) → add element at the end
        list.add("Apple");
        list.add("Mango");
        list.add("Banana");

        // 2. add(int index, E element) → insert at given position
        list.add(1,"Coconut"); // [Apple, Coconut, Mango, Banana]

        // 3. get(int index) → fetch element by index
        String fruit=list.get(1); // "Coconut"

        // 4. set(int index, E element) → update element at index
        list.set(2,"Pineapple"); // [Apple, Coconut, Pineapple, Banana]

        // 5. remove(Object o) → remove by value
        list.remove("Banana"); // [Apple, Coconut, Pineapple]

        // 6. remove(int index) → remove by index
        list.remove(1); // [Apple, Pineapple]

        // some interesting fact about remove (by index & value can be sometime confusing)
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4));
        numbers.remove(3); // now question is will it remove value 3 or index 3 element?
        System.out.println(numbers); // [1, 2, 3] -> so it will remove index 3 element i.e. 4

        numbers.remove(Integer.valueOf(2));
        System.out.println(numbers); // [1, 3] -> this time it will remove by value

        // 7. size() → number of elements
        int size = list.size(); // 2

        // 8. contains(Object o) → check existence
        boolean hasOrange = list.contains("Orange"); // false

        // 9. isEmpty() → check if list is empty
        boolean isEmpty= list.isEmpty(); // false

        // 10. clear() → remove all elements
        list.clear();

        // Adding elements again for next methods
        list.add("Dog");
        list.add("Cat");
        list.add(("Dog"));
        list.add("Elephant");

        // 11. indexOf(Object o) → first occurrence index
        int index=list.indexOf("Cat"); // 1

        // 12. lastIndexOf(Object o) → last occurrence index
        int lastIndex = list.lastIndexOf("Dog"); // 2

        // 13. subList(fromIndex, toIndex) → get portion of list
        List<String> subList = list.subList(1, 3);// [Cat, Dog]

        // 14. Collections.sort(list) → sort list
        Collections.sort(list); // [Cat, Dog, Dog, Elephant]

        // 15. List sort
        list.sort(null); // need to pass comparator as parameter, for more go to comparator

        // 16. forEach loop
        for(String s:list){
            System.out.println(s);
        }

        // 17. toArray() → convert to array
        Object[] array = list.toArray();
        String[] array1 = list.toArray(new String[0]);

        // 18. ensureCapacity() & trimToSize() → capacity management
        list.ensureCapacity(10); // Arraylist method, not List; ensures capacity >= 20
        list.trimToSize(); // trims unused capacity

        // Experiments:
        List<Integer> list1=new ArrayList<>();
        System.out.println(list1.getClass().getName()); // java.util.ArrayList

        ArrayList<Integer> list2=new ArrayList<>();
        System.out.println(list2.getClass().getName()); // java.util.ArrayList

        List<String> list3= Arrays.asList("Monday","Tuesday");
        System.out.println(list3.getClass().getName()); // java.util.Arrays$ArrayList

//        list3.add("Wednesday"); // java.lang.UnsupportedOperationException -> we can't add new elements

        list3.set(1,"Wednesday"); // but we can modify the value -> [Monday, Wednesday]

        String[] strArr={"Jan","Feb","Mar"};
        List<String> list4=Arrays.asList(strArr);
        System.out.println(list4.getClass().getName()); // java.util.Arrays$ArrayList

//        list4.add("Apr"); // java.lang.UnsupportedOperationException -> we can't add new elements
        list4.set(2,"Apr"); // [Jan, Feb, Apr]

        // But if we create list from another list we can add:
        List<String> list5=new ArrayList<>(list4);
        list5.add("May"); // [Jan, Feb, Apr, May]

        List<Integer> list6=List.of(1,2,3,4);
//        list6.set(1,4); // java.lang.UnsupportedOperationException -> we can't even modify in this case

        // Time complexity:
        // get (access by index) : O(1)
        // Add, Remove, Iteration : O(N)

    }
}

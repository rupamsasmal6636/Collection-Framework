package list;

import java.util.LinkedList;
import java.util.Stack;

public class StackLearn {
    public static void main(String[] args) {
        // -------------------------
        // About Stack
        // -------------------------
        // 1. Stack extends Vector (so it has all Vector & List methods).
        // 2. It represents a LIFO (Last In First Out) data structure.
        // 3. Commonly used for undo operations, expression evaluation, recursion, etc.
        // 4. Methods: push(), pop(), peek(), empty(), search().

        // Create a Stack
        Stack<String> stack = new Stack<>();

        // 1. push(E item) → add element on top
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Mango"); // Stack: [Apple, Banana, Mango]

        // 2. peek() → get top element without removing
        String topElement = stack.peek(); // Mango

        // 3. pop() → remove and return top element
        String popped = stack.pop(); //Removes "Mango" from top

        // 4. empty() → check if stack is empty
        boolean isEmpty = stack.empty(); // false
        boolean isEmpty2 = stack.isEmpty(); // false -> this is vector method

        // 5. search(Object o) → 1-based position from top of stack
        int position = stack.search("Apple"); // 2 (since Banana is at top)

        // -------------------------
        // Vector methods (inherited)
        // -------------------------
        int size = stack.size();           // number of elements
        boolean contains = stack.contains("Banana"); // true
        String first = stack.firstElement(); // "Apple"
        String last = stack.lastElement();   // "Banana"

        // Print stack
        System.out.println("Stack elements: " + stack);
        System.out.println("Top element: " + topElement);
        System.out.println("Popped element: " + popped);
        System.out.println("Is empty: " + isEmpty);
        System.out.println("Search 'Apple': " + position);


        // We can also use LinkedList as Stack also, it has all the required methods
        LinkedList<Integer> list=new LinkedList<>();
        list.addLast(1); // push
        list.addLast(2); // push
        list.addLast(3); // push
        list.getLast(); // peek
        list.removeLast(); // pop

        // ArrayDeque is the best alternative for stack, it has the best memory management

    }
}

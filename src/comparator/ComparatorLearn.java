package comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Custom Comparator to sort integers in **descending** order.
 * Comparator<T> → external/custom sorting logic.
 */
class DecendingOrderComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer a, Integer b) {

        /*
         Rules of compare(a, b):

         ➜ return negative  → a comes BEFORE b
         ➜ return positive  → b comes BEFORE a
         ➜ return zero      → equal order (no swap)

         Example: a = 3, b = 5
         b - a = 2 → positive → b should come before a → descending order

         Example: a = 5, b = 3
         b - a = -2 → negative → a comes before b → correct for descending order
        */

        return b - a; // swapping logic for descending order
    }
}

/**
 * Custom Comparator to sort Strings based on their **length** (ascending).
 */
class StringLengthComparator implements Comparator<String>{
    @Override
    public int compare(String s1, String s2) {

        /*
         Example:
         s1 = "banana" → length = 6
         s2 = "apple"  → length = 5

         s1.length() - s2.length() = 1 (positive)
         → s2 (apple) will come before s1 (banana)
         → ASCENDING order by length
        */

        return s1.length() - s2.length();
    }
}

/**
 * Student class demonstrating Comparable interface.
 * Comparable<T> defines **default / natural sorting order** for the class.
 */
class Student implements Comparable<Student>{

    private final String name;
    private final double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public String getName() { return name; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }

    /**
     * compareTo() → defines natural sorting order.
     * Here we sort Students by GPA in ascending order.

     * this < other → negative
     * this > other → positive
     * this == other → zero
     */
    @Override
    public int compareTo(Student other) {

        // Better than (this.gpa - other.gpa) because of floating precision issues
        return Double.compare(this.getGpa(), other.getGpa());
    }
}

public class ComparatorLearn {
    public static void main(String[] args) {

        /**
         * ========== PART 1: SORTING PRIMITIVE WRAPPER (Integer) ==========
         */

        List<Integer> list = new ArrayList<>(List.of(1, 3, 6, 2));

        // Default sort → uses natural ordering (Comparable) of Integer
        list.sort(null);  // Output: [1, 2, 3, 6]

        // Use custom comparator (descending order)
        list.sort(new DecendingOrderComparator()); // Output: [6, 3, 2, 1]


        /**
         * ========== PART 2: SORTING STRINGS ==========
         */

        List<String> words = new ArrayList<>(List.of("Lemon", "Apple", "Banana"));

        // Default sorting → lexicographical (dictionary order)
        words.sort(null);  // Output: [Apple, Banana, Lemon]

        // Custom sorting by string length (ascending)
        words.sort(new StringLengthComparator()); // Output: [Apple, Lemon, Banana]

        // Lambda expression for descending order (longest first)
        words.sort((s1, s2) -> s2.length() - s1.length());
        // Output: [Banana, Apple, Lemon]


        /**
         * ========== PART 3: SORTING OBJECTS USING COMPARABLE & COMPARATOR ==========
         */

        List<Student> students = new ArrayList<>();
        students.add(new Student("Tarik", 8.9));
        students.add(new Student("Pratik", 6.7));
        students.add(new Student("Sam", 9.9));
        students.add(new Student("Kartik", 7.2));
        students.add(new Student("Raj", 8.9));

        /*
         Default sorting (students.sort(null)):
         ➜ Works only if Student implements Comparable
         ➜ Otherwise: ClassCastException
        */
        students.sort(null);  // ASCENDING sort by GPA (because compareTo does that)


        /**
         * Custom sorting using Anonymous Class
         */
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                // ascending by GPA
                return Double.compare(o1.getGpa(), o2.getGpa());
            }
        });


        /**
         * Sorting using Lambda Expression (DESCENDING order)
         */
        students.sort((o1, o2) -> Double.compare(o2.getGpa(), o1.getGpa()));


        /**
         * Using Comparator.comparing() — Cleaner, more readable
         */
        Comparator<Student> comparing = Comparator.comparing(s -> s.getGpa());
        students.sort(comparing); // ascending by GPA


        /**
         * Method Reference + reversed() for descending order
         */
        students.sort(Comparator.comparing(Student::getGpa).reversed());


        /**
         * Sorting using multiple fields → thenComparing()
         * 1. Sort by GPA (descending)
         * 2. If GPA is same, sort by name (ascending)
         */
        Comparator<Student> comparing3 =
                Comparator.comparing(Student::getGpa)
                        .reversed()
                        .thenComparing(Student::getName);

        students.sort(comparing3);


        /**
         * Old way (before Java 8) using Collections.sort()
         * List.sort() is preferred now (more direct, more flexible)
         */
        Collections.sort(students, comparing3);

    }
}

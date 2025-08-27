package comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DecendingOrderComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer a, Integer b) {
        // return -ve means a will come before b, +ve means b will come before a
        // for example a=3, b=5 , b-a=2 -> positive so b=5 will come before a=3
        // for example a=5, b=3 , b-a= - 2 -> negative so a=5 will come before b=3
        return b-a;
    }
}

class StringLengthComparator implements Comparator<String>{
    @Override
    public int compare(String s1, String s2) {
        // ascending order sort by string length
        // s1 = banana ~ 6 ; s2 = apple ~ 5
        // s1-s2 = 5-6 = -1 -> s1 will come before than s2
        return s1.length() - s2.length();
    }
}

class Student implements Comparable<Student>{
    private final String name;
    private final double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.getGpa(),other.getGpa());
    }
}

public class ComparatorLearn {
    public static void main(String[] args) {
        // Default order sorting
        List<Integer> list=new ArrayList<>(List.of(1,3,6,2));
        list.sort(null); // [1, 2, 3, 6]

        // Decending order number sort
        list.sort(new DecendingOrderComparator()); // [6, 3, 2, 1]

        List<String> words= new ArrayList<>(List.of("Lemon", "Apple", "Banana"));
        words.sort(null); // [Apple, Banana, Lemon]

        // now we want custom sorting -> sort according the length
        words.sort(new StringLengthComparator()); // [Apple, Lemon, Banana]

        // using lambda expression [descending order sort]
        words.sort((s1,s2)-> s2.length() - s1.length()); // [Banana, Apple, Lemon]

        // Now see sorting in class objects
        List<Student> students=new ArrayList<>();
        students.add(new Student("Tarik", 8.9));
        students.add(new Student("Pratik", 6.7));
        students.add(new Student("Sam", 9.9));
        students.add(new Student("Kartik", 7.2));
        students.add(new Student("Raj", 8.9));

        // default sort
        // [Note : it will only work if Student class implements Comparable Interface and compareTo() method ]
        students.sort(null); // otherwise it will throw exception

        // using Anonymous class
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getGpa(),o2.getGpa()); // ascending order
            }
        });

        // sort using lambda expression -> sort by gpa in descending order
        students.sort(((o1, o2) -> Double.compare(o2.getGpa(), o1.getGpa())));

        // using Comparator.comparing() -> functional interface
        Comparator<Student> comparing = Comparator.comparing(s -> s.getGpa()); // ascending order
        students.sort(comparing);

        // lambda replaced with method reference & reversed order
        students.sort(Comparator.comparing(Student::getGpa).reversed()); // descending order

        // Multiple fields (thenComparing)
        Comparator<Student> comparing3 = Comparator.comparing(Student::getGpa).reversed().thenComparing(Student::getName); // descending order , then name
        students.sort(comparing3);

        // using collections sort: [better to go with List.sort() ]
        Collections.sort(students,comparing3);

    }
}

package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Student{
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Overriding equals() to compare based on id and name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // same object
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
public class StudentHashMap {
    public static void main(String[] args) {
        Map<Student,Integer> studentMarks = new HashMap<>(12,0.8f); // (initialCapacity,loadFactor)

        Student s1 = new Student(101, "Alice");
        Student s2 = new Student(102, "Bob");
        Student s3 = new Student(101, "Alice"); // same id & name as s1

        // Adding students with marks
        studentMarks.put(s1, 85);
        studentMarks.put(s2, 90);
        // Trying to put s3 (same as s1 logically)
        studentMarks.put(s3, 95); // This will update marks of s1, not add a new entry

        // Print all entries
        for(Map.Entry<Student,Integer> s:studentMarks.entrySet()){
            System.out.println(s.getKey()+" -> Marks : "+s.getValue());
        }

        // Fetch marks for a student
        System.out.println("Marks of Alice (id=101): " + studentMarks.get(new Student(101, "Alice")));
    }
}

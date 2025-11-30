# Comparator vs Comparable (Java) – Complete Guide

Java provides two interfaces to define sorting logic for objects:

- **Comparable** → Default (natural) sorting
- **Comparator** → Custom/external sorting

Understanding the difference is essential for mastering Java Collections & interviews.

---

## 1. What is Comparable?

`Comparable<T>` defines the **natural/default ordering** of a class.

- Sorting logic is **inside** the class
- A class can have **only one** natural order
- Implemented using `compareTo()`

### Syntax:
```java
class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student other) {
        return this.gpa - other.gpa;
    }
}
```

### When to use Comparable?
- When a class should have a *default* sorting
- When sorting is part of the object’s identity  
  (e.g., Integer → ascending, String → alphabetical)

---

## 2. What is Comparator?

`Comparator<T>` is used for **custom sorting logic outside the class**.

- Sorting logic is **external**
- A class can have **multiple** comparators
- Useful when:
    - You cannot modify the original class
    - You want multiple sorting orders

### Syntax:
```java
class SortByGpa implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s1.getGpa(), s2.getGpa());
    }
}
```

---

## 3. compareTo() vs compare()

| Feature | Comparable | Comparator |
|--------|------------|------------|
| Method | `compareTo(T obj)` | `compare(T o1, T o2)` |
| Location | Inside class | Outside class |
| Sorting type | Natural/default | Custom |
| Number of orders | One | Multiple |
| Modifying class required | Yes | No |
| Used by | `sort(null)` | `sort(comparator)` |

---

## 4. How Sorting Works Internally

### Using Comparable
```java
Collections.sort(list);
list.sort(null); // Natural ordering
```

### Using Comparator
```java
Collections.sort(list, new MyComparator());
list.sort(new MyComparator()); // Custom ordering
```

---

## 5. Example: Sorting Student Objects

### Comparable → Natural Sorting by GPA (Ascending)
```java
class Student implements Comparable<Student> {
    double gpa;

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa);
    }
}
```

### Comparator → Sort by Name (Ascending)
```java
Comparator<Student> byName =
        (s1, s2) -> s1.getName().compareTo(s2.getName());
```

### Comparator → Sort by GPA (Descending)
```java
Comparator<Student> byGpaDesc =
        (s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa());
```

### Comparator → Multiple Fields
```java
Comparator<Student> multiSort =
        Comparator.comparing(Student::getGpa)
                  .reversed()
                  .thenComparing(Student::getName);
```

---

## 6. When to Use What?

### Use Comparable when:
- You want a **default** sorting
- Class naturally fits one order  
  (Integer, String, Date, etc.)

### Use Comparator when:
- You need **multiple sorting options**
- Class is from a third-party library
- Sorting changes depending on the use case

---

## 7. Interview-Friendly Notes

- String implements Comparable → natural alphabetical order
- Object does NOT implement Comparable → no natural order
- If you sort without Comparable/Comparator → `ClassCastException`
- `Comparator.comparing()` introduced in Java 8 for cleaner code

---

## 8. Summary Table (Quick Revision)

| Topic | Comparable | Comparator |
|-------|------------|------------|
| Purpose | Natural/default sorting | Custom sorting |
| Method | `compareTo()` | `compare()` |
| Inside class | Yes | No |
| Multiple rules | No | Yes |
| Example | String, Integer | Custom sorting logic |

---

## Final Conclusion

**Comparable is for natural/default sorting.  
Comparator is for flexible/custom sorting.**

Both are essential for mastering Java Collections & interviews.


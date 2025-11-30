# Java List Interface – Complete Revision Notes

The **List interface** in Java represents an ordered collection that allows duplicate elements and provides indexed access.

This README summarises all major List implementations:
- **ArrayList**
- **LinkedList**
- **Vector**
- **Stack**
- **CopyOnWriteArrayList**

Each section includes:
- Key properties
- Internal working
- Performance
- When to use
- Real examples

---

# 1️⃣ ArrayList

### ✅ Overview
ArrayList is the most commonly used List implementation.  
Internally based on **dynamic array**.

### 🔧 Internal Working
- Uses a **resizable array**
- Default capacity = **10**
- When array is full → capacity grows by **1.5x**  
  Example: 10 → 15 → 22 → 33 → ...

### ⚡ Performance
| Operation | Time |
|----------|------|
| get(index) | **O(1)** – random access |
| add(element) | **Amortized O(1)** |
| add(index) | **O(n)** (shifting needed) |
| remove(index) | **O(n)** |
| contains() | **O(n)** |

### 🛠 Best Use Cases
- Frequent read operations
- Access by index
- Low memory overhead
- General purpose lists

### 🚫 Avoid When
- Many insertions/removals in middle of list

---

# 2️⃣ LinkedList

### ✅ Overview
LinkedList is doubly linked list implementation of List & Deque.

### 🔧 Internal Working
Each node stores:
- data
- previous pointer
- next pointer

No continuous memory required.

### ⚡ Performance
| Operation | Time |
|----------|------|
| addFirst() / addLast() | **O(1)** |
| removeFirst() / removeLast() | **O(1)** |
| add(index) | **O(n)** |
| remove(index) | **O(n)** |
| get(index) | **O(n)** (no random access) |

### 🛠 Best Use Cases
- Frequent insert/remove at **start or end**
- Implementing queues and deques
- Heavy modification operations

### 🚫 Avoid When
- You need fast random access
- More memory usage is a problem (node overhead)

---

# 3️⃣ Vector

### ✅ Overview
Legacy synchronized version of ArrayList.

### 🔧 Internal Working
Same as ArrayList (dynamic array).  
BUT all methods are **synchronized**, hence thread-safe.

### ⚡ Performance
- Slower than ArrayList due to synchronization
- Grows by **2x** capacity each time (instead of 1.5x)

### 🛠 Best Use Cases
- Multi-threaded environments (but still outdated)
- When legacy code requires it

### 🚫 Avoid When
- You need high performance
- Use **Collections.synchronizedList()** or **CopyOnWriteArrayList** instead

---

# 4️⃣ Stack

### ✅ Overview
Legacy LIFO (Last-In-First-Out) data structure.

**Extends Vector**, making it synchronized and outdated.

### 🔧 Internal Working
- Inherits Vector methods
- Adds `push()`, `pop()`, `peek()`

### ⚡ Performance
- Slower due to synchronization
- Not recommended in modern Java

### 🛠 Best Replacement
Use:
```java
Deque<Integer> stack = new ArrayDeque<>();
```

### 🚫 Avoid When
- Performance is important
- Using for production-level stack operations

---

# 5️⃣ CopyOnWriteArrayList

### ✅ Overview
Thread-safe List for **highly concurrent read operations**.

### 🔧 Internal Working
- On every write operation (add/remove), the list creates a **new copy** of entire array
- Read operations happen on a **snapshot** → no locking required

### ⚡ Performance
| Operation | Time | Notes |
|----------|------|-------|
| read | **O(1)** | very fast (no lock) |
| write | **O(n)** | slow because array copy |

### 🛠 Best Use Cases
- **Read-mostly** scenarios (90% reads, 10% writes)
- Multithreaded environments
- Event listener lists

### 🚫 Avoid When
- Too many writes occur
- Large list size + heavy updates

---

# 📌 Summary Table (Quick Revision)

| Feature | ArrayList | LinkedList | Vector | Stack | CopyOnWriteArrayList |
|--------|-----------|------------|--------|--------|------------------------|
| Internal Structure | Dynamic array | Doubly linked list | Dynamic array | Dynamic array | Dynamic array (copied on write) |
| Thread Safe | ❌ | ❌ | ✔ | ✔ | ✔ (optimized) |
| Random Access | ✔ (fast) | ❌ | ✔ | ✔ | ✔ |
| Insert/Delete Mid | Slow | Fast-ish | Slow | Slow | Very Slow |
| Best For | Frequent reads | Frequent add/remove | Legacy threads | Legacy stack | Many readers, few writers |

---

# 📌 When to Use Which?

### ✔ ArrayList
- Most cases
- Fast access, moderate inserts

### ✔ LinkedList
- Many insertions/removals
- Implementing queues

### ✔ Vector
- Legacy thread-safe list (avoid)

### ✔ Stack
- Legacy LIFO (avoid, use Deque instead)

### ✔ CopyOnWriteArrayList
- Multi-threaded environment with heavy reads
- Listener/event systems

---

# 📁 Mapping to Your Source Files

| Topic | File |
|-------|------|
| ArrayList | `ArrayListLearn.java` |
| LinkedList | `LinkedListLearn.java` |
| Vector | `VectorLearn.java` |
| Stack | `StackLearn.java` |
| CopyOnWriteArrayList | `CopyOnWriteArrayListLearn.java` |

---

# 🎯 Final Summary

- **ArrayList** → best general purpose
- **LinkedList** → best for frequent insert/delete
- **Vector** → thread-safe but outdated
- **Stack** → outdated LIFO (use Deque)
- **CopyOnWriteArrayList** → best for multi-threaded read-heavy systems

These notes cover everything needed for interviews + revision.


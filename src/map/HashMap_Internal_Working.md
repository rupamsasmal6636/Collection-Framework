# HashMap Internal Working in Java

## 🔑 Components of HashMap
1. **Key** – Identifier used to store and retrieve values.
2. **Value** – Data associated with the key.
3. **Bucket** – A slot in the underlying array where entries are stored.
4. **Hash Function** – Function that converts a key into an integer (hash code).
5. **Entry (Node)** – A key-value pair along with hash info stored inside a bucket.

---

## ⚙️ How Data is Stored
1. When you insert (`put(key, value)`) into a `HashMap`:
    - `hashCode()` of the key is computed.
    - Hash function maps this hash to an **index** in the internal array (`table`). **int index = hashCode % arraySize;**
    - The key-value entry is placed inside the corresponding **bucket**.

---

## 🧩 Collision Handling
- **Collision** occurs when two different keys map to the same bucket index.
- Java handles this using **Linked List chaining** (before Java 8).
    - Each bucket holds a **linked list of nodes** if multiple entries collide.

---

## 🌳 HashMap after Java 8
- If a single bucket contains **more than 8 entries** (due to collisions),  
  the linked list is converted into a **balanced binary search tree (Red-Black Tree)**.
- This improves worst-case lookup from **O(n)** (linked list) to **O(log n)** (tree).

---

## 🔄 Resizing / Rehashing
- `HashMap` starts with a **default capacity = 16** buckets.
- **Load Factor** (default `0.75`) controls resizing.
    - When the number of entries exceeds `capacity * loadFactor`,  
      the table is **resized (doubled)** and all entries are **rehashed** to new buckets.
- Example: `capacity = 16`, `loadFactor = 0.75` → resize after `12` elements.

---

## ⏱️ Time Complexity
- **put() / get() / remove():**
    - Average case → **O(1)** (constant time).
    - Worst case:
        - Before Java 8 (linked list) → **O(n)**.
        - After Java 8 (tree) → **O(log n)**.

---

## 📝 Summary
- `HashMap` = **Array + LinkedList/Tree**.
- Keys are hashed → mapped to bucket index.
- Collisions handled with **LinkedList**, later upgraded to **Red-Black Tree**.
- **Load factor & resizing** ensure balance between performance and memory.
- Time complexity is usually **O(1)** but can degrade if hashing is poor.

---

## 📌 Conceptual Diagram
- Index:   0      1      2      3      ...
- Bucket: [ ] -> [K1,V1] -> [K2,V2]  [ ] -> [K3,V3]
#### (collision handled via list/tree)
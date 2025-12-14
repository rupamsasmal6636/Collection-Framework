package map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**  Key concepts:
    - Thread-safe Map implementation optimized for high concurrency.
    - Allows concurrent reads and updates without global synchronization.
    - Does NOT allow null keys or null values.
    - Iterators are "weakly consistent" (no ConcurrentModificationException).
    - Useful atomic methods: putIfAbsent(), remove(key, value), replace(), compute(), merge(), etc.
*/
public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentMap<String,Integer> chm=new ConcurrentHashMap<>();
         /** Working:
         * java 7 ---> segment based locking --> 16 segment -> smaller hashmaps
           only the segment being written or read is being locked
           read : do not required any locking unless there is a write operation happening in the same segment
           write : must lock

         * java 8 ---> no segmentation concept (scaling is very difficult in earlier concept)
           compare & swap approach --> no locking except resizing or collision
           Thread A last show : x = 42
           Thread A work : x to 50
           if x is still 42, then change it to 50, else don't change and retry
          */

        // 2) Basic operations: put, get, remove
        chm.put("A", 1);
        chm.put("B", 2);
        chm.put("C", 3);

        System.out.println("Initial ConcurrentHashMap: " + chm);
        // Output order is not guaranteed (hash-based map).

        // 3) Null key/value behavior
        // ConcurrentHashMap does NOT allow null keys or null values.
        // The following lines, if uncommented, will throw NullPointerException:
        // chm.put(null, 100);     // ❌ NPE
        // chm.put("nullValue", null); // ❌ NPE

        // 4) Why ConcurrentHashMap (high-level idea)
        /**
         * - Hashtable and Collections.synchronizedMap(new HashMap<>()) use a single lock
         *   for the entire map, causing contention under heavy concurrency.
         * - ConcurrentHashMap uses finer-grained locking and non-blocking techniques
         *   so multiple threads can operate concurrently on different parts of the map.
         * - Reads are typically lock-free, and writes lock only small portions.
         */

        // 5) Demonstrate thread-safe concurrent updates
        ConcurrentHashMap<String, Integer> counterMap = new ConcurrentHashMap<>();
        counterMap.put("counter", 0);

        // Two threads incrementing the same key concurrently
        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                // Not safe: counterMap.put("counter", counterMap.get("counter") + 1);
                // Because get + put is not atomic.
                // Instead, use compute() or merge() for atomic updates:

                counterMap.compute("counter", (key, oldValue) -> {
                    if (oldValue == null) {
                        return 1;
                    }
                    return oldValue + 1;
                });
            }
        };

        Thread t1 = new Thread(incrementTask);
        Thread t2 = new Thread(incrementTask);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("counterMap after concurrent increments: " + counterMap);
        // Expected "counter" ≈ 2000 (no lost updates due to compute() atomicity).

        // 6) putIfAbsent() – atomic "insert only if key not present"
        chm.clear();
        chm.put("X", 10);

        // If key not present → inserts and returns null.
        Integer old = chm.putIfAbsent("Y", 20);
        System.out.println("putIfAbsent(\"Y\", 20) old value: " + old); // null
        System.out.println("Map now: " + chm);

        // If key already present → does not overwrite, returns existing value.
        old = chm.putIfAbsent("X", 99);
        System.out.println("putIfAbsent(\"X\", 99) old value: " + old); // 10
        System.out.println("Map after failed overwrite: " + chm);

        // 7) replace() – conditional updates
        chm.replace("X", 10, 100); // replace only if current value == 10
        System.out.println("After replace(\"X\", 10, 100): " + chm);

        // replace(key, newValue) – replace unconditionally (if key exists)
        chm.replace("Y", 200);
        System.out.println("After replace(\"Y\", 200): " + chm);

        // 8) remove(key, value) – conditional removal
        boolean removed = chm.remove("Y", 999); // fails, value != 999
        System.out.println("remove(\"Y\", 999) success? " + removed + ", map: " + chm);

        removed = chm.remove("Y", 200); // succeeds
        System.out.println("remove(\"Y\", 200) success? " + removed + ", map: " + chm);

        // 9) forEach – concurrent-friendly traversal
        chm.put("P", 5);
        chm.put("Q", 15);
        chm.put("R", 25);

        System.out.println("Iterating using forEach:");
        chm.forEach((key, val) -> System.out.println(key + " -> " + val));

        // 10) Weakly consistent iteration
        /**
         * - Iterators/traversals of ConcurrentHashMap are "weakly consistent":
         *      * They do NOT throw ConcurrentModificationException.
         *      * They may reflect some, all, or none of the updates happening
         *        after the iterator was created.
         * - This is different from "fail-fast" iterators of HashMap/ArrayList.
         */

        System.out.println("Demonstrating weakly consistent iteration:");
        for (Map.Entry<String, Integer> entry : chm.entrySet()) {
            System.out.println("Visiting: " + entry);
            // Concurrent modification during iteration is allowed
            if (entry.getKey().equals("P")) {
                chm.put("S", 50); // modifying map while iterating – no CME
            }
        }
        System.out.println("Map after iteration+modification: " + chm);

        // 11) computeIfAbsent() – lazy initialization
        /**
         * Use computeIfAbsent() when you want to create a default value for a key only once,
         * in a thread-safe way.
         */
        ConcurrentHashMap<String, StringBuilder> logs = new ConcurrentHashMap<>();

        Runnable logTask = () -> {
            // Thread-safe: only one StringBuilder per key will be created
            StringBuilder sb = logs.computeIfAbsent("threadLogs", k -> new StringBuilder());
            synchronized (sb) {
                // StringBuilder itself is not thread-safe, so we still synchronize on it
                sb.append(Thread.currentThread().getName()).append(" ");
            }
        };

        Thread l1 = new Thread(logTask, "T-1");
        Thread l2 = new Thread(logTask, "T-2");
        Thread l3 = new Thread(logTask, "T-3");
        l1.start();
        l2.start();
        l3.start();
        l1.join();
        l2.join();
        l3.join();

        System.out.println("logs map: " + logs);
        System.out.println("logs.get(\"threadLogs\") contents: " + logs.get("threadLogs"));

        // 12) merge() – combine values atomically
        /**
         * merge(key, value, remappingFunction) is useful to aggregate counts or merge collections.
         */
        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();

        String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};

        for (String w : words) {
            // If key absent → inserts with 1
            // If key present → newValue = oldValue + 1
            wordCount.merge(w, 1, (oldVal, newVal) -> oldVal + newVal);
        }

        System.out.println("wordCount using merge(): " + wordCount);

        // 13) size and isEmpty under concurrency (approximate cost)
        /**
         * - size() is not constant-time in all implementations; internally it may need to
         *   traverse segments/buckets to compute size accurately.
         * - In Java 8+, size() is improved, but with many concurrent updates the value may be
         *   slightly out-of-date at the moment you read it.
         */
        System.out.println("chm size: " + chm.size());
        System.out.println("chm isEmpty: " + chm.isEmpty());

        // 14) When to use ConcurrentHashMap?
        /**
         * Use ConcurrentHashMap when:
         *  - You need a Map in a multi-threaded environment.
         *  - There are many read and write operations happening concurrently.
         *  - You want better performance than Hashtable or synchronizedMap.
         *  - You can tolerate weakly consistent iteration (no strict snapshot).
         *
         * Do NOT use ConcurrentHashMap when:
         *  - You rely on null keys/values (use HashMap instead).
         *  - You need strict iteration snapshot (consider copying into an immutable map).
         */

    }
}

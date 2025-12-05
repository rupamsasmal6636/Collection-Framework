package map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Key concepts shown:
 *  - Hashtable is a legacy synchronized map (thread-safe by method-level synchronization).
 *  - Does NOT allow null keys or null values (throws NullPointerException).
 *  - Uses rehashing/growth policy (capacity doubles by default on resize).
 *  - Uses only linked list in case of collision.
 *  - Iteration via Enumeration (legacy) and keySet()/entrySet() (modern).
 *  - Enumeration is NOT fail-fast; Iterator is fail-fast (throws CME).
 *  - For high-concurrency use cases prefer ConcurrentHashMap.
 */
public class HashTableDemo {
    public static void main(String[] args) {
        // 1) Basic creation & insertion
        Hashtable<String, Integer> ht= new Hashtable<>();
        // put() — adds key/value pairs
        ht.put("one", 1);
        ht.put("two", 2);
        ht.put("three", 3);
        ht.put("four", 4);

        // print contents — note Hashtable preserves no insertion order (it's not LinkedHashMap)
        System.out.println("Initial Hashtable: " + ht); // {two=2, one=1, three=3, four=4}

        // 2) Null key/value behavior
        // Hashtable does NOT allow null keys or null values.
        // The following lines, if uncommented, will throw NullPointerException at runtime:
        // ht.put(null, 10);      // ❌ throws NPE
        // ht.put("nullValue", null); // ❌ throws NPE

        // 3) Thread-safety: method-level synchronization
        // Methods like put/get/remove are synchronized in Hashtable.
        // This provides safety for simple concurrent access but can be a scalability bottleneck.
        // For demo: two threads doing concurrent puts (not real heavy concurrent test here).
        Thread t1 = new Thread(()->{
            for (int i = 5; i <=7 ; i++) {
                ht.put("t1-"+i,i);
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 8; i <=10 ; i++) {
                ht.put("t2-"+i,i);
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {}
        System.out.println("After concurrent puts"+ ht);

        // 4) Access methods
        Integer value= ht.get("two"); // 2

        boolean containsKey = ht.containsKey("three"); // true
        boolean containsValue = ht.containsValue(99); // false

        System.out.println(value + " " + containsKey + " " + containsValue);

        // 5) Iteration: Enumeration (legacy, not fail-fast)
        // Enumeration can be obtained via elements() -> [values] or keys() -> [keys]
        Enumeration<Integer> valuesEnum = ht.elements();
        while (valuesEnum.hasMoreElements()){
            System.out.print(valuesEnum.nextElement() + ", ") ;
        }
        System.out.println();
        Enumeration<String> keysEnum = ht.keys();
        while (keysEnum.hasMoreElements()){
            System.out.println(keysEnum.nextElement() + ", ");
        }
        System.out.println();

        // Enumeration is not fail-fast — modifying the map during enumeration may not throw
        // ConcurrentModificationException (behavior historically allowed).

        // 6) Iteration: Iterator via keySet() / entrySet() (modern)
        Set<Map.Entry<String,Integer>> entrySet = ht.entrySet();
        Iterator<Map.Entry<String,Integer>> it =entrySet.iterator();
        while (it.hasNext()){
            Map.Entry<String,Integer> entry = it.next();
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // 7) Fail-fast behavior demonstration with Iterator
        // If you modify the Hashtable structurally during iteration using Iterator, it should throw
        // ConcurrentModificationException. (Note: Hashtable's iterators are fail-fast as of modern JVMs.)
        try {
            Iterator<String> keyIt = ht.keySet().iterator();
            while (keyIt.hasNext()) {
                String key = keyIt.next();
                if (key.startsWith("t1-")) {
                    // structural modification via Hashtable.put/remove while iterating -> CME likely
                    ht.remove(key); // modification during iteration
                }
            }
            System.out.println("Removed t1- keys without CME (unexpected on many JVMs). Current ht: " + ht);
        } catch (Exception ex) {
            System.out.println("Exception during iteration-modification (expected behavior on many JVMs): " + ex);
        }

        // 8) Re-insert some entries to continue demo
        ht.put("A", 100);
        ht.put("B", 200);

        // 9) Size, clear, remove
        System.out.println("Size before remove: " + ht.size());
        ht.remove("A");
        System.out.println("After remove(\"A\"): " + ht.size());
        ht.clear();
        System.out.println("After clear(): " + ht + " size=" + ht.size());

        // 10) Growth policy & capacity note (conceptual)
        /*
         * - Hashtable uses an internal array of buckets. Initial capacity default is 11 (legacy).
         * - Load factor default is 0.75 (similar to HashMap), and rehashing doubles capacity + 1 historically.
         * - Because Hashtable is legacy, exact resizing details may vary across Java versions.
         * - For most modern purposes prefer HashMap/ConcurrentHashMap for predictable behavior.
         */

        // 11) Hashtable vs HashMap vs ConcurrentHashMap (summary examples)
        Hashtable<String, Integer> legacy = new Hashtable<>(); // synchronized, no nulls
        // HashMap<String, Integer> modern = new HashMap<>();    // not synchronized, allows one null key and multiple null values
        // ConcurrentHashMap<String, Integer> concurrent = new ConcurrentHashMap<>(); // concurrent, no null keys/values

        // Quick demo showing HashMap allows nulls (uncomment the HashMap example to test):
        // HashMap<String, Integer> modern = new HashMap<>();
        // modern.put(null, 999); // HashMap accepts null key
        // System.out.println("HashMap with null key: " + modern);

        // ConcurrentHashMap example (recommended for concurrent access)
        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        chm.put("x", 1);
        chm.put("y", 2);
        System.out.println("ConcurrentHashMap example: " + chm);

        // 12) When to use Hashtable?
        /*
         * - Use Hashtable only if you're maintaining legacy code that already uses it.
         * - Prefer:
         *     - HashMap if single-threaded / externally synchronized
         *     - Collections.synchronizedMap(new HashMap<>()) for simple synchronization
         *     - ConcurrentHashMap for high-concurrency use-cases (better scalability)
         */

        // 13) Final practical snippet: safe iteration under concurrency using ConcurrentHashMap
        ConcurrentHashMap<String, Integer> safe = new ConcurrentHashMap<>();
        safe.put("p", 10);
        safe.put("q", 20);
        // Iteration over ConcurrentHashMap's keySet() is weakly consistent — does not throw CME,
        // and reflects some but not necessarily all modifications
        for (String k : safe.keySet()) {
            System.out.println("Concurrent map key: " + k);
            safe.put("new-" + k, 999); // allowed during iteration
        }
        System.out.println("ConcurrentHashMap after concurrent modifications: " + safe);

    }
}

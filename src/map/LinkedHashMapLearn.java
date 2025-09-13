package map;

import java.util.LinkedHashMap;

public class LinkedHashMapLearn {
    public static void main(String[] args) {
        // -------------------------
        // About LinkedHashMap
        // -------------------------
        // 1. Extends HashMap → stores key-value pairs.
        // 2. Maintains insertion order by default.
        // 3. Can also maintain access order (least-recently-used) if enabled in constructor.
        // 4. Allows one null key and multiple null values.
        // 5. Not synchronized (not thread-safe).


        // Create LinkedHashMap (insertion order)
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        // 1. put(K, V) → add entry
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Mango");

        // 2. get(Object key) → fetch value
        String fruit = map.get(2); // Banana

        // 3. remove(Object key) → remove by key
        map.remove(3);

        // 4. containsKey(Object key)
        boolean hasKey = map.containsKey(1);

        // 5. containsValue(Object value)
        boolean hasValue = map.containsValue("Banana");

        // 6. size()
        int size = map.size();

        // 7. isEmpty()
        boolean empty = map.isEmpty();

        // 8. keySet()
        System.out.println("Keys: " + map.keySet());

        // 9. values()
        System.out.println("Values: " + map.values());

        // 10. entrySet() → iterate all key-value pairs
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 11. putIfAbsent(K, V) → add only if key not already present
        map.putIfAbsent(3, "Orange");

        // 12. replace(K, V) → replace value
        map.replace(2, "Pineapple");

        // 13. getOrDefault(K, defaultVal)
        String val = map.getOrDefault(10, "DefaultFruit");

        // 14. forEach(BiConsumer) (Java 8+)
        map.forEach((k, v) -> System.out.println(k + " => " + v));

        // -------------------------
        // Access-order LinkedHashMap (like LRU cache)
        // -------------------------
        LinkedHashMap<Integer, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put(1, "A");
        accessOrderMap.put(2, "B");
        accessOrderMap.put(3, "C");

        // Access key 1 → moves it to end
        accessOrderMap.get(1);

        System.out.println("Access-order map: " + accessOrderMap);

    }
}

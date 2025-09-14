package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapLearn {
    public static void main(String[] args) {
        // -------------------------
        // About HashMap
        // -------------------------
        // 1. Stores key-value pairs (Map interface).
        // 2. Keys are unique, values can be duplicate.
        // 3. Allows one null key and multiple null values.
        // 4. NOT synchronized (not thread-safe).
        // 5. Backed by hashing (average O(1) for get/put).
        // 6. Order is not guaranteed (unordered collection).

        // Create HashMap
        HashMap<Integer,String> map=new HashMap<>();

        // 1. put(K key, V value) → add key-value pair -> O(1) avg
        map.put(1,"Rupam");
        map.put(2,"Subham");
        map.put(3,"Ankit");
        map.put(4,"Yash");

        System.out.println(map); // {1=Rupam, 2=Subham, 3=Ankit, 4=Yash} -> like Dictionary in Python

        // 2. get(Object key) → fetch value by key -> O(1) avg
        String s = map.get(2); // Subham
        String s1 = map.get(69); // null

        // 3. remove(Object key) → remove entry by key
        String removedValue = map.remove(3);// it returns and removes the value with corresponding key -> Ankit
        map.remove(33); // null
        boolean isRemoved = map.remove(4, "xcbj"); // false

        // 4. containsKey(Object key) → check if key exists -> O(1) avg
        boolean hasKey = map.containsKey(3); // false

        // 5. containsValue(Object value) → check if value exists
        boolean hasValue = map.containsValue("Rupam"); // true

        // 6. size() → number of key-value pairs
        int size = map.size(); // 3

        // 7. isEmpty() → check if map is empty
        boolean isEmpty = map.isEmpty(); // false

        // 8. keySet() → get all keys
        Set<Integer> keys = map.keySet(); // [1, 2, 4]
        for(int key:keys){
            System.out.println(map.get(key));
        }

        // 9. values() → get all values
        Collection<String> values = map.values(); // [Rupam, Subham, Yash]

        // 10. entrySet() → get all key-value pairs
        Set<Map.Entry<Integer, String>> entries = map.entrySet();

        for(Map.Entry<Integer, String> entry:entries){
            System.out.println(entry.getKey()+" : "+ entry.getValue());
        }

        // 11. getOrDefault() -> return value of the key if present otherwise return the default value
        String name1= map.getOrDefault(1,"Unknown"); // Rupam
        String name2= map.getOrDefault(6,"Unknown"); // Unknown

        // 12. putIfAbsent()
        map.putIfAbsent(1,"Rupam Sasmal"); // present, so no put
        map.putIfAbsent(6,"Hari"); // missing, so put

        System.out.println(map); // {1=Rupam, 2=Subham, 4=Yash, 6=Hari}

        // Note: if any key is already present and if we put the value over the key it overwrite the value
        map.put(1,"Rupam Sasmal");
        System.out.println(map); // {1=Rupam Sasmal, 2=Subham, 4=Yash, 6=Hari}

        // 13. clear()
        map.clear(); // {}

    }
}

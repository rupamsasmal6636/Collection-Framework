package map;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K,V> extends LinkedHashMap<K,V>{
    private final int capacity;
    public LRUCache(int initialCapacity) {
        super(initialCapacity,0.75f,true);
        this.capacity=initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>capacity;
    }
}
public class LRUCacheDemo {
    public static void main(String[] args) {
    /*
    LRU (Least Recently Used) cache is a cache eviction strategy.
	•	When cache reaches its maximum capacity, it removes the least recently used entry first.
	•	“Recently used” means recently accessed (get/put).

⚙️  Why LinkedHashMap?
	•	LinkedHashMap maintains a doubly linked list of entries.
	•	If created with accessOrder = true, it moves an entry to the end of the list every time it is accessed.
	•	This naturally keeps the least recently used entry at the head of the list.
	•	By overriding removeEldestEntry(), we can automatically evict old entries.
	*/

        LRUCache<Integer,String> cache=new LRUCache<>(3);
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        System.out.println("Cache: " + cache); // Cache: {1=One, 2=Two, 3=Three}

        // Access key 1 → moves it to end (most recently used)
        cache.get(1);
        System.out.println("After accessing 1: " + cache); // After accessing 1: {2=Two, 3=Three, 1=One}

        // Add a new key → evicts least recently used (key 2)
        cache.put(4, "Four");
        System.out.println("After adding 4: " + cache); // After adding 4: {3=Three, 1=One, 4=Four}

        // Access key 3 → moves it to end
        cache.put(3,"THREE");

        // Add another new key → evicts least recently used (key 1)
        cache.put(5, "Five");
        System.out.println("After adding 5: " + cache); // After adding 5: {4=Four, 3=THREE, 5=Five}

        // Time Complexity: get() → O(1)  ,   put() → O(1)
    }
}

package map;

import java.util.*;

/**
 * Demonstrates: SortedMap, NavigableMap and TreeMap
 * Key points repeated in comments:
 *  - TreeMap implements NavigableMap interface (which extends SortedMap interface).
 *  - TreeMap is a Red-Black tree internally -> most operations O(log n).
 *  - TreeMap orders entries by key (natural order or provided Comparator).
 *  - Many useful range and navigation operations are provided (subMap, headMap, tailMap,
 *    lowerKey/floorKey/ceilingKey/higherKey, descendingMap, pollFirstEntry, pollLastEntry).
 */
public class TreeMapNavigableMapDemo {
    public static void main(String[] args) {
        // basic TreeMap -> Natural ordering
        TreeMap<Integer,String> scores = new TreeMap<>();

        scores.put(50, "Alice");
        scores.put(90, "Sam");
        scores.put(70, "Monica");
        scores.put(30, "Raj");
        scores.put(80, "Priya");
        // scores.put(null, "NullKey"); // TreeMap with natural ordering does NOT allow null keys

        // TreeMap prints entries in key-sorted order (ascending).
        System.out.println("TreeMap with Natural ordering: " + scores); // {30=Raj, 50=Alice, 70=Monica, 80=Priya, 90=Sam}

        // SortedMap is a super-interface of NavigableMap. You can use -> subMap/headMap/tailMap methods
        SortedMap<Integer,String> topHalf = scores.tailMap(70); // keys >= 70
        System.out.println("tailMap(70): " + topHalf); // {70=Monica, 80=Priya, 90=Sam}

        SortedMap<Integer,String> below70 = scores.headMap(70); // keys < 70
        System.out.println("headMap(70): " + below70); // {30=Raj, 50=Alice}

        // subMap(fromKey, toKey) -> keys in [fromKey, toKey) (inclusive/exclusive)
        SortedMap<Integer,String> between50and90 = scores.subMap(50,90); // keys >= 50 & keys < 90
        System.out.println("subMap(50,90): " + between50and90); // {50=Alice, 70=Monica, 80=Priya}

        // TreeMap implements NavigableMap, which adds navigation helpers:
        NavigableMap<Integer,String> navigableMap = scores;

        // lowerKey(k) -> greatest key strictly less than k (or null)
        System.out.println("lowerKey(70) : " + navigableMap.lowerKey(70)); // 50

        // floorKey(k) -> greatest key <= k (or null)
        System.out.println("floorKey(70) : " + navigableMap.floorKey(70)); // 70

        // ceilingKey(k) -> least key >= k (or null)
        System.out.println("ceilingKey(75) : " + navigableMap.ceilingKey(75)); // 80
        System.out.println("ceilingKey(80) : " + navigableMap.ceilingKey(80)); // 80

        // higherKey(k) -> least key strictly greater than k (or null)
        System.out.println("higherKey(80) : " + navigableMap.higherKey(80)); // 90

        // lowerEntry / floorEntry / ceilingEntry / higherEntry return Map.Entry<K,V>
        Map.Entry<Integer, String> e = navigableMap.floorEntry(76); // <k,v> where k<=76
        System.out.println("floorEntry(76) -> " + e); // {70=Monica} as Entry

        // pollFirstEntry() / pollLastEntry() remove and return first/last entry
        Map.Entry<Integer, String> first = navigableMap.pollFirstEntry();
        System.out.println("pollFirstEntry() removed: " + first);
        System.out.println("TreeMap after pollFirstEntry: " + navigableMap);

        Map.Entry<Integer, String> last = navigableMap.pollLastEntry();
        System.out.println("pollLastEntry() removed: " + last);
        System.out.println("TreeMap after pollLastEntry: " + navigableMap);

        // descendingMap() -> reversed-order view of the entries (does not copy entries)
        NavigableMap<Integer, String> descendingOrderMap = navigableMap.descendingMap();
        System.out.println("descendingMap view: " + descendingOrderMap); // {80=Priya, 70=Monica, 50=Alice}

        // descendingKeySet() -> keys in descending order (NavigableSet)
        NavigableSet<Integer> descKeys = navigableMap.descendingKeySet();
        System.out.println("descendingKeySet: " + descKeys); // [80, 70, 50]

        // ---------- Range methods with inclusion control ----------
        // subMap(fromKey, fromInclusive, toKey, toInclusive)
        // Example: keys between 50 (inclusive) and 85 (exclusive) -> k >= 50 & k < 85
        NavigableMap<Integer, String> range = navigableMap.subMap(50, true, 85, false);
        System.out.println("subMap(50, true, 85, false) : " + range); // {50=Alice, 70=Monica, 80=Priya}

        // ---------- Comparator & custom ordering ----------
        // TreeMap can accept a Comparator for custom order (e.g., reverse order)
        TreeMap<String,Integer> descOrder = new TreeMap<>(Comparator.reverseOrder());

        descOrder.put("apple",18);
        descOrder.put("banana",13);
        descOrder.put("pineapple",14);

        System.out.println("TreeMap with Comparator.reverseOrder(): " + descOrder); // {pineapple=14, banana=13, apple=18}

        // we can check whether comparator is being used or not
        // comparator() returns the Comparator used, or null if natural ordering
        System.out.println(scores.comparator()); // null
        System.out.println(descOrder.comparator()); // java.util.Collections$ReverseComparator@7cc355be

        /**
         - TreeMap is implemented as a Red-Black tree:
             * Lookup, insertion, deletion -> O(log n)
         - Compared to HashMap:
             * HashMap offers O(1) average for get/put but no ordering guarantees.
             * TreeMap keeps keys sorted and supports range operations but with O(log n).
         - Use TreeMap when:
             * You need sorted keys, range queries, or navigation (closest floor/ceiling).
         - Use HashMap when:
             * You need fastest insertion/lookup and don't require ordering.
         */
    }
}

package map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMapDemo {
    public static void main(String[] args) {
        Map<String,Integer> map1=new HashMap<>();
        map1.put("A",1);
        map1.put("B",2);

        // process 1:
        Map<String,Integer> map2 = Collections.unmodifiableMap(map1);
        System.out.println(map2);
//        map2.put("C",3); // It will throw this error -> java.lang.UnsupportedOperationException

        // process 2:
        Map<String,Integer> map3 = Map.of("Rupam",98, "Subham",80); // but we can only store 9 <k,v> pairs
//        map3.put("Rahul",43); // throw -> java.lang.UnsupportedOperationException
        System.out.println(map3);

        // process 3:
        Map<String, Integer> map4 = Map.ofEntries(Map.entry("Raju",80), Map.entry("Bishal",78)); // no restriction

    }
}

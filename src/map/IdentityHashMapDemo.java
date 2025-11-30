package map;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapDemo {
    public static void main(String[] args) {
        // using HashMap
        Map<String, String> hashMap = new HashMap<>();
        String key1 = new String("hello");
        String key2 = new String("hello"); //  different object, same content

        hashMap.put(key1, "word1");
        hashMap.put(key2, "word2");

        System.out.println(hashMap.get("hello")); // word2
        System.out.println(hashMap.get(key1)); // word2
        System.out.println(hashMap.get(key2)); // word2

        System.out.println(hashMap); // {hello=word2}
        System.out.println("Hashmap size : "+hashMap.size()); // Hashmap size : 1

        /* HashMap block:
        key1 and key2 are two distinct String objects but with the same content ("hello").
        When you put both into a HashMap, because HashMap uses equals() + hashCode() to compare keys.
        If some class has no hashCode() defined, then it uses Object class's hashCode() method which uses memory address.
        Here String class has own hashCode() and equals() method, where it compares -> key1.equals(key2) -> true
        it treats them as one key (so one entry overridden).
        So hashMap.size() is typically 1; hashMap.get(key1) and hashMap.get(key2) both return the latest value (here “world2”). */

        // using IdentityHashMap
        Map<String,String > identityMap = new IdentityHashMap<>();
        identityMap.put(key1,"world3");
        identityMap.put(key2,"world4");

        System.out.println(identityMap); // {hello=world4, hello=world3}
        System.out.println("Identity Hashmap size : "+ identityMap.size()); // Identity Hashmap size : 2

        /*In the IdentityHashMap block:
        Same scenario with key1 and key2 (distinct objects, same content).
        IdentityHashMap uses Identity Hashcode (Object Class's hashCode() method) &
        reference equality (==) rather than equals() to compare keys.

        Since key1 != key2, they are treated as two separate keys.
        So identityMap.size() is typically 2; identityMap.get(key1) returns “world3”,
        and identityMap.get(key4) returns “world4”.  */

        System.out.println(identityMap.get("hello")); // null
        System.out.println(identityMap.get(key1)); // word3
        System.out.println(identityMap.get(key2)); // word4

        // Identity Hash code : refers to the hash based on the object's identity (==) rather than its value (equals).
        String s1= new String("hello");
        String s2= new String("hello");
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
        // Even though both strings contain "hello",
        // identity hash codes will be different (because they are two separate objects in heap).

    }
}

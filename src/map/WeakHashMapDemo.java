package map;

import java.util.WeakHashMap;

class Image{
    String name;

    public Image(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
public class WeakHashMapDemo {
    // WeakHashMap<K, V> is a hash table–based implementation of Map where keys are held using weak references
    // instead of strong references like in HashMap

    public static void main(String[] args) {
        WeakHashMap<Integer,Image> imageCache = new WeakHashMap<>();
        Integer key1=1001, key2=1002;

        imageCache.put(key1,new Image("Image 1")); // if we use normal "example"-> it's a strong reference which is stored in String Pool
        imageCache.put(key2,new Image("Image 2"));

        System.out.println("Before running:\n"+imageCache);

        key1 = null; // Remove strong reference for key1
        System.gc(); // Ask JVM to run GC (not guaranteed, but usually works in demos)

        runApplication(); // Give GC a bit of time

        System.out.println("cache after running (some entries may be deleted)\n"+ imageCache) ;
    }

    private static void runApplication() {
        try {
            System.out.println("Application is running ..............");
            Thread.sleep(10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

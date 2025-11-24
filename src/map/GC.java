package map;

import java.lang.ref.WeakReference;

class Phone{
    String brand;
    String model;

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
public class GC {
    public static void main(String[] args) {
        Phone phone1=new Phone("Apple","iphone 17 pro max"); // here we are creating an object and assign a memory allocation in heap memory, and phone1 is the reference which is storing the memory address
        System.out.println(phone1);
        // now if we make phone1 as null
        phone1=null; // then the allocated memory will be never used, that will be wasted
        // now garbage collection comes to the picture
        System.gc(); // no need to mention it manually, jvm will do it automatically -> it suggests jvm to run garbage collection
        System.out.println(phone1);


        // WeakReference: we can store inside WeakReference, it can be used as caching, gc can clean the reference if not required
        WeakReference<Phone> phoneWeakReference=new WeakReference<>(new Phone("Nokia","1300"));
        System.out.println(phoneWeakReference.get());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.gc();
        System.out.println(phoneWeakReference.get());
    }
}

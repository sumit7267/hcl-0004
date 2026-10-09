
package OOPS;

// Parent class
class Animal {
    String type = "Animal";

    void eat() {
        System.out.println("Animal khana khata hai");
    }

    void sound() {
        System.out.println("Animal awaaz karta hai");
    }
}

// Single inheritance: Animal -> Dog
class Dog extends Animal {
    void bark() {
        System.out.println("Dog bhokta hai");
    }

    // Method overriding: parent ka method apne tareeke se likha
    @Override
    void sound() {
        System.out.println("Dog: Bhow Bhow");
    }

    void show() {
        System.out.println(super.type);   // super se parent ka variable
        super.sound();                    // super se parent ka method
    }
}

// Multilevel inheritance: Animal -> Dog -> Puppy
class Puppy extends Dog {
    void weep() {
        System.out.println("Puppy rota hai");
    }
}

// Hierarchical inheritance: Animal -> Cat (Dog ke saath)
class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat: Meow");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("--- Single ---");
        Dog d = new Dog();
        d.eat();        // parent se mila
        d.bark();       // apna
        d.sound();      // overridden
        d.show();

        System.out.println("--- Multilevel ---");
        Puppy p = new Puppy();
        p.eat();        // Animal se
        p.bark();       // Dog se
        p.weep();       // apna

        System.out.println("--- Hierarchical ---");
        Cat c = new Cat();
        c.eat();        // Animal se
        c.sound();      // overridden
    }
}

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


class Dog extends Animal {
    void bark() {
        System.out.println("Dog bhokta hai");
    }

    // Method overriding
    @Override
    void sound() {
        System.out.println("Dog: Bhow Bhow");
    }

    void show() {
        System.out.println(super.type);
        super.sound();
    }
}

class Puppy extends Dog {
    void weep() {
        System.out.println("Puppy rota hai");
    }
}


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
        d.eat();
        d.bark();
        d.sound();
        d.show();

        System.out.println("--- Multilevel ---");
        Puppy p = new Puppy();
        p.eat();
        p.bark();
        p.weep();

        System.out.println("--- Hierarchical ---");
        Cat c = new Cat();
        c.eat();
        c.sound();
    }
}
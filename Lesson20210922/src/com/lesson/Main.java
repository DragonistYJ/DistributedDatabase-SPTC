package com.lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu Jian
 * @create 2021/9/1 10:48
 */

public class Main {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Alice"));
        dogs.add(new Dog("Bob", false));
        dogs.add(new Dog("Catlin"));
        dogs.add(new Dog("Dot"));
        dogs.add(new Dog("Eggy", false));
        dogs.add(new Dog("Fashion"));

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("CAlice"));
        cats.add(new Cat("CBob", false));
        cats.add(new Cat("CCatlin"));
        cats.add(new Cat("CDot"));
        cats.add(new Cat("CEggy", false));
        cats.add(new Cat("CFashion"));

        List<Animal> animals = new ArrayList<>();
        animals.addAll(dogs);
        animals.addAll(cats);

        Hospital<Animal> hospital = new Hospital<>();
        for (Animal animal : animals) {
            if (!animal.isHealthy()) {
                System.out.println(animal.getName());
                hospital.treat(animal);
            }
        }

        animals.parallelStream()
                .filter(animal -> !animal.isHealthy())
                .forEach(animal -> {
                    System.out.println(animal.getName());
                    hospital.treat(animal);
                });


        for (int i = 0; i < dogs.size(); i++) {
            if (!dogs.get(i).isHealthy()) {
                System.out.println(dogs.get(i).getName());
            }
            if (!cats.get(i).isHealthy()) {
                System.out.println(cats.get(i).getName());
            }
        }

        for (int i = 0; i < animals.size(); i++) {
            if (!animals.get(i).isHealthy()) {
                System.out.println(animals.get(i).getName());
            }
        }

        for (Dog dog : dogs) {
            if (!dog.isHealthy()) {
                System.out.println(dog.getName());
            }
        }
        for (Cat cat : cats) {
            if (!cat.isHealthy()) {
                System.out.println(cat.getName());
            }
        }
    }
}

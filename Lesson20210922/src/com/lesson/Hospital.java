package com.lesson;

/**
 * @author Yu Jian
 * @create 2021/9/22 10:46
 */

public class Hospital<T> {
    public void treat(T patient) {
        if (patient instanceof Animal) {
            ((Animal) patient).setHealthy(true);
            System.out.println(((Animal) patient).getName() + " has been treated");
        }
    }
}

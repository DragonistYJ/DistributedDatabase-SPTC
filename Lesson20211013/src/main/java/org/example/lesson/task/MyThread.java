package org.example.lesson.task;

import java.util.ArrayList;
import java.util.List;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getId() + " " + i);
        }
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new MyThread());
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

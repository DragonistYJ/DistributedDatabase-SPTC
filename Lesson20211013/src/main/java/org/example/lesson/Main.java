package org.example.lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        OnlineStore store = new OnlineStore(1000L);

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            final int innerI = i;
            Thread thread = new Thread(() -> {
                long phoneNum = store.salePhone();
                if (phoneNum >= 0) {
                    System.out.println("Buyer " + innerI + " has bought successfully");
                    System.out.println("Store has " + phoneNum + " remind");
                } else {
                    System.out.println("Sorry Buyer " + innerI + " failed bought");
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}

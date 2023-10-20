package com.company.zadanie1;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {

        Dekker thread1 = new Dekker(0,true,100);
        Dekker thread2 = new Dekker(1,true,100);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException p) {
            p.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException r) {
            r.printStackTrace();
        }
        System.out.println("Koniec");

    }
}

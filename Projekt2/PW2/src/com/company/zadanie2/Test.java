package com.company.zadanie2;

public class Test {
    public static void main(String[] args) {
        Peterson thread1 = new Peterson(0,true,100);
        Peterson thread2 = new Peterson(1,true,100);

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

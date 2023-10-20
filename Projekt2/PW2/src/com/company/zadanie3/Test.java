package com.company.zadanie3;

public class Test {
    public static void main(String[] args) {

        Lamport thread1 = new Lamport(0,true,100);
        Lamport thread2 = new Lamport(1,true,100);
        Lamport thread3 = new Lamport(2,true,100);
        Lamport thread4 = new Lamport(3,true,100);
        Lamport thread5 = new Lamport(4,true,100);


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


    }
}

package com.company.zadanie4;

public class Klasa {
    public static void main(String[] args) {
        Sem thread1 = new Sem(0,true,100);
        Sem thread2 = new Sem(1,true,100);
        Sem thread3 = new Sem(2,true,100);
        Sem thread4 = new Sem(3,true,100);
        Sem thread5 = new Sem(4,true,100);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }
}

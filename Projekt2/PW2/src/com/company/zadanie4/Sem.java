package com.company.zadanie4;

import java.util.Random;
import java.util.concurrent.Semaphore;


public class Sem extends Thread{
    private int nr;
    private boolean prog;
    private int N;
    public static volatile Semaphore sem = new Semaphore(1);
    public static volatile char[]znaki = {'+','-','*','/','^'};
    Random random = new Random();

    Sem(int nr,boolean prog,int N) {
        super("Semafor-"+nr);
        this.nr = nr;
        this.prog = prog;
        this.N=N;
    }

    @Override
    public void run() {
        if(prog) {
            dzialanieSynchr(nr);
        }
        else {
            dzialanieNiesynchr(nr);
        }
    }
    public void dzialanieNiesynchr(int nr){
        for(int i = 0;i<N;i++) {
            //sekcja lokalna
            try {
                Thread.sleep(random.nextInt(10)+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //sekcja krytyczna
            System.out.println("Sekcja krytyczna watku: "+getName()+", nr.powt="+i);

            for(int j=0;j<100;j++) {
                System.out.print(znaki[nr]);
            }

            System.out.println();
        }

    }

    public void dzialanieSynchr(int nr) {
        for(int i = 0;i<N;i++) {
            //sekcja lokalna
            try {
                Thread.sleep(random.nextInt(10)+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                sem.acquire();
                System.out.println("Sekcja krytyczna watku: "+getName()+", nr.powt="+i);
                //sekcja krytyczna
                for(int j=0;j<100;j++) {
                    System.out.print(znaki[nr]);
                }
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            sem.release();
            System.out.println();
        }
    }
}
package com.company.zadanie3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Lamport extends Thread {

    private int nr;
    private boolean prog;
    private int N;
    public static volatile boolean[]wybieranie = {false,false,false,false,false};
    public static volatile int[] numerek= {0,0,0,0,0};
    public static volatile char[]znaki = {'+','-','*','/','^'};
    Random random = new Random();
    Lamport(int nr,boolean prog,int N) {
        super("Lamport-"+nr);
        this.nr = nr;
        this.prog = prog;
        this.N=N;
    }

    @Override
    public void run() {
        if(prog) {
            dzialanieSynchr(nr);
        }
        else{
            dzialanieNiesynchr(nr);
        }
    }
    public void dzialanieSynchr(int nr) {
        for(int i=0;i<N;i++) {
            //Sekcja lokalna
            try {
                Thread.sleep(random.nextInt(10)+1);
                //System.out.println("�pie");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wybieranie[nr] = true;
            numerek[nr] = MAX(numerek) + 1;
            wybieranie[nr] = false;
            for(int j=0;j<5;j++) {
                while (wybieranie[j]) {
                    //czekaj
                }

                while (numerek[j] != 0 && numerek[j]<numerek[nr]){
                    //czekaj
                }
                if (numerek[j] == numerek[nr]) {
                    while(numerek[j] != 0 && j<(nr)) {
                        //czekaj
                    }
                }
            }
            //sekacja krytyczna
            System.out.println("Sekcja krytyczna watku: "+getName()+", nr.powt="+i);

            for(int j=0;j<100;j++) {
                System.out.print(znaki[nr]);
            }

            System.out.println();
            numerek[nr] = 0;
        }
    }
    public int MAX(int array[]) {
        int max;
        max = array[0];
        for(int i : array) {
            if (i>max) {
                max = i;
            }
        }
        return max;
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

}

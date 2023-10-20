package com.company.zadanie2;
import java.util.Random;

public class Peterson extends Thread {

    private int nr;
    private boolean prog;
    private int N;
    private int druga;
    public static volatile boolean[]chce = {false,false};
    public static volatile int czyjaKolej = 0;
    public static volatile char[]znaki = {'+','-'};
    Random random = new Random();

    Peterson(int nr,boolean prog,int N){
        super("Person-"+nr);
        this.nr = nr;
        this.prog = prog;
        this.N=N;
    }
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
                System.out.print(znaki[nr-1]);
            }

            System.out.println();
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
            druga = (nr+1)%2;
            chce[nr] = true;
            czyjaKolej = druga;
            while (chce[druga] == true && czyjaKolej == druga) {
                //czekanie
            }

            //sekcja krytyczna
            System.out.println("Sekcja krytyczna watku: "+getName()+", nr.powt="+i);

            for(int j=0;j<100;j++) {
                System.out.print(znaki[nr]);
            }

            System.out.println();
            System.out.println();
            chce[nr] = false;
        }
    }

}

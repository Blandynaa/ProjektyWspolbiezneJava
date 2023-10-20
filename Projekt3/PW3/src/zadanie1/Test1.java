package zadanie1;

import java.util.concurrent.Semaphore;

public class Test1 {
    public static void main(String[] args) throws InterruptedException
    {
        int rozmiar_bufora = 10;
        Semaphore wolne = new Semaphore(rozmiar_bufora);
        Semaphore zajete = new Semaphore(0);
        Semaphore chron_j = new Semaphore(1);
        Semaphore chron_k = new Semaphore(1);
        String[]bufor = new String[10];
        int j=1;
        int k=1;
        Thread[] Producenci = new Thread[4];
        Thread[] Konsumenci = new Thread[5];

        for(int i=0;i<5;i++) //create
        {
            if(i<4){
                Producenci[i] = new Producent(rozmiar_bufora, j, i, wolne, zajete, chron_j, bufor);
            }
            Konsumenci[i] = new Konsument(rozmiar_bufora, k, i, wolne, zajete, chron_k, bufor);
        }

        for(int i=0;i<5;i++) //start
        {
            if(i<4){
                Producenci[i].start();
            }
            Konsumenci[i].start();
        }

        try {
            Thread.sleep(1000);
            for(int i=0;i<5;i++)
            {
                if(i<4){
                    Producenci[i].interrupt();
                }
                Konsumenci[i].interrupt();
            }
            for(int i=0;i<5;i++)
            {
                if(i<4){
                    Producenci[i].join();
                }
                Konsumenci[i].join();
            }
        } catch (InterruptedException e) {
        }



        System.out.println("Koniec");

    }
}

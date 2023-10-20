package zadanie2;

import java.util.concurrent.Semaphore;

public class Test2 {
    public static void main(String[] args) throws InterruptedException
    {
        Semaphore czyt = new Semaphore(0);
        Semaphore pis = new Semaphore(0);
        Semaphore chron = new Semaphore(1);
        Semaphore mutex_pis = new Semaphore(1);

        Osoby osoby = new Osoby();
        Thread[] Czytelnicy = new Thread[4];
        Thread[] Pisarze = new Thread[2];

        for(int i=0;i<4;i++)
        {
            Czytelnicy[i] = new Czytelnik(i, chron, mutex_pis, pis, czyt, osoby);
            if(i<2) Pisarze[i] = new Pisarz(i, chron, mutex_pis, pis, czyt, osoby);
        }

        for(int i=0;i<4;i++)
        {
            Czytelnicy[i].start();
            if(i<2) Pisarze[i].start();
        }

        try {
            Thread.sleep(1000);
            for(int i=0;i<4;i++)
            {
                Czytelnicy[i].interrupt();
                if(i<2) Pisarze[i].interrupt();
            }
            for(int i=0;i<4;i++)
            {
                Czytelnicy[i].join();
                if(i<2) Pisarze[i].join();
            }
        } catch (InterruptedException e) {
        }



        System.out.println("Koniec");
    }
}

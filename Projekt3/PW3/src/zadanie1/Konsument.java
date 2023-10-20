package zadanie1;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Konsument extends Thread{
    Random los = new Random();
    private int nr;
    private int rozmiar_bufora;
    private String dana;
    public static int k;
    public volatile static String[] bufor;
    public volatile static Semaphore wolne, zajete, chron_k;

    Konsument(int rozmiar_bufora, int k, int nr, Semaphore wolne, Semaphore zajete, Semaphore chron_k, String bufor[])
    {
        super(String.valueOf(nr));
        this.nr = nr;
        this.k = k;
        this.wolne = wolne;
        this.chron_k = chron_k;
        this.zajete = zajete;
        this.bufor = bufor;
        this.rozmiar_bufora = rozmiar_bufora;
    }
    @Override
    public void run()
    {
        for(int i=0;i<400;i++)
        {
            try
            {
                zajete.acquire();
                chron_k.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            dana = bufor[k];
            wolne.release();
            k=(k+1)%rozmiar_bufora;
            chron_k.release();
            try
            {
                sleep(los.nextInt(11)+2);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("[K-"+nr+", "+i+", "+k+"] :: "+dana);
        }
    }
}

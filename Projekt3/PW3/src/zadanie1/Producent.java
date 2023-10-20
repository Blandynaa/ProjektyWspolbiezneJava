package zadanie1;

import static java.lang.String.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producent extends Thread
{
    Random los = new Random();
    private String temp;
    private int nr;
    private int rozmiar_bufora;
    public volatile static int j;
    public volatile static String[] bufor;
    public volatile static Semaphore wolne, zajete, chron_j;
    Producent(int rozmiar_bufora, int j, int nr, Semaphore wolne, Semaphore zajete, Semaphore chron_j, String bufor[])
    {
        super(valueOf(nr));
        this.nr = nr;
        this.j = j;
        this.wolne = wolne;
        this.chron_j = chron_j;
        this.zajete = zajete;
        this.bufor = bufor;
        this.rozmiar_bufora = rozmiar_bufora;
    }

    @Override
    public void run()
    {
        for(int i=0;i<500;i++)
        {
            try
            {
                Thread.sleep(los.nextInt(10)+1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            temp = "Dana = [P-"+nr+", "+i+", "+j+", "+los.nextInt(100)+"]";
            try
            {
                wolne.acquire();
                chron_j.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
                bufor[j]=temp;
                zajete.release();
                j=(j+1)%rozmiar_bufora;
                chron_j.release();
        }
    }
}

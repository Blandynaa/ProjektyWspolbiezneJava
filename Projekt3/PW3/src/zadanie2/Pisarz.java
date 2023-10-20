package zadanie2;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Pisarz extends Thread
{
    Random los = new Random();
    int nr;
    public Osoby osoby;
    public volatile static Semaphore chron, mutex_pis, pis, czyt;

    Pisarz(int nr, Semaphore chron, Semaphore mutex_pis, Semaphore pis, Semaphore czyt, Osoby osoby)
    {
        this.nr=nr;
        this.chron = chron;
        this.mutex_pis = mutex_pis;
        this.pis = pis;
        this.czyt = czyt;
        this.osoby = osoby;
    }
    @Override
    public void run()
    {
        for(int i=0;i<100;i++)
        {
            try
            {
                Thread.sleep(los.nextInt(11)+5);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            try
            {
                chron.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            osoby.ap++;
            if(osoby.ac==0)
            {
                osoby.dp++;
                System.out.println(">>> [P-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
                pis.release();
            }
            chron.release();
            try
            {
                pis.acquire();
                mutex_pis.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("==> [P-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
            try
            {
                Thread.sleep(los.nextInt(5)+1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("<== [P-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
            mutex_pis.release();
            try
            {
                chron.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            osoby.dp--;
            System.out.println("<<< [P-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
            osoby.ap--;
            if(osoby.dp==0)
            {
                while(osoby.dc<osoby.ac)
                {
                    osoby.dc++;
                    czyt.release();
                }
            }
            chron.release();
        }
    }
}

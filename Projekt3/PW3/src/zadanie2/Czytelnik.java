package zadanie2;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Czytelnik extends Thread{
    Random los = new Random();
    int nr;
    public Osoby osoby;
    public volatile static Semaphore chron, mutex_pis, pis, czyt;

    Czytelnik(int nr, Semaphore chron, Semaphore mutex_pis, Semaphore pis, Semaphore czyt, Osoby osoby)
    {
        this.nr=nr;
        this.chron = chron;
        this.mutex_pis = mutex_pis;
        this.pis = pis;
        this.czyt = czyt;
        this.osoby = osoby;
    }

    @Override
    public void run() {
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
            osoby.ac++;
            if(osoby.ap==0)
            {
                osoby.dc++;
                System.out.println(">>> [C-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
                czyt.release();
            }
            chron.release();
            try
            {
                czyt.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("==> [C-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
            try
            {
                Thread.sleep(los.nextInt(5)+1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("<== [C-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
            try
            {
             chron.acquire();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            osoby.dc--;
            System.out.println("<<< [C-"+nr+", "+i+"] :: [licz_czyt="+osoby.ac+", licz_czyt_pocz="+osoby.dc+", licz_pis="+osoby.ap+", licz_pis_pocz="+osoby.dp+"]");
            osoby.ac--;
            if(osoby.dc==0)
            {
                while(osoby.dp<osoby.ap)
                {
                    osoby.dp++;
                    pis.release();
                }
            }
            chron.release();
        }
    }
}

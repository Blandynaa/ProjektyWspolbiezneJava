package zadanie4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filozof extends Thread{
    private int id, ilosc, rep;
    private volatile  static Semaphore dopusc;
    private volatile static Semaphore[] widelec;

    public Filozof(int id, int rep, int ilosc, Semaphore dopusc) {
        this.id = id;
        this.rep = rep;
        this.ilosc = ilosc;
        this.dopusc = dopusc;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (int i = 0; i < rep; i++) {

            try {
                Thread.sleep(random.nextInt(11) + 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                dopusc.acquire();
                widelec[id].acquire();
                widelec[(id + 1) % ilosc].acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">>> [F-" + id + ", " + i + "] :: [fil_przy_stole=2, w[0]=" + widelec[0].availablePermits() + ", w[1]=" + widelec[1].availablePermits() + ", w[2]=" + widelec[2].availablePermits() + ", w[3]=" + widelec[3].availablePermits() + ", w[4]=" + widelec[4].availablePermits() + "]");

            try {
                Thread.sleep(random.nextInt(5) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("<<< [F-" + id + ", " + i + "] :: [fil_przy_stole=2, w[0]=" + widelec[0].availablePermits() + ", w[1]=" + widelec[1].availablePermits() + ", w[2]=" + widelec[2].availablePermits() + ", w[3]=" + widelec[3].availablePermits() + ", w[4]=" + widelec[4].availablePermits() + "]");

            widelec[id].release();
            widelec[(id + 1) % ilosc].release();
            dopusc.release();
        }


    }

    public static void setWidelec(Semaphore[] widelec) {
        Filozof.widelec = widelec;
    }
}

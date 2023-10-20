package zadanie3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Czytelnik extends Thread{
    private int id, rep, l_czyt, l_pis, K, M;
    private Semaphore wolne;

    public Czytelnik(int id, int rep, Semaphore wolne, int l_czyt, int l_pis, int K, int M) {
        this.id = id;
        this.K = K;
        this.M = M;
        this.l_czyt = l_czyt;
        this.l_pis = l_pis;
        this.rep = rep;
        this.wolne = wolne;

    }

    @Override
    public void run() {

        Random random = new Random();

        for (int i = 0; i < rep; i++) {
            try {
                Thread.sleep(random.nextInt(5) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                wolne.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            l_czyt++;

            System.out.println(">>> [C-" + id + ", " + i + "] :: [licz_czyt=" + l_czyt + ", licz_pis=" + l_pis + "]");

            try {
                Thread.sleep(random.nextInt(5)+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            l_czyt--;
            System.out.println("<<< [C-" + id + ", " + i + "] :: [licz_czyt=" + l_czyt + ", licz_pis=" + l_pis + "]");
            wolne.release();
        }
    }
}

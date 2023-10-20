package zadanie3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Pisarz extends Thread{
    private int id, rep, M, l_czyt, l_pis;
    private volatile static Semaphore wolne, pis;

    public Pisarz(int id, int rep, Semaphore wolne, int l_czyt, int l_pis, Semaphore pis, int M, int K) {
        this.id = id;
        this.rep = rep;
        this.l_czyt = l_czyt;
        this.l_pis = l_pis;
        this.wolne = wolne;
        this.pis = pis;
        this.M = M;
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
                pis.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int j = 0; j < M; j++) {

                try {
                    wolne.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            l_pis = 1;
            System.out.println("==> [P-" + id + ", " + i + "] :: [licz_czyt=" + l_czyt + ", licz_pis=" + l_pis + "]");
            try {
                Thread.sleep(random.nextInt(5)+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            for (int j = 0; j < M; j++) {
                wolne.release();

            }
            l_pis = 0;
            System.out.println("<== [P-" + id + ", " + i + "] :: [licz_czyt=" + l_czyt + ", licz_pis=" + l_pis + "]");
            pis.release();
        }
    }
}

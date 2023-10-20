package zadanie3;

import java.util.concurrent.Semaphore;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        int M = 5;
        int K = 3;
        int l_czyt = 0;
        int l_pis = 0;

        Semaphore wolne = new Semaphore(M);
        Semaphore pis = new Semaphore(1);

        Czytelnik[] czytelniks = new Czytelnik[5];
        Pisarz[] pisarzs = new Pisarz[2];

        for (int i = 0; i < 2; i++) {
            Pisarz pisarz = new Pisarz(i, 100, wolne, l_czyt, l_pis, pis, M, K);
            pisarzs[i] = pisarz;
        }
        for (int i = 0; i < 5; i++) {
            Czytelnik czytelnik = new Czytelnik(i, 100, wolne, l_czyt, l_pis, K,M);
            czytelniks[i] = czytelnik;
        }
        for (int i = 0; i < 2; i++) {
            pisarzs[i].start();
        }
        for (int i = 0; i < M; i++) {
            czytelniks[i].start();
        }


        try {
            Thread.sleep(1000);
            for (int i = 0; i < 2; i++) {
                pisarzs[i].interrupt();
            }
            for (int i = 0; i < M; i++) {
                czytelniks[i].interrupt();
            }

            for (int i = 0; i < 2; i++) {
                pisarzs[i].join();
            }
            for (int i = 0; i < M; i++) {
                czytelniks[i].join();
            }
        } catch (InterruptedException e) {
        }


        System.out.println("Koniec programu");
    }
}

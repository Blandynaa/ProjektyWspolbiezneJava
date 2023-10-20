package zadanie3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Widelce {
    private Condition[] widelec;
    private int[] zajety;
    private Lock lock = new ReentrantLock();
    private Condition lokaj = lock.newCondition();
    private int jest;
    private int N;

    public Widelce() {
        this.N = 5;
        this.zajety = new int[N];
        this.widelec = new Condition[N];
        this.jest = 0;

        for (int i = 0; i < N; i++) {
            this.zajety[i] = 0;
            this.widelec[i] = lock.newCondition();
        }

    }

    public void WEZ(int nr, int i) {
        lock.lock();
        try {
            System.out.println(">>> (1) [F-" + nr + ", " + i + "] :: [" + zajety[0] + ", " + zajety[1] + ", " + zajety[2] + ", " + zajety[3] + ", " + zajety[4] + "] - " + jest);
            while (jest == 4) {
                lokaj.await();
            }
            jest++;
            while (zajety[nr]==1) {
                widelec[nr].await();

            }
            zajety[nr] = 1;
            while (zajety[(nr + 1) % N]==1) {
                widelec[(nr + 1) % N].await();
            }
            zajety[(nr + 1) % N] = 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(">>> (2) [F-" + nr + ", " + i + "] :: [" + zajety[0] + ", " + zajety[1] + ", " + zajety[2] + ", " + zajety[3] + ", " + zajety[4] + "] - " + jest);
        } finally {
            lock.unlock();
        }

    }

    public void ODLOZ(int nr, int i) {
        lock.lock();
        try {
            System.out.println("<<< (1) [F-" + nr + ", " + i + "] :: [" + zajety[0] + ", " + zajety[1] + ", " + zajety[2] + ", " + zajety[3] + ", " + zajety[4] + "] - " + jest);
            zajety[nr] = 0;
            widelec[nr].signal();
            zajety[(nr + 1) % N] = 0;
            widelec[(nr + 1) % N].signal();
            jest--;
            lokaj.signal();
            System.out.println("<<< (2) [F-" + nr + ", " + i + "] :: [" + zajety[0] + ", " + zajety[1] + ", " + zajety[2] + ", " + zajety[3] + ", " + zajety[4] + "] - " + jest);
        } finally {
            lock.unlock();
        }

    }
    public int[] getZajety() {
        return zajety;
    }
}

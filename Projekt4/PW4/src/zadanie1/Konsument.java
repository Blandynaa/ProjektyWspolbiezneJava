package zadanie1;

import java.util.Random;

public class Konsument extends Thread{
    private int id;
    private Buffer monitor;
    private int rep;

    public Konsument(int id, int rep, Buffer monitor) {
        this.id = id;
        this.monitor = monitor;
        this.rep = rep;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < rep; i++) {
            try {
                Thread.sleep(random.nextInt(11) + 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String wartosc = monitor.pobierz();
            System.out.println("[K-" + id + ", " + i + "] >> " + wartosc);
        }

    }
}

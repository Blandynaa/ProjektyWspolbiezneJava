package zadanie1;

import java.util.Random;

public class Producent extends Thread{
    private int id;
    private Buffer monitor;
    private int rep;


    public Producent(int id, int rep, Buffer monitor) {
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

            int wartosc = random.nextInt(100);
            String dana = "Dana=[" + "P-" + id + ", " + i + ", " + wartosc + "]";
            monitor.wstaw(dana);
        }

    }
}

package zadanie4;

import java.util.concurrent.Semaphore;

public class Test4 {
    public static void main(String[] args) throws InterruptedException {

        Semaphore dopusc = new Semaphore(5);

        Filozof[] filozofs = new Filozof[5];

        Semaphore[] widelec = new Semaphore[5];

        for(int i = 0; i< widelec.length;i++){
            widelec[i] = new Semaphore(1);
        }
        Filozof.setWidelec(widelec);


        for (int i = 0; i < 5; i++) {
            Filozof filozof = new Filozof(i, 100, 5, dopusc);
            filozofs[i] = filozof;
        }

        for (int i = 0; i < 5; i++) {
            filozofs[i].start();
        }


        try {
            Thread.sleep(1000);
            for (int i = 0; i < 5; i++) {
                filozofs[i].interrupt();
            }
            for (int i = 0; i < 5; i++) {
                filozofs[i].join();
            }
        } catch (InterruptedException e) {
        }



        System.out.println("Koniec programu");
    }
}

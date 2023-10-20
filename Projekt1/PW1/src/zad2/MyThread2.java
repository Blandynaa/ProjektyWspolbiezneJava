package zad2;

public class MyThread2 implements Runnable {
    public MyThread2() {
    }

    public void run() {
        for(int i = 0; i < 10; ++i) {
            System.out.println("Pozdrowienia z wątku " + Thread.currentThread().getName());
        }

    }
}

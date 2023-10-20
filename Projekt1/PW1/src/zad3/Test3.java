package zad3;

public class Test3 {
    public Test3() {
    }

    public static void main(String[] args) {
        Thread watek = new MyThread4("Thread4");
        watek.start();

        try {
            Thread.sleep(4500L);
            watek.interrupt();
            watek.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("Koniec");
    }
}

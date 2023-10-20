package zad2;

public class Test2 {
    public Test2() {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] watki = new Thread[10];

        for(int i = 0; i < 10; ++i) {
            MyThread2 t = new MyThread2();
            watki[i] = new Thread(t, "" + i);
        }
        for(int i = 0; i < 10; ++i) watki[i].start();
        for(int i = 0; i < 10; ++i) watki[i].join();

        System.out.println("Koniec");
    }
}

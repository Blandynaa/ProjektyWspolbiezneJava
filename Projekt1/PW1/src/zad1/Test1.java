package zad1;

public class Test1
{

    public Test1() {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] watki = new Thread[10];
        for(int i = 0; i < watki.length; ++i) {
            watki[i] = new MyThread1("" + i);
        }
        for(int i = 0; i < 10; ++i) watki[i].start();
        for(int i = 0; i < 10; ++i) watki[i].join();

        System.out.println("Koniec");
    }
}

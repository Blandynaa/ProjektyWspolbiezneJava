package zad4;

public class Test4
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread[] watki = new Thread[10];
        Licznik licznik = new Licznik();
        for (int i = 0; i < watki.length; i++) watki[i] =new MyThread5("Watek " + i, licznik);
        for (int i = 0; i < watki.length; i++) watki[i].start();
        for (int i = 0; i < watki.length; i++) watki[i].join();
        System.out.println("Stan licznika = " + licznik.getLicznik());
    }
}

package zad3;

public class MyThread4 extends Thread
{
    public MyThread4(String name) {
        super(name);
    }

    public void run() {
        Thread watek = new MyThread3("Thread3");
        watek.start();
        for(int i = 0; i < 5; ++i) {
            try
            {
                Thread.sleep(2000L);
                System.out.println("Spalem przez 2s " + this.getName());
            }
            catch (InterruptedException e2)
            {
                System.out.println("Zostalem obudzony " + this.getName());
                watek.interrupt();
                try
                {
                    watek.join();
                }
                catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                return;
            }
        }

    }
}

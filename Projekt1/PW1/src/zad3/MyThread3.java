package zad3;

public class MyThread3 extends Thread {
    public MyThread3(String name) {
        super(name);
    }

    public void run() {
        for(int i = 0; i < 10; ++i) {
            super.run();
            try
            {
                Thread.sleep(1000L);
                System.out.println("Spalem przez 1s " + this.getName());
            }
            catch (InterruptedException e1)
            {
                System.out.println("Zostalem obudzony " + this.getName());
                return;
            }
        }

    }
}

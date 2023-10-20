package zadanie1;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        int N = 10;

        Buffer monitor = new Buffer(N);
        Producent[] producents = new Producent[4];
        Konsument[] konsuments = new Konsument[5];

        for (int i = 0; i < 4; i++) {
            Producent producent = new Producent(i, 500, monitor);
            producents[i] = producent;
            producent.start();
        }

        for (int i = 0; i < 5; i++) {
            Konsument konsument = new Konsument(i, 400, monitor);
            konsuments[i] = konsument;
            konsument.start();
        }

        for (int i = 0; i < 4; i++) {
            producents[i].join();
        }

        for (int i = 0; i < 5; i++) {
            konsuments[i].join();
        }

        System.out.println("Koniec programu");
    }
}

package zadanie2;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Czytelnia czytelnia = new Czytelnia();

        Czytelnik[] czytelniks = new Czytelnik[4];
        Pisarz[] pisarzs = new Pisarz[2];

        for (int i = 0; i < 2; i++) {
            Pisarz pisarz = new Pisarz(i, 100, czytelnia);
            pisarzs[i] = pisarz;
        }

        for (int i = 0; i < 4; i++) {
            Czytelnik czytelnik = new Czytelnik(i,100,czytelnia);
            czytelniks[i] = czytelnik;
        }

        for (int i = 0; i < 2; i++) {
            pisarzs[i].start();
        }

        for (int i = 0; i < 4; i++) {
            czytelniks[i].start();
        }

        for (int i = 0; i < 2; i++) {
            pisarzs[i].join();
        }

        for (int i = 0; i < 4; i++) {
            czytelniks[i].join();
        }


        System.out.println("Koniec programu");
    }
}

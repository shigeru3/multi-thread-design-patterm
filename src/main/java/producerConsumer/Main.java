package producerConsumer;

/**
 * Created by ikawa on 2015/10/24.
 */
public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MakerThread("Sakai", table, 34564).start();
        new MakerThread("Michiba", table, 67876).start();
        new MakerThread("Chin", table, 19876).start();
        new EaterThread("Kishi", table, 97349).start();
        new EaterThread("Shigeru", table, 34698).start();
        new EaterThread("Tomoaki", table, 94372).start();
    }
}

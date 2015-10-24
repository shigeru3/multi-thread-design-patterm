package producerConsumer;

import java.util.Random;

/**
 * Created by ikawa on 2015/10/24.
 */
public class MakerThread extends Thread {
    private final Random random;
    private final Table table;
    private static int id = 0;
    public MakerThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[ Cake No." + nextId() + " by " + getName() + " ]";
                table.put(cake);
            }
        } catch (InterruptedException e) {
        }
    }

    private static synchronized int nextId() {
        return id++;
    }
}

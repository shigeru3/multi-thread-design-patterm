package readWriteLockPattern;

import java.util.Random;

/**
 * Created by ikawa on 2016/05/05.
 */
public class WriterThread extends Thread {
    private static final Random random = new Random();
    private final Data data;
    private final String filter;
    private int index = 0;

    public WriterThread(Data data, String filter) {
        this.data = data;
        this.filter = filter;
    }

    public void run() {
        try {
            while (true) {
                char c = nextchar();
                data.write(c);
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {

        }
    }

    private char nextchar() {
        char c = filter.charAt(index);
        index++;
        if (index >= filter.length()) {
            index = 0;
        }
        return c;
    }
}

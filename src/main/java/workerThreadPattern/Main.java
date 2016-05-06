package workerThreadPattern;

/**
 * Created by ikawa on 2016/05/06.
 */
public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();
        new ClientThread("Ando", channel).start();
        new ClientThread("Ishida", channel).start();
        new ClientThread("Ueda", channel).start();
    }
}

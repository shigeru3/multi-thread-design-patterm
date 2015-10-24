package guardedSuspension;

/**
 * Created by ikawa on 2015/08/30.
 */
public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Akiko", 31415962L).start();
        new ServerThread(requestQueue, "Shigeru", 6535897L).start();
    }
}

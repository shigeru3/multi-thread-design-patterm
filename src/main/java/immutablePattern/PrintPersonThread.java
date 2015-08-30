package immutablePattern;

/**
 * Created by ikawa on 2015/08/30.
 */
public class PrintPersonThread extends Thread {
    private Person person;
    public PrintPersonThread(Person person) {
        this.person = person;
    }
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }
    }
}

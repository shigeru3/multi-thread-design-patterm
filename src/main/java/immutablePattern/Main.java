package immutablePattern;

/**
 * Created by ikawa on 2015/08/30.
 */
public class Main {
    public static void main(String[] args) {
        Person akiko = new Person("Akiko", "Akita");
        new PrintPersonThread(akiko).start();
        new PrintPersonThread(akiko).start();
        new PrintPersonThread(akiko).start();
    }
}

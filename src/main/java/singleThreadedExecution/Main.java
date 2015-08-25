package singleThreadedExecution;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing Gate, hit CTRL+C to exit.");
        Gate gate = new Gate();
        new UserThread(gate, "Akiko", "Aomori").start();
        new UserThread(gate, "Bob", "Brazil").start();
        new UserThread(gate, "Chie", "Chiba").start();
    }
}
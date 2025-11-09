

class PatternPrinter {
    int n=10;
    boolean printStar = true;


    public synchronized void printStars() {
        for (int i = 0; i < n; i++) {
            while (!printStar) {
                try { wait(); } catch (InterruptedException e) {}
            }
            System.out.print("*");
            printStar = false;
            notify();
        }
    }

    public synchronized void printDashes() {
        for (int i = 0; i < n; i++) {
            while (printStar) {
                try { wait(); } catch (InterruptedException e) {}
            }
            System.out.print("-");
            printStar = true;
            notify();
        }
    }
}

public class Main {
    public static void main(String[] args) {

        PatternPrinter pp = new PatternPrinter();

        Thread t1 = new Thread(() -> pp.printStars());
        Thread t2 = new Thread(() -> pp.printDashes());

        t1.start();
        t2.start();
    }
}

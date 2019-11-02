package buildings.threads;

import buildings.Floor;
import buildings.Space;

import java.util.concurrent.Semaphore;

public class SequentalRepairer implements Runnable {
    private Floor floor;
    private Semaphore semaphore;
    private int i;
    private boolean ready = true;

    public SequentalRepairer(int i,Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            if (ready) {
                semaphore.acquire();
                Space[] spaces = floor.getSpaces();
                System.out.println("Repaired space number " + i + " with total area " + spaces[i].getArea() + " square meters.");
                ready= false;
                semaphore.release();

            }
        } catch (InterruptedException e) {
            System.out.println("something wrong");
        }
    }
}

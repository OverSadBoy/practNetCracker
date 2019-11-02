package buildings.threads;

import buildings.Floor;
import buildings.Space;

import java.util.concurrent.Semaphore;

public class SequentalCleaner implements Runnable {
    private Floor floor;
    private Semaphore semaphore;
    private int i;
    private boolean ready = true;

    public SequentalCleaner(int i, Floor floor, Semaphore semaphore) {
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
                System.out.println("Cleaning space number " + i + " with total area " + spaces[i].getArea() + " square meters.");
                ready = false;
                semaphore.release();
            }
        } catch (InterruptedException e) {
            System.out.println("something wrong");
        }
    }
}


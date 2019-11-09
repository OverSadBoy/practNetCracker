package buildings.threads;

import buildings.Floor;
import buildings.Space;
import buildings.sup.SemaphorePlace;

import java.util.concurrent.Semaphore;

public class SequentalCleaner implements Runnable {
    private Floor floor;
    private SemaphorePlace semaphore;

    public SequentalCleaner( Floor floor, SemaphorePlace semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    public void run() {
        for (int i = 0; i<floor.getSpaceNum(); i++) {
            semaphore.enter(floor);
            System.out.println(String.format("Cleaning space number %d with total area %f square meters.",
                    i, ((Space) floor.getSpace(i)).getArea()));
            semaphore.leave(floor);
        }
        System.out.println("Cleaner has stopped working");
    }
}


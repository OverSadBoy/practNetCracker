package buildings.threads;

import buildings.Floor;
import buildings.Space;
import buildings.sup.SemaphorePlace;

public class SequentalRepairer implements Runnable {
    private Floor floor;
    private SemaphorePlace semaphore;

    public SequentalRepairer(Floor floor, SemaphorePlace semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    public void run() {
        for (int i = 0; i<floor.getSpaceNum(); i++) {
            semaphore.acquire();
            System.out.println(String.format("Repairer space number %d with total area %f square meters.",
                    i, ((Space) floor.getSpace(i)).getArea()));
            semaphore.release();
        }
        System.out.println("Repairer has stopped working");
    }
}

import buildings.dwelling.DwellingFloor;
import buildings.sup.SemaphorePlace;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        DwellingFloor dwellingFloor = new DwellingFloor(30);
        SemaphorePlace semaphorePlace = new SemaphorePlace();
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < dwellingFloor.getSpaceNum(); i++) {
            new Thread(new SequentalCleaner(i,dwellingFloor,semaphore)).start();
            new Thread(new SequentalRepairer(i,dwellingFloor,semaphore)).start();
        }
    }
}

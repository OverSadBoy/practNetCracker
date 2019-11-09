import buildings.dwelling.DwellingFloor;
import buildings.sup.SemaphorePlace;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        DwellingFloor dwellingFloor = new DwellingFloor(30);
        SemaphorePlace semaphorePlace = new SemaphorePlace(1);
        new Thread(new SequentalRepairer(dwellingFloor, semaphorePlace)).start();
        new Thread(new SequentalCleaner(dwellingFloor, semaphorePlace)).start();
    }

}

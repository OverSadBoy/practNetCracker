import buildings.Building;
import buildings.BuildingsUI;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.sup.Buildings;
import buildings.sup.SemaphorePlace;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws IOException {
        Buildings.writeBuilding(new Dwelling(3,new int[]{1,2,3}),new FileWriter("netInput.dat"));
        new BuildingsUI();
    }

}

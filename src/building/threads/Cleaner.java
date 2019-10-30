package building.threads;

import building.Floor;
import building.Space;

public class Cleaner extends  Thread{
    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getSpaces();
        for (int i = 0; i < spaces.length; i++) {
            System.out.println("Cleaning room number " + i + " with total area " + spaces[i].getArea() + " square meters.");
        }
    }
}

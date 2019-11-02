package buildings.sup;

import buildings.Building;
import buildings.Floor;

import java.util.Iterator;

public class BuildingIterator implements Iterator<Floor> {
    private int index;
    private Building building;

    public BuildingIterator(Building building) {
        this.building = building;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return building.getFloor(index+1) != null;
    }

    @Override
    public Floor next() {
        if (index < building.getFloorsNum())
            return building.getFloor(index++);
        index = 0;
        return null;
    }
}

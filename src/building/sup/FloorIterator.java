package building.sup;

import building.Floor;
import building.Space;

import java.util.Iterator;

public class FloorIterator implements Iterator<Space> {

    private int index;
    private Floor floor;

    public FloorIterator(Floor floor) {
        this.floor = floor;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return floor.getSpace(index+1) != null;
    }

    @Override
    public Space next() {
        if (index < floor.getSpaceNum())
            return floor.getSpace(index++);
        index = 0;
        return null;
    }
}

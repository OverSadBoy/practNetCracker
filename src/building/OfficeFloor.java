package building;

import exception.SpaceIndexOutOfBoundsException;
import lList.CLList;
import lList.Item;

public class OfficeFloor implements Floor {

    private CLList list;

    public OfficeFloor(Space[] offices) {
        list = new CLList();
        for (Space value : offices) {
            list.addItemEnd(value);
        }
    }

    public OfficeFloor(int num) {
        list = new CLList(num);
        for (int i = 0; i < num; i++) {
            list.addItemEnd(new Office());
        }
    }

    private Item getItem(int num) {
        return list.getItem(num);
    }

    private void addItem(int num, Space space) {
        list.addItem(num, space);
    }

    private void deleteItem(int num) {
        list.deleteItem(num);
    }

    @Override
    public int getSpaceNum() {
        return list.getLength();
    }

    @Override
    public int getAreas() {
        int areas = 0;
        for (int i = 1; getItem(i) != null; i++)
            areas += getItem(i).data.getArea();
        return areas;
    }

    @Override
    public int getRooms() {
        int rooms = 0;
        for (int i = 1; getItem(i) != null; i++)
            rooms += getItem(i).data.getRoom();
        return rooms;
    }

    @Override
    public Space[] getSpaces() {
        Space[] offices = new Office[list.getLength()];
        for (int i = 1; i < offices.length; i++) {
            offices[i] = getItem(i).data;
        }
        return offices;
    }

    @Override
    public Space getSpace(int num) throws SpaceIndexOutOfBoundsException {
        return getItem(num).data;
    }

    @Override
    public void setSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        getItem(num).data = space;
    }

    @Override
    public void addSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        addItem(num, space);
    }

    @Override
    public void deleteSpace(int num) throws SpaceIndexOutOfBoundsException {
        deleteItem(num);
    }

    @Override
    public Space getBestSpace() {
        Space bestSpace = null;
        for (int i = 0, max = 0; getItem(i) != null; i++) {
            Space space = getItem(i).data;
            if (max < space.getArea()) {
                max = space.getArea();
                bestSpace = space;
            }
        }
        return bestSpace;
    }
}

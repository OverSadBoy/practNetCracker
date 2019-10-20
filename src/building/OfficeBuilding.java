package building;

import exception.FloorIndexOutOfBoundsException;
import exception.SpaceIndexOutOfBoundsException;
import lList.CDLList;
import lList.CLList;
import lList.DoubleItem;
import lList.Item;

import java.io.Serializable;

public class OfficeBuilding implements Building, Serializable {
    private CDLList list;

    private DoubleItem getItem(int num) {
        return list.getDoubleItem(num);
    }

    private void addItem(int num, Floor floor) {
        list.addDoubleItem(num, floor);
    }

    private void deleteItem(int num) {
        list.deleteDoubleItem(num);
    }

    public OfficeBuilding(int numFloor, int[] numSpace) {
        list = new CDLList(numFloor);
        for (int i = 0; i < numFloor; i++) {
            list.addItemEnd(new OfficeFloor(numSpace[i]));
        }
    }

    public OfficeBuilding(Floor[] floor) {
        list = new CDLList();
        for (Floor value : floor) {
            list.addItemEnd(value);
        }
    }

    @Override
    public int getFloorsNum() {
        return list.getLength();
    }

    @Override
    public int getSpacesNum() {
        int spaceNum = 0;
        for (int i = 1; i <= list.getLength(); i++) {
            spaceNum += getFloor(i).getSpaceNum();
        }
        return spaceNum;
    }

    @Override
    public int getSpacesArea() {
        int spacesArea = 0;
        for (int i = 1; i < list.getLength(); i++) {
            spacesArea += getFloor(i).getAreas();
        }
        return spacesArea;
    }

    @Override
    public int getSpacesRoom() {
        int spacesRoom = 0;
        for (int i = 1; i < list.getLength(); i++) {
            spacesRoom += getFloor(i).getRooms();
        }
        return spacesRoom;
    }

    @Override
    public Floor[] getFloors() {
        Floor[] floors = new Floor[list.getLength()];
        for (int i = 1; i < floors.length; i++) {
            floors[i] = getItem(i).data;
        }
        return floors;
    }

    @Override
    public Floor getFloor(int num) {
        return getItem(num).data;
    }

    @Override
    public void setFloor(int num, Floor floor) {
        getItem(num).data = floor;
    }

    @Override
    public Space getSpace(int num) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
        for (int i = 1, numSpace = 0; i <= list.getLength(); i++) {
            for (int j = 1; j <= getItem(i).data.getSpaceNum(); j++) {
                if (numSpace == num) {
                    return getItem(i).data.getSpace(j);
                }
                numSpace++;
            }
        }
        return null;
    }

    @Override
    public void setSpace(int num, Space space) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
        for (int i = 1, numSpace = 0; i <= list.getLength(); i++) {
            for (int j = 1; j <= getItem(i).data.getSpaceNum(); j++) {
                if (numSpace == num) {
                    getItem(i).data.setSpace(j, space);
                    return;
                }
                numSpace++;
            }
        }
    }

    @Override
    public void addSpace(int num, Space space) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
        for (int i = 1, numSpace = 0; i <= list.getLength(); i++) {
            for (int j = 1; j <= getItem(i).data.getSpaceNum(); j++) {
                if (numSpace == num) {
                    getItem(i).data.addSpace(j, space);
                    return;
                }
                numSpace++;
            }
        }
    }

    @Override
    public void deleteSpace(int num) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
        for (int i = 1, numSpace = 0; i <= list.getLength(); i++) {
            for (int j = 1; j <= getItem(i).data.getSpaceNum(); j++) {
                if (numSpace == num) {
                    getItem(i).data.deleteSpace(j);
                    return;
                }
                numSpace++;
            }
        }
    }

    @Override
    public Space[] getSortSpace() {
        Space[] spaces = new Space[getSpacesNum()];
        for (int i = 1, k = 0; i <= list.getLength(); i++) {
            for (int j = 1; j <= getItem(i).data.getSpaceNum(); j++) {
                spaces[k] = getItem(i).data.getSpace(j);
                k++;
            }
        }
        for (int i = 0; i < spaces.length; i++) {
            double maxArea = 0;
            for (int j = i; j < spaces.length; j++) {
                if (spaces[j].getArea() > maxArea) {
                    maxArea = spaces[j].getArea();
                    Space flat = spaces[i];
                    spaces[i] = spaces[j];
                    spaces[j] = flat;
                }
            }
        }
        return spaces;
    }

    @Override
    public Space getBestSpace() {
        return getSortSpace()[0];
    }
}

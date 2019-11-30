package buildings.dwelling;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import buildings.sup.BuildingIterator;
import buildings.sup.Buildings;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable, Cloneable {
    private Floor[] dwellingFloors;

    public Dwelling(int floorNum, int... numFlatForFloor) {
        dwellingFloors = new DwellingFloor[floorNum];
        for (int i = 0; i < dwellingFloors.length; i++)
            dwellingFloors[i] = new DwellingFloor(numFlatForFloor[i]);
    }

    public Dwelling(Floor... dwellingFloor) {
        this.dwellingFloors = dwellingFloor;
    }

    @Override
    public int getFloorsNum() {
        return dwellingFloors.length;
    }

    @Override
    public int getSpacesNum() {
        int allFlat = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            allFlat += dwellingFloor.getSpaceNum();
        }
        return allFlat;
    }

    @Override
    public int getSpacesArea() {
        int allFlatArea = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            allFlatArea += dwellingFloor.getAreas();
        }
        return allFlatArea;
    }

    @Override
    public int getSpacesRoom() {
        int allFlatRoom = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            allFlatRoom += dwellingFloor.getRooms();
        }
        return allFlatRoom;
    }

    @Override
    public Floor[] getFloors() {
        return dwellingFloors;
    }

    @Override
    public Floor getFloor(int num) {
        return dwellingFloors[num];
    }

    @Override
    public void setFloor(int num, Floor dwellingFloor) {
        dwellingFloors[num] = dwellingFloor;
    }

    @Override
    public Space getSpace(int num) {
        Space flatG = null;
        int numFlat = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            for (Space space : dwellingFloor.getSpaces()) {
                if (numFlat == num)
                    flatG = space;
                numFlat++;
            }
        }
        return flatG;
    }

    @Override
    public void setSpace(int num, Space space) {
        for (int i = 0, numFlat = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
                if (numFlat == num)
                    dwellingFloors[i].setSpace(j, space);
                numFlat++;
            }
        }
    }

    @Override
    public void addSpace(int num, Space flat) {
        for (int i = 0, numFlat = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
                if (numFlat == num)
                    dwellingFloors[i].addSpace(j, flat);
                numFlat++;
            }
        }
    }

    @Override
    public void deleteSpace(int num) {
        for (int i = 0, numFlat = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
                if (numFlat == num)
                    dwellingFloors[i].deleteSpace(j);
                numFlat++;
            }
        }
    }

    @Override
    public Space[] getSortSpace() {
        Space[] spaceSort = new Space[getSpacesNum()];
        for (int j = 0, k = 0; j < dwellingFloors.length; j++) {
            for (int i = 0; i < dwellingFloors[j].getSpaces().length; i++) {
                spaceSort[k] = dwellingFloors[j].getSpaces()[i];
                k++;
            }
        }
        return Buildings.getSortArrayMin(spaceSort, (o1, o2) -> Double.compare(((Space) o1).getArea(), ((Space) o2).getArea()));
    }

    @Override
    public Space getBestSpace() {
        return getSortSpace()[0];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < getFloorsNum(); i++) {
            str.append(getFloor(i));
            if (i < getFloorsNum() - 1)
                str.append(", ");
        }
        return "Dwelling" + "(" + getFloorsNum() + ", " + str + ")";
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Dwelling && ((Dwelling) obj).getFloorsNum() == getFloorsNum())
            for (int i = 0; i < getFloorsNum(); i++, res = true)
                if (!((Dwelling) obj).getSpace(i).equals(getSpace(i)))
                    return false;
        return res;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer floorNum = getFloorsNum();
        for (Floor dwellingFloor : dwellingFloors) {
            result += floorNum.byteValue() ^ dwellingFloor.hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Floor[] floors = new Floor[getFloorsNum()];
        for (int i = 0; i < getFloorsNum(); i++)
            floors[i] = (Floor) ((Building) clone).getFloor(i).clone();
        return new Dwelling(floors);
    }


    @Override
    public Iterator<Floor> iterator() {
        return new BuildingIterator(this);
    }
}

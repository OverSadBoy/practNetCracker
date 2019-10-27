package building.dwelling;

import building.Building;
import building.Floor;
import building.Space;

import java.io.Serializable;

public class Dwelling implements Building, Serializable,Cloneable {
    private Floor[] dwellingFloors;

    public Dwelling(int floorNum, int[] numFlatForFloor) {
        dwellingFloors = new DwellingFloor[floorNum];
        for (int i = 0; i < dwellingFloors.length; i++)
            dwellingFloors[i] = new DwellingFloor(numFlatForFloor[i]);
    }

    public Dwelling(Floor[] dwellingFloor) {
        this.dwellingFloors = dwellingFloor;
    }

    public int getFloorsNum() {
        return dwellingFloors.length;
    }

    public int getSpacesNum() {
        int allFlat = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            allFlat += dwellingFloor.getSpaceNum();
        }
        return allFlat;
    }

    public int getSpacesArea() {
        int allFlatArea = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            allFlatArea += dwellingFloor.getAreas();
        }
        return allFlatArea;
    }

    public int getSpacesRoom() {
        int allFlatRoom = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            allFlatRoom += dwellingFloor.getRooms();
        }
        return allFlatRoom;
    }

    public Floor[] getFloors() {
        return dwellingFloors;
    }

    public Floor getFloor(int num) {
        return dwellingFloors[num];
    }

    public void setFloor(int num, Floor dwellingFloor) {
        dwellingFloors[num] = dwellingFloor;
    }

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

    public void setSpace(int num, Space space) {
        for (int i = 0, numFlat = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
                if (numFlat == num)
                    dwellingFloors[i].setSpace(j, space);
                numFlat++;
            }
        }
    }

    public void addSpace(int num, Space flat) {
        for (int i = 0, numFlat = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
                if (numFlat == num)
                    dwellingFloors[i].addSpace(j, flat);
                numFlat++;
            }
        }
    }

    public void deleteSpace(int num) {
        for (int i = 0, numFlat = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
                if (numFlat == num)
                    dwellingFloors[i].deleteSpace(j);
                numFlat++;
            }
        }
    }

    public Space[] getSortSpace() {
        Space[] spaceSort = new Space[getSpacesNum()];
        for (int j = 0, k = 0; j < dwellingFloors.length; j++) {
            for (int i = 0; i < dwellingFloors[j].getSpaces().length; i++) {
                spaceSort[k] = dwellingFloors[j].getSpaces()[i];
                k++;
            }
        }
        for (int i = 0; i < spaceSort.length; i++) {
            double maxArea = 0;
            for (int j = i; j < spaceSort.length; j++) {
                if (spaceSort[j].getArea() > maxArea) {
                    maxArea = spaceSort[j].getArea();
                    Space flat = spaceSort[i];
                    spaceSort[i] = spaceSort[j];
                    spaceSort[j] = flat;
                }
            }
        }
        return spaceSort;
    }

    public Space getBestSpace() {
        return getSortSpace()[0];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < getFloorsNum(); i++) {
            str.append(getFloor(i));
            if (i < getFloorsNum()-1)
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
        for (int i = 0; i < floorNum; i++) {
            result += floorNum.byteValue() ^ getFloor(i).hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object res;
        res = super.clone();
        for (int i = 0; i < getFloorsNum(); i++) {
            ((Building) res).setFloor(i, (Floor) getFloor(i).clone());
        }
        return res;
    }
}

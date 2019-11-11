package buildings.sup;

import buildings.Floor;
import buildings.Space;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {
    private Space[] spaces;

    public SynchronizedFloor(Space[] spaces) {
        this.spaces = spaces;
    }

    @Override
    synchronized public int getSpaceNum() {
        return spaces.length;
    }

    @Override
    synchronized public int getAreas() {
        int allArea = 0;
        for (Space space : spaces) {
            allArea += space.getArea();
        }
        return allArea;
    }

    @Override
    synchronized public int getRooms() {
        int allRoom = 0;
        for (Space space : spaces) {
            allRoom += space.getRoom();
        }
        return allRoom;
    }

    @Override
    synchronized public Space[] getSpaces() {
        return spaces;
    }

    @Override
     synchronized public Space getSpace(int num) {
        return spaces[num];
    }

    @Override
    synchronized public void setSpace(int num, Space space) {
        spaces[num] = space;
    }

    @Override
    synchronized public void addSpace(int num, Space space) {
        Space[] spacesSec = new Space[spaces.length + 1];
        if (num > spaces.length) {
            for (int i = 0; i < spaces.length; i++) {
                spacesSec[i] = spaces[i];
            }
            spacesSec[num] = space;
        } else
            for (int i = 0; i < spacesSec.length; i++) {
                if (i < num) {
                    spacesSec[i] = spaces[i];
                } else if (i == num) {
                    spacesSec[i] = space;
                }
                if (i > num)
                    spacesSec[i] = spaces[i - 1];
            }
        spaces = spacesSec;
    }

    @Override
    synchronized public void deleteSpace(int num) {
        Space[] spacesSec = new Space[spaces.length - 1];
        for (int i = 0; i < spacesSec.length; i++) {
            if (i < num)
                spacesSec[i] = spaces[i];
            else
                spacesSec[i] = spaces[i + 1];

        }
        spaces = spacesSec;
    }

    @Override
    public Space getBestSpace() {
        Space space = null;
        double bestArea = 0;
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].getArea() > bestArea) {
                bestArea = spaces[i].getArea();
                space = spaces[i];
            }
        }
        return space;
    }

    @Override
    synchronized public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < getSpaceNum(); i++) {
            str.append(getSpace(i).toString());
            if (i < getSpaceNum() - 1)
                str.append(", ");
        }
        return "SynFloor" + " (" + getSpaceNum() + ", " + str.toString() + ")";
    }

    @Override
    synchronized public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof SynchronizedFloor && ((SynchronizedFloor) obj).getSpaceNum() == getSpaceNum())
            for (int i = 0; i < getSpaceNum(); i++, res = true)
                if (!((SynchronizedFloor) obj).getSpace(i).equals(getSpace(i)))
                    return false;
        return res;
    }

    @Override
    synchronized public int hashCode() {
        int result = 0;
        Integer spacesNum = getSpaceNum();
        for (int i = 0; i < spacesNum; i++)
            result += spacesNum.byteValue() ^ getSpace(i).hashCode();
        return result;
    }

    @Override
    synchronized public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Space[] space = new Space[getSpaceNum()];
        for (int i = 0; i < getSpaceNum(); i++)
            space[i] = (Space) ((Floor) clone).getSpace(i).clone();
        return new SynchronizedFloor(space);
    }

    @Override
    synchronized public Iterator<Space> iterator() {
        return new FloorIterator(this);
    }

    @Override
    synchronized public int compareTo(Floor floor) {
        return Integer.compare(getSpaceNum(), floor.getSpaceNum());
    }
}

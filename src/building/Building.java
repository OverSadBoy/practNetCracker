package building;

import lList.CLList;

public interface Building { //extends  Iterable<Floor>{

    int getFloorsNum();

    int getSpacesNum();

    int getSpacesArea();

    int getSpacesRoom();

    Floor[] getFloors();

    Floor getFloor(int num);

    void setFloor(int num, Floor floor);

    Space getSpace(int num);

    void setSpace(int num, Space space);

    void addSpace(int num, Space space);

    void deleteSpace(int num);

    Space[] getSortSpace();

    Space getBestSpace();

    Object clone() throws CloneNotSupportedException;
}

package building;

import lList.CLList;

public interface Building {

    public int getFloorsNum();
    public int getSpacesNum();
    public int getSpacesArea();
    public int getSpacesRoom();
    public Floor[] getFloors();
    public Floor getFloor(int num);
    public void setFloor(int num, Floor floor);
    public Space getSpace(int num);
    public void setSpace(int num, Space space);
    public void addSpace(int num, Space space);
    public void deleteSpace(int num);
    public Space[] getSortSpace();
    public Space getBestSpace();
}

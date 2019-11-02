package buildings;


public interface Floor extends Iterable<Space>,Comparable<Floor>{

    int getSpaceNum();

    int getAreas();

    int getRooms();

    Space[] getSpaces();

    Space getSpace(int num);

    void setSpace(int num, Space space);

    void addSpace(int num, Space space);

    void deleteSpace(int num);

    Space getBestSpace();

    Object clone() throws CloneNotSupportedException;

    java.util.Iterator iterator();

    int compareTo(Floor floor);
}

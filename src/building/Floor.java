package building;

public interface Floor {

    public int getSpaceNum();

    public int getAreas();

    public int getRooms();

    public Space[] getSpaces();

    public Space getSpace(int num);

    public void setSpace(int num, Space space);

    public void addSpace(int num, Space space);

    public void deleteSpace(int num);

    public Space getBestSpace();

}

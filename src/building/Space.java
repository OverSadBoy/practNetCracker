package building;

public interface Space {
    public int getRoom();

    public void setRoom(int room);

    public double getArea();

    public void setArea(int area);

    public Object clone() throws CloneNotSupportedException;

}

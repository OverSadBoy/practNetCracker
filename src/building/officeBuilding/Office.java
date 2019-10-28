package building.officeBuilding;

import building.Space;

public class Office implements Space, Cloneable {

    private double area;
    private static final int AREA_DEF = 250;
    private int room;
    private static final int ROOM_DEF = 1;

    public Office(double area, int room) {
        this.area = area;
        this.room = room;
    }

    public Office(double area) {
        this(area, ROOM_DEF);
    }

    public Office() {
        this(AREA_DEF, ROOM_DEF);
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public double getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Office" + " (" + room + ", " + area + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Office && ((Office) obj).area == this.area && ((Office) obj).room == this.room;
    }

    @Override
    public int hashCode() {
        StringBuilder roomStr = new StringBuilder(Integer.toString(this.room, 2)).reverse();
        roomStr.delete(3, roomStr.length());
        StringBuilder areaStr = new StringBuilder(Integer.toString((int) this.area, 2)).reverse();
        areaStr.delete(3, areaStr.length());
        byte room = Byte.parseByte(roomStr.toString());
        byte area = Byte.parseByte(areaStr.toString());
        return room ^ area;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Space space) {
        return Double.compare(this.area, space.getArea());
    }
}

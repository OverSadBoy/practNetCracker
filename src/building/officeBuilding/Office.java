package building.officeBuilding;

import building.Space;

public class Office implements Space {

    private double area;
    private static final int AREA_DEF = 250;
    private int room;
    private static final int ROOM_DEF = 1;

    public Office(int area, int room) {
        this.area = area;
        this.room = room;
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
        Integer room = this.room;
        Double area = this.area;
        return room.byteValue() ^ area.byteValue();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

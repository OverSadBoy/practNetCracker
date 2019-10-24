package building.officeBuilding;

import building.Space;

public class Office implements Space {

    private int area;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Office" + " (" + room + ", " + area + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Office && ((Office) obj).area == this.area && ((Office) obj).room == this.room;
    }
}

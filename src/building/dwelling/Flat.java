package building.dwelling;

import building.Space;

public class Flat implements Space {
    private int area;
    private int room;
    private static final int DEF_AREA = 50;
    private static final int DEF_ROOM = 2;

    public Flat(int area, int room) {
        this.area = area;
        this.room = room;
    }

    public Flat(int area) {
        this(area, DEF_ROOM);
    }

    public Flat() {
        this(DEF_AREA, DEF_ROOM);
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
        return "Flat" + " (" + room + ", " + area + ")";
    }
}

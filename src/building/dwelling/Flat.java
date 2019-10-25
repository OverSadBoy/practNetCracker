package building.dwelling;

import building.Space;

public class Flat implements Space {
    private double area;
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

    public double getArea() {
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Flat && ((Flat) obj).area == this.area && ((Flat) obj).room == this.room;
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

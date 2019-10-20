package lList;

        import building.Floor;

public class DoubleItem {
    public Floor data;
    public DoubleItem next;
    public DoubleItem prev;

    public DoubleItem(Floor data) {
        this.data = data;
        next = null;
        prev = null;
    }

    public DoubleItem(Floor data, DoubleItem next, DoubleItem prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

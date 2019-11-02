package lList;

import buildings.Floor;
import buildings.officeBuilding.OfficeFloor;
import exception.FloorIndexOutOfBoundsException;

public class CDLList {
    private DoubleItem head;
    private int length;

    public CDLList() {
        head = new DoubleItem(new OfficeFloor(0));
        head.next = head;
        head.prev = head;
        length = 0;
    }

    public CDLList(int length) {
        this();
        this.length = length;
    }

    public void addItemEnd(Floor floor) {
        if (head != null) {
            DoubleItem p = new DoubleItem(floor, head, head.prev);
            head.prev.next = p;
            head.prev = p;
        }
    }


    public int getLength() {
        return length;
    }

    public DoubleItem getDoubleItem(int number) {
        if (head != null) {
            DoubleItem p = head.next;
            int i = 1;
            while (p != head && i != number) {
                p = p.next;
                i++;
            }
            if (p != head)
                return p;
        }
        return null;
    }

    public void addDoubleItem(int num, Floor floor) {
        if (head != null) {
            DoubleItem p = head.next;
            while (p != head && num > 1) {
                p = p.next;
                num--;
            }
            if (p != head) {
                DoubleItem q = new DoubleItem(floor, p.next, p);
                p.next = q;
                q.next.prev = q;
                this.length++;
            }
        } else {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    public void deleteDoubleItem(int number) {
        if (head != null) {
            DoubleItem p = head.next;
            while (p != head && number > 1) {
                p = p.next;
                number--;
            }
            if (p != head) {
                p.next.prev = p.prev;
                p.prev.next = p.next;
                this.length--;
            }
        } else {
            throw new FloorIndexOutOfBoundsException();
        }
    }
}

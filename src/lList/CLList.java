package lList;

import building.Office;
import building.Space;

public class CLList {
    private Item head;
    private int length;

    public CLList() {
        head = new Item(new Office(0, 0));
        head.next = head;
        length = 0;
    }

    public CLList(int num) {
        this();
        this.length = num;
    }

    public void addItemStart(Space space) {
        if (head != null) {
            Item p = new Item(space, head.next);
            head.next = p;
        }
    }

    public void addItemEnd(Space space) {
        if (head != null) {
            Item p = head;
            while (p.next != head) {
                p = p.next;
            }
            p.next = new Item(space, head);
        }
    }



    public int getLength() {
        return length;
    }

    public Item getItem(int num) {
        if (head != null) {
            Item p = head.next;
            for (int i = 1; p != head && i != num; i++)
                p = p.next;
            if (p != head)
                return p;
        }
        return null;
    }

    public void addItem(int num, Space space) {
        if (head != null) {
            Item p = head.next;
            if (p != head && num == 1) {
                addItemStart(space);
                return;
            }
            while (p != head && num > 1) {
                p = p.next;
                num--;
            }
            if (p != head) {
                p.next = new Item(space, p.next);
                this.length++;
            }

        } else {
            System.out.println("Ошибка при добавлении элемента!");
        }
    }

    public void deleteItem(int num) {
        if (head != null) {
            Item p = head.next;
            while (p != head && num > 1) {
                p = p.next;
                num--;
            }
            if (p != head) {
                p.next = p.next.next;
                this.length--;
            }
        } else {
            System.out.println("Ошибка при удалении элемента!");
        }
    }
}
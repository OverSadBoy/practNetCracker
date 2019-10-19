package building;

public class DwellingFloor implements Floor{
    private Space[] spaces;

    public DwellingFloor(int flatsNum) {
        spaces = new Space[flatsNum];
        for (int i = 0; i < spaces.length; i++)
            spaces[i] = new Flat();
    }

    public DwellingFloor(Space[] spaces) {
        this.spaces = spaces;
    }

    public int getSpaceNum() {
        return spaces.length;
    }

    public int getAreas() {
        int allArea = 0;
        for (Space space : spaces) {
            allArea += space.getArea();
        }
        return allArea;
    }

    public int getRooms() {
        int allRoom = 0;
        for (Space space : spaces) {
            allRoom += space.getRoom();
        }
        return allRoom;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public Space getSpace(int num) {
        return spaces[num];
    }

    public void setSpace(int num, Space space) {
        spaces[num] = space;
    }

    public void addSpace(int num, Space space) {
        Space[] spacesSec = new Space[spaces.length+1];
        if (num > spaces.length) {
            for (int i = 0; i < spaces.length; i++) {
                spacesSec[i] = spaces[i];
            }
            spacesSec[num] = space;
        } else
            for (int i = 0; i < spacesSec.length; i++) {
                if (i < num) {
                    spacesSec[i] = spaces[i];
                } else if (i == num) {
                    spacesSec[i] = space;
                }
                if (i > num)
                    spacesSec[i] = spaces[i - 1];
            }
        spaces = spacesSec;
    }

    public void deleteSpace(int num) {
        Space[] spacesSec = new Space[spaces.length - 1];
        for (int i = 0; i < spacesSec.length; i++) {
            if (i < num)
                spacesSec[i] = spaces[i];
            else
                spacesSec[i] = spaces[i+1];

        }
        spaces = spacesSec;
    }

    public Space getBestSpace() {
        Space space = null;
        int bestArea = 0;
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].getArea() > bestArea) {
                bestArea = spaces[i].getArea();
                space = spaces[i];
            }
        }
        return space;
    }
}

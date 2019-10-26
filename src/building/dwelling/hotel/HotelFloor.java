package building.dwelling.hotel;

import building.Space;
import building.dwelling.DwellingFloor;
import building.dwelling.Flat;

public class HotelFloor extends DwellingFloor {
    private static final int DEF_STAR = 1;

    private int star;

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public HotelFloor(int flatsNum) {
        super(flatsNum);
        star = DEF_STAR;
    }

    public HotelFloor(Space[] spaces) {
        super(spaces);
        star = DEF_STAR;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < getSpaceNum(); i++) {
            str.append(((Flat) getSpace(i)).toString());
            if (i < getSpaceNum() - 1)
                str.append(", ");
        }
        return "HotelFloor" + " (" + getStar() + ", " + getSpaceNum() + ", " + str.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof HotelFloor && ((HotelFloor) obj).getSpaceNum() == getSpaceNum())
            for (int i = 0; i < getSpaceNum(); i++, res = true)
                if (!((HotelFloor) obj).getSpace(i).equals(getSpace(i)))
                    return false;
        return res;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer spacesNum = getSpaceNum();
        for (int i = 0; i < spacesNum; i++) {
            result += spacesNum.byteValue() ^ getSpace(i).hashCode();
        }
        return result;
    }
}

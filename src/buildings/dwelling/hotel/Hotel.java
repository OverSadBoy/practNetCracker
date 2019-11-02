package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;

public class Hotel extends Dwelling {
    public Hotel(int floorNum, int[] numFlatForFloor) {
        super(floorNum, numFlatForFloor);
    }

    public Hotel(Floor[] dwellingFloor) {
        super(dwellingFloor);
    }

    public int getStarHotel() {
        int max = 0;
        for (int i = 0; i < getFloorsNum(); i++)
            if (getFloor(i) instanceof HotelFloor)
                if (max < ((HotelFloor) getFloor(i)).getStar())
                    max = ((HotelFloor) getFloor(i)).getStar();
        return max;
    }

    @Override
    public Space getBestSpace() {
        Space spaceBest = null;
        double max = 0.0;
        double[] coeff = {0.25, 0.5, 1, 1.25, 1.5};
        for (int i = 0; i < getFloorsNum(); i++) {
            for (int j = 0; j < getFloor(i).getSpaceNum(); j++) {
                double coeffBest = coeff[((HotelFloor) getFloor(i)).getStar()] * getFloor(i).getSpace(j).getArea();
                if (max < coeffBest) {
                    max = coeffBest;
                    spaceBest = getFloor(i).getSpace(j);
                }
            }
        }
        return spaceBest;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < getFloorsNum(); i++) {
            str.append(getFloor(i));
            if (i < getFloorsNum())
                str.append(", ");
        }
        return "Hotel" + "(" + getStarHotel() + ", " + getFloorsNum() + ", " + str + ")";
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Hotel && ((Hotel) obj).getFloorsNum() == getFloorsNum())
            for (int i = 0; i < getFloorsNum(); i++, res = true)
                if (!((Hotel) obj).getSpace(i).equals(getSpace(i)))
                    return false;
        return res;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer floorNum = getFloorsNum();
        for (int i = 0; i < floorNum; i++) {
            result += floorNum.byteValue() ^ getFloor(i).hashCode();
        }
        return result;
    }

}

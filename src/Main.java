import buildings.Space;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.sup.Buildings;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Flat space1 = new Flat(2, 52);
        Flat space2 = new Flat(3, 61);
        Flat space3 = new Flat(5, 861);
        Flat space4 = new Flat(4, 65);
        Flat space5 = new Flat(1, 666);
        Flat[] flats = {space1, space2, space3, space4, space5};
        DwellingFloor floor = new DwellingFloor();
        flats = (Flat[]) Buildings.getSortArrayMin(flats, (o1, o2) -> -Double.compare(((Space) o1).getArea(), ((Space) o2).getArea()));
        for (Flat flat : flats)
            System.out.println(flat.getArea());
    }
}

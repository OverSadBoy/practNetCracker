import building.*;
import building.dwelling.Dwelling;
import building.dwelling.DwellingFloor;
import building.dwelling.Flat;
import building.officeBuilding.Office;
import building.officeBuilding.OfficeBuilding;
import building.officeBuilding.OfficeFloor;
import building.sup.PlacementExchanger;
import exception.*;

public class Main {

    public static void main(String[] args) {
        int[] office = {2, 3, 4};
        OfficeBuilding officeBuilding = new OfficeBuilding(3, office);
        officeBuilding.setSpace(0, new Office(111, 8));
        officeBuilding.setSpace(1, new Office(222, 5));
        officeBuilding.setSpace(2, new Office(333, 5));
        officeBuilding.setSpace(3, new Office(444, 5));
        officeBuilding.setSpace(4, new Office(555, 5));
        officeBuilding.setSpace(5, new Office(666, 5));
        officeBuilding.setSpace(6, new Office(777, 4));
        officeBuilding.setSpace(7, new Office(888, 4));
        officeBuilding.setSpace(8, new Office(999, 4));
        Dwelling dwelling = new Dwelling(3, office);
        dwelling.setSpace(0, new Flat(234, 8));
        dwelling.setSpace(1, new Flat(6547, 5));
        dwelling.setSpace(2, new Flat(34, 5));
        dwelling.setSpace(3, new Flat(6, 5));
        dwelling.setSpace(4, new Flat(4, 5));
        dwelling.setSpace(5, new Flat(6626, 5));
        dwelling.setSpace(6, new Flat(3377, 4));
        dwelling.setSpace(7, new Flat(888, 4));
        dwelling.setSpace(8, new Flat(999, 4));

        Flat flat = new Flat(55, 66);
        Flat flat2 = new Flat(55, 32);
        System.out.println(flat.toString());
        Office office1 = new Office(77, 4);
        System.out.println(office1.toString());
        DwellingFloor dwellingFloor = new DwellingFloor(3);
        System.out.println(dwellingFloor.toString());
        OfficeFloor officeFloor = new OfficeFloor(56);
        System.out.println(officeFloor.toString());
        System.out.println(officeBuilding.toString());
        System.out.println(dwelling.toString());
        System.out.println(flat.equals(flat2));

        System.out.println(dwelling.hashCode());
    }
}

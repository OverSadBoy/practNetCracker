import building.*;
import exception.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Scientific Library");
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
        System.out.println(officeBuilding.getSpacesNum());
        Space[] spaces = officeBuilding.getSortSpace();
        for (int i = 0; i < spaces.length; i++) {
            System.out.println(spaces[i].getArea());
        }
        int[] officeS = {2, 3, 4};
        OfficeBuilding officeBuildingSec = new OfficeBuilding(3, officeS);
        officeBuildingSec.setSpace(0, new Office(122, 8));
        officeBuildingSec.setSpace(1, new Office(211, 5));
        officeBuildingSec.setSpace(2, new Office(344, 5));
        officeBuildingSec.setSpace(3, new Office(433, 5));
        officeBuildingSec.setSpace(4, new Office(566, 5));
        officeBuildingSec.setSpace(5, new Office(655, 5));
        officeBuildingSec.setSpace(6, new Office(788, 4));
        officeBuildingSec.setSpace(7, new Office(877, 4));
        officeBuildingSec.setSpace(8, new Office(999, 4));

        System.out.println(officeBuildingSec.getSpacesNum());
        Space[] spaces2 = officeBuildingSec.getSortSpace();
        for (int i = 0; i < spaces2.length; i++) {
            System.out.println(spaces2[i].getArea());
        }

        try {
            PlacementExchanger.exchangeFloorRooms(officeBuilding.getFloor(1),1, officeBuildingSec.getFloor(1),1);
        } catch (InexchangeableSpacesException e) {
            e.printStackTrace();
        }
        System.out.println();
        Space[] spaces3 = officeBuildingSec.getSortSpace();
        for (int i = 0; i < spaces3.length; i++) {
            System.out.println(spaces3[i].getArea());
        }
        Space[] spaces4 = officeBuilding.getSortSpace();
        for (int i = 0; i < spaces4.length; i++) {
            System.out.println(spaces4[i].getArea());
        }

        System.out.println("Обмен этажами");
        try {
            PlacementExchanger.exchangeBuildingFloors(officeBuilding,1,officeBuildingSec,1);
        } catch (InexchangeableFloorsException e) {
            e.printStackTrace();
        }
        Space[] spaces6 = officeBuilding.getSortSpace();
        for (int i = 0; i < spaces6.length; i++) {
            System.out.println(spaces6[i].getArea());
        }
        System.out.println("2 кв");
        Space[] spaces5 = officeBuildingSec.getSortSpace();
        for (int i = 0; i < spaces5.length; i++) {
            System.out.println(spaces5[i].getArea());
        }
    }
}

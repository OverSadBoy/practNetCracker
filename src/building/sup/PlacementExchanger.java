package building.sup;

import building.Building;
import building.Floor;
import building.Space;
import exception.InexchangeableFloorsException;
import exception.InexchangeableSpacesException;

public class PlacementExchanger {

    public static boolean isExchangeSpace(Space spaceF, Space spaceS) {
        return spaceF.getArea() == spaceS.getArea() && spaceF.getRoom() == spaceS.getRoom();
    }

    public static boolean isExchangeFloor(Floor floorF, Floor floorS) {
        return floorF.getAreas() == floorS.getAreas() && floorF.getRooms() == floorS.getRooms();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if (floor1.getSpaceNum() >= index1 && floor2.getSpaceNum() >= index2
                && !isExchangeSpace(floor1.getSpace(index1), floor2.getSpace(index2))) {
            Space space1 = floor1.getSpace(index1);
            Space space2 = floor2.getSpace(index2);
            floor1.setSpace(index1, space2);
            floor2.setSpace(index2, space1);
            return;
        }
        throw new InexchangeableSpacesException();
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if (building1.getFloorsNum() >= index1 && building2.getFloorsNum() >= index2
                && isExchangeFloor(building1.getFloor(index1), building2.getFloor(index2))) {
            Floor floor1 = building1.getFloor(index1);
            Floor floor2 = building2.getFloor(index2);
            building1.setFloor(index1, floor2);
            building2.setFloor(index2, floor1);
            return;
        }
        throw new InexchangeableFloorsException();
    }
}

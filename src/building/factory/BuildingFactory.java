package building.factory;

import building.Building;
import building.Floor;
import building.Space;

public interface BuildingFactory {

    Space createSpace(double area);

    Space createSpace(int roomsCount, double area);

    Floor createFloor(int spacesCount);

    Floor createFloor(Space[] spaces);

    Building createBuilding(int floorsCount, int[] spacesCounts);

    Building createBuilding(Floor[] floors);
}

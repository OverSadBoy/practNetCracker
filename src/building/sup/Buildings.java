package building.sup;

import building.Building;
import building.Floor;
import building.Space;
import building.factory.BuildingFactory;
import building.factory.DwellingFactory;
import building.officeBuilding.Office;
import building.officeBuilding.OfficeBuilding;
import building.officeBuilding.OfficeFloor;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;

public class Buildings {

    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    public static Space createSpace(double area) {
        return buildingFactory.createSpace(area);
    }

    public static Space createSpace(int roomsCount, double area) {
        return buildingFactory.createSpace(roomsCount, area);
    }

    public static Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        if (building != null) {
            DataOutputStream dout = new DataOutputStream(out);
            dout.writeInt(building.getFloorsNum());
            dout.writeChars(" ");
            for (int i = 0; i < building.getFloorsNum(); i++) {
                dout.writeInt(building.getFloor(i).getRooms());
                dout.writeChars(" ");
                for (int j = 0; j < building.getFloor(i).getRooms(); j++) {
                    dout.writeInt(building.getFloor(i).getSpace(j).getRoom());
                    dout.writeChars(" ");
                    dout.writeDouble(building.getFloor(i).getSpace(j).getArea());
                    dout.writeChars(" ");
                }
            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream din = new DataInputStream(in);
        Building result = null;
        if (din.available() > 0) {
            int floorsNum = din.readInt();
            din.skipBytes(2);
            Floor[] floors = new Floor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = din.readInt();
                din.skipBytes(2);
                Space[] spaces = new Space[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = din.readInt();
                    din.skipBytes(2);
                    int area = din.readInt();
                    din.skipBytes(2);
                    Space space = createSpace(area, roomNum);
                    spaces[j] = space;
                }
                floors[i] = createFloor(spaces);
            }
            result = createBuilding(floors);
        }
        return result;
    }

    public static void writeBuilding(Building building, Writer out) throws IOException {
        if (building != null) {
            int floorsCount = building.getFloorsNum();
            out.write(Integer.toString(floorsCount));
            out.write(" ");
            for (int i = 0; i < building.getFloorsNum(); i++) {
                out.write(Integer.toString(building.getFloor(i).getSpaceNum()));
                out.write(" ");
                for (int j = 0; j < building.getFloor(i).getSpaceNum(); j++) {
                    out.write(Integer.toString(building.getFloor(i).getSpace(j).getRoom()));
                    out.write(" ");
                    out.write(Double.toString(building.getFloor(i).getSpace(j).getArea()));
                    out.write(" ");
                }
            }
        }
    }

    public static Building readBuilding(Reader in) throws IOException {
        BufferedReader bin = new BufferedReader(in);
        Building result = null;
        if (bin.ready()) {
            String[] s = bin.readLine().split(" ");
            int count = 0;
            int floorsNum = Integer.parseInt(s[count++]);
            Floor[] floors = new Floor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = Integer.parseInt(s[count++]);
                Space[] offices = new Space[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = Integer.parseInt(s[count++]);
                    int area = Integer.parseInt(s[count++]);
                    Space space = createSpace(area, roomNum);
                    offices[j] = space;
                }
                floors[i] = createFloor(offices);
            }
            result = createBuilding(floors);
        }
        return result;
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        if (building != null) {
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(building);
        }
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        Building result = null;
        ObjectInputStream ois = new ObjectInputStream(in);
        result = (Building) ois.readObject();
        return result;
    }

    public static void writeBuildingFormat(Building building, Writer out) throws IOException {
        if (building != null) {
            int floorsCount = building.getFloorsNum();
            out.write("%% Количество этажей: ");
            out.write(Integer.toString(floorsCount));
            out.write(" , ");
            for (int i = 0; i < building.getFloorsNum(); i++) {
                out.write("%t %TКоличество помещений на этаже: ");
                out.write(Integer.toString(building.getFloor(i).getSpaceNum()));
                out.write(" , ");
                for (int j = 0; j < building.getFloor(i).getSpaceNum(); j++) {
                    out.write("%n Количество комнат в помещении: ");
                    out.write(Integer.toString(building.getFloor(i).getSpace(j).getRoom()));
                    out.write(" , ");
                    out.write("%s Площадь в помещении: ");
                    out.write(Double.toString(building.getFloor(i).getSpace(j).getArea()));
                    out.write(" , ");
                }
            }
        }
    }

    public Building readBuilding(Scanner scanner) {
        Building result = null;
        if (scanner.hasNext()) {
            String[] s = scanner.next().split(" ");
            int count = 0;
            int floorsNum = Integer.parseInt(s[count++]);
            Floor[] floors = new Floor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = Integer.parseInt(s[count++]);
                Space[] offices = new Space[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = Integer.parseInt(s[count++]);
                    int area = Integer.parseInt(s[count++]);
                    Space space = createSpace(area, roomNum);
                    offices[j] = space;
                }
                floors[i] = createFloor(offices);
            }
            result = createBuilding(floors);
        }
        return result;
    }

    public <T extends Comparable<T>> T[] getSortArrayMax(T[] t) {
        for (int i = 0, minIndex = i; i < t.length; i++) {
            for (int j = i + 1; j < t.length; j++) {
                if (t[j].compareTo(t[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = t[i];
            t[i] = t[minIndex];
            t[minIndex] = swapBuf;
        }
        return t;
    }
    public <T extends Comparable<T>> T[] getSortArrayMin(T[] t,java.util.Comparator comparator) {
        for (int i = 0, minIndex = i; i < t.length; i++) {
            for (int j = i + 1; j < t.length; j++) {
                if (comparator.compare(t[j],t[minIndex])<0)
                    minIndex = j;
            }
            T swapBuf = t[i];
            t[i] = t[minIndex];
            t[minIndex] = swapBuf;
        }
        return t;
    }


}

package building;

import java.io.*;
import java.util.Scanner;

public class Buildings {
   /* Записанные данные о здании представляет собой последовательность чисел,
   первым из которых является количество этажей, далее следует количество помещений текущего этажа
   и соответствующие значения количества комнат и площадей помещений текущего этажа.
    Например, символьная запись для трехэтажного здания:
            «3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0»
    Для чтения этажа из символьного потока можно использовать StreamTokenizer помня о его особенностях
    чтения данных из потока.
    Проверьте возможности всех реализованных методов, в качестве реальных потоков используя файловые потоки,
    а также потоки System.in и System.out.
*/public static void outputBuilding (Building building, OutputStream out) throws IOException {
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
                   dout.writeInt(building.getFloor(i).getSpace(j).getArea());
                   dout.writeChars(" ");
               }
           }
       }
   }

    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream din = new DataInputStream(in);
        Building result = null;
        if (din.available() > 0) {
            int floorsNum = din.readInt();
            din.skipBytes(2);
            Floor[] floors = new OfficeFloor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = din.readInt();
                din.skipBytes(2);
                Space[] offices = new Office[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = din.readInt();
                    din.skipBytes(2);
                    int area = din.readInt();
                    din.skipBytes(2);
                    Space office = new Office(area, roomNum);
                    offices[j] = office;
                }
                floors[i] = new OfficeFloor(offices);
            }
            result = new OfficeBuilding(floors);
        }
        return result;
    }

    public static void writeBuilding (Building building, Writer out) throws IOException{
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
                    out.write(Integer.toString(building.getFloor(i).getSpace(j).getArea()));
                    out.write(" ");
                }
            }
        }
    }

    public static Building readBuilding (Reader in) throws IOException {
        BufferedReader bin = new BufferedReader(in);
        Building result = null;
        if (bin.ready()) {
            String[] s = bin.readLine().split(" ");
            int count = 0;
            int floorsNum = Integer.parseInt(s[count++]);
            Floor[] floors = new OfficeFloor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = Integer.parseInt(s[count++]);
                Space[] offices = new Office[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = Integer.parseInt(s[count++]);
                    int area = Integer.parseInt(s[count++]);
                    Space office = new Office(area, roomNum);
                    offices[j] = office;
                }
                floors[i] = new OfficeFloor(offices);
            }
            result = new OfficeBuilding(floors);
        }
        return result;
    }

    public static void serializeBuilding (Building building, OutputStream out) throws IOException {
        if (building != null) {
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(building);
        }
    }

    public static Building deserializeBuilding (InputStream in) throws IOException, ClassNotFoundException {
        Building result = null;
        ObjectInputStream ois = new ObjectInputStream(in);
        result = (Building) ois.readObject();
        return result;
    }

    public static void writeBuildingFormat (Building building, Writer out) {
    }

    public Building readBuilding(Scanner scanner) {
        Building result = null;
        return result;
    }
}

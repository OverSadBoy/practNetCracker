package building;

import java.io.*;

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
*/

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        // записи данных о здании в байтовый поток
        out.write(building.getFloorsNum());
        for (int i = 1; i <= building.getFloorsNum(); i++) {
            Floor floor = building.getFloor(i);
            out.write(floor.getSpaceNum());
            out.write(floor.getRooms());
            out.write(floor.getAreas());
        }
        out.close();
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        Building building = null;

//чтения данных о здании из байтового потока
        return null;
    }

    public static void writeBuilding(Building building, Writer out) throws IOException {
        out.write(building.getFloorsNum());
        for (int i = 1; i <= building.getFloorsNum(); i++) {
            Floor floor = building.getFloor(i);
            out.write(floor.getSpaceNum());
            out.write(floor.getRooms());
            out.write(floor.getAreas());
        }
        out.close();
//записи здания в символьный поток
    }

    public static Building readBuilding(Reader in) {
        //чтения здания из символьного потока
        return null;
    }
}

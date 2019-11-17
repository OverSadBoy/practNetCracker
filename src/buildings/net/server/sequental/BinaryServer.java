package buildings.net.server.sequental;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.net.BuildingUnderArrestException;
import buildings.officeBuilding.Office;
import buildings.officeBuilding.OfficeBuilding;
import buildings.officeBuilding.OfficeFloor;
import buildings.sup.Buildings;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BinaryServer {

    public static final int DWELLING_MULTIPLIER = 1000;
    public static final int OFFICE_MULTIPLIER = 1500;
    public static final int HOTEL_MULTIPLIER = 2000;
    public static final int SOCKET_CLOSE = -1;
    public static final int ARRESTED = -10;

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(5000);
            System.out.println("Starting server...");
            Socket clientSocket = socket.accept();
            System.out.println("Client accepted");
            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
            Space[] office = new Office[1];
            office[0] = new Office(1, 1);
            Floor[] floor = new OfficeFloor[1];
            floor[0] = new OfficeFloor(office);
            Building exitBuilding = new OfficeBuilding(floor);
            Building building;
            while (!((building = Buildings.inputBuilding(clientSocket.getInputStream())) == exitBuilding)) {
                if (building != null) {
                    if (building.equals(exitBuilding))
                        break;
                    float evaluation = evaluateBuilding(building);
                    try {
                        os.writeFloat(evaluation);
                        System.out.println("Got: " + building.getClass().getSimpleName());
                        if (evaluation != ARRESTED) {
                            System.out.println(String.format("The price of the building is: %f", evaluation));
                        }
                        else
                            throw new BuildingUnderArrestException();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BuildingUnderArrestException e) {
                        System.out.println("Arrested");
                    }
                }
            }
            os.writeFloat(SOCKET_CLOSE);
            System.out.println("Finished working with client");
            try {
                os.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isArrested() {
        Random random = new Random();
        return (random.nextInt(100) < 10);
    }

    private static float evaluateBuilding(Building building) {
        float result = building.getSpacesArea();
        if (building instanceof Hotel) {
            result *= HOTEL_MULTIPLIER;
        }
        else if (building instanceof OfficeBuilding) {
            result *= OFFICE_MULTIPLIER;
        }
        else if (building instanceof Dwelling) {
            result *= DWELLING_MULTIPLIER;
        }
        else result = 0;
        return (isArrested()) ? ARRESTED : result;
    }
}
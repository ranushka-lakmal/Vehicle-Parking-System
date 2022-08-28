/**
 * 
 */
package vehicle;

import datetime.DateTime;
import java.awt.Color;
import java.util.Scanner;

/**
 * @author sankalpa
 *
 */

public class VehicleCreator {

    private Boolean dateCheck = false;
    DateTime entryTime;

    public Vehicle createVehicle(VehicleType type) {

        Vehicle obj = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Registration Number :");
        String regNumber = sc.next();
        System.out.println("Enter the Brand :");
        String brand = sc.next();
        System.out.println("Enter the model :");
        String model = sc.next();

        while (dateCheck != true) {
            try {
                System.out.println("Enter the date and time (DD/MM/YYYY-HH:mm:ss)");
                String dateTime = sc.next();
                // Adding the data in to a string array
                String[] arr = dateTime.split("-");
                String[] dateString = arr[0].split("/");
                String[] timeString = arr[1].split(":");

                entryTime = new DateTime(Integer.parseInt(dateString[0]),
                        Integer.parseInt(dateString[1]),
                        Integer.parseInt(dateString[2]),
                        Integer.parseInt(timeString[0]),
                        Integer.parseInt(timeString[1]),
                        Integer.parseInt(timeString[2]));

                dateCheck = true;
            } catch (Exception e) {
                System.out.println("Try again - Date format incorrect.");
            }

        }

        switch (type) {
            case Car:
                System.out.println("Enter number of Doors : ");
                int numDoors = sc.nextInt();

                System.out.println("Enter the color of the Car (R/G/B) : ");
                String colorString = sc.next();
                String[] colorArr = colorString.split("/");
                Color carColor = new Color(Integer.parseInt(colorArr[0]),
                        Integer.parseInt(colorArr[1]), Integer.parseInt(colorArr[2]));

                obj = new Car(regNumber, brand, model, entryTime, numDoors, carColor);
                break;

            case Van:
                System.out.println("Enter the Cargo Capacity : ");
                double cargoCapacity = sc.nextDouble();

                System.out.println("Enter the number of seats : ");
                int seats = sc.nextInt();

                obj = new Van(regNumber, brand, model, entryTime, cargoCapacity);
                break;

            case Motorbike:
                System.out.println("Enter the Engine Size : ");
                String engineSize = sc.next();

                obj = new Motorbike(regNumber, brand, model, entryTime, engineSize);
                break;

            case Lorry:
                System.out.println("Enter the Cargo Capacity : ");
                double cargoCapacityL = sc.nextDouble();

                obj = new Lorry(regNumber, brand, model, entryTime, cargoCapacityL);
                break;

            case MiniLorry:
                System.out.println("Enter the Cargo Capacity : ");
                double cargoCapacityML = sc.nextDouble();

                obj = new MiniLorry(regNumber, brand, model, entryTime, cargoCapacityML);
                break;

            case Bus:
                System.out.println("Enter number of Doors : ");
                int numDoorsB = sc.nextInt();

                System.out.println("Enter the number of seats : ");
                int seatsB = sc.nextInt();

                obj = new Bus(regNumber, brand, model, entryTime, numDoorsB, seatsB);
                break;

            case Minibus:
                System.out.println("Enter number of Doors : ");
                int numDoorsMB = sc.nextInt();

                System.out.println("Enter the number of seats : ");
                int seatsMB = sc.nextInt();

                obj = new Bus(regNumber, brand, model, entryTime, numDoorsMB, seatsMB);
                break;

            default:
                System.out.println("Invalid Choice");

        }
        return obj;
    }
}

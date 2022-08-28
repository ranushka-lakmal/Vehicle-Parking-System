/**
 * 
 */
package park;

import datetime.DateTime;
import vehicle.Vehicle;
import vehicle.VehicleType;
import vehicle.VehicleCreator;
import java.util.Scanner;
import vehicle.*;

/**
 * @author sankalpa
 *
 */

public class MainConsole {

    private static PettahMultiStoryCarParkManager pettahMultiStoryCarParkManager = PettahMultiStoryCarParkManager.getInstance();
    Scanner sc = new Scanner(System.in);
    DateTime dateTime = new DateTime();

    public static void main(String[] args) {

        while (true) {
            System.out.println("Select your Choice : ");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Delete Vehicle");
            System.out.println("3. Print the current parked vehicles");
            System.out.println("4. Print statistics");
            System.out.println("5. Hit '0' to Exit");
            System.out.println(">>>>");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addVehicle(pettahMultiStoryCarParkManager, sc);
                    break;
                case 2:
                    deleteVehicle(pettahMultiStoryCarParkManager, sc);
                    break;
                case 3:
                    printAllVehicles(pettahMultiStoryCarParkManager);
                    break;
                case 4:
                    printStatistics(pettahMultiStoryCarParkManager, sc);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice");
            }
        }
    }

    public static void addVehicle(CarParkManager manager, Scanner sc) {
        while (true) {
            System.out.println("Choose the type of vehicle you want to enter");
            printVehicleTypes();
            int choice = sc.nextInt();
            System.out.println("Enter the plateID of the vehicle");
            String plateId = sc.next();
            Vehicle vehicleToAdd;
            switch (choice) {
                case 1:
                    vehicleToAdd = new Car(plateId);
                    break;
                case 2:
                    vehicleToAdd = new Van(plateId);
                    break;
                case 3:
                    vehicleToAdd = new Motorbike(plateId);
                    break;
                case 4:
                    vehicleToAdd = new Lorry(plateId);
                    break;
                case 5:
                    vehicleToAdd = new MiniLorry(plateId);
                    break;
                case 6:
                    vehicleToAdd = new Bus(plateId);
                    break;
                case 7:
                    vehicleToAdd = new Minibus(plateId);
                    break;
                case -1:
                    return;
                default:
                    System.out.println("Invalid Choice of vehicle type! Choose between 1, 2, 3, 4 or 5");
                    vehicleToAdd = null;
                    break;
            }

            if (vehicleToAdd != null) {
                manager.addVehicle(vehicleToAdd);
            }

            System.out.println("Do you want to add more vehicles? (Y/N)");
            String wantToContinue = sc.next();
            if (wantToContinue.toLowerCase().charAt(0) == 'n') {
                return;
            }
        }
    }

    public static void deleteVehicle(PettahMultiStoryCarParkManager manager, Scanner sc) {
        while (true) {
            System.out.println("Enter the plateID of the vehicle to be deleted");
            String plateId = sc.next();
            manager.deleteVehicle(plateId);

            System.out.println("Do you want to delete another vehicle? (Y/N)");
            String wantToContinue = sc.next();
            if (wantToContinue.toLowerCase().charAt(0) == 'n') {
                return;
            }
        }
    }

    public static void printAllVehicles(PettahMultiStoryCarParkManager manager) {
        System.out.println("Vehicle list in Chronological Order");
        System.out.println("===============================================");
        manager.printVehiclesParked();
        System.out.println("===============================================");
    }

    private static void printStatisticTypes() {
        System.out.println("Enter '1'  - Percentage of Vehicles parked");
        System.out.println("Enter '2'  - Latest and the Oldest Vehicle in the Car Park");
        System.out.println("Enter '3'  - List of Vehicles on a specific Date");
        System.out.println("Enter '4'  - Parking charge for vehicles in the car park");
        System.out.println("Enter '5'  - Visualize the car park");
        System.out.println("Enter '-1' - Return to main menu");
    }

    public static void printStatistics(PettahMultiStoryCarParkManager manager, Scanner sc) {
        while (true) {
            System.out.println("Choose the type of statistic, you want to be displayed ");
            printStatisticTypes();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    manager.printVehiclesPercentages();
                    break;
                case 2:
                    manager.printLatestPark();
                    manager.printLongestPark();
                    break;
                case 3:
                    System.out.println("Enter the entry day for the list of vehicles to find.");
                    String day = sc.next();
                    System.out.println("Enter the entry month for the list of vehicles to find.");
                    String month = sc.next();
                    System.out.println("Enter the entry year for the list of vehicles to find.");
                    String year = sc.next();

                    manager.printVehicleByDay(day, month, year);
                    break;
                case 4:
                    System.out.println("Vehicle Parking Charges are");
                    System.out.println("===============================================");
                    manager.displayParkingCharges();
                    System.out.println("===============================================");
                    break;
                case 5:
                    manager.displayEntireCarPark();
                    break;

                case -1:
                    return;
                default:
                    System.out.println("Invalid Choice of vehicle type! Choose between 1, 2, 3, 4 or 5");
                    break;
            }

            System.out.println("Do you want to analyze another statistic? (Y/N)");
            String wantToContinue = sc.next();
            if (wantToContinue.toLowerCase().charAt(0) == 'n') {
                return;
            }
        }
    }

    private static void printVehicleTypes() {
        System.out.println("Enter '1'  - Car");
        System.out.println("Enter '2'  - Van");
        System.out.println("Enter '3'  - Motorbike");
        System.out.println("Enter '4'  - Lorry");
        System.out.println("Enter '5'  - Mini lorry");
        System.out.println("Enter '6'  - Bus");
        System.out.println("Enter '7'  - Minibus");
        System.out.println("Enter '-1' - To return to main menu");
    }

}

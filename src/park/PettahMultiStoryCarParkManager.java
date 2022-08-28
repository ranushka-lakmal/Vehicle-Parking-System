/**
 * 
 */
package park;

import datetime.DateTime;
import vehicle.Vehicle;
import vehicle.Car;
import vehicle.Van;
import vehicle.Motorbike;
import vehicle.Lorry;
import vehicle.MiniLorry;
import vehicle.Bus;
import vehicle.Minibus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import carParkData.CarPark;
import carParkData.FloorData;
import carParkData.PettahParkingMeter;
import vehicle.VehicleType;

/**
 * @author sankalpa
 *
 */

public class PettahMultiStoryCarParkManager implements CarParkManager {

    private ArrayList<Vehicle> listOfVehicle = new ArrayList<Vehicle>();
    private static PettahMultiStoryCarParkManager instance = null;
   
	/*
	 * private int availableSlots = 60; private double chargePerHour = 50; private
	 * double addCharge = 75; private double maxCharge = 1200;
	 */
    
    private CarPark carPark;
    private PettahParkingMeter pettahParkingMeter;

    public PettahMultiStoryCarParkManager() {
        List<FloorData> floors = this.initializepettahMultiStoryCarParkFloors();

        this.carPark = new CarPark(floors);

        pettahParkingMeter = new PettahParkingMeter();
    }

    public CarPark getCarPark() {
        return carPark;
    }

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    private List<FloorData> initializepettahMultiStoryCarParkFloors() {
        // maximum floor capacity for each floor
        double MAX_FLOOR_CAPACITY = 60.0;
        // Arraylist containing all floors
        List<FloorData> floors = Collections.synchronizedList(new ArrayList<>());

        // For each floor create two lists containing possible and preferred vehicle types
        // Then initialize from ground to nineth floor.
        //
        // Create Ground Floor
        List<VehicleType> groundPreferredVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Minibus, VehicleType.MiniLorry, VehicleType.Bus, VehicleType.Lorry
        ));
        List<VehicleType> groundPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.Motorbike, VehicleType.Van, VehicleType.Minibus, VehicleType.MiniLorry, VehicleType.Bus, VehicleType.Lorry
        ));
        FloorData groundFloor = new FloorData(groundPreferredVehicleTypes, groundPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create First Floor
        List<VehicleType> firstPreferredVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Van
        ));
        List<VehicleType> firstPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.Motorbike, VehicleType.Van
        ));
        FloorData firstFloor = new FloorData(firstPreferredVehicleTypes, firstPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create Second Floor
        List<VehicleType> secondPreferredVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Van
        ));
        List<VehicleType> secondPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.Motorbike, VehicleType.Van
        ));
        FloorData secondFloor = new FloorData(secondPreferredVehicleTypes, secondPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create Third Floor
        List<VehicleType> thirdFPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        List<VehicleType> thirdFPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.Motorbike, VehicleType.Van
        ));
        FloorData thirdFloor = new FloorData(thirdFPreferredVehicleTypes, thirdFPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create Fourth Floor
        List<VehicleType> fourthPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        List<VehicleType> fourthPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.Motorbike, VehicleType.Van
        ));
        FloorData fourthFloor = new FloorData(fourthPreferredVehicleTypes, fourthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create Fifth Floor
        List<VehicleType> fifthPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        List<VehicleType> fifthPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.Motorbike, VehicleType.Van
        ));
        FloorData fifthFloor = new FloorData(fifthPreferredVehicleTypes, fifthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create sixth Floor
        List<VehicleType> sixthPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        List<VehicleType> sixthPossibleVehicleTypes = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.Motorbike, VehicleType.Van
        ));
        FloorData sixthFloor = new FloorData(sixthPreferredVehicleTypes, sixthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create seventh Floor
        List<VehicleType> seventhPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        List<VehicleType> seventhPossibleVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        FloorData seventhFloor = new FloorData(seventhPreferredVehicleTypes, seventhPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create eighth Floor
        List<VehicleType> eighthPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        List<VehicleType> eighthPossibleVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        FloorData eighthFloor = new FloorData(eighthPreferredVehicleTypes, eighthPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Create nineth Floor
        List<VehicleType> ninethPreferredVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        List<VehicleType> ninethPossibleVehicleTypes = Collections.synchronizedList(Collections.singletonList(
                VehicleType.Car
        ));
        FloorData ninethFloor = new FloorData(ninethPreferredVehicleTypes, ninethPossibleVehicleTypes, MAX_FLOOR_CAPACITY);

        // Add each individual floor to Arraylist containing all floors.
        floors.add(groundFloor);
        floors.add(firstFloor);
        floors.add(secondFloor);
        floors.add(thirdFloor);
        floors.add(fourthFloor);
        floors.add(fifthFloor);
        floors.add(sixthFloor);
        floors.add(seventhFloor);
        floors.add(eighthFloor);
        floors.add(ninethFloor);

        return floors;
    }

    
    @Override
    public void addVehicle(Vehicle vehicle) {
        try {
            // Add vehicle to car park
            this.carPark.addVehicle(vehicle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // print number of free slots in the car park
            double slotsAvailable = this.carPark.getFreeSlots();
            System.out.println("Vehicle added successfully! There are " + String.format("%.2f", slotsAvailable) + " slots left in the car park");
        }
    }

   
    @Override
    public void deleteVehicle(String plateID) {
        try {
            // Delete vehicle with given plate ID
            Vehicle deletedVehicle = this.carPark.deleteVehicle(plateID);
            // Print details of the deleted vehicle
            String vehicleType = deletedVehicle.getVehicleType().getValue();

            System.out.println(vehicleType + " with plate id - " + plateID + " was deleted.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    @Override
    public void printVehiclesParked() {
        // Get the list of vehicles in the car park in chronological order
        List<Vehicle> vehicleList = this.carPark.getVehicleList();
        // Iterate over the vehicle list and print details of each vehicle
        for (Vehicle vehicle : vehicleList) {
            String vehicleType = vehicle.getVehicleType().getValue();
            String plateID = vehicle.getPlateId();
            //String entryTime = vehicle.getEntryTime().getDateTime();
            String entryTime = vehicle.getEntryTime().toString();

            System.out.println(vehicleType + " with plate id - " + plateID + " entered at: " + entryTime);
        }
    }

    
    @Override
    public void printVehiclesPercentages() {
        // Get the vehicle statistics in a hashmap
        HashMap<String, Double> percentagesMap = this.carPark.getVehiclePercentages();

        System.out.println("Percentages of Vehicles parked in park");
        System.out.println("=======================================");

        // Print percentage for each vehicle type
        for (String vehicleType : percentagesMap.keySet()) {
            String percentage = String.format("%.2f", percentagesMap.get(vehicleType));
            System.out.println(vehicleType + " - " + percentage + "%");
        }
    }


    @Override
    public void printLongestPark() {
        try {
            // Get the oldest vehicle
            Vehicle oldestVehicle = this.carPark.getOldestVehicle();
            // Print details of the oldest vehicle
            String vehicleType = oldestVehicle.getVehicleType().getValue();
            String plateID = oldestVehicle.getPlateId();
            //String entryTime = oldestVehicle.getEntryTime().getDateTime();
            String entryTime = oldestVehicle.getEntryTime().toString();

            System.out.println("The oldest vehicle is a " + vehicleType + " with plate ID - " + plateID + " entered at: " + entryTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void printLatestPark() {
        try {
            // Get the latest vehicle
            Vehicle latestVehicle = this.carPark.getLatestVehicle();
            // Print details of the latest vehicle
            String vehicleType = latestVehicle.getVehicleType().getValue();
            String plateID = latestVehicle.getPlateId();
            //String entryTime = latestVehicle.getEntryTime().getDateTime();
            String entryTime = latestVehicle.getEntryTime().toString();

            System.out.println("Latest vehicle is a " + vehicleType + " with plate ID - " + plateID + " entered at: " + entryTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

  
    @Override
    public void printVehicleByDay(String day, String month, String year) {
        try {
            List<Vehicle> vehicleList = this.carPark.getVehicleByDayYear(day, month, year);
            for (Vehicle vehicle : vehicleList) {
                String vehicleType = vehicle.getVehicleType().getValue();
                String plateID = vehicle.getPlateId();
                String entryTime = vehicle.getEntryTime().getDateTime();

                System.out.println(vehicleType + " with plate id - " + plateID + " entered on: " + entryTime);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

  
    @Override
    public void displayParkingCharges() {
        List<Vehicle> vehicleList = this.carPark.getVehicleList();
        for (Vehicle vehicle : vehicleList) {
            String plateID = vehicle.getPlateId();
            this.printReceipt(plateID);
        }
    }

    @Override
    public void printReceipt(String plateID) {
        try {
            Vehicle vehicle = this.carPark.getVehicleById(plateID);
            String vehicleType = vehicle.getVehicleType().getValue();
            String amount = String.format("%.2f", this.pettahParkingMeter.getParkingCharge(vehicle));

            System.out.println(vehicleType + " with plate id - " + plateID + " has a parking charge of Rs." + amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    public void displayEntireCarPark() {
        
        List<FloorData> floorList = this.carPark.getFloorList();
      
        int floorCount = 0;
        System.out.println("=========================================================");
        System.out.println("============== PettahMulti Story Car Park ==============");
       
        for (FloorData floor : floorList) {
            // Print floor level
            System.out.println("=========================================================");
            System.out.println("======================== Floor " + floorCount + " ========================");
            System.out.println("=========================================================");
            floorCount += 1;
            for (Vehicle vehicle : floor.getVehicleList()) {
                System.out.println(vehicle.getVehicleType().getValue() + " with plate id - " + vehicle.getPlateId());
            }
            if (floor.getVehicleList().size() == 0) {
                System.out.println("====================    Empty    ====================");
            }
        }
    }

    
    public static PettahMultiStoryCarParkManager getInstance() {
        if (instance == null) {
            synchronized (PettahMultiStoryCarParkManager.class) {
                if (instance == null) {
                    instance = new PettahMultiStoryCarParkManager();
                }
            }
        }
        return instance;
    }

}

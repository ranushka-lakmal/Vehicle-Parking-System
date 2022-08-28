/**
 * 
 */
package carParkData;

import datetime.DateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import vehicle.Vehicle;
import vehicle.VehicleType;

/**
 * @author sankalpa
 *
*/

public class CarPark implements Serializable {

    // Each floor level in the {@link CarPark} class
    private List<FloorData> floorList;
    // List containing all the vehicles that are currently in the CarPark class
    private List<Vehicle> vehicleList;

    public CarPark(List<FloorData> floorList) {
        // Set the floor levels
        this.floorList = floorList;
        // Initialize the vehicle list
        this.vehicleList = Collections.synchronizedList(new ArrayList<>());
    }

    public CarPark(List<FloorData> floorList, List<Vehicle> vehicleList) {
        // Set the floor levels
        this.floorList = floorList;
        // Set the vehicle list
        this.vehicleList = vehicleList;
    }

    public CarPark() {

    }

    // Return list of floors in the car park
    public List<FloorData> getFloorList() {
        return this.floorList;
    }

    // Return list of current vehicles ordered chronologically on the entered time
    public List<Vehicle> getVehicleList() {
        // Recreate a new vehicle list and return the sorted version of it
        List<Vehicle> orderedVehicleList = Collections.synchronizedList(this.vehicleList);;
        Collections.sort(orderedVehicleList);
        return orderedVehicleList;
    }


    private FloorData getMostSuitableFloorForVehicle(Vehicle vehicle) throws Exception {
        // Initialize array list to store the preferred for the vehicle
        List<FloorData> preferredFloors = Collections.synchronizedList(new ArrayList<>());
        // Initialize array list to store other possible for the vehicle
        List<FloorData> possibleFloors = Collections.synchronizedList(new ArrayList<>());

        // For each floor get preferred and possible vehicle type
        // Add floor to array list depending on vehicle type and availability of the floor
        for (FloorData currFloor : this.floorList) {
            List<VehicleType> currFloorPreferredVehicleTypes = currFloor.getPreferredVehicleType();
            List<VehicleType> currFloorPossibleVehicleTypes = currFloor.getPossibleVehicleType();

            VehicleType currVehicleType = vehicle.getVehicleType();

            // Check if the floor can accommodate the current vehicle and whether it is preferred or possible
            if (currFloorPreferredVehicleTypes.contains(currVehicleType) && currFloor.isParkingSlotsSufficient(vehicle)) {
                preferredFloors.add(currFloor);
            } else if (currFloorPossibleVehicleTypes.contains(currVehicleType) && currFloor.isParkingSlotsSufficient(vehicle)) {
                possibleFloors.add(currFloor);
            }
        }

        // Check if there are any preferred floors available
        // If so return floor with most parking slots
        if (preferredFloors.size() > 0) {
            Collections.sort(preferredFloors, Collections.reverseOrder());
            return preferredFloors.get(0);
        } else if (possibleFloors.size() > 0) {
            // Check if there are any other possible floors available
            // If so return floor with the least parking slots
            Collections.sort(possibleFloors);
            return possibleFloors.get(0);
        } else {
            // No spot is available to park the vehicle
            throw new Exception("No suitable Floor Found to park - " + vehicle.getVehicleType().getValue());
        }

    }

    // Adds the vehicle to most suitable floor if possible
    public void addVehicle(Vehicle vehicle) throws Exception {
        try {
            // Get most suitable floor for the vehicle to park
            FloorData mostSuitable = this.getMostSuitableFloorForVehicle(vehicle);
            // Add the vehicle to the floor
            mostSuitable.addVehicle(vehicle);
            // Add the vehicle to current list of vehicles
            this.vehicleList.add(vehicle);
        } catch (Exception e) {
            throw new Exception("Car park cannot accommodate vehicle at the moment.");
        }
    }

    // Return double representing the number of free slots in this car park
    public double getFreeSlots() {
        // Variable to keep track of all free slots
        double freeSlots = 0.0;
        // Iterate over available floors and add the free slots
        for (FloorData currFloor : this.floorList) {
            freeSlots += currFloor.getAvailableNumberOfSlots();
        }
        return freeSlots;
    }

    public Vehicle deleteVehicle(String plateId) throws Exception {
        // Iterate over all the floors
        // Delete the vehicle from the floor if the vehicle is parked there
        // Remove vehicle from this car park's vehicle list if the vehicle was deleted from the floor
        for (FloorData currFloor : this.floorList) {
            Vehicle vehicle = currFloor.getVehicleById(plateId);
            if (vehicle != null) {
                currFloor.deleteVehicleByInstance(vehicle);
                this.vehicleList.remove(vehicle);
                return vehicle;
            }
        }
        throw new Exception("Vehicle not found with specified ID");
    }

    // Calculate percentages of the number of different types of vehicles parked in this car park
    public HashMap<String, Double> getVehiclePercentages() {
        // Initialize variable to keep track of vehicle count
        double vehicleCount = 0;
        // Create a hashmap to store the percentage for each vehicle
        HashMap<String, Double> vehiclePercentage = new HashMap<>();

        for (Vehicle vehicle : this.vehicleList) {
            // Count the total number of vehicle for each vehicle type
            // Count the total number of vehicle for all types
            vehicleCount += 1;
            String vehicleType = vehicle.getVehicleType().getValue();
            if (vehiclePercentage.containsKey(vehicleType)) {
                vehiclePercentage.put(vehicleType, vehiclePercentage.get(vehicleType) + 1.0);
            } else {
                vehiclePercentage.put(vehicleType, 1.0);
            }
        }

        // Divide the hashmap values by total vehicle count and multiply by 100 to get the percentage of vehicles for each type
        for (String vehicleType : vehiclePercentage.keySet()) {
            double percentageFactor = 100 / vehicleCount;
            vehiclePercentage.put(vehicleType, vehiclePercentage.get(vehicleType) * percentageFactor);
        }

        return vehiclePercentage;
    }

    public Vehicle getOldestVehicle() throws Exception {
        // Sort the vehicle list in Chronological order (Descending)
        Collections.sort(this.vehicleList, Collections.reverseOrder());
        if (this.vehicleList.size() > 0) {
            return this.vehicleList.get(0);
        }
        throw new Exception("No vehicles in the car park");
    }

    public Vehicle getLatestVehicle() throws Exception {
        // Sort the vehicle list in Chronological order (Ascending)
        Collections.sort(this.vehicleList);
        if (this.vehicleList.size() > 0) {
            return this.vehicleList.get(0);
        }
        throw new Exception("No vehicles in the car park");
    }

    public Vehicle getVehicleById(String plateId) throws Exception {
        // Iterate over all floors and check if the vehicle is parked there
        // If so return the instance
        for (FloorData currFloor : this.floorList) {
            Vehicle vehicle = currFloor.getVehicleById(plateId);
            if (vehicle != null) {
                return vehicle;
            }
        }
        throw new Exception("Vehicle not found");
    }

    public List<Vehicle> getVehicleByDayYear(String day, String month, String year) throws Exception {
        // Initialize a list to store the vehicles that have entered on that day and year
        List<Vehicle> availableVehicles = new ArrayList<>();

        // Iterate over the vehicle list and see if the vehicle's entered day and year match
        // If so, add it to the availableVehicles list
        for (Vehicle vehicle : this.vehicleList) {
            DateTime enteredTime = vehicle.getEntryTime();
            String enteredDay = String.valueOf(enteredTime.getDay());
            String enteredMonth = String.valueOf(enteredTime.getMonth());
            String enteredYear = String.valueOf(enteredTime.getYear());
            if (enteredDay.equalsIgnoreCase(day) && enteredMonth.equalsIgnoreCase(month) && enteredYear.equalsIgnoreCase(year)) {
                availableVehicles.add(vehicle);
            }
        }

        if (availableVehicles.size() == 0) {
            throw new Exception("No vehicles found on that date.");
        }

        return availableVehicles;
    }
}

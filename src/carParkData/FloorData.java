/**
 * 
 */
package carParkData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;

import vehicle.Vehicle;
import vehicle.VehicleType;

/**
 * @author sankalpa
 *
 */

public class FloorData implements Comparable<FloorData>, Serializable {

    // Represents the maximum capacity of the FloorData class Used an atomic double from the guava library
    private AtomicDouble maxCapacity;
    // Represents the current capacity of the FloorData class Used an atomic double from the guava library
    private AtomicDouble currentCapacity;
    // Represents the current number of vehicles parked of the FloorData class Used atomic integer for thread safety (liveness of threads)
    private AtomicInteger currentNumberOfVehicles;
    // Represents the list of vehicles parked of the FloorData class Used atomic integer for thread safety (liveness of threads)
    private List<Vehicle> vehicleList;
    // Represents the list of vehicle types preferred to be parked of the FloorData class
    private List<VehicleType> preferredVehicleType;
    // Represents the list of vehicle types possible to be parked of the FloorData class
    private List<VehicleType> possibleVehicleType;

    public FloorData(List<VehicleType> preferredVehicleType, List<VehicleType> possibleVehicleType, double maxCapacity) {
        this.currentCapacity = new AtomicDouble(0.0);
        this.currentNumberOfVehicles = new AtomicInteger(0);
        // Initialize vehicle list using a thread safe list
        this.vehicleList = Collections.synchronizedList(new ArrayList<>());
        this.preferredVehicleType = preferredVehicleType;
        this.possibleVehicleType = possibleVehicleType;
        this.maxCapacity = new AtomicDouble(maxCapacity);
    }

    public int getCurrentNumberOfVehicles() {
        return currentNumberOfVehicles.get();
    }

    public double getCurrentCapacity() {
        return currentCapacity.get();
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public List<VehicleType> getPreferredVehicleType() {
        return preferredVehicleType;
    }

    public List<VehicleType> getPossibleVehicleType() {
        return possibleVehicleType;
    }

    public double getAvailableNumberOfSlots() {
        return maxCapacity.get() - this.currentCapacity.get();
    }

    // Return true if the vehicle can be parked on this floor
    public boolean isParkingSlotsSufficient(Vehicle vehicle) {
        double neededCapacity = vehicle.getSlotsNeeded();
        return ((this.currentCapacity.get() + neededCapacity) <= maxCapacity.get());
    }

    public void addVehicle(Vehicle vehicle) {

        // check if the vehicle can be parked
        if (!this.isParkingSlotsSufficient(vehicle)) {
            System.out.println("Capacity is: " + this.currentCapacity);
            throw new IllegalStateException("No parking slots available");
        }
        // update current vehicle capacity by slots taken
        this.currentCapacity.getAndAdd(vehicle.getSlotsNeeded());
        // update current number of vehicles by +1
        this.currentNumberOfVehicles.incrementAndGet();
        // add vehicle to list of vehicles
        this.vehicleList.add(vehicle);
    }

    public Vehicle getVehicleById(String id) {
        Vehicle found = null;
        // Iterate over the vehicle list and see if the vehicle has the plateId of vehicle to be found
        for (Vehicle v : this.vehicleList) {
            if (v.getPlateId().equals(id)) {
                // break loop once the vehicle is found
                found = v;
                break;
            }
        }
        return found;
    }

    public Vehicle deleteVehicleByInstance(Vehicle vehicle) throws Exception {
        // Delete vehicle from this floor
        boolean removed = this.vehicleList.remove(vehicle);
        if (removed) {
            // Get the space occupied by the vehicle
            double freed_space = vehicle.getSlotsNeeded();
            // Free the space previously occupied by the vehicle
            this.currentCapacity.getAndAdd(-freed_space);
            // Decrement vehicle count by 1
            this.currentNumberOfVehicles.decrementAndGet();
            return vehicle;
        } else {
            // No vehicle was found in the floor by the given instance
            throw new Exception("Vehicle not found");
        }
    }

    public Vehicle deleteVehicleByPlateId(String plateID) throws Exception {
        Vehicle vehicleToRemove = null;
        // Iterate through the vehicle list and check if the given vehicle is found by its ID
        for (Vehicle vehicle : this.vehicleList) {
            if (vehicle.getPlateId().equals(plateID)) {
                vehicleToRemove = vehicle;
                break;
            }
        }
        // Remove the vehicle which is found
        if (vehicleToRemove != null) {
            this.vehicleList.remove(vehicleToRemove);
            double freed_space = vehicleToRemove.getSlotsNeeded();
            // Free the space previously occupied by the vehicle
            this.currentCapacity.getAndAdd(-freed_space);
            // Decrement vehicle count by 1
            this.currentNumberOfVehicles.decrementAndGet();
            return vehicleToRemove;
        } else {
            // If this point is reached, the vehicle is not found.
            throw new Exception("Vehicle not found");
        }
    }

    /**
     * Other floor to be compared with this floor Return integer which is 1,
     * 0,-1 depending on the other floor's capacity
     */
    @Override
    public int compareTo(FloorData other) {
        // Get this floor's current capacity
        double currCapacity = this.getCurrentCapacity();
        // Get the other floor's current capacity
        double otherCapacity = other.getCurrentCapacity();
        // Compare and return an integer depending on the two floor's capacities
        return Double.compare(currCapacity, otherCapacity);
    }

    // Return string version of this floor
    @Override
    public String toString() {
        return "Floor{"
                + "currentCapacity=" + currentCapacity
                + ", vehicleList=" + vehicleList
                + '}';
    }
}

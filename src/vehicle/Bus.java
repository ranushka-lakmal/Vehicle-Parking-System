/**
 * 
 */
package vehicle;

import datetime.DateTime;

/**
 * @author sankalpa
 *
 */

public class Bus extends Vehicle {

    private int numDoors;
    private int seats;

    public Bus(String plateID) {
        super(plateID);
        this.vehicleType = vehicleType.Bus;
        this.slotsNeeded = 5.0;
    }

   
    @Override
    public String toString() {
        return "Bus with plate id- " + this.plateId + ", entered at: " + this.entryTime.getDateTime();
    }

    public Bus(String regNumber, String brand, String model, DateTime entryTime, int numDoors, int seats) {
        super(regNumber, brand, model, entryTime);
        this.numDoors = numDoors;
        this.seats = seats;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}

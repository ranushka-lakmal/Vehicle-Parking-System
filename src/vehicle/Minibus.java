/**
 * 
 */
package vehicle;

import datetime.DateTime;

/**
 * @author sankalpa
 *
 */

public class Minibus extends Vehicle {

    private int numDoors;
    private int seats;

    public Minibus(String plateID) {
        super(plateID);
        // Set vehicle type to mini Minibus
        this.vehicleType = vehicleType.Minibus;
        this.slotsNeeded = 3.0;
    }

   
    @Override
    public String toString() {
        return "Minibus plate id- " + this.plateId + ", entered at: " + this.entryTime.getDateTime();
    }

    public Minibus(String regNumber, String brand, String model, DateTime entryTime, int numDoors, int seats) {
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

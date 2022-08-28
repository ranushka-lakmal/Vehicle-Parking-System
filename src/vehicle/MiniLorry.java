/**
 * 
 */
package vehicle;

import datetime.DateTime;

/**
 * @author sankalpa
 *
 */

public class MiniLorry extends Vehicle {

    private double cargoVolume;

    public MiniLorry(String plateID) {
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to mini lorry
        this.vehicleType = vehicleType.MiniLorry;
        // Initialize slots needed
        this.slotsNeeded = 3.0;
    }

    @Override
    public String toString() {
        return "Mini Lorry with plate id- " + this.plateId + ", entered at: " + this.entryTime.getDateTime();
    }

    public MiniLorry(String regNumber, String brand, String model, DateTime entryTime, double cargoVolume) {
        super(regNumber, brand, model, entryTime);
        this.cargoVolume = cargoVolume;
    }

    public double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

}

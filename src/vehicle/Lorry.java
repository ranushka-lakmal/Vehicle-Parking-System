/**
 * 
 */
package vehicle;

import datetime.DateTime;

/**
 * @author sankalpa
 *
 */

public class Lorry extends Vehicle {

    private double cargoVolume;

    public Lorry(String plateID) {
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to lorry
        this.vehicleType = vehicleType.Lorry;
        // Initialize slots needed
        this.slotsNeeded = 5.0;
    }

    
    @Override
    public String toString() {
        return "Lorry with plate id- " + this.plateId + ", entered at: " + this.entryTime.getDateTime();
    }

    public Lorry(String regNumber, String brand, String model, DateTime entryTime, double cargoVolume) {
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

/**
 * 
 */
package vehicle;

import datetime.DateTime;

/**
 * @author sankalpa
 *
 */

public class Van extends Vehicle {

    private double cargoVolume;

    public Van(String plateID) {
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to van
        this.vehicleType = vehicleType.Van;
        // Initialize slots needed
        this.slotsNeeded = 2.0;
    }


    @Override
    public String toString() {
        return "Van with plate id- " + this.plateId + ", entered at: " + this.entryTime.getDateTime();
    }

    public Van(String idPlate, String brand, String model, DateTime entryTime, double cargoVolume) {
        super(idPlate, brand, model, entryTime);
        this.cargoVolume = cargoVolume;
    }

    public double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }
}

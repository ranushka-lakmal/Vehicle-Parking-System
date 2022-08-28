/**
 * 
 */
package vehicle;

import datetime.DateTime;

/**
 * @author sankalpa
 *
 */

public class Motorbike extends Vehicle {

    private String engineSize;

    public Motorbike(String plateID) {
        // Call superclass constructor along with plateID
        super(plateID);
        // Set vehicle type to motorbike
        this.vehicleType = vehicleType.Motorbike;
        // Initialize slots needed
        //3 bike = 1 slot, so 1/3 = 0.3..
        this.slotsNeeded = (1.0 / 3.0); 
    }

    
    @Override
    public String toString() {
        return "Motorbike plate id- " + this.plateId + ", Entered at: " + this.entryTime.getDateTime();
    }

    public Motorbike(String idPlate, String brand, String model, DateTime entryTime, String engineSize) {
        super(idPlate, brand, model, entryTime);
        this.engineSize = engineSize;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }
}

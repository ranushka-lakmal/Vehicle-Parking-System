/**
 * 
 */
package park;

import datetime.DateTime;
import vehicle.Vehicle;
import java.math.BigDecimal;

/**
 * @author sankalpa
 *
 */

public interface CarParkManager {

    public static final int MAX = 60; //maximum slots

    public void addVehicle(Vehicle obj);

    public void deleteVehicle(String plateId);

    public void printVehiclesParked();

    public void printVehiclesPercentages();

    public void printLongestPark();

    public void printLatestPark();

    public void printVehicleByDay(String day, String month, String year);

    void displayParkingCharges();

    void printReceipt(String plateID);

}

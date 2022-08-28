/**
 * 
 */
package carParkData;

import vehicle.Vehicle;

/**
 * @author sankalpa
 *
 */

public class PettahParkingMeter {

    // Return double representing the capped cost
    private double capCost(double cost, double maxCost) {
        return Math.min(cost, maxCost);
    }

    // Calculates the parking cost of the vehicle given
    public double getParkingCharge(Vehicle vehicle) {
        // Amount charged for first three hours
        double INITIAL_CHARGE = 50;
        // Amount charged after first 3 hours
        double ADDITIONAL_CHARGE = 75;
        // Maximum cost for a vehicle parked in a 24-hour window.
        double MAX_24_HOUR_COST = 1200.0;

        double cost;
        double hoursParked = vehicle.getTimeParked();
        double slotsUsed = vehicle.getSlotsNeeded();

        // Cost for parking when vehicle has parked for less than or equal to 3 hours
        if (hoursParked <= 3.0) {
            cost = INITIAL_CHARGE * slotsUsed * hoursParked;
            return capCost(cost, MAX_24_HOUR_COST);

            // Cost for parking when vehicle has parked for less than or equal to 24 hours
        } else if (hoursParked <= 24.0) {
            // cost of first 3 hours
            cost = INITIAL_CHARGE * slotsUsed * 3;
            hoursParked -= 3.0;
            // cost of the remaining hours
            cost += ADDITIONAL_CHARGE * slotsUsed * hoursParked;
            return capCost(cost, MAX_24_HOUR_COST);

            // Cost for parking when vehicle has parked for more than 3 hours
        } else {
            // Cost of first 3 hours
            cost = INITIAL_CHARGE * slotsUsed * 3;
            hoursParked -= 3.0;
            // Cost of next 21 hours
            cost += ADDITIONAL_CHARGE * slotsUsed * 21;
            hoursParked -= 21.0;
            cost = capCost(cost, MAX_24_HOUR_COST);

            // Cost of all hours parked after initial 24 hours
            // Each iteration after is broken to 24 hours timeframes
            while (true) {
                if (hoursParked <= 24) {
                    cost += capCost(ADDITIONAL_CHARGE * slotsUsed * hoursParked, MAX_24_HOUR_COST);
                } else {
                    cost += capCost(ADDITIONAL_CHARGE * slotsUsed * 24.0, MAX_24_HOUR_COST);
                }

                hoursParked -= 24;

                if (hoursParked <= 0) {
                    return cost;
                }
            }
        }
    }
}

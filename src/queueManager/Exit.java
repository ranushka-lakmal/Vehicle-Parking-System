/**
 * 
 */
package queueManager;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import Color.ConsoleColors;
import carParkData.CarPark;
import vehicle.Vehicle;

import static Color.ConsoleColors.*;

/**
 * @author sankalpa
 *
 */

public class Exit implements Runnable {

    private String name;
    private ConcurrentLinkedDeque queue;
    private final int SLEEP;
    private CarPark carPark;

    public Exit(String name, int sleep, ConcurrentLinkedDeque queue, CarPark carPark) {
        this.name = name;
        this.queue = queue; //ConcurrentLinkedDeque
        this.SLEEP = sleep;
        this.carPark = carPark;
    }

    // Name of the current thread
    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        // Random vehicle exit from car park periodically
        while (true) {
            List<Vehicle> vehicleList = new ArrayList<>(this.carPark.getVehicleList());
            Collections.shuffle(vehicleList);
            if (vehicleList.size() != 0) {
                // Random vehicle that will be removed
                Vehicle vehicleToRemove = vehicleList.get(0);
                try {
                    // Remove the vehicle from teh car park
                    this.carPark.deleteVehicle(vehicleToRemove.getPlateId());
                    System.out.println(vehicleToRemove +  " Exit From Car Park");
                } catch (Exception e) {

                    System.out.println(ANSI_RED_BACKGROUND+vehicleToRemove.getPlateId() +" Already Exit From Car P ark"+ANSI_RESET);
                }
            }
            try {

            // Add theread, sleep  thead giving other thread to run
                Thread.sleep(SLEEP);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

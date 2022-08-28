/**
 * 
 */
package queueManager;

import java.util.concurrent.ConcurrentLinkedDeque;

import carParkData.CarPark;
import vehicle.Vehicle;

import static Color.ConsoleColors.*;

/**
 * @author sankalpa
 *
 */

public class Entry implements Runnable {

    private String name;
    private ConcurrentLinkedDeque queue;
    private final int SLEEP;
    private CarPark carPark;

    public Entry(String name, int sleep, ConcurrentLinkedDeque queue, CarPark carPark) {
        this.queue = queue;
        this.SLEEP = sleep;
        this.name = name;
        this.carPark = carPark;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        // get the first vehicle in queue then adding to carpark Continuously
        while (true) {
            Vehicle vehicleToEnter = (Vehicle) this.queue.poll();
            try {
                // Check if a vehicle is currently in front of the queue
                if (vehicleToEnter != null) {
                    this.carPark.addVehicle(vehicleToEnter);
                    System.out.println(ANSI_BLUE_BACKGROUND+vehicleToEnter + " Successfully added to the park"+ANSI_RESET);
                }
            } catch (Exception e) {
                // car park full -> then displayed error message as sorry park is full....
                System.out.println(ANSI_BLACK_BACKGROUND+"Sorry Park is full " + vehicleToEnter + "Can't added this time" + ANSI_RESET);
            }
            try {
                // Thread.sleep giving others threads to run
                Thread.sleep(SLEEP);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

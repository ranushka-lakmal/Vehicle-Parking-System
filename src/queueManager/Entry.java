/**
 * 
 */
package queueManager;

import java.util.concurrent.ConcurrentLinkedDeque;

import carParkData.CarPark;
import vehicle.Vehicle;

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
                    System.out.println(vehicleToEnter + " ENTERD PARK");
                }
            } catch (Exception e) {
                // car park full, then display PARK FULL Message
                System.out.println("PARK FULL " + vehicleToEnter + " THIS MOMENT");
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

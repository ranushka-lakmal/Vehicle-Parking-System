/**
 * 
 */

package simulator;

import carParkData.CarPark;
import park.PettahMultiStoryCarParkManager;
import queueManager.*;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author sankalpa
 *
 */

public class PettahMultiStoryCarParkSimulator {

    private final CarPark carPark;

    // Entrance 1-9
    private ConcurrentLinkedDeque entry1Queue;
    private ConcurrentLinkedDeque entry2Queue;
    private ConcurrentLinkedDeque entry3Queue;
    private ConcurrentLinkedDeque entry4Queue;
    private ConcurrentLinkedDeque entry5Queue;
    private ConcurrentLinkedDeque entry6Queue;
    private ConcurrentLinkedDeque entry7Queue;
    private ConcurrentLinkedDeque entry8Queue;
    private ConcurrentLinkedDeque entry9Queue;

    public PettahMultiStoryCarParkSimulator() {
        PettahMultiStoryCarParkManager manager = new PettahMultiStoryCarParkManager();
        this.carPark = manager.getCarPark();

        entry1Queue = new ConcurrentLinkedDeque();
        entry2Queue = new ConcurrentLinkedDeque();
        entry3Queue = new ConcurrentLinkedDeque();
        entry4Queue = new ConcurrentLinkedDeque();
        entry5Queue = new ConcurrentLinkedDeque();
        entry6Queue = new ConcurrentLinkedDeque();
        entry7Queue = new ConcurrentLinkedDeque();
        entry8Queue = new ConcurrentLinkedDeque();
        entry9Queue = new ConcurrentLinkedDeque();
    }


    public void startSimulation() {
        System.out.println("Simulation started!");
     
        Thread entry1Queue = new Thread(new EntryQueue("Entry 1 Queue", 1000, this.entry1Queue));
        Thread entry2Queue = new Thread(new EntryQueue("Entry 2 Queue", 1000, this.entry2Queue));
        Thread entry3Queue = new Thread(new EntryQueue("Entry 3 Queue", 1000, this.entry3Queue));
        Thread entry4Queue = new Thread(new EntryQueue("Entry 4 Queue", 1000, this.entry4Queue));
        Thread entry5Queue = new Thread(new EntryQueue("Entry 5 Queue", 1000, this.entry5Queue));
        Thread entry6Queue = new Thread(new EntryQueue("Entry 6 Queue", 1000, this.entry6Queue));
        Thread entry7Queue = new Thread(new EntryQueue("Entry 7 Queue", 1000, this.entry7Queue));
        Thread entry8Queue = new Thread(new EntryQueue("Entry 8 Queue", 1000, this.entry8Queue));
        Thread entry9Queue = new Thread(new EntryQueue("Entry 9 Queue", 1000, this.entry9Queue));

        
        Thread entrance1 = new Thread(new Entry("Entrance 1", 1000, this.entry1Queue, this.carPark));
        Thread entrance2 = new Thread(new Entry("Entrance 2", 1000, this.entry2Queue, this.carPark));
        Thread entrance3 = new Thread(new Entry("Entrance 3", 1000, this.entry3Queue, this.carPark));
        Thread entrance4 = new Thread(new Entry("Entrance 4", 1000, this.entry4Queue, this.carPark));
        Thread entrance5 = new Thread(new Entry("Entrance 5", 1000, this.entry5Queue, this.carPark));
        Thread entrance6 = new Thread(new Entry("Entrance 6", 1000, this.entry6Queue, this.carPark));
        Thread entrance7 = new Thread(new Entry("Entrance 7", 1000, this.entry7Queue, this.carPark));
        Thread entrance8 = new Thread(new Entry("Entrance 8", 1000, this.entry8Queue, this.carPark));
        Thread entrance9 = new Thread(new Entry("Entrance 9", 1000, this.entry9Queue, this.carPark));

       
        Thread exit1 = new Thread(new Exit("Exit 1", 2000, this.entry1Queue, this.carPark));
        Thread exit2 = new Thread(new Exit("Exit 2", 2000, this.entry2Queue, this.carPark));
        Thread exit3 = new Thread(new Exit("Exit 3", 2000, this.entry3Queue, this.carPark));
        Thread exit4 = new Thread(new Exit("Exit 4", 2000, this.entry4Queue, this.carPark));
        Thread exit5 = new Thread(new Exit("Exit 5", 2000, this.entry5Queue, this.carPark));
        Thread exit6 = new Thread(new Exit("Exit 6", 2000, this.entry6Queue, this.carPark));
        Thread exit7 = new Thread(new Exit("Exit 7", 2000, this.entry7Queue, this.carPark));
        Thread exit8 = new Thread(new Exit("Exit 8", 2000, this.entry8Queue, this.carPark));
        Thread exit9 = new Thread(new Exit("Exit 9", 2000, this.entry9Queue, this.carPark));

        entrance1.start();
        entry1Queue.start();
        exit1.start();

        entrance2.start();
        entry2Queue.start();
        exit2.start();

        entrance3.start();
        entry3Queue.start();
        exit3.start();

        entrance4.start();
        entry4Queue.start();
        exit4.start();

        entrance5.start();
        entry5Queue.start();
        exit5.start();

        entrance6.start();
        entry6Queue.start();
        exit6.start();

    }

    public static void main(String[] args) {
        PettahMultiStoryCarParkSimulator simulator = new PettahMultiStoryCarParkSimulator();
        simulator.startSimulation();
    }

}

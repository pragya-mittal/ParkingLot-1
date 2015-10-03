import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class ParkingSystem {

	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private ArrayList<Exit> exits = new ArrayList<Exit>();
	private Lot parkingLot;
	private int numOfSpaces;
	private int numOfEntries;
	private int numOfExits;
	private int numOfCars;
	
	
	public void testStart(int numOfSpaces, int numOfEntries, int numOfExits, int numOfCars){
		this.numOfSpaces = numOfSpaces;
		this.numOfEntries = numOfEntries;
		this.numOfExits = numOfExits;
		this.numOfCars = numOfCars;
		parkingLot = new Lot(numOfSpaces, numOfEntries, numOfExits);
		
		for(int i = 0; i < numOfEntries; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < numOfExits; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numOfEntries);
		for(int j = 0; j<numOfCars; j++){
			Car car = new Car("car"+j, entries, exits);
			executor.execute(car);
		}
	
		executor.shutdown();
		
	}
	
	public static void main(String [] args){
	
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<Exit> exits = new ArrayList<Exit>();
		Lot parkingLot = new Lot(1,2,2);

		for(int i = 0; i < 2; i++){
			entries.add(new Entry("entry"+i, parkingLot));
		}
		parkingLot.setEntries(entries);

		for(int k = 0; k < 2; k++){
			exits.add(new Exit("exit"+k, parkingLot));
		}
		parkingLot.setExits(exits);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		for(int j = 0; j<2; j++){
			Car car = new Car("car"+j, entries, exits);
			executor.execute(car);
		}
	
		executor.shutdown();
		
	}
	
}

package parking;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingFloors 
{
	private int count;
	private char name;
	private ParkingManager manager;
	private Map<String,Reservation> reservedSpaces=new HashMap<>();
	private Map<String,Map<String,ParkingSpace>> parkingSpaces=new HashMap<>();
	public Map<String,PriorityQueue<ParkingSpace>> available=new HashMap<>();
	private Map<String,Ticket> allotedTickets=new HashMap<>();
	private Map<String,List<String>> transaction=new HashMap<>();
	private List<String> reservations=new ArrayList<>();


	public ParkingFloors(int spaces,char name) 
	{
		this.count=1;
		this.name=name;
		this.manager=new ParkingManager(this);

		available.put("Car", new PriorityQueue<ParkingSpace>(new MyComp()));
		transaction.put("Car", new ArrayList<String>());
		spaceGenerator("Car", (int)(spaces*0.4));

		available.put("Bike", new PriorityQueue<ParkingSpace>(new MyComp()));
		spaceGenerator("Bike", (int)(spaces*0.4));
		transaction.put("Bike", new ArrayList<String>());

		available.put("Bus", new PriorityQueue<ParkingSpace>(new MyComp()));
		spaceGenerator("Bus", (int)(spaces*0.2));
		transaction.put("Bus", new ArrayList<String>());
	}


	public Map<String, PriorityQueue<ParkingSpace>> getAvailable() {
		return available;
	}


	public void spaceGenerator(String type,int size)
	{
		Map<String,ParkingSpace> tempMap=new HashMap<>();
		for(int i=0;i<size;i++,count++) { 
			ParkingSpace temp=new ParkingSpace(type,this.name+""+count,count);
			tempMap.put(temp.getName(), temp);
			this.available.get(type).add(temp);
		}
		this.parkingSpaces.put(type, tempMap);
	}


	void displayAvailable()
	{

		this.available.forEach((k,v)->System.out.println(k+" :"+v.size()));
	}

	public void alloteSpace(Customer customer,boolean reservation,int duration) 
	{
		if(reservation)
		{
			Reservation t=new Reservation(this.manager);
			manager.alloteSpace(customer,t,duration);
		}
		else
			manager.alloteSpace(customer);	
	}

	public void  remove(String name)
	{
		manager.remove(name);

	}


	public void printTransactions()
	{
		boolean empty=true;
		System.out.println("Type	Name	Vehicle No	  Parking Space	  Entry Time 	 Exit Time 	 Cost");
		for(Map.Entry k : transaction.entrySet())
		{
			List<String> type=(List<String>) k.getValue();
			for(String details : type)
			{
				empty=false;
				System.out.print(k.getKey()+"	");
				System.out.println(details);
			}
		}
		if(empty)
			System.out.println("No Transaction");

	}

	public void printTransactions(String type)
	{
		boolean empty=true;
		System.out.println("Type	Name	Vehicle No	  Parking Space	  Entry Time 	 Exit Time 	 Cost");

		List<String> transactions=transaction.get(type);
		for(String details : transactions)
		{
			empty=false;
			System.out.print(type+"	");
			System.out.println(details);
		}

		if(empty)
			System.out.println("No Transaction");

	}

	public void printReservation()
	{
		boolean empty=true;
		System.out.println("Type	Name	Vehicle No	  Parking Space	  Entry Time 	 Cost");

		for(String details : reservations)
		{
			empty=false;
			System.out.println(details);
		}

		if(empty)
			System.out.println("No Transaction");

	}


	public Map<String, Map<String, ParkingSpace>> getParkingSpaces() {
		return parkingSpaces;
	}


	public Map<String, Ticket> getAllotedTickets() {
		return allotedTickets;
	}

	public char getName() {
		return this.name;
	}


	public Map<String,List<String>> getTransaction() {
		return transaction;
	}


	public List<String> getReservations() {
		return reservations;
	}


	public Map<String,Reservation> getReservedSpaces() {
		return reservedSpaces;
	}


	public void cancelResrvation(String name) 
	{
		manager.cancelReservation(name);
		
	}


}


class MyComp implements Comparator<ParkingSpace>
{
	public int compare(ParkingSpace a,ParkingSpace b)
	{
		if(a.distance<b.distance)
			return -1;
		else
			return 1;
	}
}

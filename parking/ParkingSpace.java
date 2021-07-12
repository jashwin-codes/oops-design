package parking;

public class ParkingSpace 
{
	private String type,name;
	private Vehicle vehicle;
	public int distance;
	boolean free;
	public ParkingSpace(String type,String name,int distance) 
	{
		this.name=name;
		this.type=type;
		this.free=true;
		this.distance=distance;
	}
	public void alloteSpace(Vehicle vehicle) 
	{
		this.free=false;
		this.vehicle=vehicle;
		
	}
	public String getName() {
		return name;
	}
	public void remove() {
		this.free=true;
		this.vehicle=null;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public String getType() {
		return type;
	}
	
}

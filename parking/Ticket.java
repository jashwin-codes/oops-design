package parking;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Ticket 
{
	private String customerName;
	private String vehicleType;
	private String vehicleNo;
    private String spaceName;
    private LocalTime entryTime;
    private int reservationCost;
  
    public Ticket(Customer c,String name)
    {
    	this.customerName=c.getName();
    	this.vehicleType=c.getVehicle().getType();
    	this.vehicleNo=c.getVehicle().getNumber();
    	this.spaceName=name;
    	this.entryTime=LocalTime.now();
    }
    
    public LocalTime getEntryTime() {
		return entryTime;
	}

	void print()
    {
    	System.out.println("--------------------Ticket-------------");
    	System.out.println("Entry Time   :" +entryTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+"\nName         :"
    				+this.customerName+"\nAlotted Space:"+this.spaceName+"\nVehicle Type :"+this.vehicleType+"\nVehicle No   :"+this.vehicleNo);
    	System.out.println("---------------------------------------\n");
    }

	public String getName() {
		return this.customerName;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public int getReservationCost() {
		return reservationCost;
	}

	public void setReservationCost(int reservationCost) {
		this.reservationCost = reservationCost;
	}
}

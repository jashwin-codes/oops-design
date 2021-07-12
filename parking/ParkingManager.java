package parking;

public class ParkingManager {
	private ParkingFloors floor;
	private Payment payment;
	public ParkingManager(ParkingFloors floor)
	{
		payment=new Payment();
		this.floor=floor;
	}
	
	public void alloteSpace(Customer customer) 
	{
		ParkingSpace alloted=floor.available.get(customer.getVehicle().getType()).poll();
		alloted.alloteSpace(customer.getVehicle());
		Ticket ticket=new Ticket(customer,alloted.getName());
		ticket.print();
		floor.getAllotedTickets().put(alloted.getName(), ticket);
	}
	 
	public void alloteSpace(Customer customer, Reservation t,int duration) 
	{
		ParkingSpace alloted=floor.available.get(customer.getVehicle().getType()).poll();
		alloted.alloteSpace(customer.getVehicle());
		Ticket ticket=new Ticket(customer,alloted.getName());
		ticket.print();
		floor.getReservations().add(payment.calculateAmmount(ticket, duration));
		t.reserveSpace(ticket,duration);
		floor.getReservedSpaces().put(alloted.getName(), t);
	}
	
	public void  remove(String name)
	{
		String type;
		ParkingSpace allotedSpace;
		Ticket allotedTicket;
		if(!floor.getAllotedTickets().containsKey(name)) {
			System.out.println("Invalid input the space entered is not alloted");
			return;
		}
		
		allotedTicket=floor.getAllotedTickets().get(name);
		type=allotedTicket.getVehicleType();
		allotedSpace=floor.getParkingSpaces().get(type).get(name);
		floor.getTransaction().get(type).add(payment.calculateAmmount(allotedTicket));
		floor.available.get(type).add(allotedSpace);
		allotedSpace.remove();
		System.out.println("Your Vehicle removed suucesfully\n");
		floor.getAllotedTickets().remove(name);
			
	}

	public void removeReserved(Ticket ticket) 
	{
		ParkingSpace allotedSpace=floor.getParkingSpaces().get(ticket.getVehicleType()).get(ticket.getSpaceName());
		floor.available.get(ticket.getVehicleType()).add(allotedSpace);
		allotedSpace.remove();
		floor.getAllotedTickets().remove(ticket.getSpaceName());
		floor.getReservedSpaces().remove(ticket.getSpaceName());
		System.out.println("\n-------Reserved Space "+ticket.getSpaceName()+" removed Succesfully-------\n");
	}

	public void cancelReservation(String name) 
	{
		Reservation thread=floor.getReservedSpaces().get(name);
		payment.calculateRefund(thread.getTicket());
		thread.cancelReservation();
	}

	

	

}

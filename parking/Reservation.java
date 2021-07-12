package parking;

public class Reservation extends Thread 
{
	ParkingManager manager;
	Ticket ticket;
	long duration;
	
	public Reservation(ParkingManager manager)
	{
		this.manager=manager;
	}
	public void run()
	{
		
		try 
		{
			Thread.sleep(1000*duration);
			manager.removeReserved(ticket);
			
		} catch (InterruptedException e)
		{
			
		}
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void reserveSpace(Ticket ticket,long duration)
	{
		this.ticket=ticket;
		this.duration=duration;
		this.start();
	}
	public void cancelReservation() 
	{
		this.interrupt();
		manager.removeReserved(ticket);
	}
	
}

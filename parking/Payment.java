package parking;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Payment 
{
	Map<String,Integer> costPerHour=new HashMap<>();
	int totalCost;
	long duration;
	int discount;
	boolean coupen;
	public Payment()
	{
		costPerHour.put("Car", 10);
		costPerHour.put("Bike", 2);
		costPerHour.put("Bus", 20);
	}

	public String calculateAmmount(Ticket t)
	{
		int costPerHour=this.costPerHour.get(t.getVehicleType());
		discount=0;
		coupen=false;
		totalCost=0;
		LocalTime time1=t.getEntryTime();
		LocalTime time2=LocalTime.now();
		Scanner sc=new Scanner(System.in);
		duration=ChronoUnit.SECONDS.between(time1, time2);

		System.out.println("Do you have a coupen yes-1 no-0");
		if(Integer.parseInt(sc.nextLine())==1)
			coupen=true;

		if(duration>10)
		{
			totalCost=10*costPerHour;
			totalCost+=(duration-10)*(costPerHour/2);
			if(coupen) {
				discount=(int)((10*costPerHour)*0.5+(totalCost-(10*costPerHour))*0.10);

			}
		}
		else 
		{
			totalCost=(int)duration*costPerHour;
			if(coupen)
				discount=(int)(totalCost*0.5);
		}
		return print(time2,t);			
	}

	public String calculateAmmount(Ticket ticket,int duration)
	{
		totalCost=duration*24*costPerHour.get(ticket.getVehicleType());
		ticket.setReservationCost(totalCost);
		this.print(ticket, duration);
		return ticket.getVehicleType()+" 	"+ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getEntryTime().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+
				" 	 "+totalCost;
	}
	
	public String print(LocalTime time2,Ticket ticket)
	{
		System.out.println("------------------Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+"\nExit Time     :"+ time2.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+
				"\nTime duration :"+duration+" secoonds \nTotal Cost    :"+totalCost+
				"\nDiscount      :"+discount+"\n--------------------"+"\nFinal Cost    :"+(totalCost-=discount));
		System.out.println("---------------------------------------");
		return ticket.getName()+" 	"+ticket.getVehicleNo()+"		 "+ticket.getSpaceName()+"	        "+ticket.getEntryTime().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+
				" 	 "+time2.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+" 	 "+totalCost;
	}

	public void print(Ticket ticket,int duration)
	{
		System.out.println("------------------Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+
				"\nTime duration :"+duration+" days\nPer Day cost  :"+costPerHour.get(ticket.getVehicleType())*24+ "\nTotal Cost    :"+totalCost);
		System.out.println("-----------------------------------------");
	}

	public void calculateRefund(Ticket ticket)
	{
		LocalTime currentTime=LocalTime.now();
		LocalTime entryTime=ticket.getEntryTime();
		duration=ChronoUnit.SECONDS.between(entryTime, currentTime);
		int durationCost=(int) (duration*costPerHour.get(ticket.getVehicleType()));
		this.printRefundRecipt(ticket,duration,currentTime,durationCost);
	}

	private void printRefundRecipt(Ticket ticket, long duration2, LocalTime currentTime,int durationCost) 
	{
		System.out.println("------------------Refund Recipt-----------------");
		System.out.println("\nVehicle Type  : "+ticket.getVehicleType()+"\nExit Time     :"+ currentTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)+
				"\nTime duration :"+duration+" secoonds \nTotal Cost    :"+totalCost+
				"\nDuration Cost :"+durationCost+"\n--------------------------"+"\nRefund Ammount   :"+(totalCost-durationCost));
		System.out.println("------------------------------------------------");
		
	}
}

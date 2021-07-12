package parking;

import java.util.List;
import java.util.Scanner;

public class Customer {
	private String name;
	private Vehicle vehicle;
	
	public void gerDetails(List<String> availableTypes) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name ");
	    this.name=sc.nextLine();
	    System.out.println("Enter your vehicle type ");
	    String vehicleType=sc.nextLine();
	    if(!availableTypes.contains(vehicleType))
	    {
	    	System.out.println("invalid input");
	    	System.out.println("Enter your vehicle type ");
		    vehicleType=sc.nextLine();
	    }
	    System.out.println("Enter your vehicle no ");
	    String vehicleNo=sc.nextLine();
	    this.vehicle=new Vehicle(vehicleType,vehicleNo);
	}
	
	public String getName() {
		return name;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	

}

package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiStoryParking 
{
	private List<ParkingFloors> floors=new ArrayList<ParkingFloors>();
	private List<String> availableTypes=new ArrayList<>();


	public MultiStoryParking(int floors,int spaces)
	{
		for(int i=0;i<floors;i++)
			this.floors.add(new ParkingFloors(spaces,(char)(65+i)));
		availableTypes.add("Car");
		availableTypes.add("Bike");
		availableTypes.add("Bus");
	}

	public  ParkingFloors getFloor(char name)
	{
		for(var k : this.floors)
			if(k.getName()==name)
				return k;
		return null;

	}
	public void displayAvailable()
	{
		System.out.println("Available spaces in each floor");
		for(ParkingFloors k: this.floors) {
			System.out.println("Floor :"+k.getName());
			k.displayAvailable();
			System.out.println();
		}

	}
	public void choose()
	{
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice 1.Entry or Resevation  2.exit  3.View Transaction  4.Cancel Reservation");
			String choice=sc.nextLine();
			switch(choice)
			{
			case "1":
			{
				boolean booked=false;
				boolean reservation=false;
				int duration=0;
				this.displayAvailable();
				Customer customer=new Customer();

				customer.gerDetails(availableTypes);
				System.out.println("Press \n1->Reservation\n2->Entry");
				if(Integer.parseInt(sc.nextLine())==1)
				{
					reservation=true;
					System.out.println("How many days you want to reserverve");
					duration=Integer.parseInt(sc.nextLine());
				}
				for(ParkingFloors k: floors)
					if(k.getAvailable().get(customer.getVehicle().getType()).size()!=0) {
						k.alloteSpace(customer,reservation,duration);
						booked=true;
						break;
					}
				if(!booked)
					System.out.println("Sorry cant't allot space, "+customer.getVehicle().getType()+" spaces are full ");
				this.displayAvailable();
				break;
			}
			case "2":
			{
				System.out.println("Enter the alloted spacee name");
				String space=sc.nextLine();
				ParkingFloors parkedFloor=this.getFloor(space.charAt(0));
				if(parkedFloor==null)
				{
					System.out.println("Invalid input");
					break;
				}
				parkedFloor.remove(space);
				break;
			}

			case "3":
			{
				System.out.println("1.All Transactions     2.Vehicle Type Transation     3.Reservation");
				int choose=Integer.parseInt(sc.nextLine());
				if(choose==1)
				{
					for(ParkingFloors k : floors)
					{
						System.out.println("Floor :"+k.getName());
						k.printTransactions();
						System.out.println();
					}
					break;
				}
				else if(choose==2)
				{
					System.out.println("Enter the Vehicle type");
					String type=sc.nextLine();
					if(availableTypes.contains(type))
					{
						for(ParkingFloors k : floors)
						{
							System.out.println("Floor :"+k.getName());
							k.printTransactions(type);
							System.out.println();
						}
					}
					else
						System.out.println("Invalid input");
					break;
				}
				else
				{
					for(ParkingFloors k : floors)
					{
						System.out.println("Floor :"+k.getName());
						k.printReservation();
						System.out.println();
					}
					break;
				}
			}
			case "4":
			{
				System.out.println("Enter your reserved space name");
				String name=sc.nextLine();
				boolean canceled=false;
				for(ParkingFloors k : floors)
					if(k.getReservedSpaces().containsKey(name))
					{
						k.cancelResrvation(name);
						canceled=true;
					}
				if(!canceled)
					System.out.println("Invalid input can not cancel the reservation\n");
				break;
			}
			default :
			{
				System.out.println("Invalid Input");
				break;
			}
			}

		}



	}
}

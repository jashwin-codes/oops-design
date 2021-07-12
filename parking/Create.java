package parking;

import java.util.Scanner;

public class Create {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the no of floors");
		int floor=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the no of parking space in floors");
		int parkingSpace=Integer.parseInt(sc.nextLine());
		MultiStoryParking building=new MultiStoryParking(floor,parkingSpace);
		building.choose();
	}

}

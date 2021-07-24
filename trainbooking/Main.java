package trainbooking;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) 
	{
		ArrayList<Train> trains=new ArrayList<>();
		trains.add(new Train(1,List.of('A','B','C','D','G')));
		trains.add(new Train(2,List.of('X','Y','C')));
		trains.add(new Train(3,List.of('E','G','F','H','I')));
		trains.add(new Train(4,List.of('L','N','F','P','Q')));
		trains.add(new Train(5,List.of('R','Q','T','U','V')));
		trains.add(new Train(6,List.of('W','r','T','a','f')));
		BookingManager book=new BookingManager(trains);
		book.menu();
	}

}

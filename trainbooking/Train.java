package trainbooking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Train {
	private int name;
	private List<Character> path=new ArrayList<>();
	private List<ArrayList<Integer>> availableSeats=new ArrayList<>();
	private boolean[] seats=new boolean[7];
	private List<WaitingList> waiting =new ArrayList<>(); 
	public Train(int name,List<Character> path) 
	{
		this.name=name;
		this.path=path;
		Arrays.fill(seats, false);
		for(int i=0;i<path.size();i++)
			availableSeats.add(new ArrayList<Integer>(List.of(1,2,3,4,5,6,7,8)));
	}

	
	public List<Integer> getAvailableSeats(char start, char end) 
	{
		List<Character> path=this.path;
		List<ArrayList<Integer>> availableSeats=this.availableSeats;
		List<Integer> minList = null;
		int first=path.indexOf(start)+1;
		int last=path.indexOf(end)-1;
		//minList=(List<Integer>) availableSeats.get(first-1).clone();
		minList=new ArrayList<>(availableSeats.get(first-1));
		for(int i=first;i<=last;i++)
			minList.retainAll(availableSeats.get(i));
		return minList;
	}
	public List<ArrayList<Integer>> getAvailableSeats() {
		return availableSeats;
	}
	public List<Character> getPath() {
		List<Character> copy=new ArrayList<>();
		copy.addAll(this.path);
		//System.out.println(copy);
		//Collections.copy(copy, this.path);
		return copy;
	}

	public void removeBookedSeats(char from, char to, List<Integer> subList)
	{
		int start=path.indexOf(from);
		int end=path.indexOf(to);
		for(int i=start;i<end;i++)
		{
			List<Integer> available=new ArrayList<>(availableSeats.get(i));
			available.removeAll(subList);
			if(subList.size()!=0)
			{
				availableSeats.remove(i);
				availableSeats.add(i, new ArrayList<>(available));;

			}
		}
	}
	public void printOccupancy(char position)
	{
		List<Integer> available=availableSeats.get(path.indexOf(position));
		for(int i=1;i<=8;i++)
		{
			System.out.print("Seat No : "+i+"    Status  : ");
			if(available.contains(i))
				System.out.println("Free");
			else
				System.out.println("Booked");

		}
	}

	public int getName() {
		return name;
	}

	public void addCanceledSeats(char from, char to, List<Integer> seatNos) 
	{
		int start=path.indexOf(from);
		int end=path.indexOf(to);
		for(int i=start;i<end;i++)
		{
			List<Integer> available=new ArrayList<>(availableSeats.get(i));
			available.addAll(seatNos);
			if(seatNos.size()!=0)
			{
				availableSeats.remove(i);
				availableSeats.add(i, new ArrayList<>(available));;

			}
		}
		
	}



	public void putInWaiting(List<String> passenger,char from,char to)
	{
		this.waiting.add(new WaitingList(passenger,from,to));
	}
	
	public void removeFromWaiting(List<WaitingList> removed)
	{
		if(removed.size()!=0)
			this.waiting.removeAll(removed);
	}

	public List<WaitingList> getWaiting() {
		return waiting;
	}
	
}

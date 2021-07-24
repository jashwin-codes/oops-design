package trainbooking;

import java.util.List;

public class WaitingList 
{
	private List<String> passenger;
	private char from,to;
	
	public WaitingList(List<String> passenger, char from, char to) 
	{
		this.passenger = passenger;
		this.from = from;
		this.to = to;
	}
	public List<String> getPassenger() {
		return passenger;
	}
	public char getFrom() {
		return from;
	}
	public char getTo() {
		return to;
	}
	public void print()
	{
		System.out.println("Passengers "+passenger+"\n From :"+from+"\n To :"+to+"\n----------------------------------------");
	}
}

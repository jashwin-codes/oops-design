package trainbooking;

import java.util.ArrayList;
import java.util.List;

public class Ticket
{
	private char from,to;
	private int pnr;
	private Train train;
	private List<String> pasengers=new ArrayList<>();
	private  List<Integer> allotedSeats=new ArrayList<>();
	
	public List<Integer> getAllotedSeats() {
		return allotedSeats;
	}

	Ticket(Train train,List<String> passengers,List<Integer> allotedSeats,char from,char to,int pnr)
	{
		this.pnr=pnr;
		this.train=train;
		this.allotedSeats=allotedSeats;
		this.pasengers=passengers;
		this.from=from;
		this.to=to;
	}
	
	public void print()
	{
		System.out.println("From  :"+from+"\nTo  :"+to+"\nTrain name :"+train.getName());
		//System.out.println(pasengers+" "+allotedSeats+" "+pasengers.size());
		for(int i=0;i<pasengers.size();i++)
			System.out.println("Passenger :"+pasengers.get(i)+"    Seat No  : "+allotedSeats.get(i));
		System.out.println("-----------------------------------------------");
	}

	public List<String> getPasengers() {
		return pasengers;
	}

	public int getPnr() {
		return pnr;
	}

	public List<Integer> getSeatNos(List<Integer> serialNo) 
	{
		List<Integer> seatNos=new ArrayList<>();
		List<Integer> seatsAfterCancel=new ArrayList<>();
		List<String> passengersAfterCancel=new ArrayList<>();
		
		for(Integer i : serialNo)
			seatNos.add(allotedSeats.get(i));
		for(int i=0;i<allotedSeats.size();i++)
		{
			if(!serialNo.contains(i))
			{
				seatsAfterCancel.add(allotedSeats.get(i));
				passengersAfterCancel.add(pasengers.get(i));
			}
		}
		this.allotedSeats=seatsAfterCancel;
		this.pasengers=passengersAfterCancel;
		return seatNos;
	}

	public char getFrom() {
		return from;
	}

	public char getTo() {
		return to;
	}

	public Train getTrain() {
		return train;
	}
}

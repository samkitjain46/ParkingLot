package com.parkinglot;
// vehicle type, ticket log
import java.time.LocalDateTime;
import java.util.HashMap;

import org.omg.CORBA.TCKind;

public class TicketServ {
	private static TicketServ ticketService;
	private ParkingLotServ parkingLotService;
	private static HashMap<Integer,Ticket> ticketMap;
	private static int ticket_count=0;
	
	public TicketServ(ParkingLotServ parkingLotService) {
		this.parkingLotService=parkingLotService;
		ticketMap= new HashMap<Integer,Ticket>();
		
		
		
	}
	
	static TicketServ getInstance()
	{
		return ticketService;
	}
	static TicketServ createInstance(int floor_count,int slot_count)
	{
		if(ticketService==null)
		{
			ParkingLotServ parkingLotService = ParkingLotServ.getInstance(slot_count, floor_count);
			ticketService= new TicketServ(parkingLotService);
		}
		return ticketService;
	}
	
	int[] issueParkingTicket(Vehicle vehicle)
	{
		int[] floor_slot_ticketNumber_assigned=new int[3];
		int[] array_being_returned_from_fillAvailableSlot= new int[2];
		LocalDateTime in_time= LocalDateTime.now();
		
		array_being_returned_from_fillAvailableSlot= parkingLotService.fillAvailableSlot();
		Ticket ticket= new Ticket(array_being_returned_from_fillAvailableSlot[0],array_being_returned_from_fillAvailableSlot[1],vehicle,++ticket_count,in_time,true);
		ticketMap.put(ticket_count, ticket);
		//System.out.println("size while putting   "+ticketMap.size());
		floor_slot_ticketNumber_assigned[0]=array_being_returned_from_fillAvailableSlot[0];
		floor_slot_ticketNumber_assigned[1]=array_being_returned_from_fillAvailableSlot[1];
		floor_slot_ticketNumber_assigned[2]=ticket_count;
		return floor_slot_ticketNumber_assigned ;
	}
	
	int[] getVacancyStatus()
	{
		return parkingLotService.getVacancyStatus();
	}
	String getTicketDetails(int ticketNumber)
	{
		Ticket ticket = ticketMap.get(ticketNumber);
		String out_time="";
		String Car_status="";
		if(ticket.out_time==null)
		{
			out_time="car is still in parking";
			Car_status="Car is still in parking";
		}
		else
		{
			out_time=ticket.out_time+"";
			Car_status="car has left parking";
		}
		String to_return= "Ticket Number: "+ticket.ticket_number+"\n Vehicle Number: "+ticket.vehicle.getRegistrationNumber()+"\n Floor Number: "+ticket.floor_Number+"\n slot number: "+(ticket.slot_number+1)+"\n In Time of car: "+ticket.in_time+"\n Out time of car: "+out_time+"\n car status: "+Car_status;                 
		return to_return;
	}
	
	String getCarStatus(String registrationNumber)
	{
		for(int i=1;i<=ticketMap.size();i++)
		{
			//System.out.println(ticketMap.get(i).vehicle.getRegistrationNumber()+"avai"+registrationNumber);
			if(registrationNumber.equals(ticketMap.get(i).vehicle.getRegistrationNumber()))
			{
				return getTicketDetails(i);
			}
		}
		return "car is not there in parking";
	}
	void LeaveParking(int ticket_Number)
	{
		Ticket ticket = ticketMap.get(ticket_Number);
		parkingLotService.vacateSlot(ticket.slot_number, ticket.floor_Number);
		ticket.isCarPresent=false;
		ticket.setOutTime(LocalDateTime.now());
		
				
	}
	LocalDateTime getCarEntryTime(int ticket_number)
	{	
		return ticketMap.get(ticket_number).getInTime();
	}
	private class Ticket
	{
		int floor_Number;
		int slot_number;
		Vehicle vehicle;
		int ticket_number;
		LocalDateTime in_time;
		Boolean isCarPresent;
		LocalDateTime out_time=null;
		
		public Ticket(int floor_Number, int slot_number, Vehicle vehicle,int ticket_number,  LocalDateTime in_time,Boolean isCarPresent) {
			
			this.floor_Number = floor_Number;
			this.slot_number = slot_number;
			this.vehicle = vehicle;
			this.ticket_number=ticket_number;
			this.in_time=in_time;
			this.isCarPresent=isCarPresent;
		}
		LocalDateTime getInTime()
		{
			return in_time;
		}
		void setOutTime(LocalDateTime outTime)
		{
			this.out_time=outTime;
		}
	}

}

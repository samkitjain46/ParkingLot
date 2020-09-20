package com.parkinglot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CommandHandler {
	private static CommandHandler commandHandler;
	
	public static CommandHandler getInstance()
	{
		if(commandHandler==null)
		{
			commandHandler= new CommandHandler();
		}
		return commandHandler;
	}
	private static String create_parking_lot(String[] commandArray)
	{
		
		int no_of_floors=Integer.parseInt(commandArray[1]);
		int no_of_slots=Integer.parseInt(commandArray[2]);
		if(ParkingLotServ.countIncrement()>1)
		{
			return "parking lot already created";
		}
		TicketServ.createInstance(no_of_floors, no_of_slots);
		
		ParkingLotServ.countIncrement();
		
		return "parking lot created";
	}
	private static String park(String[] commandArray)
	{
		TicketServ ticketService = TicketServ.getInstance();
		int[] array=ticketService.issueParkingTicket(new Vehicle(commandArray[1]));
		return "park at floor: "+array[0]+"  slot:  "+(array[1]+1)+"  ticket number:  "+array[2];
	}
	
	private static String leave(String[] commandArray)
	{
		TicketServ ticketservice = TicketServ.getInstance();
		LocalDateTime in_time=ticketservice.getCarEntryTime(Integer.parseInt(commandArray[1]));
		ticketservice.LeaveParking(Integer.parseInt(commandArray[1]));
		LocalDateTime out_time=LocalDateTime.now();
		long hours = ChronoUnit.SECONDS.between(in_time, out_time);
		long payment=hours*(200);
		return "please pay "+payment+" rupees";		
	}
	private static String showStatus()
	{
		TicketServ ticketservice = TicketServ.getInstance();
		int[] status = ticketservice.getVacancyStatus();
		String str="";
		for(int i=0;i<status.length;i++)
		{
			str="floor: "+i+" vacant spots: "+status[i]+"\n"+str;
		}
		return str;
		
	}
	private static String showTicketStatus(String[] commandArray)
	{
		TicketServ ticketservice = TicketServ.getInstance();
		return ticketservice.getTicketDetails(Integer.parseInt(commandArray[1]));
	}
	private static String showCarStatus(String[] commandArray)
	{
		TicketServ ticketservice = TicketServ.getInstance();
		return ticketservice.getCarStatus(commandArray[1]);
	}
	public String execute(String commandString)
	{
		
		 String[] commandArray =commandString.split(" ");
		 String command_title=commandArray[0];
		 String message_to_display="";
		 switch(command_title)
		 {
		 case "create_parking_lot": //create_parking_lot #floor #slot
			 message_to_display=CommandHandler.create_parking_lot(commandArray);
			break;
		 case "park":              // park #registration_number 
			 message_to_display=CommandHandler.park(commandArray);
			 break;
		 case "leave": //leave #ticket_number
			 message_to_display=CommandHandler.leave(commandArray);
			 break;
		 case "show_status": //show_status
			 message_to_display=CommandHandler.showStatus();
			 break;
		 case "ticket_status"://ticket_status #ticketNumber
			 message_to_display=CommandHandler.showTicketStatus(commandArray);
			 break;
		 case "car_status"://car_status #carRegistrationNumber
			 message_to_display=CommandHandler.showCarStatus(commandArray);
			 break;
		default:
			message_to_display="unknown command";
				
			 
			 
		 }
		
		 return message_to_display;
	}
}

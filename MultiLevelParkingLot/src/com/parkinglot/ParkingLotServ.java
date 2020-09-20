package com.parkinglot;
import java.util.*;


public class ParkingLotServ {
	private static ParkingLotServ parkinglotservice;
	private static HashMap<Integer,HashMap<Integer,Slots>> slotMap;
	private static int[] vacancy_status;
	private static int slots_on_each_floor;
	private static int total_no_of_floors;
	private static int count=0;
	
	static int countIncrement()
	{
		count++;
		return count;
	}
	
	//constructor
	protected ParkingLotServ(int slots_per_floor,int no_of_floors)
	{
		slotMap= new  HashMap<Integer,HashMap<Integer,Slots>>();
		for(int i=0;i<no_of_floors;i++)
		{
			HashMap<Integer,Slots> floor = new HashMap<Integer,Slots>();
			for(int k=0;k<slots_per_floor;k++)
			{
				floor.put(k,new Slots(k) );
			}
			slotMap.put(i,floor);
		}
	}
	
	// method which created a single object of this class, and returns it
	static ParkingLotServ getInstance(int slots_per_floor, int no_of_floors)
	{
		slots_on_each_floor=slots_per_floor;
		total_no_of_floors=no_of_floors;
		vacancy_status= new int[no_of_floors];
		for(int i=0;i<vacancy_status.length;i++)
		{
			vacancy_status[i]=slots_per_floor;
		}
		
		if(parkinglotservice==null)
		{
			return new ParkingLotServ(slots_per_floor, no_of_floors);
		}
		return parkinglotservice;
	}
	
	
	int[] fillAvailableSlot()
	{
		int nextAvailableSlot=-1;
		int nextAvailableFloor=-1;
		int[] floor_slot_assigned=new int[2]; //0- floorNumber 1-slotNumber
		
		outer:
		for(int i=0;i<slotMap.size();i++)
		{
			for(int j=0;j<slotMap.get(i).size();j++)
			{
				if(slotMap.get(i).get(j).slotStatus==false)
				{
					vacancy_status[i]--;
					Slots s=slotMap.get(i).get(j);
					s.slotNumber=j;
					s.floorNumber=i;
					s.slotStatus=true;
					floor_slot_assigned[0]=i;
					floor_slot_assigned[1]=j;
					break outer;
				}
			}
		}
		return floor_slot_assigned;
		
	}
	
	void vacateSlot(int slotNumber,int floorNumber)
	{
		slotMap.get(floorNumber).get(slotNumber).slotStatus=false;		
		vacancy_status[floorNumber]++;
	}
	
	int[] getVacancyStatus()
	{
		return vacancy_status;
	}
	private class Slots
	{
		private int slotNumber;
		private boolean slotStatus;
		private int floorNumber;
		
		public Slots(int slotNumber) {
			this.slotNumber = slotNumber;
			this.slotStatus = false;
		}
		
	}
	

}

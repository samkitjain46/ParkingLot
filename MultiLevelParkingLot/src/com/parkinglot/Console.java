package com.parkinglot;

import java.util.Scanner;

public class Console {
	public static void main(String args[])
	{
		CommandHandler commandHandler= CommandHandler.getInstance();
		Scanner sc = new Scanner(System.in);
		System.out.println("please give a command");
		System.out.println();
		String command=sc.nextLine();
		while(true)
		{
			if(command!="exit")
			{
				System.out.println(commandHandler.execute(command));
				System.out.println();
				System.out.println("Enter new command");
				command=sc.nextLine();
				
			}
			else
			{
				System.out.println("exiting System");
				System.exit(1);
			}
		}
	}

}

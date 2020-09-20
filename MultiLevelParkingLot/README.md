# Parking Lot Design with Multiple Floors 

This is the solution to problem statement for a multi level parking lot

## Problem Statement:

- Parking lot can have n number of floors with m number of slots
- Parking ticket is issued to the car as it enters
- Ticket have car's registration number, ticket number, slot assigned, floor assigned, In time of car.
- when a car exits the parking lot, ticketing system generates the payment due.
- At any time ticketing system can provide the full details related to any ticket number
- We can have the status of vacant slots at each floor at any time
- This project supports interactive command system on console. It can be used by giving commands from the console.


## Getting Started with the Setup

### System Requirements:
- Java 1.8

### Commands Supported
- create_parking_lot 'Number of Floors' 'Number of slots per floor'
- Example: "create_parking_lot 10 100" , It will create parking lot with 10 floors, each floor having 100 spots.
- park 'car Registration Number'
- Example: "park DL4CE34342", It will generate the parking ticket and return the floor and slot assigned.
- leave 'ticket number'
- Example: "1eave 1", It will empty the slot where the car with ticket number 1 is parked, and It will return the payment due.
- show_status , this command will show the number of slots vacant at each floor.
- ticket_status 'ticket number'
- Example: "ticket_status 1", it will show all the details related to ticket number 1
- car_status 'car registration number'
- Example: "car_status DL4CE34342", it will show all the details related to this car, if this car is not there in parking lot, then it will return a message accordingly.

### Sample Test Cases & Outputs for Reference

create_parking_lot 10 100
parking lot created

Enter new command
park DL4CE34343
park at floor: 0  slot:  1  ticket number:  1

Enter new command
park DL8C23232
park at floor: 0  slot:  2  ticket number:  2

Enter new command
show_status
floor: 9 vacant spots: 100
floor: 8 vacant spots: 100
floor: 7 vacant spots: 100
floor: 6 vacant spots: 100
floor: 5 vacant spots: 100
floor: 4 vacant spots: 100
floor: 3 vacant spots: 100
floor: 2 vacant spots: 100
floor: 1 vacant spots: 100
floor: 0 vacant spots: 98


Enter new command
leave 2
please pay 2400 rupees

Enter new command
ticket_status 2
Ticket Number: 2
 Vehicle Number: DL8C23232
 Floor Number: 0
 slot number: 2
 In Time of car: 2020-09-20T02:42:34.675
 Out time of car: 2020-09-20T02:42:46.869
 car status: car has left parking

Enter new command
car_status DL4CE34343
Ticket Number: 1
 Vehicle Number: DL4CE34343
 Floor Number: 0
 slot number: 1
 In Time of car: 2020-09-20T02:42:27.994
 Out time of car: car is still in parking
 car status: Car is still in parking

Enter new command

			


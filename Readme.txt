#This file describes about project and implementation

# Project Description

This project Implemented using Runnable worker threads for each of the road movement and another thread
to keep adding new cars to each of the road's both direction and also print the number of cars waiting in each road 
for each second interval.
   
# How to Run
Pre-requisite :
Please ensure you have JRE 1.8 available with the IDE (Eclipse)

Option 1:
1. Unzip the TrafficSignal.zip and open eclipse.
2. Go to File->Import->Maven->Existing Maven Project
3. Browse and give the path of the unzipped root folder of the project i.e TrafficSignal
4. click next->finish to complete the import
5. go to pom.xml and right click ->Run as->Maven install.
6. Maven will checkout all dependencies and build the project successfully.
7. Right Click com.traffic.signal.TrafficSignalApp.java ->Run as -> Java Program.
8. Observe the results in the console.

Observed Console output is below : 

Second 0 : N = 0;W = 0;S = 0;E = 0
Second 1 : N = 1;W = 1;S = 1;E = 1
Second 2 : N = 2;W = 2;S = 2;E = 2
Second 3 : N = 2;W = 3;S = 2;E = 3
Second 4 : N = 2;W = 4;S = 2;E = 4
Second 5 : N = 3;W = 5;S = 3;E = 5
Second 6 : N = 4;W = 6;S = 4;E = 6
Second 7 : N = 5;W = 6;S = 5;E = 6
Second 8 : N = 6;W = 6;S = 6;E = 6
Second 9 : N = 7;W = 7;S = 7;E = 7
Second 10 : N = 8;W = 8;S = 8;E = 8
Second 11 : N = 8;W = 9;S = 8;E = 9
Second 12 : N = 8;W = 10;S = 8;E = 10
Second 13 : N = 9;W = 11;S = 9;E = 11
Second 14 : N = 10;W = 12;S = 10;E = 12
Second 15 : N = 11;W = 12;S = 11;E = 12
Second 16 : N = 12;W = 12;S = 12;E = 12
Second 17 : N = 13;W = 13;S = 13;E = 13
Second 18 : N = 14;W = 14;S = 14;E = 14
Second 19 : N = 14;W = 15;S = 14;E = 15
Second 20 : N = 14;W = 16;S = 14;E = 16




Option 2: You can follow the same steps upto step 6.
Step 7: Go to command line using CMD command in windows machine.
Step 8 : Go to the project's target folder and run the below command

java -jar TrafficSignalApp-0.0.1.jar

You can observe the results in the command line 
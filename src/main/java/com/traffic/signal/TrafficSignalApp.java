/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */
package com.traffic.signal;


/** 
 * This class contains the main method that invokes the TrafficSignal Application
 * @author VJ
 */
public class TrafficSignalApp {

	public static void main(String[] args) throws InterruptedException {
		TrafficSimulator simulator = new TrafficSimulator();		
		simulator.runTrafficSimulator();
	}
}

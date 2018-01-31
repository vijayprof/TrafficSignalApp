/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */
package com.traffic.signal.constants;


/** 
 * This enum contains the constants representing the 4 types of traffic signals for Snell and Weaver road
 * @author VJ
 *
 */
public enum TrafficSignal {
	
	SNELL_ROAD_GREEN("Snell Road Green Signal"), 
	SNELL_ROAD_RED("Snell Road Red Signal"),
	WEAVER_ROAD_GREEN("Weaver road green Signal"),
	WEAVER_ROAD_RED("Weaver road Red Signal");
	
	private String signalDesc;
	
	/**
	 * @param signalDesc
	 */
	TrafficSignal(String signalDesc){	
		this.signalDesc = signalDesc;
	}	

	/**
	 * @return the signalDesc
	 */
	public String getSignalDesc() {
		return signalDesc;
	}
	

}

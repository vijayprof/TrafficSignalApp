/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */

package com.traffic.signal.constants;

/** 
 * This enum contains the constants representing the 4 directions of the Snell and Weaver road
 * @author VJ
 *
 */
public enum RoadDirection {

	NORTH("Snell North Road"), 
	SOUTH("Snell South road"),
	EAST("Weaver East Road"), 
	WEST("Weaver West Road");

	
	private String directionDesc;
	
	/**
	 * 
	 * @param directionDesc
	 */
	RoadDirection(String directionDesc){	
		this.directionDesc = directionDesc;
	}	

	/**
	 * @return the directionDesc
	 */
	public String getDirectionDesc() {
		return directionDesc;
	}
	

}

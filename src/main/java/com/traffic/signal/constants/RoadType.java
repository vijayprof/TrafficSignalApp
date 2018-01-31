/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */
package com.traffic.signal.constants;


/** 
 * This enum contains the constants representing the 2 types of roads i.e  Snell and Weaver road
 * @author VJ
 *
 */
public enum RoadType {
	
	
	SNELL_ROAD("Snell road"), 
	WEAVER_ROAD("Weaver road");

	
	private String roadDesc;
	
	/**
	 * 
	 * @param roadDesc
	 */
	RoadType(String roadDesc){	
		this.roadDesc = roadDesc;
	}	

	/**
	 * @return the roadDesc
	 */
	public String getRoadDesc() {
		return roadDesc;
	}
	

}

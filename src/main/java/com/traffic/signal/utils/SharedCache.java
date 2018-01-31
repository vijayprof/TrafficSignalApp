/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */
package com.traffic.signal.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.traffic.signal.constants.RoadDirection;
import com.traffic.signal.constants.RoadType;
import com.traffic.signal.constants.TrafficSignal;


/** 
 * This class implements a shared static cache that loads all the enum constants into maps and creates
 * necessary queue for each road that will be used by the worker threads of Traffic Signal application
 * @author VJ
 *
 */
public final class SharedCache {

	private final static Logger logger = LoggerFactory.getLogger(SharedCache.class);

	private static ConcurrentHashMap<RoadType, TrafficSignal> roadSignalMap;
	private static volatile boolean isSnellRoadGreen = false;
	private static volatile boolean isWeaverRoadGreen = true ;
	private static ConcurrentHashMap<RoadDirection, LinkedBlockingQueue<String>> roadMap;

	private static final Properties prop = new Properties();

	/**
	 * Private construction to prevent instantiation
	 */
	private SharedCache() {

	}
	/**
	 * @return the isSnellRoadGreen
	 */
	public static boolean isSnellRoadGreen() {
		if(roadSignalMap.get(RoadType.SNELL_ROAD) == TrafficSignal.SNELL_ROAD_GREEN) {
			isSnellRoadGreen =  true;
		} else {
			isSnellRoadGreen =  false;
		}
		return isSnellRoadGreen;
	}

	/**
	 * @return the isWeaverRoadGreen
	 */
	public static boolean isWeaverRoadGreen() {
		if(roadSignalMap.get(RoadType.WEAVER_ROAD) == TrafficSignal.WEAVER_ROAD_GREEN) {
			isWeaverRoadGreen =  true;
		} else {
			isWeaverRoadGreen =  false;
		}
		return isWeaverRoadGreen;
	}

	/**
	 * @return the roadSignalMap
	 */
	public static ConcurrentHashMap<RoadType, TrafficSignal> getRoadSignalMap() {
		return roadSignalMap;
	}


	/**
	 * @param isSnellRoadGreen the isSnellRoadGreen to set
	 */
	public static void setSnellRoadGreen(boolean isSnellRoadGreen) {
		SharedCache.isSnellRoadGreen = isSnellRoadGreen;
	}

	/**
	 * @param isWeaverRoadGreen the isWeaverRoadGreen to set
	 */
	public static void setWeaverRoadGreen(boolean isWeaverRoadGreen) {
		SharedCache.isWeaverRoadGreen = isWeaverRoadGreen;
	}

	/**
	 * @return the roadQueueMap
	 */
	public static ConcurrentHashMap<RoadDirection, LinkedBlockingQueue<String>> getRoadMap() {

		return roadMap;
	}

	static {
		initCache();

	}

	/**
	 * method creates and loads the necessary queue and constant maps into static 
	 * cache that will be used by application threads
	 */
	public static void initCache() {
		roadSignalMap = new ConcurrentHashMap<RoadType, TrafficSignal>();
		roadSignalMap.put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_GREEN);
		roadSignalMap.put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_RED);		

		LinkedBlockingQueue<String> carQueueNorth = new LinkedBlockingQueue<String>();
		LinkedBlockingQueue<String> carQueueSouth = new LinkedBlockingQueue<String>(); 
		LinkedBlockingQueue<String> carQueueEast = new LinkedBlockingQueue<String>(); 
		LinkedBlockingQueue<String> carQueueWest = new LinkedBlockingQueue<String>(); 

		roadMap = new ConcurrentHashMap<RoadDirection, LinkedBlockingQueue<String>>(); 
		roadMap.put(RoadDirection.NORTH, carQueueNorth);
		roadMap.put(RoadDirection.SOUTH, carQueueSouth);
		roadMap.put(RoadDirection.EAST, carQueueEast);
		roadMap.put(RoadDirection.WEST, carQueueWest);

		loadApplicationProperties();
	}

	private static void loadApplicationProperties(){

		InputStream input = null;
		try {
			input = SharedCache.class.getClassLoader().getResourceAsStream("config.properties");
			/** load a properties file*/
			prop.load(input);
			/** get the property value and print it out*/
			logger.info("numberOfSecondsToMonitor = "+prop.getProperty("numberOfSecondsToMonitor"));
			logger.info("milliSecForFirstCarToMove = "+prop.getProperty("milliSecForFirstCarToMove"));
			logger.info("milliSecForNextCarsToMove = "+prop.getProperty("milliSecForNextCarsToMove"));
			logger.info("milliSecAllSignalGoesRed = "+prop.getProperty("milliSecAllSignalGoesRed"));
			logger.info("freqToCheckNoOfCarsWaitingInTheRoad = "+prop.getProperty("freqToCheckNoOfCarsWaitingInTheRoad"));

		} catch (IOException ex) {
			logger.error("Error Occured while loading properties ", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("Error Occured while closing properties file", e);
				}
			}
		}
	}

	/**
	 * @return the prop
	 */
	public static Properties getProp() {
		return prop;
	}

}
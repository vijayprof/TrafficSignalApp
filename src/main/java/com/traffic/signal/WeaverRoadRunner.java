/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */
package com.traffic.signal;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.traffic.signal.constants.RoadType;
import com.traffic.signal.constants.TrafficSignal;
import com.traffic.signal.constants.TrafficSignalConstants;
import com.traffic.signal.utils.SharedCache;
import com.traffic.signal.utils.TrafficSignalUtils;

/** 
 * This class is the worker thread takes car of queuing and moving the cars
 * on Weaver Road for both EAST and WEST direction according the Traffic Signal
 * @author VJ
 *
 */
public final class WeaverRoadRunner implements Runnable {
	
	private final static Logger logger = LoggerFactory.getLogger(WeaverRoadRunner.class);

	private LinkedBlockingQueue<String> carsInEast;
	private LinkedBlockingQueue<String> carsInWest;
	private ConcurrentHashMap<RoadType, TrafficSignal> signalMap;
	private static Properties prop = SharedCache.getProp();
	
	/**
	 * Constructor to initialize thread with both side queues and the signalMap
	 * @param eastRoadCars
	 * @param westRoadCars
	 * @param signalMap
	 */
	public WeaverRoadRunner(LinkedBlockingQueue<String> eastRoadCars, LinkedBlockingQueue<String> westRoadCars, ConcurrentHashMap<RoadType, TrafficSignal> signalMap) {
		carsInEast = eastRoadCars;
		carsInWest = westRoadCars;
		this.signalMap = signalMap;
	}
	
	/**
	 * run method implemented that define the task need to be done by the worker thread.
	 */
	public void run() { 
		try {
			while (!carsInEast.isEmpty() || !carsInWest.isEmpty()) {
				synchronized(signalMap){					
					while(!SharedCache.isWeaverRoadGreen()){
						/**Put the thread to wait since since is not yet green*/
						signalMap.wait();
					}

					if(SharedCache.isWeaverRoadGreen()){
						/**set thread wait for 2 second for first car to move*/
						Thread.sleep(Integer.valueOf(prop.getProperty("milliSecForFirstCarToMove",TrafficSignalConstants.TWO_THOUSAND)));
						/**Move one car from both East and west direction*/
						if(!carsInEast.isEmpty()){
							carsInEast.poll();
						}
						if(!carsInWest.isEmpty()){
							carsInWest.poll();
						}
						
						/**set thread wait for 1 second for second car to move*/
						Thread.sleep(Integer.valueOf(prop.getProperty("milliSecForNextCarsToMove",TrafficSignalConstants.THOUSAND)));
						/**Move one car from both East and west direction*/
						if(!carsInEast.isEmpty()){
							carsInEast.poll();
						}
						if(!carsInWest.isEmpty()){
							carsInWest.poll();
						}
						TrafficSignalUtils.setAllSignalRed(signalMap);
						/**Hold the signal in all RED for 1 second*/
						Thread.sleep(Integer.valueOf(prop.getProperty("milliSecAllSignalGoesRed",TrafficSignalConstants.THOUSAND)));
						/**Turn Snell Road signal to green*/
						TrafficSignalUtils.setSnellRoadGreen(signalMap);
						
						/**Release the signal so Snell road cars will start move*/
						signalMap.notifyAll(); 
					} 
				} 
			} 
		}    	 
		catch (InterruptedException e) {
			logger.error("Error occured while processing WeaverRoad :", e);
		} 
	}
}

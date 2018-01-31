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
 * on Snell Road for both NORTH and EAST direction according the Traffic Signal
 * @author VJ
 *
 */
public final class  SnellRoadRunner implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(SnellRoadRunner.class);

	private LinkedBlockingQueue<String> carsInSouth;
	private LinkedBlockingQueue<String> carsInNorth;
	private ConcurrentHashMap<RoadType, TrafficSignal> signalMap;	
	private static Properties prop = SharedCache.getProp();

	/** 
	 * Constructor to initialize thread with both side queues and the signalMap
	 * @param northRoadCars
	 * @param southRoadCars
	 * @param signalMap
	 */
	public SnellRoadRunner(LinkedBlockingQueue<String> northRoadCars, LinkedBlockingQueue<String> southRoadCars,ConcurrentHashMap<RoadType, TrafficSignal> signalMap) {
		carsInSouth = southRoadCars;
		carsInNorth = northRoadCars;
		this.signalMap = signalMap;
	}

	/**
	 * run method implemented that define the task need to be done by the worker thread.
	 */
	public void run() { 

		try { 
			while (!carsInSouth.isEmpty() || !carsInNorth.isEmpty()) {
				synchronized(signalMap){
					while(!SharedCache.isSnellRoadGreen()){
						/**Put the thread to wait since since is not yet green*/
						signalMap.wait();
					}
					if(SharedCache.isSnellRoadGreen()){
						/**set thread wait for 2 second for first car to move*/
						Thread.sleep(Integer.valueOf(prop.getProperty("milliSecForFirstCarToMove",TrafficSignalConstants.TWO_THOUSAND)));
						/**Move one car from both North and South direction*/
						if(!carsInNorth.isEmpty()){							
							carsInNorth.poll();
						}
						if(!carsInSouth.isEmpty()){							
							carsInSouth.poll();
						}

						/**set thread wait for 1 second for second car to move*/
						Thread.sleep(Integer.valueOf(prop.getProperty("milliSecForNextCarsToMove",TrafficSignalConstants.THOUSAND)));
						/**Move one car from both North and South direction*/
						if(!carsInNorth.isEmpty()){							
							carsInNorth.poll();
						}
						if(!carsInSouth.isEmpty()){							
							carsInSouth.poll();
						}			
						TrafficSignalUtils.setAllSignalRed(signalMap);
						/**Hold the signal in all RED for 1 second*/
						Thread.sleep(Integer.valueOf(prop.getProperty("milliSecAllSignalGoesRed",TrafficSignalConstants.THOUSAND)));
						/**Turn Weaver Road signal to green*/
						TrafficSignalUtils.setWeaverRoadGreen(signalMap);
						/**Release the signal so Weaver road cars will start move*/
						signalMap.notifyAll();
					}	
				}
			}
		} catch (InterruptedException e) {
			logger.error("Error occured while processing Snell Road :", e);
		}
	}
}

/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */
package com.traffic.signal;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.traffic.signal.constants.RoadDirection;
import com.traffic.signal.constants.RoadType;
import com.traffic.signal.constants.TrafficSignal;
import com.traffic.signal.utils.SharedCache;

/** 
 * This class creates the necessary class objects and start the threads which simulates
 * Traffic Signal in a four road junction
 * @author VJ
 */
public final class TrafficSimulator {
	
	private final static Logger logger = LoggerFactory.getLogger(TrafficSimulator.class);

	public void runTrafficSimulator(){

		
		Thread carArraivalThread = new Thread(new CarQueueManager(SharedCache.getRoadMap()));
		carArraivalThread.start();

		ConcurrentHashMap<RoadType, TrafficSignal> signalMap = SharedCache.getRoadSignalMap();
		Thread snellRoadThread = new Thread(new SnellRoadRunner(SharedCache.getRoadMap().get(RoadDirection.NORTH), SharedCache.getRoadMap().get(RoadDirection.SOUTH),signalMap));
		Thread weaverRoadThread = new Thread(new WeaverRoadRunner(SharedCache.getRoadMap().get(RoadDirection.EAST), SharedCache.getRoadMap().get(RoadDirection.WEST), signalMap));
		
		snellRoadThread.start();
		weaverRoadThread.start();
	}


}

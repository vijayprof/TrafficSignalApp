/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */
package com.traffic.signal.utils;

import java.util.Properties;
/** 
 * This class contains utility methods utilized by the Traffic signal application threads
 * @author VJ
 *
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.traffic.signal.constants.RoadDirection;
import com.traffic.signal.constants.RoadType;
import com.traffic.signal.constants.TrafficSignal;

public final class TrafficSignalUtils {
	
	private static Properties prop = SharedCache.getProp();

	private TrafficSignalUtils(){
		/** private constructor to prevent instantiation**/
	}

	/**
	 * Method turns all the signals to RED
	 * @param signalMap
	 */
	public static void setAllSignalRed(ConcurrentHashMap<RoadType, TrafficSignal> signalMap) {

		for(RoadType roadType:SharedCache.getRoadSignalMap().keySet()){		
			if(roadType == RoadType.WEAVER_ROAD){
				signalMap.put(roadType, TrafficSignal.WEAVER_ROAD_RED);					
			} else if(roadType == RoadType.SNELL_ROAD) {

				signalMap.put(roadType, TrafficSignal.SNELL_ROAD_RED);
			}
		}
	}

	/**
	 * Method turns Weaver Road Signal to GREEN
	 * @param signalMap
	 */
	public static void setWeaverRoadGreen(ConcurrentHashMap<RoadType, TrafficSignal> signalMap) {
		for(RoadType roadType:SharedCache.getRoadSignalMap().keySet()){
			if(roadType == RoadType.WEAVER_ROAD){
				signalMap.put(roadType, TrafficSignal.WEAVER_ROAD_GREEN);					
			} else if(roadType == RoadType.SNELL_ROAD) {
				signalMap.put(roadType, TrafficSignal.SNELL_ROAD_RED);
			}
		}
		SharedCache.setSnellRoadGreen(false);
		SharedCache.setWeaverRoadGreen(true);

	}

	/**
	 * Method sets Snell road signal to GREEN
	 * @param signalMap
	 */
	public static void setSnellRoadGreen(ConcurrentHashMap<RoadType, TrafficSignal> signalMap) {
		for(RoadType roadType:signalMap.keySet()){
			if(roadType == RoadType.WEAVER_ROAD){
				signalMap.put(roadType, TrafficSignal.WEAVER_ROAD_RED);					
			} else if(roadType == RoadType.SNELL_ROAD) {
				signalMap.put(roadType, TrafficSignal.SNELL_ROAD_GREEN);
			}
		}
		SharedCache.setSnellRoadGreen(true);
		SharedCache.setWeaverRoadGreen(false);
	}
	
	/**
	 * method checks if all queue is empty
	 * @return
	 */
	public static boolean isAllCarQueueEmpty() {
		boolean isAllCarQueueIsEmpty = true;
		for(LinkedBlockingQueue<String> carQueue:SharedCache.getRoadMap().values()){
			if(!carQueue.isEmpty()){
				isAllCarQueueIsEmpty =  false;
				break;
			}
		}
		return isAllCarQueueIsEmpty;
		
	}
}

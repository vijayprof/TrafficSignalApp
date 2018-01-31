/**
 * This code is developed by the author Vijayakumar Ramachandran for the interview assignment
 * Anyone from barclaycardus organization feel free to copy or modify as needed as it is not bound to any copy rights
 */

package com.traffic.signal;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.traffic.signal.CarQueueManager;
import com.traffic.signal.constants.RoadDirection;
import com.traffic.signal.constants.TrafficSignalConstants;
import com.traffic.signal.utils.SharedCache;

/** 
 * This class is the worker thread that sends one car to each road per second
 * and also it prints the number of cars waiting at each road at each second
 * @author VJ
 *
 */
public final class CarQueueManager implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(CarQueueManager.class);
	
	private static AtomicInteger  totalCarsToRun = new AtomicInteger(TrafficSignalConstants.INT_ONE);
	private static int myCounter = TrafficSignalConstants.INT_ONE;
	private static int maxCarsToRunThrough = Integer.valueOf(SharedCache.getProp().getProperty("numberOfSecondsToMonitor"));
	private static ConcurrentHashMap<RoadDirection, LinkedBlockingQueue<String>> roadMap;
	
	private static Properties prop = SharedCache.getProp();
	
	public CarQueueManager(ConcurrentHashMap<RoadDirection, LinkedBlockingQueue<String>> roadMapInput) {
		roadMap = roadMapInput;
	}
	
	public void run() { 
		
		String str= null;
		String statusMessage = null;
		
		if( maxCarsToRunThrough <= 0){
			maxCarsToRunThrough = TrafficSignalConstants.INT_TWENTY;
		}
		/** initially no cars waiting in the intersection hence printing all zeros **/
		System.out.print("Second 0 : N = 0;W = 0;S = 0;E = 0\n");
		while(maxCarsToRunThrough >= totalCarsToRun.get()){			
			for(RoadDirection direction : roadMap.keySet()){
				if(direction == RoadDirection.NORTH){
					roadMap.get(direction).add(TrafficSignalConstants.N+Integer.toString(myCounter));
				} else if(direction == RoadDirection.SOUTH){
					roadMap.get(direction).add(TrafficSignalConstants.S+Integer.toString(myCounter));					
				} else if(direction == RoadDirection.EAST){
					roadMap.get(direction).add(TrafficSignalConstants.E+Integer.toString(myCounter));					
				} else if(direction == RoadDirection.WEST){		
					roadMap.get(direction).add(TrafficSignalConstants.W+Integer.toString(myCounter));	
				}
			}
			str = TrafficSignalConstants.EMPTY_STRING;
			System.out.print("Second "+myCounter+" : ");
			for(LinkedBlockingQueue<String> carQueue : roadMap.values()){
				if(!carQueue.isEmpty()){
					statusMessage = getQueueStatusMessageNew(carQueue);
					str = str + statusMessage;
				}			 
			}
			str = str.replaceAll(";$", TrafficSignalConstants.EMPTY_STRING);
			System.out.print(str+"\n");
			myCounter = totalCarsToRun.incrementAndGet();
			try {
				Thread.sleep(Integer.valueOf(prop.getProperty("milliSecToCheckWaitingCars","1000")));
			} catch (InterruptedException e) {
				logger.error("Error occured in Car arraival process : ",e);
			}
		}
	}
	
	/** This method will compose the message to be displayed in the console contains
	 *  direction type and the number of cars waiting
	 *  @param carQueue the blockingQueue of the specific direction
	 *  @return String the composed String in the required format with the size of the queue
	 *  
	 */
	private String getQueueStatusMessageNew(LinkedBlockingQueue<String> carQueue) {
		return new StringBuilder()
				.append(carQueue.peek().substring(TrafficSignalConstants.INT_ZERO,TrafficSignalConstants.INT_ONE).toString())
				.append(" = ")
				.append(carQueue.size())
				.append(";").toString();

	}
}

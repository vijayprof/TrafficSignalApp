/**
 * 
 */
package com.traffic.signal;

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.LogManager;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.traffic.signal.constants.RoadDirection;
import com.traffic.signal.constants.RoadType;
import com.traffic.signal.constants.TrafficSignal;
import com.traffic.signal.utils.SharedCache;

import ch.qos.logback.classic.Level;

/**
 * @author VJ
 *
 */
public class TestSnellRoadRunner {
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		/** Disable console printing */
		System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
		    @Override public void write(int b) {}}){
			@Override public void print(String s) {} });

	}
	
	@Test
	public void testSnellRoadRunner() throws InterruptedException {
		
		Thread carArraivalThread = new Thread(new CarQueueManager(SharedCache.getRoadMap()));
		carArraivalThread.start();
		ConcurrentHashMap<RoadType, TrafficSignal> signalMap = SharedCache.getRoadSignalMap();
		Thread snellRoadThread = new Thread(new SnellRoadRunner(SharedCache.getRoadMap().get(RoadDirection.NORTH), SharedCache.getRoadMap().get(RoadDirection.SOUTH),signalMap));
		snellRoadThread.start();
	
		String peekLaterNorth = SharedCache.getRoadMap().get(RoadDirection.NORTH).peek();
		String peekLatereSouth = SharedCache.getRoadMap().get(RoadDirection.SOUTH).peek();

		assertNotEquals(peekLaterNorth, 0);
		assertNotEquals(peekLatereSouth, 0);
		
	}

}

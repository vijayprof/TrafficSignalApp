package com.traffic.signal;

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;

import com.traffic.signal.constants.RoadDirection;
import com.traffic.signal.constants.RoadType;
import com.traffic.signal.constants.TrafficSignal;
import com.traffic.signal.utils.SharedCache;

public class TestWeaverRoadRunner {
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
	public void testWeaverRoadRunner() throws InterruptedException {
		
		Thread carArraivalThread = new Thread(new CarQueueManager(SharedCache.getRoadMap()));
		carArraivalThread.start();
		ConcurrentHashMap<RoadType, TrafficSignal> signalMap = SharedCache.getRoadSignalMap();
		Thread snellRoadThread = new Thread(new SnellRoadRunner(SharedCache.getRoadMap().get(RoadDirection.EAST), SharedCache.getRoadMap().get(RoadDirection.WEST),signalMap));
		snellRoadThread.start();
		
		String peekLaterEast = SharedCache.getRoadMap().get(RoadDirection.EAST).peek();
		String peekLatereWest = SharedCache.getRoadMap().get(RoadDirection.WEST).peek();
		
		assertNotEquals(peekLaterEast, 0);
		assertNotEquals(peekLatereWest, 0);
		
	}

}

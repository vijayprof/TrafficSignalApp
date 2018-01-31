package com.traffic.signal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.traffic.signal.constants.RoadDirection;
import com.traffic.signal.utils.SharedCache;

public class TestTrafficSimulator {
	
	private TrafficSimulator trafficSimulator;

	@Before
	public void setUp() throws Exception {
		trafficSimulator = new TrafficSimulator();
	}

	@Test
	public void test() throws InterruptedException {
		trafficSimulator.runTrafficSimulator();

		String peekLaterNorth = SharedCache.getRoadMap().get(RoadDirection.NORTH).peek();
		String peekLatereSouth = SharedCache.getRoadMap().get(RoadDirection.SOUTH).peek();

		assertNotEquals(peekLaterNorth, 0);
		assertNotEquals(peekLatereSouth, 0);
		
		
		String peekLaterEast = SharedCache.getRoadMap().get(RoadDirection.EAST).peek();
		String peekLatereWest = SharedCache.getRoadMap().get(RoadDirection.WEST).peek();
		
		assertNotEquals(peekLaterEast, 0);
		assertNotEquals(peekLatereWest, 0);
	}

}

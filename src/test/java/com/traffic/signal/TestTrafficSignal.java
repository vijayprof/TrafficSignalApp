package com.traffic.signal;

import static org.junit.Assert.*;

import org.junit.Test;

import com.traffic.signal.constants.TrafficSignal;

public class TestTrafficSignal {


	@Test
	public void testTrafficSignalEnums() {
		
		assertEquals("SNELL_ROAD_GREEN", TrafficSignal.SNELL_ROAD_GREEN.name());
		assertEquals("SNELL_ROAD_RED", TrafficSignal.SNELL_ROAD_RED.name());
		assertEquals("WEAVER_ROAD_GREEN", TrafficSignal.WEAVER_ROAD_GREEN.name());
		assertEquals("WEAVER_ROAD_RED", TrafficSignal.WEAVER_ROAD_RED.name());
		assertEquals("Snell Road Green Signal", TrafficSignal.SNELL_ROAD_GREEN.getSignalDesc());
		assertEquals("Snell Road Red Signal", TrafficSignal.SNELL_ROAD_RED.getSignalDesc());
		assertEquals("Weaver road green Signal", TrafficSignal.WEAVER_ROAD_GREEN.getSignalDesc());
		assertEquals("Weaver road Red Signal", TrafficSignal.WEAVER_ROAD_RED.getSignalDesc());
	}
}

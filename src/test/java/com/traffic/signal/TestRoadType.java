/**
 * 
 */
package com.traffic.signal;

import static org.junit.Assert.*;

import org.junit.Test;

import com.traffic.signal.constants.RoadType;

/**
 * @author VJ
 *
 */
public class TestRoadType {

	@Test
	public void testRoadTypenEnums() {
		assertEquals("SNELL_ROAD", RoadType.SNELL_ROAD.name());
		assertEquals("WEAVER_ROAD", RoadType.WEAVER_ROAD.name());
		assertEquals("Snell road", RoadType.SNELL_ROAD.getRoadDesc());
		assertEquals("Weaver road", RoadType.WEAVER_ROAD.getRoadDesc());
	}

}

/**
 * 
 */
package com.traffic.signal;

import static org.junit.Assert.*;

import org.junit.Test;

import com.traffic.signal.constants.RoadDirection;

/**
 * @author VJ
 *
 */
public class TestRoadDirection {

	@Test
	public void testRoadDirectionEnums() {
		assertEquals("NORTH", RoadDirection.NORTH.name());
		assertEquals("SOUTH", RoadDirection.SOUTH.name());
		assertEquals("EAST", RoadDirection.EAST.name());
		assertEquals("WEST", RoadDirection.WEST.name());
		assertEquals("Snell North Road", RoadDirection.NORTH.getDirectionDesc());
		assertEquals("Snell South road", RoadDirection.SOUTH.getDirectionDesc());
		assertEquals("Weaver East Road", RoadDirection.EAST.getDirectionDesc());
		assertEquals("Weaver West Road", RoadDirection.WEST.getDirectionDesc());
	}

}

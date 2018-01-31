/**
 * 
 */
package com.traffic.signal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.traffic.signal.constants.RoadType;
import com.traffic.signal.constants.TrafficSignal;
import com.traffic.signal.utils.SharedCache;


/**
 * @author VJ
 *
 */
public class TestSharedCache {	

	@Test
	public void testIsSnellRoadGreen() {
		
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_GREEN);
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_RED);		
		Assert.assertTrue(SharedCache.isSnellRoadGreen());
		
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_RED);
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_GREEN);
		Assert.assertFalse(SharedCache.isSnellRoadGreen());	
	}
	
	@Test
	public void testIsWeaverRoadGreen() {
	
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_RED);
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_GREEN);
		Assert.assertTrue(SharedCache.isWeaverRoadGreen());
		
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_GREEN);
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_RED);
		Assert.assertFalse(SharedCache.isWeaverRoadGreen());
	}

	@Test
	public void testGetterSetters () {
	
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_RED);
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_GREEN);
		Assert.assertTrue(SharedCache.isWeaverRoadGreen());
		
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_GREEN);
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_RED);
		Assert.assertFalse(SharedCache.isWeaverRoadGreen());
	}
	@Test
	public void testGetRoadMap () {

		Assert.assertNotNull(SharedCache.getRoadMap());
	}
	
	@Test
	public void testGetRoadSingleMap () {

		Assert.assertNotNull(SharedCache.getRoadSignalMap());
	}
	
	@Test
	public void testSetSnellRoadGreen () {
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_GREEN);
		SharedCache.setSnellRoadGreen(true);
		Assert.assertTrue(SharedCache.isSnellRoadGreen());
		
		SharedCache.getRoadSignalMap().put(RoadType.SNELL_ROAD, TrafficSignal.SNELL_ROAD_RED);
		SharedCache.setSnellRoadGreen(false);
		Assert.assertFalse(SharedCache.isSnellRoadGreen());
	}
	
	@Test
	public void testSetWeaverlRoadGreen () {
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_GREEN);
		SharedCache.setSnellRoadGreen(true);
		SharedCache.setWeaverRoadGreen(true);
		Assert.assertTrue(SharedCache.isWeaverRoadGreen());
		
		SharedCache.getRoadSignalMap().put(RoadType.WEAVER_ROAD, TrafficSignal.WEAVER_ROAD_RED);
		SharedCache.setWeaverRoadGreen(false);
		Assert.assertFalse(SharedCache.isWeaverRoadGreen());
	}
	
	@Test
	public void testPrivateConstructor () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Constructor<SharedCache> cons = SharedCache.class.getDeclaredConstructor();
		cons.setAccessible(true);
		SharedCache objCreatedByCons = cons.newInstance();
		Assert.assertNotNull(objCreatedByCons);
	}

}

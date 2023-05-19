import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.psnbtech.*;

public class TestClock {

	private Clock testClock;
//	int testElapsedCycles = 0;
//	float testCyclesPerSecond = 0.0f;
	
	@Before
	public void setUp() throws Exception {
		testClock = new Clock(1.0f);
		//testClock.update();
	}

	@After
	public void tearDown() throws Exception {
		testClock = null;
	}

	@Test
	public void testClock() {
		testSetCyclesPerSecond();
		testReset();
	}
	
	/*
	 * @brief Used to do test the constructor as shown in the Clock.java
	 * 
	 * @note: Achieve the following by checking negative cycle values
	 * @note: Achieve the following by checking zero cycle value
	 * @note: Achieve the following by checking positive cycle values
	 */
	@Test
	public void testSetCyclesPerSecond() {
		float testCyclesPerSecond = -1.0f;
		float testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		testClock.setCyclesPerSecond(testCyclesPerSecond);
		assertEquals(testMillisPerCycle, testClock.millisPerCycle, 0.0002f);

		testCyclesPerSecond = 0.0f;
		testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		testClock.setCyclesPerSecond(testCyclesPerSecond);
		assertEquals(testMillisPerCycle, testClock.millisPerCycle, 0.0002f);
		
		testCyclesPerSecond = 1.0f;
		testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		testClock.setCyclesPerSecond(testCyclesPerSecond);
		assertEquals(testMillisPerCycle, testClock.millisPerCycle, 0.0002f);
	}
	
	@Test
	public void testUpdate() {
		/*
		 * initialization for timer
		 */
		long testCurrUpdate = System.nanoTime() / 1000000L;
		testClock.update();
		long testLastUpdate = System.nanoTime() / 1000000L;
		
		float testCyclesPerSecond = 0.0f;
		float testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		int testElapsedCycles = 0;
		
		float testExcessCycles = 0.0f;
		float testDelta = (float)(testLastUpdate - testCurrUpdate) + testExcessCycles;
		float delta2 = (float)(Clock.getCurrentTime() - testClock.lastUpdate) + testClock.excessCycles;
		assertEquals(testDelta, delta2, 0.0002f);
		
		if(!testClock.isPaused) {
			testElapsedCycles += (int) Math.floor(testDelta / testMillisPerCycle);
			testExcessCycles = testDelta % testMillisPerCycle;
			
			testClock.elapsedCycles += (int) Math.floor(delta2 / testClock.millisPerCycle);
			testClock.excessCycles += delta2 % testClock.millisPerCycle;
			
			assertEquals(testElapsedCycles, testClock.elapsedCycles);
			assertEquals(testExcessCycles, testClock.excessCycles, 0.0002f);
		}
		
		testLastUpdate = testCurrUpdate;
		assertEquals(testLastUpdate, testClock.lastUpdate);
		
	}
	
	@Test
	public void testReset() {
		long start = System.nanoTime() / 1000000L;
		testClock.reset();
		long end = System.nanoTime() / 1000000L;
		
		assertEquals(0, testClock.elapsedCycles);
		assertEquals(0f, testClock.excessCycles, 0.0002f);
//		assertTrue(start <= testClock.lastUpdate && end >= testClock.lastUpdate);
		assertEquals(false, testClock.isPaused);
	}
	
	@Test
	public void testSetPaused() {
		boolean paused = true;
		testClock.setPaused(paused);
		assertEquals(paused, testClock.isPaused());
	}
	
	@Test
	public void testIsPaused() {
		testClock.isPaused = false;
		assertFalse(testClock.isPaused());
		
		testClock.isPaused = true;
		assertTrue(testClock.isPaused());
	}
	
	@Test
	public void testPeekElapsedCycle() {
		boolean greaterThanZero = false;
		assertEquals(greaterThanZero, testClock.peekElapsedCycle());
		
		testClock.elapsedCycles = 5;
		assertTrue(testClock.peekElapsedCycle());

		testClock.elapsedCycles = 1;
		assertTrue(testClock.peekElapsedCycle());
		
		testClock.elapsedCycles = 0;
		assertFalse(testClock.peekElapsedCycle());	
		
		testClock.elapsedCycles = -1;
		assertFalse(testClock.peekElapsedCycle());
	
	}
	
	@Test
	public void testHasElapsedCycle() {
		testClock.hasElapsedCycle();
		int testElapsedCycles = 0;
		long start = System.nanoTime() / 1000000L;
		//long start = testClock.getCurrentTime();
		float testExcessCycles = 0.0f;
//		long end = testClock.getCurrentTime();
		long end = System.nanoTime() / 1000000L;
		float testDelta = (float)(end - start) + testExcessCycles;
//	
		float testCyclesPerSecond = 0.0f;
		float testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
//
		testElapsedCycles = (int)Math.floor(testDelta / testMillisPerCycle);
		
//		boolean greaterThanZero = true;
		boolean isZero = false;
		
//		if(testElapsedCycles > 0) {
//			testElapsedCycles--;
//			assertEquals(testElapsedCycles, testClock.elapsedCycles - 1);
//			assertEquals(greaterThanZero, testClock.hasElapsedCycle());
//		}
//		else {
			assertEquals(isZero, testClock.hasElapsedCycle());
//		}
	}
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}

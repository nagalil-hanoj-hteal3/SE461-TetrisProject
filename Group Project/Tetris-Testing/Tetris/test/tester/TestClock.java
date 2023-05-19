import static org.junit.Assert.*;

//import java.time.Clock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.psnbtech.*;

public class TestClock {

	private Clock tc;
	
	@Before
	public void setUp() throws Exception {
		tc = new Clock((float)0.0);	
	}

	@After
	public void tearDown() throws Exception {
		tc = null;
	}

	@Test
	public void testClock() {
		testSetCyclesPerSecond();
		testReset();
	}


	@Test
	public void testSetCyclesPerSecond() {

		float testCyclesPerSecond = -15.0f; //negative test
		float testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		tc.setCyclesPerSecond(testCyclesPerSecond);
		assertEquals(testMillisPerCycle, tc.millisPerCycle, 0.0002f);
		
		testCyclesPerSecond = 0.0f; //zero test
		testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		tc.setCyclesPerSecond(testCyclesPerSecond);
		assertEquals(testMillisPerCycle, tc.millisPerCycle, 0.0002f);
		
		testCyclesPerSecond = 15.0f; //positive test
		testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		tc.setCyclesPerSecond(testCyclesPerSecond);
		assertEquals(testMillisPerCycle, tc.millisPerCycle, 0.0002f);
	}

	@Test
	public void testReset() {
		long startTime = System.nanoTime() / 1000000L;
		tc.reset();
		long endTime = System.nanoTime() / 1000000L;
		assertEquals(0, tc.elapsedCycles);
		assertEquals(0f, tc.excessCycles, 0.0002f);
		assertTrue(startTime <= tc.lastUpdate && endTime >= tc.lastUpdate);
		assertEquals(false, tc.isPaused);	
	}

	@Test
	public void testUpdate() {
		long testStartUpdate = System.nanoTime() / 1000000L;
		tc.update();
		long testEndUpdate = System.nanoTime() / 1000000L;
		
		float testCyclesPerSecond = 0.0f; //zero test
		float testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		int testElapsedCycles = 0;
		
		float testExcessCycles = 0.0f;
		float testDelta = (float)(testEndUpdate - testStartUpdate) + testExcessCycles;
		float delta = (float)(Clock.getCurrentTime() - tc.lastUpdate) + tc.excessCycles;
		assertEquals(testDelta, delta, 0.0002f);
		
		if(!tc.isPaused) {
			testElapsedCycles += (int)Math.floor(testDelta / testMillisPerCycle);
			testExcessCycles = testDelta % testMillisPerCycle;
			tc.elapsedCycles += (int)Math.floor(delta / tc.millisPerCycle);
			tc.excessCycles = delta % testMillisPerCycle; 
			assertEquals(testElapsedCycles, tc.elapsedCycles);
			assertEquals(testExcessCycles, tc.excessCycles, 0.0002f);
		}
		testEndUpdate = testStartUpdate;
		assertEquals(testEndUpdate, tc.lastUpdate);
	}

	@Test
	public void testSetPaused() {

		boolean paused = true;
		tc.setPaused(paused);
		assertEquals(paused, tc.isPaused());
	}

	@Test
	public void testIsPaused() {
		tc.isPaused = false;
		assertFalse(tc.isPaused());
		

		tc.isPaused = true;
		assertTrue(tc.isPaused());
	}

	@Test
	public void testHasElapsedCycle() {

		tc.hasElapsedCycle();
		int testElapsedCycles = 0;
		long testStartUpdate = System.nanoTime() / 1000000L;
		float testExcessCycles = 0.0f;
		long testEndUpdate = System.nanoTime() / 1000000L;
		float testDelta = (float)(testEndUpdate - testStartUpdate) + testExcessCycles;
		
		float testCyclesPerSecond = 0.0f; //zero test
		float testMillisPerCycle = (1.0f / testCyclesPerSecond) * 1000;
		
		testElapsedCycles = (int)Math.floor(testDelta / testMillisPerCycle);
		
		boolean testGreaterThanZero = true;
		boolean testZero = false;
		
		if(testElapsedCycles > 0){
			testElapsedCycles--;
			assertEquals(testElapsedCycles, tc.elapsedCycles - 1);
			assertEquals(testGreaterThanZero, tc.hasElapsedCycle());
		}
		else {
			assertEquals(testZero, tc.hasElapsedCycle());
		}
	}

	
	@Test
	public void testPeekElapsedCycle() {

		boolean testGreaterThanZero = false;
		assertEquals(testGreaterThanZero, tc.peekElapsedCycle());

		tc.elapsedCycles = 5;
		assertTrue(tc.peekElapsedCycle());

		tc.elapsedCycles = 1;
		assertTrue(tc.peekElapsedCycle());
		
		tc.elapsedCycles = 0;
		assertFalse(tc.peekElapsedCycle());
		
		tc.elapsedCycles = -1;
		assertFalse(tc.peekElapsedCycle());
		
	}

}

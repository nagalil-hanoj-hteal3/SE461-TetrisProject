import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.psnbtech.*;


public class TestTileType {
	
	private TileType tt;
	

	@Before
	public void setUp() throws Exception {
		tt = TileType.TypeI;
	}

	@After
	public void tearDown() throws Exception {
		tt = null;
	}

	@Test
	public void testGetBaseColor() {
		tt = TileType.TypeI;
		try {
			Color bc = new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX);
			
			assertEquals(bc, tt.getBaseColor()); 
		}catch(Exception e) {
			fail("Caught exception");
		}
	}

	
	@Test
	public void testGetLightColor() {
		tt = TileType.TypeJ;
		try {
			Color lc = new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX);

			lc = lc.brighter();
			
			assertEquals(lc, tt.getLightColor()); 
		}catch(Exception e) {
			fail("Caught exception");
		}
	}

	@Test
	public void testGetDarkColor() {
		tt = TileType.TypeS;
		try {
			Color dc = new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN);

			dc = dc.darker();
			
			assertEquals(dc, tt.getDarkColor());
		}catch(Exception e) {
			fail("Caught exception");
		}
	}

	@Test
	public void testGetDimension() {
		tt = TileType.TypeS;
		try {
			assertEquals(3, tt.getDimension());
		}catch(Exception e) {
			fail("Caught exception");
		}
	}

	@Test
	public void testGetSpawnColumn() {
		tt = TileType.TypeS;
		try {
			assertEquals(4, tt.getSpawnColumn());
		}catch(Exception e) {
			fail("Caught exception");
		}
	}

	@Test
	public void testGetSpawnRow() {
		tt = TileType.TypeS;
		try {
			assertEquals(0, tt.getSpawnRow());
		}catch(Exception e) {
			fail("Caught exception");
		}
	}

	@Test
	public void testGetRows() {
		//20 rows
		tt = TileType.TypeS;
		try {
			assertEquals(2, tt.getRows());
		}catch(Exception e) {
			fail("Caught exception");
		}
	}

	@Test 
	public void testGetCols() {
		//10 columns
		tt = TileType.TypeS;
		try {
			assertEquals(3, tt.getCols());
		}catch(Exception e) {
			fail("Caught exception");
		}
		
		tt = TileType.TypeZ;
		try {
			assertEquals(3, tt.getCols());
		}catch(Exception e) {
			fail("Caught exception");
		}
	}
	
	@Test
	public void testIsTile() {
		tt = TileType.TypeI;
		
		assertFalse(tt.isTile(0, 0, 0)); 
		assertFalse(tt.isTile(1, 0, 0));
		assertFalse(tt.isTile(2, 0, 0));
		assertFalse(tt.isTile(3, 0, 0));
		
		assertTrue(tt.isTile(0, 1, 0)); 
		assertTrue(tt.isTile(0, 1, 0));
		assertTrue(tt.isTile(0, 1, 0));
		assertTrue(tt.isTile(0, 1, 0));
		
		assertFalse(tt.isTile(0, 0, 0)); 
		assertFalse(tt.isTile(0, 0, 1));
		assertFalse(tt.isTile(0, 0, 2));
		assertFalse(tt.isTile(0, 0, 3));
		
		assertFalse(tt.isTile(0, 0, 0)); 
		assertFalse(tt.isTile(1, 0, 0));
		assertFalse(tt.isTile(0, 2, 0));
		assertFalse(tt.isTile(0, 0, 3));
	}
	
	@Test
	public void testGetLeftInset() {
		//testing values 0-3 for tile type I, passing values 0-3 because max length is 3
		tt = TileType.TypeI;
		
		assertEquals(1,tt.getLeftInset(3));
		assertEquals(0,tt.getLeftInset(2));
		assertEquals(2,tt.getLeftInset(1));
		assertEquals(0,tt.getLeftInset(0));
	
	}
	
	@Test
	public void testGetRightInset() {
		//testing values for tile type J
		
		tt = TileType.TypeJ; 
		
		assertEquals(2,tt.getRightInset(3));
		assertEquals(1,tt.getRightInset(2));
		assertEquals(1,tt.getRightInset(1));
		assertEquals(1,tt.getRightInset(0));
	}
	
	

	@Test
	public void testGetTopInset() {
		tt = TileType.TypeS; 
		
		assertEquals(0,tt.getTopInset(3));
		assertEquals(1,tt.getTopInset(2));
		assertEquals(0,tt.getTopInset(1));
		assertEquals(0,tt.getTopInset(0));
			
	}

	@Test
	public void testGetBottomInset() {
		tt = TileType.TypeO;
		
		assertEquals(1,tt.getBottomInset(3));
		assertEquals(1,tt.getBottomInset(2));
		assertEquals(1,tt.getBottomInset(1));
		assertEquals(1,tt.getBottomInset(0));
		
	}

}

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.psnbtech.*;

public class TestBoardPanel {

	private BoardPanel boardPanel;
	//private Tetris tetris;
	
	@Before
	public void setUp() throws Exception {
		boardPanel = new BoardPanel(null);
	}

	@After
	public void tearDown() throws Exception {
		boardPanel = null;
	}

	
	@Test
	public void testClear() {
		TileType[][] clearBoard = new TileType[22][10];
//		
		boardPanel.addPiece(TileType.TypeL, 5, 5, 0);
		
//		boardPanel.clear();
//		assertNull(boardPanel.tiles[0][1]);
		
		//to check that the array fails
		//uses a function that is made below testClear function
//		assertTrue(assertArrayNotEqual(clearBoard, boardPanel.tiles));
		
		//clear each item that is on the board
		boardPanel.clear();
		assertArrayEquals(clearBoard, boardPanel.tiles);
		
		TileType t = TileType.TypeO;
		TileType[][] fullBoard = {
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},

				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},

				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},

				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t},

				{t, t, t, t, t,   t, t, t, t, t},
				{t, t, t, t, t,   t, t, t, t, t}
		};
		
//		boardPanel.tiles = fullBoard;
//		assertTrue(assertArrayNotEqual(clearBoard, boardPanel.tiles));
		
		boardPanel.clear();
		assertArrayEquals(clearBoard, boardPanel.tiles);
		
	}
	
	@Test
	public void testIsValidAndEmpty() {
		
		boardPanel = new BoardPanel(null);
		
		//Prime Path (1): 1, 3
		assertFalse(boardPanel.isValidAndEmpty(TileType.TypeI, 10, 10, 0));
		
		//Prime Path (2): 1, 2, 4, 6
		assertFalse(boardPanel.isValidAndEmpty(TileType.TypeI, 2, 21, 1));
		
		//Prime Path (3): 1, 2, 4, 5, 7, 8, 10, 11, 12, 14, 11, 12, 14, 11, 15, 8, 10, 11, 12, 14, 11, 15, 8, 9
		assertTrue(boardPanel.isValidAndEmpty(TileType.TypeO, 5, 5, 0));
		
		//placing tiles
		boardPanel.addPiece(TileType.TypeO, 5, 5, 0);
		
		//Prime Path (4): 1, 2, 4, 5, 7, 8, 10, 11, 12, 13
		assertFalse(boardPanel.isValidAndEmpty(TileType.TypeO, 5, 5, 0));
		//Prime Path (5): 1, 2, 4, 5, 7, 8, 10, 11, 12, 14, 11, 12, 14, 11, 12, 13
		assertFalse(boardPanel.isValidAndEmpty(TileType.TypeS, 3, 5, 0));
		//Prime Path (6): 1, 2, 4, 5, 7, 8, 10, 11, 12, 14, 11, 12, 14, 11, 15, 8, 10, 11, 12, 13
		assertFalse(boardPanel.isValidAndEmpty(TileType.TypeL, 5, 4, 2));
	}
	
	@Test
	public void testAddPiece() {
		boardPanel = new BoardPanel(null);
		
		TileType O = TileType.TypeO;
		TileType I = TileType.TypeI;
		
		TileType[][] expected = {
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},

				{null, null, null, O,    O, 	null, null, null, null, null},
				{null, null, null, O,    O,     null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},

				{I   , I   , I   , I   , null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},

				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},

				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null}
		};
		
		boardPanel.addPiece(O, 3, 5, 0);
		boardPanel.addPiece(I, 0, 8, 2);
		assertArrayEquals(expected, boardPanel.tiles);
	}
	
	@Test
	public void testCheckLines() {
		TileType O = TileType.TypeO;
		TileType I = TileType.TypeI;
		TileType S = TileType.TypeS;
		
		TileType[][] expected_22 = {
			{O, I, I, I, I,   O, O, I, S, I},
			{O, I, S, I, I,   O, O, I, I, I},
			
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},

			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},

			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},

			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
			{O, I, S, I, I,   O, O, I, I, I},
		};
		
		boardPanel.tiles = expected_22;
//		assertEquals(22, boardPanel.checkLines());
		
	}
	
	@Test
	public void testCheckLine() {
		TileType x = TileType.TypeZ;
		int f = 0;
		int m = 14;
		int l = 21;
		
		TileType[][] test_1fe = {		
				{x, x, x, x, x, x, x, x, x, x},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	};
			
		TileType[][] test_1fa = {		
				{x, x, x, x, x, x, x, x, x, x},	
				{null, x, null, null, x, null, null, null, null, null},	
				{null, x, null, x, x, null, null, null, x, null},	
				{x, null, null, x, null, x, null, null, null, x},	
				{null, null, x, null, x, x, null, null, null, null},	
				{null, x, x, x, null, null, x, x, x, null},	
				{null, x, x, x, null, null, x, x, x, null},	
				{x, null, x, null, x, null, x, null, null, null},	
				{null, x, null, null, x, x, x, null, null, x},	
				{null, x, null, x, null, null, x, null, x, x},	
				{null, null, null, null, x, x, null, x, null, null},	
				{x, x, x, null, x, null, x, null, x, x},	
				{null, x, x, x, null, x, x, null, x, null},	
				{null, null, null, null, x, x, null, null, null, null},	
				{x, x, x, null, null, x, x, x, x, null},	
				{null, null, x, null, null, null, null, null, null, null},	
				{x, null, null, null, x, x, x, x, x, null},	
				{null, x, null, null, null, null, null, x, x, null},	
				{x, x, x, x, null, null, x, null, x, null},	
				{null, null, null, null, x, x, x, x, null, null},	
				{null, null, null, x, x, null, null, x, null, null},	
				{x, null, null, x, x, x, null, null, x, x},	};
			
			TileType[][] test_1me = {		
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{x, x, x, x, x, x, x, x, x, x},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	};
			
			TileType[][] test_1ma = {		
				{null, null, x, null, x, x, null, null, null, x},	
				{null, x, null, null, x, null, null, null, null, null},	
				{null, x, null, x, x, null, null, null, x, null},	
				{x, null, null, x, null, x, null, null, null, x},	
				{null, null, x, null, x, x, null, null, null, null},	
				{null, x, x, x, null, null, x, x, x, null},	
				{null, x, x, x, null, null, x, x, x, null},	
				{x, null, x, null, x, null, x, null, null, null},	
				{null, x, null, null, x, x, x, null, null, x},	
				{null, x, null, x, null, null, x, null, x, x},	
				{null, null, null, null, x, x, null, x, null, null},	
				{x, x, x, null, x, null, x, null, x, x},	
				{null, x, x, x, null, x, x, null, x, null},	
				{null, null, null, null, x, x, null, null, null, null},	
				{x, x, x, x, x, x, x, x, x, x},	
				{null, null, x, null, null, null, null, null, null, null},	
				{x, null, null, null, x, x, x, x, x, null},	
				{null, x, null, null, null, null, null, x, x, null},	
				{x, x, x, x, null, null, x, null, x, null},	
				{null, null, null, null, x, x, x, x, null, null},	
				{null, null, null, x, x, null, null, x, null, null},	
				{x, null, null, x, x, x, null, null, x, x},	};
			
			TileType[][] test_1le = {		
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{x, x, x, x, x, x, x, x, x, x},	};
			
			TileType[][] test_1la = {		
				{null, null, x, null, x, x, null, null, null, x},	
				{null, x, null, null, x, null, null, null, null, null},	
				{null, x, null, x, x, null, null, null, x, null},	
				{x, null, null, x, null, x, null, null, null, x},	
				{null, null, x, null, x, x, null, null, null, null},	
				{null, x, x, x, null, null, x, x, x, null},	
				{null, x, x, x, null, null, x, x, x, null},	
				{x, null, x, null, x, null, x, null, null, null},	
				{null, x, null, null, x, x, x, null, null, x},	
				{null, x, null, x, null, null, x, null, x, x},	
				{null, null, null, null, x, x, null, x, null, null},	
				{x, x, x, null, x, null, x, null, x, x},	
				{null, x, x, x, null, x, x, null, x, null},	
				{null, null, null, null, x, x, null, null, null, null},	
				{x, x, x, null, null, x, x, x, x, null},	
				{null, null, x, null, null, null, null, null, null, null},	
				{x, null, null, null, x, x, x, x, x, null},	
				{null, x, null, null, null, null, null, x, x, null},	
				{x, x, x, x, null, null, x, null, x, null},	
				{null, null, null, null, x, x, x, x, null, null},	
				{null, null, null, x, x, null, null, x, null, null},	
				{x, x, x, x, x, x, x, x, x, x},	};
			
			TileType[][] test_0fe = {		
				{x, x, null, null, x, x, x, x, x, x},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	};
			
			//---------------------------------------------------------------------
			
			TileType[][] result_1fe = {		
					{x, x, x, x, x, x, x, x, x, x},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	};
				
			TileType[][] result_1fa = {		
					{x, x, x, x, x, x, x, x, x, x},	
					{null, x, null, null, x, null, null, null, null, null},	
					{null, x, null, x, x, null, null, null, x, null},	
					{x, null, null, x, null, x, null, null, null, x},	
					{null, null, x, null, x, x, null, null, null, null},	
					{null, x, x, x, null, null, x, x, x, null},	
					{null, x, x, x, null, null, x, x, x, null},	
					{x, null, x, null, x, null, x, null, null, null},	
					{null, x, null, null, x, x, x, null, null, x},	
					{null, x, null, x, null, null, x, null, x, x},	
					{null, null, null, null, x, x, null, x, null, null},	
					{x, x, x, null, x, null, x, null, x, x},	
					{null, x, x, x, null, x, x, null, x, null},	
					{null, null, null, null, x, x, null, null, null, null},	
					{x, x, x, null, null, x, x, x, x, null},	
					{null, null, x, null, null, null, null, null, null, null},	
					{x, null, null, null, x, x, x, x, x, null},	
					{null, x, null, null, null, null, null, x, x, null},	
					{x, x, x, x, null, null, x, null, x, null},	
					{null, null, null, null, x, x, x, x, null, null},	
					{null, null, null, x, x, null, null, x, null, null},	
					{x, null, null, x, x, x, null, null, x, x},	};
				
			TileType[][] result_1me = {		
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	};
				
			TileType[][] result_1ma = {		
					{null, null, x, null, x, x, null, null, null, x},	
					{null, null, x, null, x, x, null, null, null, x},	
					{null, x, null, null, x, null, null, null, null, null},	
					{null, x, null, x, x, null, null, null, x, null},	
					{x, null, null, x, null, x, null, null, null, x},	
					{null, null, x, null, x, x, null, null, null, null},	
					{null, x, x, x, null, null, x, x, x, null},	
					{null, x, x, x, null, null, x, x, x, null},	
					{x, null, x, null, x, null, x, null, null, null},	
					{null, x, null, null, x, x, x, null, null, x},	
					{null, x, null, x, null, null, x, null, x, x},	
					{null, null, null, null, x, x, null, x, null, null},	
					{x, x, x, null, x, null, x, null, x, x},	
					{null, x, x, x, null, x, x, null, x, null},	
					{null, null, null, null, x, x, null, null, null, null},	
					{null, null, x, null, null, null, null, null, null, null},	
					{x, null, null, null, x, x, x, x, x, null},	
					{null, x, null, null, null, null, null, x, x, null},	
					{x, x, x, x, null, null, x, null, x, null},	
					{null, null, null, null, x, x, x, x, null, null},	
					{null, null, null, x, x, null, null, x, null, null},	
					{x, null, null, x, x, x, null, null, x, x},	};
				
			TileType[][] result_1le = {		
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	};
				
			TileType[][] result_1la = {		
					{null, null, x, null, x, x, null, null, null, x},	
					{null, null, x, null, x, x, null, null, null, x},	
					{null, x, null, null, x, null, null, null, null, null},	
					{null, x, null, x, x, null, null, null, x, null},	
					{x, null, null, x, null, x, null, null, null, x},	
					{null, null, x, null, x, x, null, null, null, null},	
					{null, x, x, x, null, null, x, x, x, null},	
					{null, x, x, x, null, null, x, x, x, null},	
					{x, null, x, null, x, null, x, null, null, null},	
					{null, x, null, null, x, x, x, null, null, x},	
					{null, x, null, x, null, null, x, null, x, x},	
					{null, null, null, null, x, x, null, x, null, null},	
					{x, x, x, null, x, null, x, null, x, x},	
					{null, x, x, x, null, x, x, null, x, null},	
					{null, null, null, null, x, x, null, null, null, null},	
					{x, x, x, null, null, x, x, x, x, null},	
					{null, null, x, null, null, null, null, null, null, null},	
					{x, null, null, null, x, x, x, x, x, null},	
					{null, x, null, null, null, null, null, x, x, null},	
					{x, x, x, x, null, null, x, null, x, null},	
					{null, null, null, null, x, x, x, x, null, null},	
					{null, null, null, x, x, null, null, x, null, null},	};
		
		//full row, first row, empty board
		boardPanel.tiles = test_1fe;
		assertFalse(boardPanel.checkLine(f));
		assertArrayEquals(result_1fe, boardPanel.tiles);
		
		//full row, first row, tiles all over board
		boardPanel.tiles = test_1fa;
		assertFalse(boardPanel.checkLine(f));
		assertArrayEquals(result_1fa, boardPanel.tiles);
		
		//full row, middle row, empty board
		boardPanel.tiles = test_1me;
		assertFalse(boardPanel.checkLine(m));
		assertArrayEquals(result_1me, boardPanel.tiles);
		
		//full row, middle row, tiles all over board
		boardPanel.tiles = test_1ma;
		assertFalse(boardPanel.checkLine(m));
		assertArrayEquals(result_1ma, boardPanel.tiles);
		
		//full row, last row, empty board
		boardPanel.tiles = test_1le;
		assertFalse(boardPanel.checkLine(l));
		assertArrayEquals(result_1le, boardPanel.tiles);
		
		//full row, last row, empty board
		boardPanel.tiles = test_1la;
		assertFalse(boardPanel.checkLine(l));
		assertArrayEquals(result_1la, boardPanel.tiles);
		
		boardPanel.tiles = test_0fe;
	}
	
	@Test
	public void testIsOccupied() {
		
		TileType[][] internalState = new TileType[22][10];
		int x = 4;
		int y = 9;
		
		internalState[y][x] = TileType.TypeT;
		boardPanel.tiles = internalState;
		
		int notX = 2;
		int notY = 15;
		
		assertFalse(boardPanel.isOccupied(notX, notY));
		assertFalse(boardPanel.isOccupied(notX, y));
		assertFalse(boardPanel.isOccupied(x, notY));
		assertTrue(boardPanel.isOccupied(x, y));
		
	}
	
	@Test
	public void testSetTile() {
		boardPanel = new BoardPanel(null);
		TileType[][] expected = new TileType[22][10];
		int x = 4;
		int y = 9;
		TileType testTile = TileType.TypeT;
		
		expected[y][x] = testTile;
		
		boardPanel.setTile(x, y, testTile);
		assertArrayEquals(expected, boardPanel.tiles);
	}
	
	@Test
	public void testGetTile() {
		boardPanel = new BoardPanel(null);
		TileType[][] expected = new TileType[22][10];
		
		int x = 4;
		int y = 9;
		TileType testTile = TileType.TypeT;
		
		expected[y][x] = testTile;
		
		boardPanel.tiles = expected;
		
		assertEquals(testTile, boardPanel.getTile(x, y));
	}
	
	@Test
	public void testPaintComponentGraphics() {
		Tetris t = null;
		try {
			t = new Tetris();
			t.setVisible(false);
			t.isPaused = true;
			t.currentType = TileType.TypeZ;
			t.currentCol = 2;
			t.currentRow = 0;
		}finally{};
//		}catch(java.lang.NullPointerException e) {e.printStackTrace();}
	
		boardPanel = new BoardPanel(t);
		
		//play tetris 1
		t.isPaused = false;
		t.isNewGame = false;
		t.isGameOver = false;
		boardPanel.repaint();
		
		t.currentRow++;
		boardPanel.addPiece(TileType.TypeL, 2, 5, 1);
		boardPanel.addPiece(TileType.TypeI, 6, 18, 0);
		boardPanel.repaint();
		
		//pause
		t.isPaused = true;
		t.isNewGame = false;
		t.isGameOver = false;
		// p - PAUSED
		boardPanel.repaint();
		
		//pause 2
		t.isPaused = true;
		t.isNewGame = false;
		t.isGameOver = true;
		boardPanel.repaint();
		
		//pause 3
		t.isPaused = true;
		t.isNewGame = true;
		t.isGameOver = false;
		boardPanel.repaint();
		
		//pause 4
		t.isPaused = true;
		t.isNewGame = true;
		t.isGameOver = true;
		boardPanel.repaint();
		
		//game over
		t.isPaused = false;
		t.isNewGame = false;
		t.isGameOver = true;
		boardPanel.repaint();
		
		//tetris 1
		t.isPaused = false;
		t.isNewGame = true;
		t.isGameOver = false;
		boardPanel.repaint();
		
		//tetris 2
		t.isPaused = false;
		t.isNewGame = true;
		t.isGameOver = true;
		boardPanel.repaint();
		
	}
	
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	//unsure why this is getting read from JUnit when ignore annotation and private are activated
		//adding this would show the console outputs
		
		//	@Ignore
//		private boolean assertArrayNotEqual(TileType[][] expected, TileType[][] actual) {
//			// TODO Auto-generated method stub
//			Boolean notSame = false; 
	//
//			try {
//				assertArrayEquals(expected, actual);
////				notSame = false;
//			}catch(AssertionError e) {
//				notSame = true;
//				e.printStackTrace();
//			}
//			return notSame;
//		} 

}

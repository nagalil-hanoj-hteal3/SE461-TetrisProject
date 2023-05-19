import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.psnbtech.*;

public class TestBoardPanel {

	private BoardPanel bp;
	private Scanner testerInput;
	@Before
	public void setUp() throws Exception {
		testerInput = AllTests.testerInput;
		bp = new BoardPanel(null);
	}

	@After
	public void tearDown() throws Exception {
		bp = null;
	}

	@Test
	public void testBoardPanel() {
		Dimension expected = new Dimension(250, 490);
		
		Tetris t = null;
		try{
			t = new Tetris();
			t.isGameOver = true;
			t.setVisible(false);
		}catch(java.lang.NullPointerException e) {}
		
		bp = new BoardPanel(t);
		JFrame frame = new JFrame();
		frame.add(bp);
		frame.setFocusable(true);
		frame.setAlwaysOnTop(true);
		frame.setFocusableWindowState(true);
		frame.pack();
		frame.setVisible(true);
		
		assertEquals(expected, bp.getSize());
		
		Color expectedColor = Color.BLACK;
		assertEquals(expectedColor, bp.getBackground());
		
		assertEquals(t, bp.tetris);
		
		frame.dispose();

		// t was used in the construction of the class and is to be save as a class viable
		assertSame(t, bp.tetris);	
	}

	@Test
	public void testClear() {
		//Prime Path coverage is not feasible
		TileType[][] emptyTileBoard = new TileType[22][10];
		bp.addPiece(TileType.TypeL, 5, 5, 0);
		
		// assert that it is true that assertArrayEquals(expected, actual) fails
		assertTrue(assertArrayNotEqual(emptyTileBoard, bp.tiles));
		
		bp.clear();
		assertArrayEquals(emptyTileBoard, bp.tiles); //test 1 - cleared a single item from the board
		
		TileType t = TileType.TypeO;
		TileType[][] fullBoardTileBoard = {
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
		bp.tiles = fullBoardTileBoard;
		// assert that it is true that assertArrayEquals(expected, actual) fails
		assertTrue(assertArrayNotEqual(emptyTileBoard, bp.tiles));
		
		bp.clear();
		assertArrayEquals(emptyTileBoard, bp.tiles);	// test 2 - cleared all items from the board
		
	}
	
	@Ignore
	private Boolean assertArrayNotEqual(TileType[][] expected, TileType[][] actual) {
		Boolean isNotSame = null;
		try {
			assertArrayEquals(expected, actual);
			isNotSame = false;
		}catch(AssertionError e) {
			isNotSame = true;
		}
		return isNotSame;
	}

	@Test
	public void testIsValidAndEmpty() {
		
		bp = new BoardPanel(null);
		
		// board is empty
		
		//Prime Path: 1, 11
		assertFalse(bp.isValidAndEmpty(TileType.TypeI, 10, 10, 0));
		//Prime Path: 1, 2, 12
		assertFalse(bp.isValidAndEmpty(TileType.TypeI, 2, 21, 1));
		//Prime Path: 1, 2, 3, 4, 5, 6, 7, 8,	6, 7, 8, 6, 9, 4, 5, 6, 7, 8, 6, 7, 8, 6, 9, 4, 10
		assertTrue(bp.isValidAndEmpty(TileType.TypeO, 5, 5, 0));
		
		// tile places at [5][5], [5][6], [6][5], [6][6]
		bp.addPiece(TileType.TypeO, 5, 5, 0);
		
		//Prime Path: 1, 2, 3, 4, 5, 6, 7, 17
		assertFalse(bp.isValidAndEmpty(TileType.TypeO, 5, 5, 0));
		//Prime Path: 1, 2, 3, 4, 5, 6, 7, 8, 6, 7, 8, 6, 7, 17
		assertFalse(bp.isValidAndEmpty(TileType.TypeS, 3, 5, 0));
		//Prime Path: 1, 2, 3, 4, 5, 	6, 7, 8	6, 7, 8	6, 7, 8, 6. 9, 4, 5, 6, 7, 17
		assertFalse(bp.isValidAndEmpty(TileType.TypeL, 5, 4, 2));
	}
	

	@Test
	public void testAddPiece() {
		bp = new BoardPanel(null);
		
		TileType o = TileType.TypeO;
		TileType i = TileType.TypeI;
		
		TileType[][] expected = {
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},

				{null, null, null, o,    o, 	null, null, null, null, null},
				{null, null, null, o,    o,     null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},

				{i   , i   , i   , i   , null,  null, null, null, null, null},
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
		
		// path covered 1, 2, 3, 4, 6, 7, 8, 4, 6, 7, 8, 4, 5, 2, 3, 4, 6, 7, 8, 4, 6, 7, 8, 4, 5, 2, 9
		bp.addPiece(o, 3, 5, 0);
		//path covered 1, 2, 3, 4, 6, 8, 4, 6, 8, 4, 6, 7, 8, 4, 6, 8, 4, 5, 2, 3, 4, 6, 8, 4, 6, 8, 4, 6, 7, 8, 4, 6, 8, 4, 5, 2, 3, 4, 6, 8, 4, 6, 8, 4, 6, 7, 8, 4, 6, 8, 4, 5, 2, 3, 4, 6, 8, 4, 6, 8, 4, 6, 7, 8, 4, 6, 8, 4, 5, 2, 9
		bp.addPiece(i, 0, 8, 2);
		assertArrayEquals(expected, bp.tiles);
		
	}

	@Test
	public void testCheckLines() {
		TileType o = TileType.TypeO;
		TileType i = TileType.TypeI;
		TileType s = TileType.TypeS;
		
		TileType[][] expected_4 = {
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{o, i, i, i, i, o, o, i, i, i},
				{o, i, i, i, i, o, o, i, i, i},
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
				{   i,    s,    i,    s,    i,     i,    i,    i, null, null},

				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null,    i,    i,  null, null, null, null, null},

				{o, i, i, i, i, o, o, i, s, i},
				{o, i, s, i, i, o, o, i, i, i},
		};

		TileType[][] expected_1 = {
				{   o,    i,    i,    i,    i,     o,    o,    i,    i,    i},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},
				
				{null, null, null, null, null,  null, null, null, null, null},
				{null, null, null, null, null,  null,    i,    i, null, null},
				{null, null, null, null,    i,     i, null, null, null, null},
				{null, null, null, null,    i,     i, null, null, null, null},
				{null, null, null, null, null,  null, null, null, null, null},

				{null, null, null,    i,    i,  null, null, null, null, null},
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
				{null, null, null, null, null,  null, null, null, null, null},
		};
		
		TileType[][] expected_22 = {	{o, i, i, i, i,   o, o, i, s, i},
										{o, i, s, i, i,   o, o, i, i, i},
										
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},

										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},

										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},

										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
										{o, i, s, i, i,   o, o, i, i, i},
		}; // cheater test case to up code coverage
		
		bp.tiles = expected_22;
		assertEquals(22, bp.checkLines());
		
		bp = new BoardPanel(null); // new empty board should return 0
		assertEquals(0, bp.checkLines());
		
		bp.tiles = expected_4;
		assertEquals(4, bp.checkLines());
		
		bp.tiles = expected_1;		
		assertEquals(1, bp.checkLines());
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
			TileType[][] test_0fa = {		
				{x, x, null, null, x, x, x, x, x, x},	
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
			TileType[][] test_0me = {		
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
				{x, x, null, null, x, x, x, x, x, x},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	
				{null, null, null, null, null, null, null, null, null, null},	};
			TileType[][] test_0ma = {		
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
				{x, x, null, null, x, x, x, x, x, x},	
				{null, null, x, null, null, null, null, null, null, null},	
				{x, null, null, null, x, x, x, x, x, null},	
				{null, x, null, null, null, null, null, x, x, null},	
				{x, x, x, x, null, null, x, null, x, null},	
				{null, null, null, null, x, x, x, x, null, null},	
				{null, null, null, x, x, null, null, x, null, null},	
				{x, null, null, x, x, x, null, null, x, x},	};
			TileType[][] test_0le = {		
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
				{x, x, null, null, x, x, x, x, x, x},	};
			TileType[][] test_0la = {		
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
				{x, x, null, null, x, x, x, x, x, x},	};
			
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
				TileType[][] result_0fe = {		
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
				TileType[][] result_0fa = {		
					{x, x, null, null, x, x, x, x, x, x},	
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
				TileType[][] result_0me = {		
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
					{x, x, null, null, x, x, x, x, x, x},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	
					{null, null, null, null, null, null, null, null, null, null},	};
				TileType[][] result_0ma = {		
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
					{x, x, null, null, x, x, x, x, x, x},	
					{null, null, x, null, null, null, null, null, null, null},	
					{x, null, null, null, x, x, x, x, x, null},	
					{null, x, null, null, null, null, null, x, x, null},	
					{x, x, x, x, null, null, x, null, x, null},	
					{null, null, null, null, x, x, x, x, null, null},	
					{null, null, null, x, x, null, null, x, null, null},	
					{x, null, null, x, x, x, null, null, x, x},	};
				TileType[][] result_0le = {		
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
					{x, x, null, null, x, x, x, x, x, x},	};
				TileType[][] result_0la = {		
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
					{x, x, null, null, x, x, x, x, x, x},	};
					

				bp.tiles = test_1fe;
				assertTrue(bp.checkLine(f));
				assertArrayEquals(result_1fe, bp.tiles);
				
				bp.tiles = test_1fa;
				assertTrue(bp.checkLine(f));
				assertArrayEquals(result_1fa, bp.tiles);
				
				bp.tiles = test_1me;
				assertTrue(bp.checkLine(m));
				assertArrayEquals(result_1me, bp.tiles);
				
				bp.tiles = test_1ma;
				assertTrue(bp.checkLine(m));
				assertArrayEquals(result_1ma, bp.tiles);
				bp.tiles = test_1le;
				assertTrue(bp.checkLine(l));
				assertArrayEquals(result_1le, bp.tiles);
				
				bp.tiles = test_1la;
				assertTrue(bp.checkLine(l));
				assertArrayEquals(result_1la, bp.tiles);
				
				bp.tiles = test_0fe;
				assertFalse(bp.checkLine(f));
				assertArrayEquals(result_0fe, bp.tiles);
				bp.tiles = test_0fa;
				assertFalse(bp.checkLine(f));
				assertArrayEquals(result_0fa, bp.tiles);
				bp.tiles = test_0me;
				assertFalse(bp.checkLine(m));
				assertArrayEquals(result_0me, bp.tiles);
				bp.tiles = test_0ma;
				assertFalse(bp.checkLine(m));
				assertArrayEquals(result_0ma, bp.tiles);
				
				bp.tiles = test_0le;
				assertFalse(bp.checkLine(l));
				assertArrayEquals(result_0le, bp.tiles);
				
				bp.tiles = test_0la;
				assertFalse(bp.checkLine(l));
				assertArrayEquals(result_0la, bp.tiles);

	}
	
	@Test
	public void testIsOccupied() {
		
		/*
		All Combination Coverage
			Characteristic 1: X		[x, x’]
				Partitions:	X is in a column that contains a tile
						X is in a column that does not contain a tile
			
			Characteristic 2: Y		[y, y’]
				Partitions:	Y is in a row that contains a tile
						Y is in a row that does not contain a tile 
		 */
		TileType[][] internalState = new TileType[22][10];
		int x = 4;
		int y = 9;
		internalState[y][x] = TileType.TypeT;
		bp.tiles = internalState;
		
		int notX = 2;
		int notY = 15;
		
		assertFalse(bp.isOccupied(notX, notY));
		assertFalse(bp.isOccupied(notX, y));
		assertFalse(bp.isOccupied(x, notY));
		assertTrue(bp.isOccupied(x, y));
		
		
	}
	
	@Test
	public void testSetTile() {
		bp = new BoardPanel(null);
		TileType[][] expected = new TileType[22][10];
		int x = 4;
		int y = 9;
		TileType testTile = TileType.TypeT;
		
		// set the example board
		expected[y][x] = testTile;
		
		bp.setTile(x, y, testTile);
		
		assertArrayEquals(expected, bp.tiles);	
	}
	
	@Test
	public void testGetTile() {
		bp = new BoardPanel(null);
		TileType[][] expected = new TileType[22][10];
		int x = 4;
		int y = 9;
		TileType testTile = TileType.TypeT;
		
		// set the example board
		expected[y][x] = testTile;
		
		bp.tiles = expected;
		
		assertEquals(testTile, bp.getTile(x, y));
		
	}

	@Test
	public void testPaintComponentGraphics() {
		Tetris t = null;
		try{
			t = new Tetris();
			t.setVisible(false);
			t.isPaused = true;
			t.currentType = TileType.TypeZ;
			t.currentCol = 2;
			t.currentRow = 0;
					
		}catch(java.lang.NullPointerException e) {}
		
		bp = new BoardPanel(t);	// start with new board
		JFrame frame = new JFrame();
		frame.add(bp);
		frame.setFocusable(true);
		frame.setAlwaysOnTop(true);
		frame.setFocusableWindowState(true);
		frame.pack();
		frame.setVisible(true);
		
		Scanner testerInput = new Scanner(System.in);
		// State [0] Active board
		t.isPaused = false;
		t.isNewGame = false;
		t.isGameOver = false;
			// e - empty
			bp.repaint();
			assertEquals('e', getTesterInput(testerInput));
			// a - active board with tiles
			t.currentRow++;
			bp.addPiece(TileType.TypeL, 2, 5, 1);
			bp.addPiece(TileType.TypeI, 6, 18, 0);
			bp.repaint();
			assertEquals('a', getTesterInput(testerInput));
			
		// State [1] GAME OVER
		t.isPaused = false;
		t.isNewGame = false;
		t.isGameOver = true;
			// 3 - game over
			bp.repaint();
			assertEquals('g', getTesterInput(testerInput));

		// State [2] TETRIS
		t.isPaused = false;
		t.isNewGame = true;
		t.isGameOver = false;
			// t - TETRIS
			bp.repaint();
			assertEquals('t', getTesterInput(testerInput));

		// State [3] TETRIS
		t.isPaused = false;
		t.isNewGame = true;
		t.isGameOver = true;
			// t - TETRIS
			bp.repaint();
			assertEquals('t', getTesterInput(testerInput));


		// State [4 - 7] Pause
		t.isPaused = true;
		t.isNewGame = false;
		t.isGameOver = false;
			// p - PAUSED
			bp.repaint();
			assertEquals('p', getTesterInput(testerInput));


		//[5]
		t.isPaused = true;
		t.isNewGame = false;
		t.isGameOver = true;
			// p - PAUSED
			bp.repaint();
			assertEquals('p', getTesterInput(testerInput));
			
		//[6]
		t.isPaused = true;
		t.isNewGame = true;
		t.isGameOver = false;
			// p - PAUSED
			bp.repaint();
			assertEquals('p', getTesterInput(testerInput));
			
		//[7]
		t.isPaused = true;
		t.isNewGame = true;
		t.isGameOver = true;
			// p - PAUSED
			bp.repaint();
			assertEquals('p', getTesterInput(testerInput));

		testerInput.close();
		frame.dispose();
	}
	private int m = 0;
	@Ignore
	private char getTesterInput(Scanner testerInput) {
		//Get user input
		System.out.println("Which of the following does the Board Panel Represent?\n"
				+ "[t] TETRIS - Press Enter to Play\n"
				+ "[p] PAUSED\n"
				+ "[g] GAME OVER - Press Enter to Play Again\n"
				+ "[e] New Empty Game Board (no tiles painted) \n"
				+ "[a] Active Game Board (at least 1 tile painted)\n"
				+ "[x] None of the above");
		return testerInput.nextLine().toLowerCase().charAt(0);
//		char[] a = {'e', 'a', 'g', 't', 't', 'p', 'p', 'p', 'p'};
//		return a[m++];
		
	}

}

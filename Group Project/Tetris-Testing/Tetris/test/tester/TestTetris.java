import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.psnbtech.*;

public class TestTetris {

	private Tetris tet;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		tet = null;
	}

	@Test
	public void testTetris() {
		tet = new Tetris();
		assertTrue(tet.board instanceof BoardPanel);
		assertTrue(tet.side instanceof SidePanel);
		assertEquals(1, tet.getKeyListeners().length);
		Dimension expected = new Dimension(450, 490);
		assertEquals(expected,tet.getContentPane().getSize());
		Component[] c = tet.getContentPane().getComponents();
		assertEquals(2, c.length);
		assertTrue(c[0] instanceof BoardPanel);
		assertTrue(c[1] instanceof SidePanel);
		tet.dispose();
	}

	@Test
	public void testStartGameRandom() {
		Thread rt = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Robot robot = new Robot();
					
					
					assertTrue(tet.isNewGame);	// start the game

					robot.delay(1000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.delay(10);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(200);
					
					
					//Game Started
					robot.delay(1200);
//					assertFalse(tet.isNewGame);	
					
					int[] keys = {
							// keys that do stuff
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							// keys that do not do stuff
							KeyEvent.VK_K, 
							KeyEvent.VK_Z, 
							KeyEvent.VK_6, 
							KeyEvent.VK_W, 
					};
					while(!tet.isGameOver) {
						int k = keys[(int)(Math.random()*100)%11];	// pick random key to hit
						
						// get current state
						int col = tet.currentCol;
						int rot = tet.currentRotation;
						int row = tet.currentRow;
						float mspc = tet.logicTimer.millisPerCycle;
						TileType tt = tet.currentType; 
						
						//	------------------------------------------------------
						robot.delay((int)(Math.random()*1000)+200);	// wait to hit
						robot.keyPress(k);							// hit key
						robot.delay((int)(Math.random()*500)+100);	// hold key
						robot.keyRelease(k);						// release key
						robot.delay(175);							// delay before test
						//	------------------------------------------------------
						
						if(tt == tet.currentType && row < tet.currentRow) { 
							// if we have not spawned a new tile at the top of the board we can check that the keys did stuff
							switch(k) {
								case KeyEvent.VK_Q:	
									assertEquals( (rot==0? 3: (rot-1%4)) , tet.currentRotation);
									break;
								case KeyEvent.VK_E:	
									assertEquals((++rot)%4, tet.currentRotation);
									break;
								case KeyEvent.VK_A:	
									assertTrue(tet.currentCol <= col);
									break;
								case KeyEvent.VK_D:	
									assertTrue(tet.currentCol >= col);
									break;
								default:
									// other keys were hit so there was no significant change
									assertEquals(col, tet.currentCol);
									assertEquals(rot, tet.currentRotation);
									assertTrue(tet.currentRow > row);
							}
						}
						
					}
					robot.delay(200);
					assertTrue(tet.isGameOver);
					
				// start new game
					
					assertFalse(tet.isNewGame);	// start the game

					robot.delay(1000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.delay(10);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(200);
					
					assertFalse(tet.isNewGame);	
					
					//Game Started
					robot.delay(1200);
					
					// hit the edges
					int[] keyOrder = {
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_S, 	//DOWN
							
							KeyEvent.VK_P, 	//pause
							KeyEvent.VK_P, 	//pause
							
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT

							KeyEvent.VK_P, 	//pause
							KeyEvent.VK_P, 	//pause
							
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_S, 	//DOWN
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							
					};
					for(int i = 0; i < 3; i++)
						for(int k: keyOrder) {
							robot.delay((int)(Math.random()*100)+200);	// wait to hit
							robot.keyPress(k);							// hit key
							robot.delay((int)(Math.random()*100)+100);	// hold key
							robot.keyRelease(k);						// release key
							robot.delay(75);							// delay before test
							if(tet.isGameOver)
								break;
						}
					
					
					
					// end infinite loop, must remove while(testing) after test
					tet.testing = false;
					tet.dispose();
					
				} catch (AWTException e) {
					fail("start() has an error");
					e.printStackTrace();
				};
				
			}
			
		});
		tet = new Tetris();
		tet.requestFocus();
		tet.testing = true;
		rt.start();
		tet.startGame();
	}
	
	@Test
	public void testRotatePiece() {
		tet = new Tetris();
		tet.setVisible(false);
		
		TileType[] types = {
				TileType.TypeI,
				TileType.TypeO,
				TileType.TypeJ,
				TileType.TypeL,
				TileType.TypeS,
				TileType.TypeZ,
		};
		
		for(TileType tt: types) {
			tet.currentType = tt;
			tet.currentRow = 15;

			//to far to the left
			tet.currentCol = -3;
			tet.currentRotation = 1;
			tet.rotatePiece(2);
			assertEquals(0,tet.currentCol);
			
			//too far to the right
			tet.currentCol = 11;
			tet.currentRotation = 1;
			tet.rotatePiece(2);
			assertEquals(10-tt.dimension, tet.currentCol);

			//middle
			tet.currentCol = 2;
			tet.currentRotation = 1;
			tet.rotatePiece(2);
			assertEquals(2, tet.currentCol);
			assertEquals(15, tet.currentRow);
			

			tet.currentCol = 11;
			
			// to close to the top
			tet.currentRow = -3;
			tet.currentRotation = 0;
			tet.rotatePiece(1);
			assertEquals(0, tet.currentRow);

			//too close to the bottom
			tet.currentRow = 23;
			tet.currentRotation = 0;
			tet.rotatePiece(1);
			assertEquals(22-tt.dimension, tet.currentRow);
			
		}
		tet.dispose();
	}
	
	@Test
	public void testUpdate() {
		TileType l = TileType.TypeL;
		TileType j = TileType.TypeJ;
		TileType o = TileType.TypeO;
		TileType i = TileType.TypeI;
		TileType z = TileType.TypeZ;
		TileType s = TileType.TypeS;
		TileType t = TileType.TypeT;
		
		TileType[][] internalStateStart = {
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
				{null, null, i, null, null, s, null, t, t, t},
				{l, l, i, null, s, s, o, o, t, t},
				{l, l, i, null, s, l, o, o, t, t},
				{l, l, i, z, null, l, null, j, j, t},
				{o, o, z, z, null, l, l, j, null, s},
				{o, o, z, i, i, i, i, j, null, s},
		};

		TileType[][] internalState_O = {
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
				{o, o, null, null, null, null, null, null, null, null},
				{o, o, i, null, null, s, null, t, t, t},
				{l, l, i, null, s, s, o, o, t, t},
				{l, l, i, null, s, l, o, o, t, t},
				{l, l, i, z, null, l, null, j, j, t},
				{o, o, z, z, null, l, l, j, null, s},
				{o, o, z, i, i, i, i, j, null, s},
		};

		TileType[][] internalState_T = {
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
				{o, o, null, null, null, t, t, t, null, null},
				{o, o, i, null, null, s, t, t, t, t},
				{l, l, i, null, s, s, o, o, t, t},
				{l, l, i, null, s, l, o, o, t, t},
				{l, l, i, z, null, l, null, j, j, t},
				{o, o, z, z, null, l, l, j, null, s},
				{o, o, z, i, i, i, i, j, null, s},
		};

		TileType[][] internalState_J = {
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
				{o, o, null, null, null, t, t, t, null, null},
				{l, l, i, z, null, l, null, j, j, t},
				{o, o, z, z, null, l, l, j, null, s},
				{o, o, z, i, i, i, i, j, null, s},
		};
		
		float delta = 0.0002f;
		
		tet = new Tetris();
		tet.setVisible(false);
		tet.random = new Random();
		
		int testScore = 1000;
		float testGameSpeed = 1.45f;
		int testDropCooldown = 222;
		int testLevel = 8;
		tet.logicTimer = new Clock(testGameSpeed);
		
		int testRow, testCol, testRot;
		TileType testTile, testTile2;
		
		tet.score = testScore;
		tet.gameSpeed = testGameSpeed;
		tet.dropCooldown = testDropCooldown;
		tet.level = testLevel;
		
		// Prime Path 1, 2, 3
		testRow = 11;
		testCol = 4;
		testRot = 3;
		testTile = o;
		testTile2 = t;
		
		tet.board.tiles = internalStateStart;
		tet.currentRow = testRow;
		tet.currentCol = testCol;
		tet.currentRotation = testRot;
		tet.currentType = testTile;
		tet.nextType = testTile2;
		
		tet.updateGame();
		// only move down
		assertEquals(testRow+1, tet.currentRow);
		assertEquals(testCol, tet.currentCol);
		assertEquals(testRot, tet.currentRotation);
		//no changes otherwise
		assertEquals(testScore, tet.score);
		assertEquals(testGameSpeed, tet.gameSpeed, delta);
		assertEquals(testDropCooldown, tet.dropCooldown);
		assertEquals(testLevel, tet.level);
		assertArrayEquals(internalStateStart, tet.board.tiles);
		assertEquals(testTile, tet.currentType);
		assertEquals(testTile2, tet.nextType);
		
		// Prime Path 1, 4, 6, 3 – O
		testRow = 15;
		testCol = 0;
		testRot = 2;
		testTile = o;
		testTile2 = t;
		
		
		tet.board.tiles = internalStateStart;
		tet.currentRow = testRow;
		tet.currentCol = testCol;
		tet.currentRotation = testRot;
		tet.currentType = testTile;
		tet.currentType = testTile;
		tet.nextType = testTile2;
		
		tet.updateGame();
		// only move down
		assertEquals(0, tet.currentRow);
		assertEquals(4, tet.currentCol);
		assertEquals(0, tet.currentRotation);
		//no changes otherwise
		assertEquals(testScore, tet.score);
		assertEquals(1.485, tet.gameSpeed, delta);
		assertEquals(25, tet.dropCooldown);
		assertEquals(2, tet.level);
		assertArrayEquals(internalState_O, tet.board.tiles);
		assertEquals(testTile2, tet.currentType);
		
		// Prime Path 1, 4, 6, 3 – T
		testRow = 14;
		testCol = 5;
		testRot = 2;
		testTile = t;
		testTile2 = l;
		
		
		tet.board.tiles = internalStateStart;
		tet.currentRow = testRow;
		tet.currentCol = testCol;
		tet.currentRotation = testRot;
		tet.currentType = testTile;
		tet.currentType = testTile;
		tet.nextType = testTile2;
		
		tet.updateGame();
		// only move down
		assertEquals(0, tet.currentRow);
		assertEquals(4, tet.currentCol);
		assertEquals(0, tet.currentRotation);
		//no changes otherwise
		assertEquals(testScore, tet.score);
		assertEquals(1.52, tet.gameSpeed, delta);
		assertEquals(2, tet.level);
		assertArrayEquals(internalState_T, tet.board.tiles);
		assertEquals(testTile2, tet.currentType);
				

		// Prime Path 1, 4, 5, 6, 3 – J
		testRow = 16;
		testCol = 2;
		testRot = 1;
		testTile = j;
		testTile2 = l;
		
		
		tet.board.tiles = internalStateStart;
		tet.currentRow = testRow;
		tet.currentCol = testCol;
		tet.currentRotation = testRot;
		tet.currentType = testTile;
		tet.currentType = testTile;
		tet.nextType = testTile2;
		
		tet.updateGame();
		// only move down
		assertEquals(0, tet.currentRow);
		assertEquals(4, tet.currentCol);
		assertEquals(0, tet.currentRotation);
		//no changes otherwise
		assertEquals(testScore+400, tet.score);
		assertEquals(1.555, tet.gameSpeed, delta);
		assertEquals(2, tet.level);
		assertArrayEquals(internalState_J, tet.board.tiles);
		assertEquals(testTile2, tet.currentType);
		
		
		//points
		testRow = 16;
		testCol = 2;
		testRot = 1;
		testTile = j;
		testTile2 = l;
		
		
		
		TileType[][] internalState_PointsStart = {
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
				{o, o, null, null, null, t, t, t, null, null},
				{o, o, i, null, null, s, t, t, t, t},
				{l, l, i, null, s, s, o, o, t, t},
				{l, l, i, null, s, l, o, o, t, t},
				{l, l, i, z, z, l, z, j, j, t},
				{o, o, z, z, z, l, l, j, o, s},
				{o, o, z, i, i, i, i, j, o, s},
		};

		TileType[][] internalState_PointsEnd = {
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
				{o, o, null, null, null, t, t, t, null, null},
		};

		tet.board.tiles = internalState_PointsStart;
		tet.currentRow = testRow;
		tet.currentCol = testCol;
		tet.currentRotation = testRot;
		tet.currentType = testTile;
		tet.currentType = testTile;
		tet.nextType = testTile2;
		tet.score = 0;
		int score_6 = 3200;
		
		tet.updateGame();
		assertArrayEquals(internalState_PointsEnd, tet.board.tiles);
		assertEquals(score_6, tet.score);
		
	}
}

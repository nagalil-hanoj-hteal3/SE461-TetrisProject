import static org.junit.Assert.*;

//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;

import java.awt.Robot; //for the player to not control the game

import org.psnbtech.*;
import java.awt.event.KeyEvent;

public class TestTetris {

//	private Robot rob;
	private Tetris tetris;
	
	//adding this would have an extra GUI pop up
//	@Before
//	public void setUp() throws Exception{
//		rob = new Robot();
//		tetris = new Tetris();
//	}
	
//	@After
//	public void tearDown() throws Exception{
//		tetris = null;
////		rob = null;
//	}
	
	@Test
	public void testStartGame() {
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				try {
					Robot rob = new Robot();
					
					//starts new game
					assertTrue(tetris.isNewGame);
					
					//allow the robot to react for a second and proceed to enter the game
					rob.delay(1000);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.delay(10);
					rob.keyRelease(KeyEvent.VK_ENTER);
					rob.delay(200);
//					
					rob.delay(1000);
//					
//					//set an array of keys that will be interacted in tetris
					int[] press_key = {
							KeyEvent.VK_Q,
							KeyEvent.VK_E,
							KeyEvent.VK_Q,
							KeyEvent.VK_E,

							KeyEvent.VK_Q,
							KeyEvent.VK_E,
							KeyEvent.VK_Q,
							KeyEvent.VK_E,

							KeyEvent.VK_A,
							KeyEvent.VK_A, 
							KeyEvent.VK_D,
							KeyEvent.VK_D,

							KeyEvent.VK_S,
							
							KeyEvent.VK_A,
							KeyEvent.VK_A, 	
							KeyEvent.VK_D,
							KeyEvent.VK_D,
//
////							KeyEvent.VK_D,
							KeyEvent.VK_Q,
							KeyEvent.VK_E,
							KeyEvent.VK_D,
							
							KeyEvent.VK_K, 
							KeyEvent.VK_Z, 
							KeyEvent.VK_6, 
//							KeyEvent.VK_W
////							KeyEvent.VK_P,
////							KeyEvent.VK_P,
					};
//					
					while(!tetris.isGameOver) {
//						//choose a random key to hit
						int key = press_key[(int)(Math.random() * 100) % 23];
//					
//						//initialize the directions of the blocks to be used below
						int column = tetris.currentCol;
						int rotation = tetris.currentRotation;
						int row = tetris.currentRow;
						float millis = tetris.logicTimer.millisPerCycle;
						TileType tile = tetris.currentType;	
//						
//						//-- break --\\
//						//Starts Game 1
//						
						rob.delay((int)(Math.random() * 1000) + 500); //delay to press key
						rob.keyPress(key); //presses key
						rob.delay((int)(Math.random() * 500) + 250); //delay to release key
						rob.keyRelease(key); //releases key
						rob.delay((int)millis);
////						rob.delay(100); //delay before test
//						
//						//-- break --\\
//						
						if(tile == tetris.currentType && row < tetris.currentRow) {
							switch(key) {
								//Rotate anticlockwise -- counterclockwise
								case KeyEvent.VK_Q:
									assertEquals((rotation == 0 ? 3:(rotation-1%4)), tetris.currentRotation);
////									tetris.rotatePiece(rotation);
									break;
								//Rotate clockwise
								case KeyEvent.VK_E:
									assertEquals((++rotation)%4, tetris.currentRotation);
////									tetris.rotatePiece(rotation);
									break;
								//Move left
								case KeyEvent.VK_A:
									assertTrue(tetris.currentCol <= column);
////									assertFalse(tetris.currentCol > column);
									break;
								//Move right
								case KeyEvent.VK_D:
									assertTrue(tetris.currentCol >= column);
////									assertFalse(tetris.currentCol < column);
									break;
								//miscellaneous keys that aren't significant towards the game
								default:
									assertEquals(column, tetris.currentCol); //touches the sides of the board panel
									assertEquals(rotation, tetris.currentRotation);
////									assertNotEquals(row, tetris.currentRow);
////									assertNotEquals()
//									
									assertTrue(tetris.currentRow > row);					
////									assertFalse(tetris.currentRow <= row);
							}
						}
					}
//					
//					//Starts Game 2
//					
					rob.delay(200);
					assertTrue(tetris.isGameOver);
					
					//	start a new game after the first game
					assertFalse(tetris.isNewGame);	// start the game

					rob.delay(1000);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.delay(10);
					rob.keyRelease(KeyEvent.VK_ENTER);
					rob.delay(200);
//					
					assertFalse(tetris.isNewGame);	
					
					//Game Started
					rob.delay(1000);
					
					// hit the edges
					int[] keyOrder = {
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_S,  //DOWN
							KeyEvent.VK_S,  //DOWN
							
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_D, 	//LEFT
							KeyEvent.VK_D, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_D, 	//LEFT
							KeyEvent.VK_D, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
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
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
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
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_Q, 	//COUNTER CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_E, 	//CLOCKWISE
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_D, 	//RIGHT
							KeyEvent.VK_A, 	//LEFT
							KeyEvent.VK_A, 	//LEFT
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
					while(!tetris.isGameOver){
						for(int k: keyOrder) {
							rob.delay((int)(Math.random()*100)+150);	// wait to hit
							rob.keyPress(k);							// hit key
							rob.delay((int)(Math.random()*100)+75);	// hold key
							rob.keyRelease(k);						// release key
							rob.delay(75);							// delay before test
							if(tetris.isGameOver) {
								//controls for the robot to close the game by itself
//								rob.delay(10);
						        rob.keyPress(KeyEvent.VK_ALT);
						        rob.keyPress(KeyEvent.VK_F4);
						        rob.keyRelease(KeyEvent.VK_F4);
						        rob.keyRelease(KeyEvent.VK_ALT);
							}		
						}
					}
					
					
				}catch(Exception a) {}
			}
			
		});
		
		//removing this would not allow the thread to add the next game for the robot to play
		tetris = new Tetris();
		tetris.requestFocus();
		t.start();
		tetris.startGame();
		
	}
	
}
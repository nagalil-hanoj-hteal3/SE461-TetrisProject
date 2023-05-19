import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.psnbtech.SidePanel;
import org.psnbtech.*;

public class TestSidePanel {

	private SidePanel sp; 
	private Scanner testerInput;
	@Before
	public void setUp() throws Exception {
		testerInput = AllTests.testerInput;
		sp  = new SidePanel(null);
	}

	@After
	public void tearDown() throws Exception {
		sp = null;
	}

	@Test
	public void testSidePanel() {
		Dimension expected = new Dimension(200, 490);
		
		Tetris t = null;
		try{
			t = new Tetris();
			t.isGameOver = true;
			t.setVisible(false);
		}catch(java.lang.NullPointerException e) {}
		
		sp = new SidePanel(t);
		
		JFrame frame = new JFrame();
		frame.add(sp);
		frame.pack();
		frame.setVisible(true);
		
		assertEquals(expected, sp.getSize());
		
		Color expectedColor = Color.BLACK;
		assertEquals(expectedColor, sp.getBackground());
		
		assertEquals(t, sp.tetris);
		
		frame.dispose();
	}


	@Test
	public void testPaintComponentGraphics() {

		Tetris t = null;
		try{
			t = new Tetris();
			t.isGameOver = true;
			t.setVisible(false);
			
			t.isPaused = false;
			t.isGameOver = false;
		}catch(java.lang.NullPointerException e) {}
		
		sp = new SidePanel(t);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.add(sp);
		frame.setFocusable(true);
		frame.setAlwaysOnTop(true);
		frame.setFocusableWindowState(true);
		frame.pack();
		frame.setVisible(true);
		
		
		TileType[] expectedTile = {TileType.TypeO, TileType.TypeS, null};
		int[] expectedLevel = {5, 0, -2};
		int[] expectedScore = {123, -321, 0};
		
		for(int i = 0; i < 3; i++) {
			t.nextType = expectedTile[i];
			t.score = expectedScore[i];
			t.level = expectedLevel[i];
			
			sp.repaint();
			frame.requestFocusInWindow();
			frame.requestFocus();
		    
		    System.out.println("What Tile Piece is int the box? \n"
		    		+ "[i] Long\n"
		    		+ "[o] Square\n"
		    		+ "[j] J (tail to the left)\n"
		    		+ "[l] L (tail the to right)\n"
		    		+ "[s] S\n"
		    		+ "[z] Z\n"
		    		+ "[t] T\n"
		    		+ "[x] None of the above");
		    
		    TileType actualTile = null;
		    switch(testerInput.nextLine().toLowerCase().charAt(0)) {
			    case 'i':	actualTile = TileType.TypeI; break;
			    case 'o':	actualTile = TileType.TypeO; break;
			    case 'j':	actualTile = TileType.TypeJ; break;
			    case 'l':	actualTile = TileType.TypeL; break;
			    case 's':	actualTile = TileType.TypeS; break;
			    case 'z':	actualTile = TileType.TypeZ; break;
			    case 't':	actualTile = TileType.TypeT; break;
			    default:	break;
		    }
		    
		    System.out.println("What is the value printed next to \"Level\"?");
		    String actualLevel = testerInput.nextLine().trim();

		    System.out.println("What is the value printed next to \"Score\"?");
		    String actualScore = testerInput.nextLine().trim();
		    
		    assertEquals(expectedTile[i], actualTile);
		    assertEquals(expectedScore[i], Integer.parseInt(actualScore));
		    assertEquals(expectedLevel[i], Integer.parseInt(actualLevel));
		    
		}
	
		frame.dispose();
	}
	
}

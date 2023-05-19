import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.psnbtech.BoardPanel;

@RunWith(Suite.class)
@SuiteClasses({ TestBoardPanel.class, TestClock.class, TestSidePanel.class, TestTetris.class, TestTileType.class })
public class AllTests {

	public static Scanner testerInput = new Scanner(System.in);
	@After
	public void tearDown() throws Exception {
		testerInput.close();
	}
	
}

package hanto.studentrno.gui;

import static org.junit.Assert.*;

import org.junit.*;

import hanto.common.HantoGameID;
import hanto.studentrnorlando.gui.MainScreen;
import hanto.studentrnorlando.gui.contentpane.GameContentPane;
import hanto.studentrnorlando.gui.contentpane.HomeContentPane;
import hanto.studentrnorlando.gui.contentpane.SettingsContentPane;

public class GUIIntialazationTest {

	static MainScreen screen;
	
	
	//!!! need to find a better way, the test frezezes on these now
	@BeforeClass
	public static void initializeClass()
	{
		screen = new MainScreen();
	}
	
	@Test // 1
	public void intialazationHomeContentPane()
	{
		HomeContentPane home = new HomeContentPane(screen);
		assertNotNull(home);
	}
	
	@Test // 2
	public void intialazationSettingsContentPane()
	{
		SettingsContentPane settings = new SettingsContentPane(screen);
		assertNotNull(settings);
	}
	
	@Test // 3
	public void intialazationMainScreen()
	{
		assertNotNull(screen);
	}
	
	@Test // 4
	public void intialazationGameScreen()
	{
		GameContentPane game = new GameContentPane(screen, HantoGameID.ALPHA_HANTO);
		assertNotNull(game);
	}
	
}

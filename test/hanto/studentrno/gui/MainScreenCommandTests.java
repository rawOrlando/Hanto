package hanto.studentrno.gui;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import hanto.studentrnorlando.gui.MainScreen;
import hanto.studentrnorlando.gui.contentpane.HomeContentPane;
import hanto.studentrnorlando.gui.contentpane.SettingsContentPane;
import hanto.common.HantoAILevel;
import hanto.common.HantoException;
import hanto.common.HantoGameID;

public class MainScreenCommandTests {
	
static MainScreen screen;
	
	/*
	 * !!! need to find a better way, the test frezezes on these now
	@BeforeClass
	public static void initializeClass()
	{
		screen = new MainScreen();
	}
	
	@Test	//1
	public void GoToSettings()
	{
		screen.doAction("GoTo Settings");
		assertTrue(screen.getContentPane().getClass().equals(SettingsContentPane.class));
		assertTrue(screen.getTitle().equals((new SettingsContentPane(screen)).getTitle()));
	}
	
	@Test 	//2
	public void GoToSettingsFromSettings()
	{
		screen.doAction("GoTo Settings");
		screen.doAction("GoTo Settings");
		assertTrue(screen.getContentPane().getClass().equals(SettingsContentPane.class));
		assertTrue(screen.getTitle().equals((new SettingsContentPane(screen)).getTitle()));
	}
	
	@Test 	//3
	public void GoToHome()
	{
		screen.doAction("GoTo Settings");
		screen.doAction("GoTo Home");
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	
	@Test 	//4
	public void GoToHomeFromHome()
	{
		screen.doAction("GoTo Home");
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	
	@Test 	//5
	public void GoToNoExistingPlace()
	{
		screen.doAction("GoTo NOWHEREE");
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	
	@Test 	//6
	public void saveNumberOfPlayers1()
	{
		screen.doAction("Save PlayerNumber 1");
		assertTrue(screen.getNumberOfPlayers() == 1);
	}
	
	@Test	//7
	public void saveNumberOfPalyers2()
	{
		screen.doAction("Save PlayerNumber 2");
		assertTrue(screen.getNumberOfPlayers() == 2);
	}
	
	@Test	//7.1
	public void saveNumberOfPalyersNonNumber()
	{
		int temp = screen.getNumberOfPlayers();
		screen.doAction("Save PlayerNumber HO");
		assertTrue(screen.getNumberOfPlayers() == temp);
	}
	
	@Test	//8
	public void saveAILevelEasy()
	{
		screen.doAction("Save AILevel EASY");
		assertTrue(screen.getAILevel() == HantoAILevel.EASY);
	}
	
	@Test	//8
	public void saveAILevelMedium()
	{
		screen.doAction("Save AILevel MEDIUM");
		assertTrue(screen.getAILevel() == HantoAILevel.MEDIUM);
	}

	@Test	//9
	public void saveAILevelHard()
	{
		screen.doAction("Save AILevel HARD");
		assertTrue(screen.getAILevel() == HantoAILevel.HARD);
	}
	
	@Test	//11
	public void saveAILevelNoExisting()
	{
		HantoAILevel temp = screen.getAILevel();
		screen.doAction("Save AILevel EXTrOM");
		assertTrue(screen.getAILevel() == temp);
	}
	
	@Test	//11
	public void saveGameIDAlpha()
	{
		screen.doAction("Save GameID ALPHA_HANTO");
		assertTrue(screen.getGameID() == HantoGameID.ALPHA_HANTO);
	}
	
	@Test	//11
	public void saveGameIDNonOption()
	{
		HantoGameID temp = screen.getGameID();
		screen.doAction("Save GameID WIngdsal");
		assertTrue(screen.getGameID() == temp);
	}
	
	@Test	//12
	public void saveNonVariable()
	{
		HantoGameID temp = screen.getGameID();
		HantoAILevel temp2 = screen.getAILevel();
		screen.doAction("Save Please please");
		assertTrue(screen.getGameID() == temp);
		assertTrue(screen.getAILevel() == temp2);
	}
	
	@Test	//13
	public void saveNothing()
	{
		HantoGameID temp = screen.getGameID();
		HantoAILevel temp2 = screen.getAILevel();
		screen.doAction("Save GameID");
		assertTrue(screen.getGameID() == temp);
		assertTrue(screen.getAILevel() == temp2);
	}
	
	@Test	//14
	public void GoToNothing()
	{
		screen.doAction("GoTo");
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	
	@Test	//14
	public void GoToNonExistingPlace()
	{
		screen.doAction("GoTo MArs");
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	
	
	@Test	//14
	public void EmptyComand()
	{
		assertTrue(!screen.doAction(""));
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	*/
}

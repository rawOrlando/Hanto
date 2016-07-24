package hanto.studentrno.gui;

import static org.junit.Assert.*;

import org.junit.*;

import hanto.studentrnorlando.gui.HomeContentPane;
import hanto.studentrnorlando.gui.MainScreen;
import hanto.studentrnorlando.gui.SettingsContentPane;

public class GUIMasterTest {

	static MainScreen screen;
	
	@BeforeClass
	public static void initializeClass()
	{
		screen = new MainScreen();
	}
	
	@Test //1
	public void startOnHomeConentScreen()
	{
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	
	//Test GoTo
	@Test // 2
	public void switchingToSettignsScreen()
	{
		screen.doAction("GoTo Settings");
		assertTrue(screen.getContentPane().getClass().equals(SettingsContentPane.class));
		assertTrue(screen.getTitle().equals((new SettingsContentPane(screen)).getTitle()));
	}
	
	@Test // 3
	public void switchingFromSelfToHomeScreen()
	{
		screen.doAction("GoTo Home");
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
	@Test // 4
	public void switchingFromSettingsToHomeScreen()
	{
		screen.doAction("GoTo Settings");
		screen.doAction("GoTo Home");
		assertTrue(screen.getContentPane().getClass().equals(HomeContentPane.class));
		assertTrue(screen.getTitle().equals((new HomeContentPane(screen)).getTitle()));
	}
}

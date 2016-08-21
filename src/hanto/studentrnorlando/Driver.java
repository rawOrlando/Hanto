package hanto.studentrnorlando;

import hanto.studentrnorlando.gui.MainScreen;

public class Driver 
{
	MainScreen screen;
	
	public Driver()
	{
		screen = new MainScreen(this);
	}
	
	public static void main( String[] args ) 
	{
		new  Driver();  
	}
}

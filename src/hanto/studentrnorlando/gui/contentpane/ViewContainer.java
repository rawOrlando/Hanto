/**
 * This file is a class designed to alow MVC to work in JSwing.
 * This will play the part as the view.
 */
package hanto.studentrnorlando.gui.contentpane;

import java.awt.Container;

import hanto.studentrnorlando.gui.Screen;

/**
 * Is the View in the MVC model working with JSwing
 * @author Orlando
 */
public class ViewContainer extends Container{

	private static final long serialVersionUID = 1L;
	
	private String title;
	protected Screen handler;
	
	/**
	 * viewContainer constructor
	 * @param screen the controller for the view
	 * @param title the title this view would like to be displayed
	 */
	public ViewContainer(Screen screen, String title)
	{
		handler = screen;
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	

}

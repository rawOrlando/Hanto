/**
 * This will be the class to extends to use MVC with JSwing, 
 * This class , the screen will be the controller
 */
package hanto.studentrnorlando.gui;

import javax.swing.JFrame;

/**
 * Is the controler for the JSwing, MVC schema
 * @author Orlando
 *
 */
public class Screen extends JFrame{
	
	/**
	 * Default added serialization ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * do Action  will give a message to the controller.
	 * The controller decides whether the message is something he should respond to.
	 * and does the apropreate action if he should
	 * @param actionLine the message the view is sending the control
	 * @return whether it did a action or not
	 */
	public boolean doAction(String actionLine)
	{
		return false;
	}

}

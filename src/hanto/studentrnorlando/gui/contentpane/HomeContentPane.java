/**
 * This container will act like an View in the MVC model
 * This is responsible for the view on the home screen
 */
package hanto.studentrnorlando.gui.contentpane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import hanto.studentrnorlando.gui.Screen;

/**
 * this is the content pane which will hold all GUi elemetns need for the home screen
 * @author Orlando
 *
 */
public class HomeContentPane extends ViewContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Basic constructor of HomeContentPane
	 * @param screen It's Control
	 */
	public HomeContentPane(Screen screen)
	{
		super(screen, "Hanto");
		this.setLayout( null );
		// !!! the background color is not working !!!
		this.setBackground(Color.RED);
		createButtons();
		 
	}
	
	/**
	 * Creates the home Screen buttons
	 */
	public void createButtons()
	{
		
		// Create The Settings Button
		JButton settingsButton = new JButton("Setings");
	    settingsButton.setBounds( 120, 290, 100, 25 );
	    this.add(settingsButton);
	    settingsButton.addActionListener(

	         new ActionListener() // anonymous inner class
	         {
	            // event handler called when countJButton is pressed
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					handler.doAction("GoTo Settings");
				}

	         }); // end anonymous inner class
	    
	    // Create The Play Button
	    //Chang FOnt size to be pretty !!!
 		JButton playButton = new JButton("Play");
 	    playButton.setBounds( 100, 250, 200, 40 );
 	    this.add(playButton);
 	    playButton.addActionListener(

 	         new ActionListener() // anonymous inner class
 	         {
 	            // event handler called when countJButton is pressed
 				@Override
 				public void actionPerformed(ActionEvent e) {
 					// TODO Auto-generated method stub
 					
 					handler.doAction("GoTo Game");
 				}

 	         }); // end anonymous inner class
	}
	
	

}

/**
 * this is the content pane which will hold all GUi elemetns need for the settigns screen
 */
package hanto.studentrnorlando.gui.contentpane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hanto.common.HantoAILevel;
import hanto.common.HantoGameID;
import hanto.studentrnorlando.gui.Screen;

/**
 * Is the View in MVC, for the Settings Screen 
 * @author Orlando
 *
 */
public class SettingsContentPane extends ViewContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	// !!! change to map so that there name could stay with them ??
	private List <JSpinner> spinners;
	
	/**
	 * Settings ContentPane basic Constructor
	 * @param screen the controler for this view
	 */
	public SettingsContentPane(Screen screen)
	{
		this(screen, 2, HantoGameID.ALPHA_HANTO, HantoAILevel.EASY);
	}
	
	public SettingsContentPane(Screen screen, int numberOfPlayers, HantoGameID gameID, HantoAILevel aiLevel)
	{
		super(screen, "Hanto Settings");
		this.setLayout( null );
		// !!! the background color is not working !!!
		this.setBackground(Color.RED);
		this.setupHomeButton();
		setupChoices(numberOfPlayers, gameID, aiLevel);
	}
	
	private void setupHomeButton()
	{
		//create home Button
		// change to icon
		JButton playButton = new JButton("HOME");
 	    playButton.setBounds( 100, 100, 170, 50 );
 	    this.add(playButton);
 	    playButton.addActionListener(

 	         new ActionListener() // anonymous inner class
 	         {
 	            // event handler called when countJButton is pressed
 				@Override
 				public void actionPerformed(ActionEvent e) {
 					// TODO Auto-generated method stub
 					
 					handler.doAction("GoTo Home");
 				}

 	         }); // end anonymous inner class
	}
	
	private void setupChoices(int numberOfPlayers, HantoGameID gameID, HantoAILevel aiLevel)
	{
		spinners = new ArrayList<JSpinner>(3);
		setupGameChoices(gameID);
		setupAIChoices(aiLevel);
		setupPlayerChoices(numberOfPlayers);
	}
	
	private void setupPlayerChoices(int numberOfPlayers)
	{
		createText(50, 250, "Number of Players:");
		setupPlayerChoicesSpinner(numberOfPlayers);
	}
	
	private void setupPlayerChoicesSpinner(int numberOfPlayers)
	{
		String [] playerOptions = {"2 Players", "1 Player"};
		SpinnerListModel model = new SpinnerListModel(playerOptions);
		if(numberOfPlayers == 1)
		{
			//model.setValue("1 Player");
		}
		else
		{
			//model.setValue("2 Player");
		}
		JSpinner spinner = new JSpinner(model);	
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		spinner.setBounds( 200, 250, 100, 25 );
		spinner.addChangeListener(new ChangeListener() {      
			  @Override
			  public void stateChanged(ChangeEvent e) {
				  // get value  	  
				  handler.doAction("Save PlayerNumber " + spinner.getValue().toString());
			  }
			});
		spinners.add(spinner);
		add(spinner);	
		
	}
	
	
	private void setupGameChoices(HantoGameID gameID)
	{
		createText(50, 290, "Game Mode:");
		setupGameChoicesSpinner(gameID);
	}
	//!!! fidn a way to gneral ize these
	private void setupGameChoicesSpinner(HantoGameID gameID)
	{
		Set<HantoGameID> allGameIDs = EnumSet.allOf(HantoGameID.class);
		SpinnerListModel model = new SpinnerListModel(allGameIDs.toArray());
		//model.setValue(gameID);
		JSpinner spinner = new JSpinner(model);	
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		spinner.setBounds( 200, 290, 100, 25 );
		spinner.addChangeListener(new ChangeListener() {      
			  @Override
			  public void stateChanged(ChangeEvent e) {
				  // get value  	  
				  handler.doAction("Save GameID " + ((HantoGameID)spinner.getValue()).name());
			  }
			});
		spinners.add(spinner);
		add(spinner);	
		
	}
	
	/*
	//Temp !!! warning bad code but I am sure there is a way to generalize it
	private void setupSpinner(final int yCordinate, Enum example)
	{
		Set all = EnumSet.allOf(example.getClass());
		SpinnerListModel model = new SpinnerListModel(all.toArray());
		JSpinner spinner = new JSpinner(model);	
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		spinner.setBounds( 120, yCordinate, 100, 25 );
		spinners.add(spinner);
		this.add(spinner);	
	}
	*/
	
	private void setupAIChoices(HantoAILevel aiLevel)
	{
		createText(50, 330, "AI Level:");
		setupAIChoicesSpinner(aiLevel);
	}
	
	private void setupAIChoicesSpinner(HantoAILevel aiLevel)
	{
		Set<HantoAILevel> all = EnumSet.allOf(HantoAILevel.class);
		SpinnerListModel model = new SpinnerListModel(all.toArray());
		HantoAILevel level = (HantoAILevel) model.getValue();
		
		//model.setValue(aiLevel);
		JSpinner spinner = new JSpinner(model);	
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		spinner.setBounds( 200, 330, 100, 25 );
		spinner.addChangeListener(new ChangeListener() {      
			  @Override
			  public void stateChanged(ChangeEvent e) {
				  // get value  	  
				  handler.doAction("Save AILevel " + spinner.getValue().toString());
			  }
			});
		add(spinner);
		spinners.add(spinner);
	}

	private void createText(int xCordinate, int yCordinate, String text)
	{
		JLabel label = new JLabel(text);
		//come with a better way to figure out length !!!
		label.setBounds(xCordinate, yCordinate, 150, 25);
		add(label);
	}
}

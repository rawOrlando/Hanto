/**
 * this going to be the main screen for the GUi displayed to the user
 * It will also act as the control in a MVC model, where the containers will act as the views.
 */
package hanto.studentrnorlando.gui;

import javax.swing.JFrame;

import hanto.common.HantoAILevel;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentrnorlando.Driver;
import hanto.studentrnorlando.common.game.HantoBaseSmartGame;
import hanto.studentrnorlando.common.game.HantoModelGame;
import hanto.studentrnorlando.common.game.HantoSmartGame;
import hanto.studentrnorlando.factory.HantoGameFactory;
import hanto.studentrnorlando.gui.contentpane.GameContentPane;
import hanto.studentrnorlando.gui.contentpane.HomeContentPane;
import hanto.studentrnorlando.gui.contentpane.SettingsContentPane;
import hanto.studentrnorlando.gui.contentpane.ViewContainer;
import hanto.tournament.HantoMoveRecord;

/**
 * this is the main screen the will be a single window for the game
 * This will also act as a controller to all the different views
 * @author Orlando
 */
public class MainScreen extends Screen {


	private static final long serialVersionUID = 1L;
	
	private HomeContentPane HomePane;
	private SettingsContentPane SettingPane;
	private GameContentPane GamePane;
	private Driver model;
	private HantoAILevel AILevel;
	private HantoGameID gameType;
	private int numberOfPlayers;
	
	private HantoModelGame game;
	
	private boolean gameOver;
	
	/**
	 * Contructor, if you don't want to play the game
	 */
	public MainScreen()
	{
		HomePane = new HomeContentPane(this);
		SettingPane = new SettingsContentPane(this);
		GamePane = new GameContentPane(this, HantoGameID.GAMMA_HANTO);
		this.createUserInterface();
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		numberOfPlayers = 2;
		gameType = HantoGameID.GAMMA_HANTO;
		game = HantoGameFactory.getInstance().makeHantoModelGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLACK);
		//Move this !!!
		GamePane.updateGame(game.getBoard(), game.getAllPlayersOptions(), game.getPlayer(), null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public MainScreen(Driver model)
	{
		this();
		this.model = model;
	}
	
	@Override
	public boolean doAction(String actionLine)
	{
		String[] splited = actionLine.split(" ");
		if(splited[0].equals("GoTo"))
		{
			if(splited.length <= 1)
			{
				return false;
			}
			if(splited[1].equals("Home"))
			{
				this.changeUserInterface(HomePane);
				return true;
			}
			if(splited[1].equals("Settings"))
			{
				this.changeUserInterface(SettingPane);
				return true;
			}
			if(splited[1].equals("Game"))
			{
				createGame();
				//!!! change so it creates game based on setting dessisions
				this.changeUserInterface(GamePane);
				return true;
			}
		}
		if(splited[0].equals("Save"))
		{
			if(splited.length <= 2)
			{
				return false;
			}
			//!!! pass to brain
			if(splited[1].equals("PlayerNumber"))
			{
				System.out.println(splited[2]);
				try
				{
					numberOfPlayers = Integer.parseInt(splited[2]);
				}
				catch(Exception e)
				{
					System.out.println("Some bad code not folloing the command paramters");
					e.printStackTrace();
				}
				return true;
			}
			if(splited[1].equals("AILevel"))
			{
				System.out.println(splited[2]);
				try
				{
					AILevel = HantoAILevel.valueOf(splited[2]);
				}
				catch(Exception e)
				{
					System.out.println("Some bad code not folloing the command paramters");
					e.printStackTrace();
				}
				return true;
			}
			if(splited[1].equals("GameID"))
			{
				System.out.println(splited[2]);
				try
				{
					gameType = HantoGameID.valueOf(splited[2]);
				}
				catch(Exception e)
				{
					System.out.println("Some bad code not folloing the command paramters");
					e.printStackTrace();
				}
				return true;
			}
		}
		if(splited[0].equals("Move"))
		{
			if(splited.length >= 4)
			{
				HantoMoveRecord move = HantoMoveRecord.convertFromString(splited[1] + " " + splited[2] + " " + splited[3] + " ");
				if(move != null)
				{
					
					try {
						MoveResult result = game.makeMove(move);
						gameOver = this.GamePane.updateGame(game.getBoard(), game.getAllPlayersOptions(), game.getPlayer(), result);
					} catch (HantoException e) {
						System.out.println("Game Broke");
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Message was messaesed up");
					//We messed up else where
				}
			}
		}
		return super.doAction(actionLine);
	}
	
	private void createGame()
	{
		//!!! will need more for this later
		if(gameOver)
		{
			
			this.GamePane = new GameContentPane(this, HantoGameID.GAMMA_HANTO);
			
			game = HantoGameFactory.getInstance().makeHantoModelGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLACK);
			
			GamePane.updateGame(game.getBoard(), game.getAllPlayersOptions(), game.getPlayer(), null);
		}
	}
	
	/**
	 * Changes the User Interface to a different contain
	 * Can be though of as a segue
	 * @param pane the container being moved to
	 */
	private void changeUserInterface(ViewContainer pane)
	{
		this.setContentPane(pane);
	    
	    setupJFrame(pane);
	        
	}
	
	/**
	 * creates the user Interface, should be call on instantiaiton
	 */
	private void createUserInterface()
	{
		this.changeUserInterface(HomePane);        
	}
	
	/**
	 * sets up the JFrame for display
	 */
	private void setupJFrame(ViewContainer container)
	{
		setTitle(container.getTitle()); // set title bar text
	    setSize( 600, 650 );   // set window size
	    setVisible( true );    // display window
	}
	
	public HantoGameID getGameID()
	{
		return gameType;
	}
	
	public HantoAILevel getAILevel()
	{
		return AILevel;	
	}
	
	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}

}

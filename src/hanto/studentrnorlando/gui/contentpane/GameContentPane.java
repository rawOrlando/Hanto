package hanto.studentrnorlando.gui.contentpane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.beta.BetaHantoPlayer;
import hanto.studentrnorlando.common.GeneralHantoPlayer;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.gui.GUIHantoGameBoard;
import hanto.studentrnorlando.gui.JGameTile;
import hanto.studentrnorlando.gui.Screen;



public class GameContentPane extends ViewContainer{

	private int initXOffset = 0;
	private int initYOffset = 0;
	
	private int tileSize = 140;
	
	//!!! som stuff is hard code coem back and fix
	// lets get it to show first
	private static final long serialVersionUID = 1L;
	HantoGameID gameID;
	ImageIcon img;
	JPanel paintPanel, textPanel;
	GUIHantoGameBoard board;
	List<HantoCoordinate> optionsTemp = new ArrayList<HantoCoordinate>();
	Map<HantoCoordinate, List<HantoCoordinate>> options = new HashMap<HantoCoordinate, List<HantoCoordinate>>();
	Map<HantoPieceImpl, List<HantoCoordinate>> placementOptions = new HashMap<HantoPieceImpl, List<HantoCoordinate>>();;
	HantoCoordinate lastPieceClickedLocation = null;
	HantoPiece pieceMove;
	
	List<JGameTile> currentOptionsTiles =  new ArrayList<JGameTile>();
	
	GeneralHantoPlayer player;
	int numberOfPieces = 0;
	
	//!!! test delete later
	JButton button;
	
	public GameContentPane(Screen screen, HantoGameID gameID) {
		//!!! figure out title
		
		super(screen, gameID.toString() + " Game");
		
		BetaHantoPlayer player = new BetaHantoPlayer(HantoPlayerColor.BLACK);
		PlayerPieceContentPane playerPane = new PlayerPieceContentPane(player);
		playerPane.setBounds(0, 0, 500, 500);
		playerPane.setVisible(true);
		this.add(new PlayerPieceContentPane(player));
		
		this.player = new BetaHantoPlayer(HantoPlayerColor.BLACK);
		
		//!!! temp for testing
		options = new HashMap<HantoCoordinate, List<HantoCoordinate>>();
		List<HantoCoordinate> tempList = new ArrayList<HantoCoordinate>();
		tempList.add(new HantoCordinateImpl(2,0));
		options.put(new HantoCordinateImpl(0,0), tempList);
		tempList = new ArrayList<HantoCoordinate>();
		
		tempList.add(new HantoCordinateImpl(-2,0));
		//tempList.add(new HantoCordinateImpl(-3,0));
		options.put(new HantoCordinateImpl(-1,0), tempList);
		//reSizeTiles();
		
		tempList = new ArrayList<HantoCoordinate>();
		tempList.add(new HantoCordinateImpl(3,0));
		placementOptions.put(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), tempList);
		placementOptions.put(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.SPARROW), tempList);
		/*
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		paintPanel = new JPanel();
		textPanel = new JPanel();

		this.add(paintPanel);
		this.add(textPanel);
		*/
		Map<HantoCoordinate, HantoPiece> pieces = new HashMap<HantoCoordinate, HantoPiece>();
		pieces.put(new HantoCordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY));
		
		
		pieces.put(new HantoCordinateImpl(1,1), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		pieces.put(new HantoCordinateImpl(1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		pieces.put(new HantoCordinateImpl(-1,0), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		//pieces.put(new HantoCordinateImpl(0,-1), null);
		
		//pieces.put(new HantoCordinateImpl(7,-4), new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY));
		
		//drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(5,0));
		//drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(6,0));
		//drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(7,0));
		//drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(8,0));
		//drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(9,0));
		 board = new GUIHantoGameBoard(pieces, HantoGameID.BETA_HANTO);
		 reSizeTiles();
		 draw();
		/*
		drawTitle(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.CRAB), new HantoCordinateImpl(1,1));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW), new HantoCordinateImpl(2,1));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW), new HantoCordinateImpl(2,2));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.CRAB), new HantoCordinateImpl(3,-2));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(2,-2));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.HORSE), new HantoCordinateImpl(2,-1));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.HORSE), new HantoCordinateImpl(4,-2));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW), new HantoCordinateImpl(5,-3));
		
		drawTitle(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW), new HantoCordinateImpl(6,-3));
		*/
		
		// TODO Auto-generated constructor stub !!!
		 
		drawPlayerUnPlayedPieces();
	}
	
	public void reSizeTiles()
	{
		//figure out X
		int boardLength = board.getXBoardLength();
		System.out.println(this.getWidth());
		// if any piece is 
		int xTileSize = 450/(boardLength + 2) ;
		
		//figure out Y
		boardLength = board.getYBoardLength();
		int yTileSize = (450) / (boardLength +2);
		tileSize = Math.min(xTileSize, yTileSize);
		
		//Figure out off set
		int far = board.getXFartherestLeftPoint();
		initXOffset =   (tileSize) * -1 * (far - 1);
		
		// figure out y offset
		int high = board.getYHighestPoint();
		initYOffset = tileSize * (high + 1); 
		
	}
	
	//!!! cahgne it so Optionsant tiles are same (stop using board. Jsut store a map brain is else where)
	public void draw()
	{
		drawTiles();
		//drawOptions();
	}
	
	public void drawTiles()
	{
		Map<HantoCoordinate, HantoPiece> pieces = board.getMap();
		for(HantoCoordinate coordinate: pieces.keySet())
		{
			drawTile(pieces.get(coordinate), coordinate);
		}
	}
	
	public void drawOptions(List<HantoCoordinate> options)
	{
		currentOptionsTiles.clear();
		for(HantoCoordinate coordinate: options)
		{
			System.out.println("adding options");
			currentOptionsTiles.add(drawTile(null, coordinate));
		}
	}
	
	public JGameTile drawTile(HantoPiece piece, HantoCoordinate coordinate)
	{
		JGameTile tile = new JGameTile(piece, coordinate);
		tile.setBounds(initXOffset, initYOffset, tileSize, tileSize);
		this.add(tile);
		return tile;
	}
	
	//
	//draw Bag
	//
	private void drawPlayerUnPlayedPieces()
	{
		drawLine();
		if(player != null )
		{
			for(HantoPieceType type: player.getPieceOptions())
			{
				drawPlayerUnPlayedPiece(type);
				numberOfPieces++;
			}
		}
	}
	
	private void drawPlayerUnPlayedPiece(HantoPieceType type)
	{
		JGameTile tile = new JGameTile(type, player.getPlayerColor());
		tile.setBounds(30 + 100 * numberOfPieces, 500, 100, 100);
		this.add(tile);
	}
	
	private void drawLine()
	{
		// !!! to do
	}
	
	//
	//Done with Bag
	//
	
	@Override
	public Component add(Component comp)
	{
		Component temp =  super.add(comp);
		if(comp.getClass().equals(JGameTile.class))
		{
			JGameTile tile = (JGameTile)comp;
			List<JButton> buttons = tile.getButtons();
			for(JButton button: buttons)
			{
				
				button.addActionListener(

				         new ActionListener() // anonymous inner class
				         {
				            // event handler called when countJButton is pressed
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								pieceMove(tile.getCordinate(), tile.getPiece());
								
							}

				         }); // end anonymous inner class
				super.add(button);
			}
			
		}
		return temp;
		
	}
	
	private void pieceMove(HantoCoordinate cordinate, HantoPiece piece)
	{
		if (piece != null)
			startMove(cordinate, piece);
		else
			finishMove(cordinate);
	}
	
	private void startMove(HantoCoordinate cordinate, HantoPiece piece)
	{
		clearOptions();
		lastPieceClickedLocation = cordinate;
		pieceMove = piece;
		showOptions(lastPieceClickedLocation, pieceMove);
			
	}
	
	private void finishMove(HantoCoordinate cordiante)
	{
		System.out.println("Finshed");
		clearOptions();
	}
	
	public void showOptions(HantoCoordinate cordinate, HantoPiece piece)
	{
		if(cordinate != null)
		{
			showMoveOptions(cordinate);
		}
		else
		{
			showPlaceOptions(piece);
		}
	}
	
	public void showPlaceOptions(HantoPiece piece)
	{
		System.out.println(piece.getType().getPrintableName() + " " + piece.getColor().getPrintableName(piece.getColor()));
		System.out.println(placementOptions.size());
		if(placementOptions.get(piece) != null)
		{
			System.out.println("HI");
			drawOptions(placementOptions.get(piece));
			this.repaint();
		}
		for(HantoPieceImpl piece1: placementOptions.keySet())
		{
			// this should not be this bad...
			if(piece1.equals(piece))
			{
				drawOptions(placementOptions.get(piece1));
				this.repaint();
				return;
			}
		}
	}
	
	public void showMoveOptions(HantoCoordinate cordinate)
	{
		
		if(options.get(cordinate) != null)
		{
			drawOptions(options.get(cordinate));
			this.repaint();
		}
	}
	
	private void clearOptions()
	{
		clearOptions(lastPieceClickedLocation);
	}
	
	private void clearOptions(HantoCoordinate cordinate)
	{
		for(JGameTile tile: currentOptionsTiles)
		{
			remove(tile);
			tile.setVisible(false);
			for(JButton button: tile.getButtons())
			{
				remove(button);
			}
			
		}
		
		
		
		lastPieceClickedLocation = null;
		pieceMove = null;
		this.repaint();

		this.revalidate();
		
	}
	
	

}

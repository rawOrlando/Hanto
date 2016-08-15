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
import hanto.studentrnorlando.common.HantoMove;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.common.IHantoPlayer;
import hanto.studentrnorlando.common.board.IHantoGameBoard;
import hanto.studentrnorlando.gui.GUIHantoGameBoard;
import hanto.studentrnorlando.gui.JGameTile;
import hanto.studentrnorlando.gui.Screen;
import hanto.tournament.HantoMoveRecord;



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
	Map<HantoPiece, List<HantoCoordinate>> placementOptions = new HashMap<HantoPiece, List<HantoCoordinate>>();;
	HantoCoordinate lastPieceClickedLocation = null;
	HantoPiece pieceMove;
	
	List<JGameTile> currentOptionsTiles =  new ArrayList<JGameTile>();
	
	IHantoPlayer player;
	int numberOfPieces = 0;
	
	//!!! test delete later
	JButton button;
	
	public GameContentPane(Screen screen, HantoGameID gameID) {
		//!!! figure out title
		
		super(screen, gameID.toString() + " Game");
		//!!! fix later genreate of somethign else
		player = new BetaHantoPlayer(HantoPlayerColor.BLACK);
		
	}
	
	// make lazy way nt furture
	public void updateGame(IHantoGameBoard board, List<HantoMoveRecord> moves, IHantoPlayer player)
	{

		placementOptions = new HashMap<HantoPiece, List<HantoCoordinate>>();
		System.out.println(moves.size() + player.getPlayerColor().toString());
		
		this.board = new GUIHantoGameBoard(board);
		convertMoves(moves);
		this.player = player;
		this.removeAll();
		
		reSizeTiles();
		draw();
		this.repaint();
		this.revalidate();
		
		System.out.println("Whats still Left");
		for(HantoPiece thing : placementOptions.keySet() )
		{
			System.out.println(placementOptions.get(thing).get(0) +thing.getType().name());
		}
		
		if(this.placementOptions.get(new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY)) != null)
		{
			for(HantoCoordinate temp: this.placementOptions.get(HantoPieceType.BUTTERFLY))
			{
				System.out.println("\t" +"smaed" + temp.toString());
			}
		}
	}
	
	public void convertMoves(List<HantoMoveRecord> moves)
	{
		// !!! make movre effecent use a real sort algorithm
		while(0 != moves.size())
		{
			HantoMoveRecord move = moves.get(0);
			List<HantoCoordinate> tempList = new ArrayList<HantoCoordinate>();
			System.out.println("\t" +"adding" + "\t" +move.toString());
			for(int index = 1; index < moves.size(); index++)
			{
				HantoMoveRecord otherMove = moves.get(index);
				if(move.getPiece() != null && move.getPiece().equals(otherMove.getPiece()) &&
						(move.getFrom() == null && otherMove.getFrom() == null)
						|| move.getFrom().equals(otherMove.getFrom()))
				{
					System.out.println("\t" +"adding" + "\t" + otherMove.toString());
					tempList.add(otherMove.getTo());
					moves.remove(otherMove);
					index--;
				}
			}
			tempList.add(move.getTo());
			if(move.getFrom() == null)
			{
				System.out.println("\t" +"convert added " + move.getTo());
				HantoPieceImpl piece = new HantoPieceImpl(player.getPlayerColor(), move.getPiece());
				for(HantoCoordinate thing : tempList )
				{
					System.out.println("\t" +"\t" + piece.getType().name() + (new HantoCordinateImpl(thing)).toString());
				}
				placementOptions.put(piece, tempList);
			}
			else
			{
				options.put(move.getFrom(), tempList);
			}
			moves.remove(move);
		}
		if(placementOptions.size() == 0)
		{
			
			System.out.println("Failed to convert Moves");
		}
		else
		{
			System.out.println("Whats Left");
			for(HantoPiece thing : placementOptions.keySet() )
			{
				System.out.println(placementOptions.get(thing).get(0));
			}
		}
	}
	
	public void reSizeTiles()
	{
		//figure out X
		int boardLength = board.getXBoardLength();
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
		drawPlayerUnPlayedPieces();
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
		System.out.println("\t " + options.size());
		for(HantoCoordinate coordinate: options)
		{
			System.out.println("\t " + coordinate.toString());
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
		numberOfPieces = 0;
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
								
								System.out.println("Clicked the button");
								pieceMove(tile.getCordinate(), tile.getPiece());
							}

				         }); // end anonymous inner class
				super.add(button);
			}
			for(HantoPiece thing : placementOptions.keySet() )
			{
				System.out.println("AfterAdding:"+placementOptions.get(thing).get(0) +thing.getType().name());
			}

			
		}
		return temp;
		
	}
	
	private void pieceMove(HantoCoordinate cordinate, HantoPiece piece)
	{
		if(piece == null)
		{
			System.out.println("piece Move");
		}
		else System.out.println("piece Move: " + piece.getType().getPrintableName());
		if(this.placementOptions.get(HantoPieceType.BUTTERFLY) != null)
		{
			for(HantoCoordinate temp: this.placementOptions.get(HantoPieceType.BUTTERFLY))
			{
				System.out.println("\t" +"smaed" + temp.toString());
			}
		}
		
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
		System.out.println("\t startMove");
		showOptions(lastPieceClickedLocation, pieceMove);
			
	}
	
	private void finishMove(HantoCoordinate cordinate)
	{
		handler.doAction(generateMovementCommand(cordinate));
		clearOptions();
	}
	
	private String generateMovementCommand(HantoCoordinate cordinate)
	{
		String reValue = "Move ";
		reValue = reValue + this.pieceMove.getType().name() + " ";
		if(lastPieceClickedLocation == null)
		{
			reValue = reValue + "null ";
		}
		else
		{
			reValue = reValue + lastPieceClickedLocation.toString() + " ";
		}
		reValue = reValue + cordinate.toString();
		return reValue;
	}
	
	public void showOptions(HantoCoordinate cordinate, HantoPiece piece)
	{
		if(cordinate != null)
		{
			showMoveOptions(cordinate);
		}
		else
		{
			System.out.println("\t Placement");
			showPlaceOptions(piece);
		}
	}
	
	public void showPlaceOptions(HantoPiece piece)
	{
		System.out.println("\t \t" + placementOptions.size() );
		if(placementOptions.get(piece) != null)
		{
			drawOptions(placementOptions.get(piece));
			this.repaint();
		}
		for(HantoPiece piece1: placementOptions.keySet())
		{
			System.out.println("\t \t Null");
			for (HantoCoordinate cor :placementOptions.get(piece1))
			{
				System.out.println("\t\t " + cor);
			}
			// this should not be this bad...
			if(piece1.getType().equals(piece.getType()))
			{
				
				System.out.println("\t Drawing");
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

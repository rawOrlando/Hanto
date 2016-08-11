package hanto.studentrnorlando.gui.contentpane;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.gui.GUIHantoGameBoard;
import hanto.studentrnorlando.gui.JGameTile;
import hanto.studentrnorlando.gui.Screen;



public class GameContentPane extends ViewContainer{

	private int initXOffset = 0;
	private int initYOffset = 0;
	
	private int tileSize = 140;
	
	
	private static final long serialVersionUID = 1L;
	HantoGameID gameID;
	ImageIcon img;
	JPanel paintPanel, textPanel;
	GUIHantoGameBoard board;
	List<HantoCoordinate> options = new ArrayList<HantoCoordinate>();
	
	
	HantoCoordinate startCordiante;
	HantoPiece pieceMove;
	
	
	
	public GameContentPane(Screen screen, HantoGameID gameID) {
		//!!! figure out title
		
		super(screen, gameID.toString() + " Game");
		
		//reSizeTiles();
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
		 drawTiles();
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
		drawOptions();
	}
	
	public void drawTiles()
	{
		Map<HantoCoordinate, HantoPiece> pieces = board.getMap();
		for(HantoCoordinate coordinate: pieces.keySet())
		{
			drawTile(pieces.get(coordinate), coordinate);
		}
	}
	
	public void drawOptions()
	{
		for(HantoCoordinate coordinate: options)
		{
			drawTile(null, coordinate);
		}
	}
	
	public void drawTile(HantoPiece piece, HantoCoordinate coordinate)
	{
		JGameTile tile = new JGameTile(piece, coordinate);
		tile.setBounds(initXOffset, initYOffset, tileSize, tileSize);
		this.add(tile);
	}
	
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
								System.out.println("button Clicked");
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
		startCordiante = cordinate;
		pieceMove = piece;
		showOptions(null);
	}
	
	private void finishMove(HantoCoordinate cordiante)
	{
		System.out.println("Finshed");
		clearOptions();
	}
	
	public void showOptions(List<HantoCoordinate> corrdiantes)
	{
		
		options.add(new HantoCordinateImpl(0,-1));
		
		draw();
		this.repaint();
		
		//!!!
	}
	
	private void clearOptions()
	{
		for(HantoCoordinate cor: options)
		{
			this.remove(new JGameTile(null, cor));
		}
		options.clear();
		
		options = new ArrayList<HantoCoordinate>();
		options.add(new HantoCordinateImpl(0,-2));
		draw();
		this.repaint();
	}
	
	

}

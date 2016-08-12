package hanto.studentrnorlando.gui.contentpane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	List<HantoCoordinate> optionsTemp = new ArrayList<HantoCoordinate>();
	Map<HantoCoordinate, List<HantoCoordinate>> options = new HashMap<HantoCoordinate, List<HantoCoordinate>>();;
	HantoCoordinate lastPieceClickedLocation = null;
	HantoPiece pieceMove;
	
	List<JGameTile> currentOptionsTiles =  new ArrayList<JGameTile>();
	
	//!!! test delete later
	JButton button;
	
	public GameContentPane(Screen screen, HantoGameID gameID) {
		//!!! figure out title
		
		super(screen, gameID.toString() + " Game");
		
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
		showOptions(lastPieceClickedLocation);
	}
	
	private void finishMove(HantoCoordinate cordiante)
	{
		System.out.println("Finshed");
		clearOptions();
	}
	
	public void showOptions(HantoCoordinate corrdiantes)
	{
		if(options.get(corrdiantes) != null)
		{
			drawOptions(options.get(corrdiantes));
			this.repaint();
		}
		//!!!
	}
	
	private void clearOptions()
	{
		clearOptions(lastPieceClickedLocation);
	}
	
	private void clearOptions(HantoCoordinate cordinate)
	{
		List<HantoCoordinate> list = options.get(cordinate);
		if(cordinate != null && list != null)
		{
			for(JGameTile tile: currentOptionsTiles)
			{
				for(JButton button: tile.getButtons())
				{
					remove(button);
				}
				remove(tile);
				
			}
			/*
			 *Should work but it does not, the meory might be different or somethign
			for(HantoCoordinate cor: list)
			{
				JGameTile tile = new JGameTile(null, cor);
				tile.setBounds(initXOffset, initYOffset, tileSize, tileSize);
				this.remove(tile);
			}
			lastPieceClickedLocation = null;
			pieceMove = null;
			this.repaint();
			*/
			//Magic number bandaid
			/*
			for(HantoCoordinate cor: list)
			{
				Component [] stuff = this.getComponents();
				this.remove(stuff[stuff.length-1]);
				this.remove(stuff[stuff.length-2]);
				this.remove(stuff[stuff.length-3]);
			}
			*/
			/*
			for(HantoCoordinate cor: list)
			{
				Component [] stuff = this.getComponents();
				System.out.println("Tile " + cor.getX() + "," + cor.getY());
				boolean delete = false;
				// processor intenseive
				// !!! should I just hold all the Tile in an aditional place, for a less process intensive method
				// this medtod will be called alot would like a better way to do it
				for(Component com : stuff)
				{
					if(com.getClass().equals(JGameTile.class))
					{
						HantoCoordinate componentsCordinate = ((JGameTile)com).getCordinate();
						
						if(componentsCordinate.getX() == cor.getX() && componentsCordinate.getY() ==cor.getY())
						{
							System.out.println("Removed Somthing");
							this.remove(com);
							for(JButton button: ((JGameTile)com).getButtons())
							{
								this.remove(button);
							}
						}
					}
				}
			}
			*/
			lastPieceClickedLocation = null;
			pieceMove = null;
			this.repaint();

			this.revalidate();
			//BandAid to processor intensive...
			/*this.removeAll();
			draw();
			this.repaint();*/
		}
	}
	
	

}

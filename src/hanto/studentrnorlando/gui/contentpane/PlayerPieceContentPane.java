package hanto.studentrnorlando.gui.contentpane;

import java.awt.Container;

import javax.swing.JPanel;

import hanto.common.HantoPieceType;
import hanto.studentrnorlando.common.GeneralHantoPlayer;
import hanto.studentrnorlando.gui.JGameTile;

public class PlayerPieceContentPane extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	GeneralHantoPlayer player;
	int numberOfPieces = 0;
	
	public PlayerPieceContentPane(GeneralHantoPlayer player)
	{
		super();
		this.player = player;
		draw();
		showPieces();
		this.draw();
		this.revalidate();
	}
	
	public void setPlayer(GeneralHantoPlayer player)
	{
		this.player = player;
		this.removeAll();
		showPieces();
	}
	
	public void draw()
	{
		drawLine();
		showPieces();
	}
	
	private void drawLine()
	{
		// !!! to do
	}
	
	private void showPieces()
	{
		for(HantoPieceType type: player.getPieceOptions())
		{
			numberOfPieces++;
			showPiece(type);
		}
	}
	
	public void showPiece(HantoPieceType type)
	{
		JGameTile tile = new JGameTile(type, player.getPlayerColor());
		tile.setBounds(30 + 100 * numberOfPieces, 30, 100, 100);
		this.add(tile);
	}
	

}

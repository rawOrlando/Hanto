package hanto.studentrno.gui;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoCordinateImpl;
import hanto.studentrnorlando.common.HantoPieceImpl;
import hanto.studentrnorlando.gui.JGameTile;

public class JGameTileTest 
{
	
	@Test	//1
	public void intializeEmpty()
	{
		HantoCoordinate cor = null;
		new JGameTile(null, cor);
	}
	
	@Test	//2
	public void intializeOption()
	{
		new JGameTile(null, new HantoCordinateImpl(1,1));
	}
	
	@Test	//3
	public void intializeTile()
	{
		new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(1,1));
	}
	
	
	
	@Test	//4
	public void equalsForOptionTiles()
	{
		JGameTile tile1 = new JGameTile(null, new HantoCordinateImpl(1,1));
		JGameTile tile2 = new JGameTile(null, new HantoCordinateImpl(1,1));
		assertTrue(tile1.equals(tile2));
	}
	
	@Test	//5
	public void equalsForTile()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		JGameTile tile2 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		
		assertTrue(tile1.equals(tile2));
	}
	
	@Test	//6
	public void equalsForOptionTilesDisimular()
	{
		JGameTile tile1 = new JGameTile(null, new HantoCordinateImpl(0,1));
		JGameTile tile2 = new JGameTile(null, new HantoCordinateImpl(1,2));
		assertTrue(!tile1.equals(tile2));
	}
	
	@Test	//7
	public void equalsForTileDisimularSpot()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(0,1));
		JGameTile tile2 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		assertTrue(!tile1.equals(tile2));
	}
	
	@Test	//8
	public void equalsForTileDisimularColor()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		JGameTile tile2 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		assertTrue(!tile1.equals(tile2));
	}
	
	@Test	//9
	public void equalsForTileDisimularType()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.SPARROW), new HantoCordinateImpl(-1,1));
		JGameTile tile2 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.BLACK, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		assertTrue(!tile1.equals(tile2));
	}
	
	@Test	//9.1
	public void equalsForTileandOption()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		JGameTile tile2 = new JGameTile(null, new HantoCordinateImpl(1,2));
		assertTrue(!tile1.equals(tile2));
	}
	
	@Test	//9.2
	public void equalsForTileandOptionReverse()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		JGameTile tile2 = new JGameTile(null, new HantoCordinateImpl(1,2));
		assertTrue(!tile2.equals(tile1));
	}
	
	@Test	//9.3
	public void equalsForNotJGameTile()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		JLabel tile2 = new JLabel("HI");
		assertTrue(!tile1.equals(tile2));
	}
	
	@Test	//9.3
	public void equalsForNull()
	{
		JGameTile tile1 = new JGameTile(new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY), new HantoCordinateImpl(-1,1));
		assertTrue(!tile1.equals(null));
	}
	
	
	
	//FilePath Test Private so No tests
	
	//set Bounds
	
	@Test	//10
	public void setBoundsSquare()
	{
		JGameTile tile1 = new JGameTile(null, new HantoCordinateImpl(0,1));
		tile1.setBounds(10, 11, 200, 200);
		assertEquals(tile1.getX(), 10 + tile1.getXPoint());
		assertEquals(tile1.getY(), 11 + tile1.getYPoint());
		assertEquals(tile1.getWidth(), 200);
		assertEquals(tile1.getHeight(), 200);
	}
	
	@Test	//11
	public void setBoundsAsRectangleReSizeToSquare()
	{
		JGameTile tile1 = new JGameTile(null, new HantoCordinateImpl(0,1));
		tile1.setBounds(10, 11, 200, 300);
		tile1.getLocation().getX();
		assertEquals(tile1.getX(), 10 + tile1.getXPoint());
		assertEquals(tile1.getY(), 11 + tile1.getYPoint());
		assertEquals(tile1.getWidth(), 200);
		assertEquals(tile1.getHeight(), 200);
	}
	
	//Rough test for buttons make sure there are two of them
	
	@Test	//12
	public void getTwoButtons()
	{
		JGameTile tile1 = new JGameTile(null, new HantoCordinateImpl(0,1));
		List<JButton> list = tile1.getButtons();
		assertEquals(list.size(), 2);
	}
	
	@Test	//13
	public void getButtonsStaysTheSame()
	{
		//!!! should be more stamments... !!!
		JGameTile tile1 = new JGameTile(null, new HantoCordinateImpl(0,1));
		List<JButton> list = tile1.getButtons();
		List<JButton> list2 = tile1.getButtons();
		assertEquals(list.size(), 2);
		assertEquals(list2.size(), 2);
	}
	
	@Test 	//14
	public void noImageForNullStuffers()
	{
		JGameTileFalseImage tile1 = new JGameTileFalseImage(new HantoPieceImpl(null, null), new HantoCordinateImpl(-1,1));
		assertNull(tile1.getIcon());
	}
	
	@Test	//15
	public void defaultColorIsBlack()
	{
		JGameTile tile = new JGameTile(HantoPieceType.BUTTERFLY, null);
		assertEquals(tile.getPiece().getColor(), HantoPlayerColor.BLACK);
	}
	
	

}

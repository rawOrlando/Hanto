package hanto.studentrno.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;

public class HantoPieceTypeTest {
	
	@Test // 1
	public void sixPieceTypeExist() 
	{
		assertTrue(HantoPieceType.values().length == 6);
	}
	
	@Test // 2
	public void StringToBUTTERFLY()
	{
		//HantoGameID.ALPHA.
		assertEquals(HantoPieceType.valueOf("BUTTERFLY"), HantoPieceType.BUTTERFLY);
	}
	
	@Test(expected=IllegalArgumentException.class)	 // 3
	public void StringToNonPiece()
	{
		HantoPieceType.valueOf("dire");
	}
	
	@Test	//4
	public void SymbolIsCorrect()
	{
		assertEquals(HantoPieceType.BUTTERFLY.getSymbol(), "B");
		assertEquals(HantoPieceType.CRAB.getSymbol(), "C");
		assertEquals(HantoPieceType.HORSE.getSymbol(), "H");
		assertEquals(HantoPieceType.CRANE.getSymbol(), "N");
		assertEquals(HantoPieceType.SPARROW.getSymbol(), "S");
	}
	
}

package hanto.studentrno.common;

import static org.junit.Assert.*;

import org.junit.Test;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentrnorlando.common.HantoPieceImpl;

public class HantoPieceImplTest 
{
	@Test	//1
	public void equalsSelf()
	{
		HantoPieceImpl first = new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY);
		assertTrue(first.equals(first));
	}
	
	@Test	//2
	public void equalsOtherObject()
	{
		HantoPieceImpl first = new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY);
		Object other = new Object();
		assertTrue(!first.equals(other));
	}
	
	@Test	//3
	public void equalsNull()
	{
		HantoPieceImpl first = new HantoPieceImpl(HantoPlayerColor.WHITE, HantoPieceType.BUTTERFLY);
		HantoPieceImpl other = null;
		assertTrue(!first.equals(other));
	}
}

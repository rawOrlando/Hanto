package hanto.studentrno.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hanto.common.HantoGameID;
import hanto.common.MoveResult;

public class MoveResultTest 
{
	
	@Test // 1
	public void fourMoveResultsExist() 
	{
		assertTrue(MoveResult.values().length == 4);
	}
	
	@Test // 2
	public void StringToOK()
	{
		//HantoGameID.ALPHA.
		assertEquals(MoveResult.valueOf("OK"), MoveResult.OK);
	}
	
	@Test(expected=IllegalArgumentException.class)	 // 3
	public void StringToNonResult()
	{
		MoveResult.valueOf("dire");
	}
}

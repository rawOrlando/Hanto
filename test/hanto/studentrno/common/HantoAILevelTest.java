package hanto.studentrno.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hanto.common.HantoAILevel;
import hanto.common.HantoGameID;

public class HantoAILevelTest 
{
	@Test // 1
	public void threeAILevelsExist() 
	{
		assertTrue(HantoAILevel.values().length == 3);
	}
	
	@Test // 2
	public void StringToEasy()
	{
		//HantoGameID.ALPHA.
		assertEquals(HantoAILevel.valueOf("EASY"), HantoAILevel.EASY);
	}
	
	@Test(expected=IllegalArgumentException.class)	 // 3
	public void StringToNonLevel()
	{
		HantoAILevel.valueOf("dire");
	}
}

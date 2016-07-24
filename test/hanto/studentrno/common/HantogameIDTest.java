package hanto.studentrno.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hanto.common.HantoGameID;

public class HantogameIDTest 
{
	@Test // 1
	public void fiveGameTypesExistExist() 
	{
		assertTrue(HantoGameID.values().length == 5);
	}
	
	@Test // 2
	public void StringToAlpha()
	{
		//HantoGameID.ALPHA.
		assertEquals(HantoGameID.valueOf("ALPHA_HANTO"), HantoGameID.ALPHA_HANTO);
	}
	
	@Test(expected=IllegalArgumentException.class)	 // 3
	public void StringToNonDirection()
	{
		HantoGameID.valueOf("dire");
	}

}
